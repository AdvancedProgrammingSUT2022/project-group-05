package model.unit.civilian;

import model.game.Civilization;
import model.tile.Tile;
import model.unit.Unit;

public class Worker extends Unit {

    public Worker(Civilization civilization, Tile tile) {
        super(civilization, tile);
        this.cost = 70;
        this.meleeStrength = 0;
        this.rangedStrength = 0;
        this.maxAttackRange = 0;
        this.maxMovement = 2;
    }
    public void addImprovement() { //Tells a worker to add improvement on current tile
        //TODO
    }

    public void removeImprovement() {
        //TODO
    }

    public void repairImprovement() {
        //TODO
    }

    public void removeFeature() {
        //TODO
    }
}
