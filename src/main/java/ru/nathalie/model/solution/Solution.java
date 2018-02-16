package ru.nathalie.model.solution;

import ru.nathalie.model.game.Dragon;

public class Solution {
    private Dragon dragon;

    public Solution() {
    }

    public Solution(Dragon dragon) {
        this.dragon = dragon;
    }

    public Dragon getDragon() {
        return dragon;
    }
}
