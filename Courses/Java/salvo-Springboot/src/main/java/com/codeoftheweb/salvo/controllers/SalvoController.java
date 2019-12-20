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
public class SalvoController {

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

    @Autowired
    private ScoreRepository scoreRepository;

    private List<Salvo> orderSalvoes(Set<Salvo> salvoes){
        return salvoes
                .stream()
                .sorted(Comparator.comparing(Salvo::getTurn))
                .sorted(Comparator.comparing(Salvo::getPlayerId))
                .collect(Collectors.toList());
    }

    private List<Ship> orderShips(Set<Ship> ships){
        return ships
                .stream()
                .sorted(Comparator.comparing(Ship::getId))
                .collect(Collectors.toList());
    }

    private List<GamePlayer> orderGamePlayers(Set<GamePlayer> gamePlayers){
        return gamePlayers
                .stream()
                .sorted(Comparator.comparing(GamePlayer::getId))
                .collect(Collectors.toList());
    }

    private long countHits(Salvo salvo, String type){
        GamePlayer opponent = salvo.getGamePlayer().getOpponent();
        if (opponent == null){
            return 0;
        }
        return opponent
                .getShips()
                .stream()
                .filter(ship -> ship.getType().equals(type))
                .flatMap(ship -> ship.getLocations().stream())
                .filter(opponentLocationInStream ->
                        salvo.getSalvoLocations().contains(opponentLocationInStream))
                .count();
    }

    private Set<String> hitLocations(Salvo salvo){
        GamePlayer opponent = salvo.getGamePlayer().getOpponent();
        if (opponent == null){
            return new HashSet<>();
        }
        return opponent
                .getShips()
                .stream()
                .flatMap(shipInStream -> shipInStream.getLocations().stream())
                .filter(locationInStream -> salvo.getSalvoLocations().contains(locationInStream))
                .collect(Collectors.toSet());
    }

    private Set<String> getAllSalvoLocations(GamePlayer gamePlayer){
        return gamePlayer
                .getSalvoes()
                .stream()
                .flatMap(salvoInStream -> salvoInStream.getSalvoLocations().stream())
                .collect(Collectors.toSet());
    }

    private boolean wasDefeated(GamePlayer gamePlayer){
        Set<String> allGamePlayerShipLocations = gamePlayer
                .getShips()
                .stream()
                .flatMap(ship -> ship.getLocations().stream())
                .collect(Collectors.toSet());
        GamePlayer opponent = gamePlayer.getOpponent();
        if (opponent == null){
            return false;
        }
        return getAllSalvoLocations(opponent).containsAll(allGamePlayerShipLocations);
    }

    private long countMisses(Salvo salvo){
        return salvo.getSalvoLocations().size() - hitLocations(salvo).size();
    }

    private boolean isGuest(Authentication authentication) {
        return authentication == null || authentication instanceof AnonymousAuthenticationToken;
    }

    private String updateState (GamePlayer gamePlayer){
        if (gamePlayer.getShips().isEmpty()){
            return "PLACESHIPS";
        }
        if (gamePlayer.getOpponent() == null){
            return "WAITINGFOROPP";
        }
        if (gamePlayer.getOpponent().getShips().isEmpty()){
            return "WAIT";
        }
        if (gamePlayer.getSalvoes().size() > gamePlayer.getOpponent().getSalvoes().size()){
            return "WAIT";
        }
        if (gamePlayer.getSalvoes().size() == gamePlayer.getOpponent().getSalvoes().size()
                && wasDefeated(gamePlayer) && !wasDefeated(gamePlayer.getOpponent())){
            Date date = new Date();
            Score score = new Score(gamePlayer.getGame(),gamePlayer.getPlayer(), 0.0,date);
            scoreRepository.save(score);
            return "LOST";
        }
        if (gamePlayer.getSalvoes().size() == gamePlayer.getOpponent().getSalvoes().size()
                && !wasDefeated(gamePlayer) && wasDefeated(gamePlayer.getOpponent())){
            Date date = new Date();
            Score score = new Score(gamePlayer.getGame(),gamePlayer.getPlayer(), 1.0,date);
            scoreRepository.save(score);
            return "WON";
        }
        if (gamePlayer.getSalvoes().size() == gamePlayer.getOpponent().getSalvoes().size()
                && wasDefeated(gamePlayer) && wasDefeated(gamePlayer.getOpponent())){
            Date date = new Date();
            Score score = new Score(gamePlayer.getGame(),gamePlayer.getPlayer(), 0.5,date);
            scoreRepository.save(score);
            return "TIE";
        }
        return "PLAY";
    }

