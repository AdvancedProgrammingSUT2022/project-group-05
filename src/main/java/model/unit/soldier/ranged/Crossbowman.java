package model.unit.soldier.ranged;

import model.game.civilization.Civilization;
import model.tile.Tile;

public class Crossbowman extends Ranged {
    public Crossbowman(Civilization civilization, Tile tile) {
        super(civilization, tile);
        this.cost = 120;
        this.meleeStrength = 6;
        this.rangedStrength = 12;
        this.maxAttackRange = 2;
        this.maxMovement = 2;
    }
}
