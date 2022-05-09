package model.unit.soldier.ranged.siege;

import model.game.Civilization;
import model.tile.Tile;

public class Canon extends Siege {
    public Canon(Civilization civilization, Tile tile) {
        super(civilization, tile);
        this.cost = 250;
        this.meleeStrength = 10;
        this.rangedStrength = 26;
        this.maxAttackRange = 2;
        this.maxMovement = 2;
    }

    @Override
    public String toString () {

        return "Canon";
    }
}
