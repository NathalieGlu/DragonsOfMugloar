package ru.nathalie.model.game;

import org.springframework.stereotype.Component;

@Component
public class GameId {
    private Integer gameid;

    public GameId(Integer gameid) {
        this.gameid = gameid;
    }

    public GameId() {
    }

    public Integer getGameid() {
        return gameid;
    }

    public void setGameid(Integer gameid) {
        this.gameid = gameid;
    }
}
