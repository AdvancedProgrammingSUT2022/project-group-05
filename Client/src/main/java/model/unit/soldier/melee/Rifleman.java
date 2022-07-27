package model.unit.soldier.melee;

import model.game.Civilization;
import model.research.Research;
import model.tile.Tile;

public class Rifleman extends Melee {
    public Rifleman(Civilization civilization, Tile tile) {
        super(civilization, tile);
        this.cost = 200;
        this.initialCost = cost;
        this.meleeStrength = 25;
        this.rangedStrength = 0;
        this.maxAttackRange = 1;
        this.maxMovement = 2;
        this.requiredResearch = Research.RIFLING;

        this.textureAddress = "file:src/main/resources/images/units/melee/Rifleman/";
    }

    @Override
    public String toString () {
        return "Rifleman";
    }
}
