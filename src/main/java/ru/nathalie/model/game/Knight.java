package ru.nathalie.model.game;

import java.util.Objects;

public class Knight {
    private String name;
    private Integer attack;
    private Integer armor;
    private Integer agility;
    private Integer endurance;
    private int hashCode = 0;

    public Knight() {
    }

    public Knight(String name, Integer attack, Integer armor, Integer agility, Integer endurance) {
        this.name = name;
        this.attack = attack;
        this.armor = armor;
        this.agility = agility;
        this.endurance = endurance;
    }

    public String getName() {
        return name;
    }

    public Integer getAttack() {
        return attack;
    }

    public Integer getArmor() {
        return armor;
    }

    public Integer getAgility() {
        return agility;
    }

    public Integer getEndurance() {
        return endurance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Knight knight = (Knight) o;
        return hashCode == knight.hashCode &&
                Objects.equals(name, knight.name) &&
                Objects.equals(attack, knight.attack) &&
                Objects.equals(armor, knight.armor) &&
                Objects.equals(agility, knight.agility) &&
                Objects.equals(endurance, knight.endurance);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, attack, armor, agility, endurance, hashCode);
    }
}
