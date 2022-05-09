package model.unit.soldier.melee;

import model.game.Civilization;
import model.tile.Tile;

public class Cavalry extends Melee {
    public Cavalry(Civilization civilization, Tile tile) {
        super(civilization, tile);
        this.cost = 260;
        this.meleeStrength = 25;
        this.rangedStrength = 0;
        this.maxAttackRange = 0;
        this.maxMovement = 3;
    }

    @Override
    public String toString () {
        return "Cavalry";
    }
}
