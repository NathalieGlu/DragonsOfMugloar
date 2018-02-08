package ru.nathalie.model;

public class Knight {
    private String name;
    private Integer attack;
    private Integer armor;
    private Integer agility;
    private Integer endurance;

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
}
