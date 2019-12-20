package com.codeoftheweb.salvo.models;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.List;
import java.util.Set;
import static java.util.stream.Collectors.toList;

@Entity
public class Player {

    /*------- PRIVATE -------*/

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String userName;

    private String password;

    @OneToMany(mappedBy="player", fetch=FetchType.EAGER)
    private Set<GamePlayer> gamePlayers;

    @OneToMany(mappedBy="player", fetch=FetchType.EAGER)
    private Set<Score> scores;

    /*------- PUBLIC -------*/

    public Player(){}

    public Player(String userName, String password){
        this.userName = userName;
        this.password = password;
    }

    public String getUserName(){
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public long getId(){
        return id;
    }

    public Set<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }

    public List<Game> getGames() {
        return gamePlayers
                .stream()
                .map(sub -> sub.getGame())
                .collect(toList());
    }

    public Set<Score> getScores() {
        return scores;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}