package ru.nathalie.model;

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
        if (o == this)
            return true;
        if (!(o instanceof BattleInfo))
            return false;
        Knight knight = (Knight) o;
        return knight.agility.equals(agility) && knight.armor.equals(armor)
                && knight.attack.equals(attack) && knight.endurance.equals(endurance)
                && knight.name.equals(name);
    }

    @Override
    public int hashCode() {
        int result = hashCode;
        if (result == 0) {
            result = name.hashCode();
            result = 31 * result + Integer.hashCode(agility);
            result = 31 * result + Integer.hashCode(armor);
            result = 31 * result + Integer.hashCode(attack);
            result = 31 * result + Integer.hashCode(endurance);
            hashCode = result;
        }
        return result;
    }
}
