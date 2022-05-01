package model.unit.soldier.melee;

import model.game.civilization.Civilization;
import model.tile.Tile;

public class Scout extends Melee {
    public Scout(Civilization civilization, Tile tile) {
        super(civilization, tile);
        this.cost = 25;
        this.meleeStrength = 4;
        this.rangedStrength = 0;
        this.maxAttackRange = 0;
        this.maxMovement = 2;
    }
}
