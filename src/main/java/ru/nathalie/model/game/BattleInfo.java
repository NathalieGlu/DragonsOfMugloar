package ru.nathalie.model.game;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BattleInfo {
    @JsonProperty("gameId")
    private GameId gameId;
    private Knight knight;
    private Dragon dragon;
    private WeatherCode weatherCode;
    private int hashCode = 0;
    private final int GOOD_STAT = 2;

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

    public Dragon calculateDragon() {
        Dragon dragon = null;
        String weather = weatherCode.getCode();
        switch (weather) {
            case "NMR":
                dragon = calculateDragonStats();
                break;
            case "SRO":
                dragon = null;
                break;
            case "HVA":
                dragon = new Dragon(10, 10, 0, 0);
                break;
            case "T E":
                dragon = new Dragon(5, 5, 5, 5);
                break;
            case "FUNDEFINEDG":
                dragon = new Dragon(0, 0, 10, 10);
                break;
        }

        return dragon;
    }

    private Dragon calculateDragonStats() {
        List<Integer> list = Stream.of(knight.getAgility(), knight.getArmor(), knight.getAttack(), knight.getEndurance())
                .collect(Collectors.toList());

        int maxStat = Collections.max(list);

        Dragon dragon = new Dragon(knight.getAttack(), knight.getArmor(),
                knight.getAgility(), knight.getEndurance());

        if (knight.getAttack() == maxStat) { //+
            dragon.setScaleThickness(maxStat + GOOD_STAT);
            if (knight.getArmor() > 0) {
                dragon.setClawSharpness(knight.getArmor() - 1);
                if (knight.getAgility() > 0) {
                    dragon.setWingStrength(knight.getAgility() - 1);
                } else {
                    dragon.setFireBreath(knight.getEndurance() - 1);
                }
            } else {
                dragon.setFireBreath(knight.getEndurance() - 1);
                dragon.setWingStrength(knight.getAgility() - 1);
            }
        } else if (knight.getArmor() == maxStat) {
            dragon.setClawSharpness(maxStat + GOOD_STAT);
            if (knight.getAttack() > 0) {
                dragon.setScaleThickness(knight.getAttack() - 1);
                if (knight.getEndurance() > 0) {
                    dragon.setFireBreath(knight.getEndurance() - 1);
                } else {
                    dragon.setWingStrength(knight.getAgility() - 1);
                }
            } else {
                dragon.setFireBreath(knight.getEndurance() - 1);
                dragon.setWingStrength(knight.getAgility() - 1);
            }
        } else if (knight.getAgility() == maxStat) { //
            dragon.setWingStrength(maxStat + GOOD_STAT);
            if (knight.getArmor() > 0) {
                dragon.setClawSharpness(knight.getArmor() - 1);
                if (knight.getEndurance() > 0) {
                    dragon.setFireBreath(knight.getEndurance() - 1);
                } else {
                    dragon.setScaleThickness(knight.getAttack() - 1);
                }
            } else {
                dragon.setFireBreath(knight.getEndurance() - 1);
                dragon.setScaleThickness(knight.getAttack() - 1);
            }
        } else if (knight.getEndurance() == maxStat) { //+
            dragon.setFireBreath(maxStat + GOOD_STAT);
            if (knight.getArmor() > 0) {
                dragon.setClawSharpness(knight.getArmor() - 1);
                if (knight.getAgility() > 0) {
                    dragon.setWingStrength(knight.getAgility() - 1);
                } else {
                    dragon.setScaleThickness(knight.getAttack() - 1);
                }
            } else {
                dragon.setWingStrength(knight.getAgility() - 1);
                dragon.setScaleThickness(knight.getAttack() - 1);
            }
        }

        return dragon;
    }


}
