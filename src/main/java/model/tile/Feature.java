package model.tile;

import java.util.ArrayList;
import java.util.List;

public enum Feature{
    PLAIN(2, 0, 0, -33, 1,
            List.of(Terrain.DESERT)
    ),
    FOREST(1, 0, 1, 25, 2,
            List.of(Terrain.FIELD, Terrain.GRASS, Terrain.HILL, Terrain.TUNDRA)
    ),
    ICE(0, 0, 0, 0, 100,
            List.of(Terrain.OCEAN)
    ),
    JUNGLE(1, 0, -1, 25, 2,
            List.of(Terrain.FIELD, Terrain.HILL)
    ),
    MARSH(-1, 0, 0, -33, 2,
            List.of(Terrain.GRASS)
    ),
    OASIS(3, 1, 0, -33, 1,
            List.of(Terrain.DESERT)
    ),
    NO_FEATURE(0, 0, 0, 0, 0,
            List.of()
    );

    private final int food;
    private final int gold;
    private final int production;
    private final int combatBoost;
    private final int movementCost;

    private final ArrayList<Terrain> compatibleTerrains;

    Feature(int food, int gold, int production, int combatBoost, int movementCost,
            List<Terrain> compatibleTerrains) {
        this.food = food;
        this.gold = gold;
        this.production = production;
        this.combatBoost = combatBoost;
        this.movementCost = movementCost;

        this.compatibleTerrains = new ArrayList<>(compatibleTerrains);
    }

    public boolean matchesTerrain(Terrain terrain) {
        return this.compatibleTerrains.contains(terrain);
    }

    //GETTERS
    public int getFood() {
        return this.food;
    }

    public int getGold() {
        return this.gold;
    }

    public int getProduction() {
        return this.production;
    }

    public int getCombatBoost() {
        return this.combatBoost;
    }

    public int getMovementCost() {
        return this.movementCost;
    }

    public ArrayList<Terrain> getCompatibleTerrains() {
        return this.compatibleTerrains;
    }
}
