package com.codeoftheweb.salvo.models;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

@Entity
public class GamePlayer {

    /*------- PRIVATE -------*/

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private Date creationDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="game_id")
    private Game game;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="player_id")
    private Player player;

    @OneToMany(mappedBy="gamePlayer", fetch=FetchType.EAGER)
    private Set<Ship> Ships;

    @OneToMany(mappedBy="gamePlayer", fetch=FetchType.EAGER)
    private Set<Salvo> Salvoes;

    /*------- PUBLIC -------*/

    public GamePlayer(){}

    public GamePlayer(Date creationDate, Game game, Player player){
        this.creationDate = creationDate;
        this.game = game;
        this.player = player;
    }

    public GamePlayer(Game game, Player player){
        this.game = game;
        this.player = player;
    }

    public GamePlayer getOpponent(){
        return game.getGamePlayers()
                .stream()
                .filter(gamePlayerInStream -> gamePlayerInStream.getId() != getId())
                .findFirst().orElse(null);
    }

    public long getId() {
        return id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Game getGame() {
        return game;
    }

    public Player getPlayer() {
        return player;
    }

    public Set<Ship> getShips() {
        return Ships;
    }

    public Set<Salvo> getSalvoes() {
        return Salvoes;
    }

    public Optional<Score> getScore(){
        return getPlayer()
                .getScores()
                .stream()
                .filter(score -> score.getGame() == game).findFirst();
    }
}
