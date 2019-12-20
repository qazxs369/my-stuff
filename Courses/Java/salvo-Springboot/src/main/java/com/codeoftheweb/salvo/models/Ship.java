package com.codeoftheweb.salvo.models;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.Set;

@Entity
public class Ship {

    /*------- PRIVATE -------*/

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="gamePlayer_id")
    private GamePlayer gamePlayer;

    @ElementCollection
    @Column(name="locations")
    private Set<String> locations;

    /*------- PUBLIC -------*/

    public Ship(){}

    public Ship(GamePlayer gamePlayer, String type, Set<String> locations){
        this.gamePlayer = gamePlayer;
        this.type = type;
        this.locations = locations;
    }

    public long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }

    public Set<String> getLocations() {
        return locations;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setGamePlayer(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

    public void setLocations(Set<String> shipLocations) {
        this.locations = shipLocations;
    }
}
