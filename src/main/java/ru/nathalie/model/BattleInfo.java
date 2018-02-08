package ru.nathalie.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.nathalie.model.Knight;

public class BattleInfo {
    @JsonProperty("gameId")
    private Integer gameId;
    private Knight knight;

    public BattleInfo() {
    }

    public BattleInfo(Integer gameId, Knight knight) {
        this.gameId = gameId;
        this.knight = knight;
    }

    public Integer getGameId() {
        return gameId;
    }

    public Knight getKnight() {
        return knight;
    }
}