    private Map<String, Object> makeScoreDTOfromGamePlayer(GamePlayer gamePlayer){
        Map<String, Object> res = new LinkedHashMap<>();
        if(!gamePlayer.getScore().isPresent()){
            return res;
        }
        res.put("player", gamePlayer.getPlayer().getId());
        res.put("score", gamePlayer.getScore().get().getScore());
        res.put("finishDate", gamePlayer.getScore().get().getFinishDate().toInstant().toEpochMilli());
        return res;
    }

    private Map<String, Object> makeShipDTO(Ship ship){
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("type", ship.getType());
        dto.put("locations", ship.getLocations());
        return dto;
    }

    private Map<String, Object> makePlayerDTO(Player player){
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id", player.getId());
        dto.put("email", player.getUserName());
        return dto;
    }

    private Map<String, Object> makeGamePlayerDTO(GamePlayer gamePlayer){
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id", gamePlayer.getId());
        dto.put("player", makePlayerDTO(gamePlayer.getPlayer()));
        return dto;
    }

    private Map<String, Object> makeSalvoDTO(Salvo salvo){
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("turn", salvo.getTurn());
        dto.put("player", salvo.getGamePlayer().getPlayer().getId());
        dto.put("locations", salvo.getSalvoLocations());
        return dto;
    }

    private Map<String, Object> makeGameDTO(Game game){
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id", game.getId());
        dto.put("created", game.getCreationDate().toInstant().toEpochMilli());
        dto.put("gamePlayers", orderGamePlayers(game.getGamePlayers())
                .stream()
                .map(gamePlayer -> makeGamePlayerDTO(gamePlayer))
                .collect(Collectors.toList()));
        dto.put("scores", orderGamePlayers(game.getGamePlayers())
                .stream()
                .map(gamePlayerInStream -> makeScoreDTOfromGamePlayer(gamePlayerInStream) )
                .collect(Collectors.toList()));
        return dto;
    }

    private Map<String, Object> makeHitsDTO(GamePlayer self){
        Map<String, Object> dto = new LinkedHashMap<>();

        GamePlayer opponent = self.getOpponent();
        if (opponent == null){
            dto.put("self", new ArrayList<>());
            dto.put("opponent", new ArrayList<>());
            return dto;
        }

        /*como java no puede tomar parametros por referencia,
        hagotodo en un mismo metodo y voy modificando los valores de los acumuladores*/


        long carrier = 0;
        long battleship = 0;
        long submarine = 0;
        long destroyer = 0;
        long patrolboat = 0;




        List<Map<String, Object>> opponentHitsOnSelfDTO = new ArrayList<>();
        for (Salvo salvoInLoop : orderSalvoes(opponent.getSalvoes())){
            Map<String, Object> salvoDto = new LinkedHashMap<>();
            salvoDto.put("turn", salvoInLoop.getTurn());
            salvoDto.put("hitLocations", hitLocations(salvoInLoop));

            Map<String, Object> damageDto = new LinkedHashMap<>();

            damageDto.put("carrierHits", countHits(salvoInLoop, "carrier"));
            carrier = carrier + countHits(salvoInLoop, "carrier");

            damageDto.put("battleshipHits", countHits(salvoInLoop, "battleship"));
            battleship = battleship + countHits(salvoInLoop, "battleship");

            damageDto.put("submarineHits", countHits(salvoInLoop, "submarine"));
            submarine = submarine + countHits(salvoInLoop, "submarine");

            damageDto.put("destroyerHits", countHits(salvoInLoop, "destroyer"));
            destroyer = destroyer + countHits(salvoInLoop, "destroyer");

            damageDto.put("patrolboatHits", countHits(salvoInLoop, "patrolboat"));
            patrolboat = patrolboat + countHits(salvoInLoop, "patrolboat");

            damageDto.put("carrier", carrier);
            damageDto.put("battleship", battleship);
            damageDto.put("submarine", submarine);
            damageDto.put("destroyer", destroyer);
            damageDto.put("patrolboat", patrolboat);

            salvoDto.put("damages", damageDto);
            salvoDto.put("misses", countMisses(salvoInLoop));

            opponentHitsOnSelfDTO.add(salvoDto);
        }
        dto.put("self", opponentHitsOnSelfDTO);




        List<Map<String, Object>> selfHitsOnOpponentDTO = new ArrayList<>();


        carrier = 0;
        battleship = 0;
        submarine = 0;
        destroyer = 0;
        patrolboat = 0;


        for (Salvo salvoInLoop : orderSalvoes(self.getSalvoes())) {
            Map<String, Object> salvoDto = new LinkedHashMap<>();
            salvoDto.put("turn", salvoInLoop.getTurn());
            salvoDto.put("hitLocations", hitLocations(salvoInLoop));

            Map<String, Object> damageDto = new LinkedHashMap<>();

            damageDto.put("carrierHits", countHits(salvoInLoop, "carrier"));
            carrier = carrier + countHits(salvoInLoop, "carrier");

            damageDto.put("battleshipHits", countHits(salvoInLoop, "battleship"));
            battleship = battleship + countHits(salvoInLoop, "battleship");

            damageDto.put("submarineHits", countHits(salvoInLoop, "submarine"));
            submarine = submarine + countHits(salvoInLoop, "submarine");

            damageDto.put("destroyerHits", countHits(salvoInLoop, "destroyer"));
            destroyer = destroyer + countHits(salvoInLoop, "destroyer");

            damageDto.put("patrolboatHits", countHits(salvoInLoop, "patrolboat"));
            patrolboat = patrolboat + countHits(salvoInLoop, "patrolboat");

            damageDto.put("carrier", carrier);
            damageDto.put("battleship", battleship);
            damageDto.put("submarine", submarine);
            damageDto.put("destroyer", destroyer);
            damageDto.put("patrolboat", patrolboat);

            salvoDto.put("damages", damageDto);
            salvoDto.put("misses", countMisses(salvoInLoop));

            selfHitsOnOpponentDTO.add(salvoDto);
        }

        dto.put("opponent", selfHitsOnOpponentDTO);

        return dto;
    }

