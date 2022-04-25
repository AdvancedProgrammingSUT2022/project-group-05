package model.tile;

public enum Feature{
    PLAIN(2, 0, 0, -33, 1),
    FOREST(1, 0, 1, 25, 2),
    ICE(0, 0, 0, 0, 100),
    JUNGLE(1, 0, -1, 25, 2),
    SWAMP(-1, 0, 0, -33, 2),
    OASIS(3, 1, 0, -33, 1),
    NO_FEATURE(0, 0, 0, 0, 0);

    final int foodIncrease;
    final int goldIncrease;
    final int productionIncrease;
    final int combatPercentage;
    final int movementCost;

    Feature(int foodIncrease, int goldIncrease, int productionIncrease,
            int combatPercentage, int movementCost) {
        this.foodIncrease = foodIncrease;
        this.goldIncrease = goldIncrease;
        this.productionIncrease = productionIncrease;
        this.combatPercentage = combatPercentage;
        this.movementCost = movementCost;
    }

    public boolean matchesTerrain(Terrain terrain) {
        //TODO... Complete matching terrains and features
        if (this == OASIS) return terrain == Terrain.DESERT;
        if (this == ICE) return terrain == Terrain.SNOW || terrain == Terrain.TUNDRA;
        return true;
    }
}
