package ru.nathalie.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BattleInfo {
    @JsonProperty("gameId")
    private GameId gameId;
    private Knight knight;
    private Dragon dragon;
    private WeatherCode weatherCode;
    private int hashCode = 0;

    public BattleInfo() {
    }

    public BattleInfo(GameId gameId, Knight knight, Dragon dragon) {
        this.gameId = gameId;
        this.knight = knight;
        this.dragon = dragon;
    }

    public Integer getId() {
        return gameId.getGameid();
    }

    public GameId getGameId() {
        return gameId;
    }

    public Knight getKnight() {
        return knight;
    }

    public WeatherCode getWeatherCode() {
        return weatherCode;
    }

    public void setWeatherCode(WeatherCode weatherCode) {
        this.weatherCode = weatherCode;
    }

    public Dragon getDragon() {
        return dragon;
    }

    public void setDragon(Dragon dragon) {
        this.dragon = dragon;
    }

    @Override
    public int hashCode() {
        int result = hashCode;
        if (result == 0) {
            result = gameId.hashCode();
            result = 31 * result + knight.hashCode();
            result = 31 * result + dragon.hashCode();
            result = 31 * result + weatherCode.hashCode();
            hashCode = result;
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof BattleInfo))
            return false;
        BattleInfo battle = (BattleInfo) o;
        return battle.gameId.equals(gameId) && battle.knight.equals(knight)
                && battle.dragon.equals(dragon) && battle.weatherCode.equals(weatherCode);
    }
}
