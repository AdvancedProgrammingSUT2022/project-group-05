package model.unit.soldier.melee;

import model.game.civilization.Civilization;
import model.tile.Tile;

public class Knight extends Melee {
    public Knight(Civilization civilization, Tile tile) {
        super(civilization, tile);
        this.cost = 150;
        this.meleeStrength = 18;
        this.rangedStrength = 0;
        this.maxAttackRange = 0;
        this.maxMovement = 3;
    }
}
