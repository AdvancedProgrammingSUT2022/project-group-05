package model.unit.soldier.ranged.siege;

import model.game.Civilization;
import model.tile.Tile;

public class Artillery extends Siege {
    public Artillery(Civilization civilization, Tile tile) {
        super(civilization, tile);
        this.cost = 420;
        this.meleeStrength = 16;
        this.rangedStrength = 32;
        this.maxAttackRange = 3;
        this.maxMovement = 2;
    }
}
