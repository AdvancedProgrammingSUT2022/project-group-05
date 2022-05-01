package model.unit.soldier.melee;

import model.game.civilization.Civilization;
import model.tile.Tile;

public class Horseman extends Melee {
    public Horseman(Civilization civilization, Tile tile) {
        super(civilization, tile);
        this.cost = 80;
        this.meleeStrength = 12;
        this.rangedStrength = 0;
        this.maxAttackRange = 0;
        this.maxMovement = 4;
    }
}
