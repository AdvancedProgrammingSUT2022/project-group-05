package model.tile;

public enum Route{
    RAIL(0),
    ROAD(0),
    NO_ROUTE(0);

    private final int movementBoost;

    Route(int movementBoost) {
        this.movementBoost = movementBoost;
    }

    public int getMovementBoost() {
        return  this.movementBoost;
    }
}
