package model.unit.civilian;

import model.game.Civilization;
import model.improvement.Improvement;
import model.tile.Feature;
import model.tile.Route;
import model.tile.Tile;
import model.unit.UnitState;

public class Worker extends Civilian {

    public Worker(Civilization civilization, Tile tile) {
        super(civilization, tile);
        this.cost = 70;
        this.meleeStrength = 0;
        this.rangedStrength = 0;
        this.maxAttackRange = 0;
        this.maxMovement = 2;
    }

    public void addRoute(Route route) {
        this.tile.startRouteConstruction(route);
        this.unitState = UnitState.WORKING;
    }

    public void addImprovement(Improvement improvement) {
        this.tile.startImprovementConstruction(improvement);
        this.unitState = UnitState.WORKING;
    }

    public void removeImprovement() {
        this.tile.startImprovementRemoval();
        this.unitState = UnitState.WORKING;
    }

    public void removeRoute() {
        this.tile.startRouteRemoval();
        this.unitState = UnitState.WORKING;
    }

    public void removeFeature(Feature feature) {
        this.tile.startFeatureRemoval(feature);
        this.unitState = UnitState.WORKING;
    }

    public void repair() {
        this.tile.startRepair();
        this.unitState = UnitState.WORKING;
    }

    @Override
    public String toString () {
        return "Worker";
    }
}