    /*------- PUBLIC -------*/

    @RequestMapping(value = "/games", method = RequestMethod.GET)
    public Map<String, Object> makeGamesDTO(Authentication authentication) {
        Map<String, Object> dto = new LinkedHashMap<>();
        if(isGuest(authentication)){
            dto.put("player", "Guest");
        }else{
            Player player = playerRepository.findByUserName(authentication.getName());
            dto.put("player", makePlayerDTO(player));
        }
        dto.put("games", gameRepository
                .findAll()
                .stream()
                .sorted(Comparator.comparing(Game::getId))
                .map(game -> makeGameDTO(game))
                .collect(Collectors.toList()));
        return dto;
    }

    @RequestMapping(value = "/game_view/{gamePlayerId}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> makeGameViewDTO(Authentication authentication,
                                               @PathVariable @Valid Long gamePlayerId){
       GamePlayer gamePlayer = gamePlayerRepository.findById(gamePlayerId).get();
        if (authentication.getName() == gamePlayer.getPlayer().getUserName()){
            Map<String, Object> dto = new LinkedHashMap<>();
            dto.put("id", gamePlayer.getGame().getId());
            dto.put("created", gamePlayer.getGame().getCreationDate().toInstant().toEpochMilli());

            String GAMESTATE;

            GAMESTATE = updateState(gamePlayer);

            dto.put("gameState", GAMESTATE);

            dto.put("gamePlayers", orderGamePlayers(gamePlayer.getGame().getGamePlayers())
                    .stream()
                    .map(gamePlayerInStream -> makeGamePlayerDTO(gamePlayerInStream))
                    .collect(Collectors.toList()));


            dto.put("ships", orderShips(gamePlayer.getShips())
                    .stream()
                    .map(shipInStream -> makeShipDTO(shipInStream))
                    .collect(Collectors.toList()));


            dto.put("salvoes", orderGamePlayers(gamePlayer.getGame().getGamePlayers())
                    .stream()
                    .flatMap(gamePlayerInStream -> orderSalvoes(gamePlayerInStream.getSalvoes())
                            .stream()
                            .map(salvoInStream -> makeSalvoDTO(salvoInStream)))
                    .collect(Collectors.toList()));


            dto.put("hits", makeHitsDTO(gamePlayer));



            return ResponseEntity.ok(dto);
        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
}
    }
}
