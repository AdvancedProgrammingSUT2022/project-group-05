package model.unit.civilian;

import model.game.Civilization;
import model.improvement.Improvement;
import model.tile.Feature;
import model.tile.Route;
import model.tile.Tile;

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
    }

    public void addImprovement(Improvement improvement) {
        this.tile.startImprovementConstruction(improvement);
    }

    public void removeImprovement() {
        this.tile.startImprovementRemoval();
    }

    public void removeRoute() {
        this.tile.startRouteRemoval();
    }

    public void removeFeature(Feature feature) {
        this.tile.startFeatureRemoval(feature);
    }

    public void repair() {
        this.tile.startRepair();
    }

    @Override
    public String toString () {
        return "Worker";
    }
}
