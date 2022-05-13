package model.unit.soldier.melee;

import model.game.Civilization;
import model.research.Research;
import model.tile.Tile;

public class Pikeman extends Melee {
    public Pikeman(Civilization civilization, Tile tile) {
        super(civilization, tile);
        this.cost = 100;
        this.meleeStrength = 10;
        this.rangedStrength = 0;
        this.maxAttackRange = 0;
        this.maxMovement = 2;
        this.requiredResearch = Research.CIVIL_SERVICE;
    }

    @Override
    public String toString () {
        return "Pikeman";
    }
}
