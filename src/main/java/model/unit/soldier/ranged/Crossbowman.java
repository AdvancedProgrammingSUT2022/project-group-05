package model.unit.soldier.ranged;

import model.game.Civilization;
import model.research.Research;
import model.tile.Tile;

public class Crossbowman extends Ranged {
    public Crossbowman(Civilization civilization, Tile tile) {
        super(civilization, tile);
        this.cost = 120;
        this.initialCost = cost;
        this.meleeStrength = 6;
        this.rangedStrength = 12;
        this.maxAttackRange = 2;
        this.maxMovement = 2;
        this.requiredResearch = Research.MACHINERY;

        this.textureAddress = "file:src/main/resources/images/units/ranged/Crossbowman/";
    }

    @Override
    public String toString () {
        //return "Crossbowman";
        //TODO we can edit after map printing beacause its too long to print
        return "Crossbow";
    }
}
