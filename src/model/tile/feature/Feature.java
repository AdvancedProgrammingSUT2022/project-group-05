package model.tile.feature;

import model.tile.Tile;

public abstract class Feature {
    protected int foodBoost;
    protected int goldBoost;
    protected int productionBoost;
    protected int combatBoost;
    protected int movementPoint;

    protected abstract boolean isTileEligible(Tile tile);

}
