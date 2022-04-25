package model.tile;

public enum Terrain {
    DESERT(0, 0, 0, -33, 1),
    GRASS(2, 0, 0, -33, 1),
    HILL(0, 0, 2, 25, 2),
    MOUNTAIN(0, 0, 0, 0, 100),
    OCEAN(0, 0, 0, 0, 100),
    FIELD(1, 0, 1, -33, 1),
    SNOW(0, 0, 0, -33, 1),
    TUNDRA(1, 0, 0, -33, 1);
    //Movement cost of 100 indicates that mountains and oceans cannot be passed.

    final int foodIncrease;
    final int goldIncrease;
    final int productionIncrease;
    final int combatPercentage;
    final int movementCost;

    Terrain(int foodIncrease, int goldIncrease, int productionIncrease,
            int combatPercentage, int movementCost) {
        this.foodIncrease = foodIncrease;
        this.goldIncrease = goldIncrease;
        this.productionIncrease = productionIncrease;
        this.combatPercentage = combatPercentage;
        this.movementCost = movementCost;
    }
}
