package model.tile;

import model.research.Research;

public enum Route{
    RAIL(0, 2, Research.RAILROAD),
    ROAD(0, 1, Research.THE_WHEEL),
    NO_ROUTE(0, 0, Research.NO_RESEARCH);

    private final int movementBoost;
    private final int maintenanceCost;

    private final Research neededResearch;

    Route(int movementBoost, int maintenanceCost, Research neededResearch) {
        this.movementBoost = movementBoost;
        this.maintenanceCost = maintenanceCost;

        this.neededResearch = neededResearch;
    }

    public int getMovementBoost() {
        return  this.movementBoost;
    }

    public int getMaintenanceCost() {
        return this.maintenanceCost;
    }

    public Research getNeededResearch() {
        return this.neededResearch;
    }
}
