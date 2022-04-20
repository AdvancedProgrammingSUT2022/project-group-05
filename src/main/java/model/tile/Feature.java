package model.tile;

public enum Feature {
    PLAIN(2, 0, 0, -33, 1),
    FOREST(1, 0, 1, 25, 2),
    ICE(0, 0, 0, 0, 100),
    JUNGLE(1, 0, -1, 25, 2),
    SWAMP(-1, 0, 0, -33, 2),
    OASIS(3, 1, 0, -33, 1),
    NO_FEATURE(0, 0, 0, 0, 0);

    final int foodBoost;
    final int goldBoost;
    final int productionBoost;
    final int combatBoostPercentage;
    final int movementCost;

    Feature(int foodBoost, int goldBoost, int productionBoost,
            int combatBoostPercentage, int movementCost) {
        this.foodBoost = foodBoost;
        this.goldBoost = goldBoost;
        this.productionBoost = productionBoost;
        this.combatBoostPercentage = combatBoostPercentage;
        this.movementCost = movementCost;
    }

    public boolean matchesTerrain(Terrain terrain) {
        //TODO... Complete matching terrains and features
        if (this == OASIS) return terrain == Terrain.DESERT;
        if (this == ICE) return terrain == Terrain.SNOW || terrain == Terrain.TUNDRA;
        return true;
    }
}
