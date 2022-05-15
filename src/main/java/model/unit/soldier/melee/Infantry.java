package model.unit.soldier.melee;

import model.game.Civilization;
import model.research.Research;
import model.tile.Tile;

public class Infantry extends Melee {
    public Infantry(Civilization civilization, Tile tile) {
        super(civilization, tile);
        this.cost = 300;
        this.initialCost = cost;
        this.meleeStrength = 36;
        this.rangedStrength = 0;
        this.maxAttackRange = 0;
        this.maxMovement = 2;
        this.requiredResearch = Research.REPLACEABLE_PARTS;
    }

    @Override
    public String toString () {
        return "Infantry";
    }
}
