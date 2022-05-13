package model.tile;

public enum Route{
    RAIL(0, 2),
    ROAD(0, 1),
    NO_ROUTE(0, 0);

    private final int movementBoost;
    private final int maintenanceCost;

    Route(int movementBoost, int maintenanceCost) {
        this.movementBoost = movementBoost;
        this.maintenanceCost = maintenanceCost;
    }

    public int getMovementBoost() {
        return  this.movementBoost;
    }

    public int getMaintenanceCost() {
        return this.maintenanceCost;
    }
}
