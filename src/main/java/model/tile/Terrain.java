package model.tile;

import java.util.ArrayList;
import java.util.List;

public enum Terrain{
    DESERT(0, 0, 0, -33, 1),
    GRASS(2, 0, 0, -33, 1),
    HILL(0, 0, 2, 25, 2),
    MOUNTAIN(0, 0, 0, 0, 100),
    OCEAN(0, 0, 0, 0, 100),
    FIELD(1, 0, 1, -33, 1),
    SNOW(0, 0, 0, -33, 1),
    TUNDRA(1, 0, 0, -33, 1),
    NO_TERRAIN(0, 0, 0, 0, 0);
    //Movement cost of 100 indicates that mountains and oceans cannot be passed.

    private final int food;
    private final int gold;
    private final int production;
    private final int combatBoost;
    private final int movementCost;

    Terrain(int food, int gold, int production, int combatBoost, int movementCost) {
        this.food = food;
        this.gold = gold;
        this.production = production;
        this.combatBoost = combatBoost;
        this.movementCost = movementCost;
    }

    //GETTERS
    public int getFood() {
        return food;
    }

    public int getGold() {
        return gold;
    }

    public int getProduction() {
        return production;
    }

    public int getCombatBoost() {
        return combatBoost;
    }

    public int getMovementCost() {
        return movementCost;
    }
}
