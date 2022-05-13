package model.unit.soldier.melee;

import model.game.Civilization;
import model.research.Research;
import model.tile.Tile;

public class Tank extends Melee {
    public Tank(Civilization civilization, Tile tile) {
        super(civilization, tile);
        this.cost = 450;
        this.meleeStrength = 50;
        this.rangedStrength = 0;
        this.maxAttackRange = 0;
        this.maxMovement = 4;
        this.requiredResearch = Research.COMBUSTION;
    }

    @Override
    public String toString () {
        return "Tank";
    }
}
