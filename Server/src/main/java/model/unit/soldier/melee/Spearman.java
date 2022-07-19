package model.unit.soldier.melee;

import model.game.Civilization;
import model.research.Research;
import model.tile.Tile;
import model.unit.addOns.BonusVsMounted;

public class Spearman extends Melee implements BonusVsMounted {
    public Spearman(Civilization civilization, Tile tile) {
        super(civilization, tile);
        this.cost = 50;
        this.initialCost = cost;
        this.meleeStrength = 7;
        this.rangedStrength = 0;
        this.maxAttackRange = 0;
        this.maxMovement = 2;
        this.requiredResearch = Research.BRONZE_WORKING;

        this.textureAddress = "file:src/main/resources/images/units/melee/Spearman/";
    }

    @Override
    public String toString () {
        return "Spearman";
    }
}
