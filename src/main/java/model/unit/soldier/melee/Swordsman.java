package model.unit.soldier.melee;

import model.game.Civilization;
import model.research.Research;
import model.resource.Resource;
import model.tile.Tile;

public class Swordsman extends Melee {
    public Swordsman(Civilization civilization, Tile tile) {
        super(civilization, tile);
        this.cost = 80;
        this.initialCost = cost;
        this.meleeStrength = 11;
        this.rangedStrength = 0;
        this.maxAttackRange = 0;
        this.maxMovement = 2;
        this.requiredResource = Resource.IRON;
        this.requiredResearch = Research.IRON_WORKING;

        this.textureAddress = "file:src/main/resources/images/units/melee/Swordsman/";
    }

    @Override
    public String toString () {
        //return "Swordsman";
        //TODO we can edit after map printing beacause its too long to print
        return "Sword";
    }
}
