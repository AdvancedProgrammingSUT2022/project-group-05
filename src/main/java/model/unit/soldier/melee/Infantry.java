package model.unit.soldier.melee;

import model.game.Civilization;
import model.tile.Tile;

public class Infantry extends Melee {
    public Infantry(Civilization civilization, Tile tile) {
        super(civilization, tile);
        this.cost = 300;
        this.meleeStrength = 36;
        this.rangedStrength = 0;
        this.maxAttackRange = 0;
        this.maxMovement = 2;
    }

    @Override
    public String toString () {
        return "Infantry";
    }
}
