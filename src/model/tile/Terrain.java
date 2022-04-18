package model.tile;

public enum Terrain{
    DESERT  (0, 0, 0, -33, 1),
    GRASS   (2, 0, 0, -33, 1),
    HILL    (0, 0, 2, 25 , 2),
    MOUNTAIN(0, 0, 0, 0  , 100),
    OCEAN   (0, 0, 0, 0  , 100),
    FIELD   (1, 0, 1, -33, 1),
    SNOW    (0, 0, 0, -33, 1),
    TUNDRA  (1, 0, 0, -33, 1);
    //Movement cost of 100 indicates that mountains and oceans cannot be passed.

    final int foodBoost;
    final int goldBoost;
    final int productionBoost;
    final int combatBoostPercentage;
    final int movementCost;

    Terrain(int foodBoost, int goldBoost, int productionBoost,
            int combatBoostPercentage, int movementCost)
    {
        this.foodBoost = foodBoost;
        this.goldBoost = goldBoost;
        this.productionBoost = productionBoost;
        this.combatBoostPercentage = combatBoostPercentage;
        this.movementCost = movementCost;
    }
}
