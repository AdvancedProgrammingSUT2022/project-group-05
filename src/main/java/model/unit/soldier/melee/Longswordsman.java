package model.unit.soldier.melee;

import model.game.Civilization;
import model.tile.Tile;

public class Longswordsman extends Melee {
    public Longswordsman(Civilization civilization, Tile tile) {
        super(civilization, tile);
        this.cost = 150;
        this.meleeStrength = 18;
        this.rangedStrength = 0;
        this.maxAttackRange = 0;
        this.maxMovement = 3;
    }
}
