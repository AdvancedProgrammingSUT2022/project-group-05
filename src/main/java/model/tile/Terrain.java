package model.tile;

import java.util.ArrayList;

public enum Terrain{
    DESERT(0, 0, 0, -33, 1) {

    },
    GRASS(2, 0, 0, -33, 1) {

    },
    HILL(0, 0, 2, 25, 2) {

    },
    MOUNTAIN(0, 0, 0, 0, 100) {

    },
    OCEAN(0, 0, 0, 0, 100) {

    },
    FIELD(1, 0, 1, -33, 1) {

    },
    SNOW(0, 0, 0, -33, 1) {

    },
    TUNDRA(1, 0, 0, -33, 1) {

    };
    //Movement cost of 100 indicates that mountains and oceans cannot be passed.

    final int food;
    final int gold;
    final int production;
    final int combatBoost;
    final int movementCost;

    final ArrayList<Feature> compatibleFeatures;
    final ArrayList<Resource> compatibleResources;

    Terrain(int food, int gold, int production,
            int combatBoost, int movementCost) {
        this.food = food;
        this.gold = gold;
        this.production = production;
        this.combatBoost = combatBoost;
        this.movementCost = movementCost;

        this.compatibleFeatures = findCompatibleFeatures();
        this.compatibleResources = findCompatibleResources();
    }

    private ArrayList<Feature> findCompatibleFeatures() {
        return new ArrayList<Feature>();
    }
    private ArrayList<Resource> findCompatibleResources() {
        return new ArrayList<Resource>();
    }
}
