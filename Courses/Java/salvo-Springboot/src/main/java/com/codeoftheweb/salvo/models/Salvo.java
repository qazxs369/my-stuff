package com.codeoftheweb.salvo.models;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.Set;

@Entity
public class Salvo {

    /*------- PRIVATE -------*/

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="gamePlayer_id")
    private GamePlayer gamePlayer;

    private Integer turn;

    @ElementCollection
    @Column(name="salvoLocations")
    private Set<String> salvoLocations;

    /*------- PUBLIC -------*/

    public Salvo(){}

    public Salvo(Integer turn, GamePlayer gamePlayer, Set<String> locations){
        this.gamePlayer = gamePlayer;
        this.turn = turn;
        this.salvoLocations = locations;
    }

    public long getId() {
        return id;
    }

    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }

    public Integer getTurn() {
        return turn;
    }

    public Set<String> getSalvoLocations() {
        return salvoLocations;
    }

    public long getPlayerId(){
        return gamePlayer.getPlayer().getId();
    }

    public void setGamePlayer(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

    public void setTurn(Integer turn) {
        this.turn = turn;
    }

    public void setSalvoLocations(Set<String> salvoLocations) {
        this.salvoLocations = salvoLocations;
    }


}
