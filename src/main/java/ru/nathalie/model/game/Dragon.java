package ru.nathalie.model;

import org.springframework.stereotype.Component;

@Component
public class Dragon {

    private Integer scaleThickness;
    private Integer clawSharpness;
    private Integer wingStrength;
    private Integer fireBreath;
    private int hashCode = 0;

    public Dragon() {
    }

    public Dragon(Integer scaleThickness, Integer clawSharpness, Integer wingStrength, Integer fireBreath) {
        this.scaleThickness = scaleThickness;
        this.clawSharpness = clawSharpness;
        this.wingStrength = wingStrength;
        this.fireBreath = fireBreath;
    }

    public Integer getScaleThickness() {
        return scaleThickness;
    }

    public Integer getClawSharpness() {
        return clawSharpness;
    }

    public Integer getWingStrength() {
        return wingStrength;
    }

    public Integer getFireBreath() {
        return fireBreath;
    }

    public void setScaleThickness(Integer scaleThickness) {
        this.scaleThickness = scaleThickness;
    }

    public void setClawSharpness(Integer clawSharpness) {
        this.clawSharpness = clawSharpness;
    }

    public void setWingStrength(Integer wingStrength) {
        this.wingStrength = wingStrength;
    }

    public void setFireBreath(Integer fireBreath) {
        this.fireBreath = fireBreath;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof BattleInfo))
            return false;
        Dragon dragon = (Dragon) o;
        return dragon.clawSharpness.equals(clawSharpness) &&
                dragon.fireBreath.equals(fireBreath) &&
                dragon.scaleThickness.equals(scaleThickness) &&
                dragon.wingStrength.equals(wingStrength);
    }
}
