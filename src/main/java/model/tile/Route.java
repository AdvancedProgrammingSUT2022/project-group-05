package model.tile;

public enum Route{
    RAIL(0),
    ROAD(0),
    NONE(0);

    int movementBoost;

    Route(int movementBoost) {
        this.movementBoost = movementBoost;
    }

    public int getMovementBoost() {
        return  this.movementBoost;
    }
}
