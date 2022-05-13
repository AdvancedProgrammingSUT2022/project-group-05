package model.unit.soldier.ranged;

import model.game.Civilization;
import model.research.Research;
import model.tile.Tile;

public class Archer extends Ranged {
    public Archer(Civilization civilization, Tile tile) {
        super(civilization, tile);
        this.cost = 70;
        this.meleeStrength = 4;
        this.rangedStrength = 6;
        this.maxAttackRange = 2;
        this.maxMovement = 2;
        this.requiredResearch = Research.ARCHERY;
    }

    @Override
    public String toString () {
        return "Archer";
    }
}
