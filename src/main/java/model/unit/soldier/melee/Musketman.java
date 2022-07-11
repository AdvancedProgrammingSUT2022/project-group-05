package model.unit.soldier.melee;

import model.game.Civilization;
import model.research.Research;
import model.tile.Tile;

public class Musketman extends Melee {
    public Musketman(Civilization civilization, Tile tile) {
        super(civilization, tile);
        this.cost = 120;
        this.initialCost = cost;
        this.meleeStrength = 16;
        this.rangedStrength = 0;
        this.maxAttackRange = 0;
        this.maxMovement = 2;
        this.requiredResearch = Research.GUNPOWDER;

        this.textureAddress = "file:src/main/resources/images/units/melee/Musketman/";
    }

    @Override
    public String toString () {
        //return "Musketman";
        //TODO we can edit after map printing beacause its too long to print
        return "Musket";
    }
}
