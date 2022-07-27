package model.unit.soldier.melee;

import model.game.Civilization;
import model.research.Research;
import model.resource.Resource;
import model.tile.Tile;

public class Longswordsman extends Melee {
    public Longswordsman(Civilization civilization, Tile tile) {
        super(civilization, tile);
        this.cost = 150;
        this.initialCost = cost;
        this.meleeStrength = 18;
        this.rangedStrength = 0;
        this.maxAttackRange = 1;
        this.maxMovement = 3;
        this.requiredResource = Resource.IRON;
        this.requiredResearch = Research.STEEL;

        this.textureAddress = "file:src/main/resources/images/units/melee/Longswordsman/";
    }

    @Override
    public String toString () {
        return "Longswordsman";
    }
}
