package model.unit.soldier.melee;

import model.game.Civilization;
import model.tile.Tile;

public class Swordsman extends Melee {
    public Swordsman(Civilization civilization, Tile tile) {
        super(civilization, tile);
        this.cost = 80;
        this.meleeStrength = 11;
        this.rangedStrength = 0;
        this.maxAttackRange = 0;
        this.maxMovement = 2;
    }
}
