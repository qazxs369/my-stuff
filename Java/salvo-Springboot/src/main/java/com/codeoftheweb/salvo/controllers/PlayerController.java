package com.codeoftheweb.salvo.controllers;

import com.codeoftheweb.salvo.models.*;
import com.codeoftheweb.salvo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PlayerController {

    /*------- PRIVATE -------*/

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GamePlayerRepository gamePlayerRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private ShipRepository shipRepository;

    @Autowired
    private SalvoRepository salvoRepository;

    private boolean isGuest(Authentication authentication) {
        return authentication == null || authentication instanceof AnonymousAuthenticationToken;
    }

    private Map<String, Object> makeMap(String key, Object value) {
        Map<String, Object> map = new HashMap<>();
        map.put(key, value);
        return map;
    }

    private boolean wasHitBySalvoLocations(String location, Set<String> salvoLocations){
        return salvoLocations
                .stream()
                .filter(locationInSteam -> locationInSteam == location)
                .count() == 1;
    }

    private Set<String> getAllSalvoLocations(GamePlayer gamePlayer){
        return gamePlayer.getSalvoes()
                .stream()
                .flatMap(salvoInStream -> salvoInStream.getSalvoLocations().stream())
                .collect(Collectors.toSet());
    }

    /*------- PUBLIC -------*/

    @RequestMapping(value = "/games", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> createGame(Authentication authentication) {
        if (isGuest(authentication)){
            return new ResponseEntity<>(makeMap("error", "No current user is logged in"), HttpStatus.UNAUTHORIZED);
        }
        Date date = new Date();
        Game newGame = new Game(date);
        gameRepository.save(newGame);
        Player player = playerRepository.findByUserName(authentication.getName());
        GamePlayer gamePlayer = gamePlayerRepository.save(new GamePlayer(newGame, player));
        return new ResponseEntity<>(makeMap("gpid", gamePlayer.getId()), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/game/{gameId}/players", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> joinGame(Authentication authentication,
                                                        @PathVariable @Valid long gameId) {
        if (isGuest(authentication)){
            return new ResponseEntity<>(makeMap("error", "No current user is logged in"), HttpStatus.UNAUTHORIZED);
        }
        Player player = playerRepository.findByUserName(authentication.getName());
        Optional<Game> gameToJoinOpt = gameRepository.findById(gameId);
        if (!gameToJoinOpt.isPresent()){
            return new ResponseEntity<>(makeMap("error", "No such game"), HttpStatus.FORBIDDEN);
        }
        Game gameToJoin = gameToJoinOpt.get();
        if (gameToJoin.getGamePlayers().size() == 2){
            return new ResponseEntity<>(makeMap("error", "Game is full"), HttpStatus.FORBIDDEN);
        }
        if (gameToJoin.getPlayers().stream().findFirst().isPresent() &&
                gameToJoin.getPlayers().stream().findFirst().get().getId() == player.getId()){
            return new ResponseEntity<>(makeMap("error", "You already joined"), HttpStatus.FORBIDDEN);
        }
        GamePlayer gamePlayer = gamePlayerRepository.save(new GamePlayer(gameToJoin, player));
        return new ResponseEntity<>(makeMap("gpid", gamePlayer.getId()), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/games/players/{gamePlayerId}/ships", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> addShips(Authentication authentication,
                                                        @PathVariable long gamePlayerId,
                                                        @RequestBody Set<Ship> ships){
        if (isGuest(authentication)){
            return new ResponseEntity<>(makeMap("error", "No current user is logged in"), HttpStatus.UNAUTHORIZED);
        }
        Optional<GamePlayer> gamePlayerOpt = gamePlayerRepository.findById(gamePlayerId);
        if (!gamePlayerOpt.isPresent()){
            return new ResponseEntity<>(makeMap("error", "There is no game player with the given ID"), HttpStatus.UNAUTHORIZED);
        }
        GamePlayer gamePlayer = gamePlayerOpt.get();
        if(gamePlayer.getId() != gamePlayerId){
            return new ResponseEntity<>(makeMap("error", "The current user is not the game player the ID references"), HttpStatus.UNAUTHORIZED);
        }
        if (!gamePlayer.getShips().isEmpty()){
            return new ResponseEntity<>(makeMap("error", "The user already has ships"), HttpStatus.FORBIDDEN);
        }

        ships.forEach(shipInFor -> {
            shipInFor.setGamePlayer(gamePlayer);
            shipRepository.save(shipInFor);
        });

        return new ResponseEntity<>(makeMap("OK", "ships saved"), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/games/players/{gamePlayerId}/salvoes", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> addSalvo(Authentication authentication,
                                                        @PathVariable long gamePlayerId,
                                                        @RequestBody Salvo salvo){
        if (isGuest(authentication)){
            return new ResponseEntity<>(makeMap("error", "No current user is logged in"), HttpStatus.UNAUTHORIZED);
        }
        Optional<GamePlayer> gamePlayerOpt = gamePlayerRepository.findById(gamePlayerId);
        if (!gamePlayerOpt.isPresent()){
            return new ResponseEntity<>(makeMap("error", "There is no game player with the given ID"), HttpStatus.UNAUTHORIZED);
        }
        GamePlayer gamePlayer = gamePlayerOpt.get();
        if(gamePlayer.getId() != gamePlayerId){
            return new ResponseEntity<>(makeMap("error", "The current user is not the game player the ID references"), HttpStatus.UNAUTHORIZED);
        }

        //CHEAT JUEGO PERFECTO
        //USAR CON RESPONSABILIDAD
        if (gamePlayer.getPlayer().getUserName().equals("mati@gmail.com")//este usuario nunca falla los salvos
                && gamePlayer.getOpponent() != null){
            List<String> allOpponentLocations = gamePlayer
                    .getOpponent()
                    .getShips()
                    .stream()
                    .flatMap(shipInStream -> shipInStream.getLocations().stream())
                    .collect(Collectors.toList());
            Set<String> salvoLocationsTramposas = new LinkedHashSet<>();
            for (String opponentLocation : allOpponentLocations){
                if (salvoLocationsTramposas.size() < salvo.getSalvoLocations().size()){
                    if (!wasHitBySalvoLocations(opponentLocation, getAllSalvoLocations(gamePlayer))){
                        salvoLocationsTramposas.add(opponentLocation);
                    }
                } else {
                    break;
                }
            }
            salvo.setSalvoLocations(salvoLocationsTramposas);
        }

        salvo.setTurn(gamePlayer.getSalvoes().size() + 1);
        salvo.setGamePlayer(gamePlayer);
        salvoRepository.save(salvo);
        return new ResponseEntity<>(makeMap("OK", "salvo saved"), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/players", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> register (@RequestParam String email, @RequestParam String password) {
        if(email.isEmpty() || password.isEmpty()){
            return new ResponseEntity<>(makeMap("error", "Missing Data"), HttpStatus.FORBIDDEN);
        }
        if(playerRepository.findByUserName(email) != null){
            return new ResponseEntity<>(makeMap("error", "Name already in use"), HttpStatus.FORBIDDEN);
        }
        playerRepository.save(new Player(email, passwordEncoder.encode(password)));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
