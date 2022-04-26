package model.tile;

public enum Feature{
    PLAIN(2, 0, 0, -33, 1),
    FOREST(1, 0, 1, 25, 2),
    ICE(0, 0, 0, 0, 100),
    JUNGLE(1, 0, -1, 25, 2),
    SWAMP(-1, 0, 0, -33, 2),
    OASIS(3, 1, 0, -33, 1),
    NO_FEATURE(0, 0, 0, 0, 0);

    final int food;
    final int gold;
    final int production;
    final int combatBoost;
    final int movementCost;

    Feature(int food, int gold, int production,
            int combatBoost, int movementCost) {
        this.food = food;
        this.gold = gold;
        this.production = production;
        this.combatBoost = combatBoost;
        this.movementCost = movementCost;
    }

    public boolean matchesTerrain(Terrain terrain) {
        //TODO... Complete matching terrains and features
        if (this == OASIS) return terrain == Terrain.DESERT;
        if (this == ICE) return terrain == Terrain.SNOW || terrain == Terrain.TUNDRA;
        return true;
    }
}
