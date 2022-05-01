package model.unit.soldier.ranged;

import model.game.civilization.Civilization;
import model.tile.Tile;

public class ChariotArcher extends Ranged {
    public ChariotArcher(Civilization civilization, Tile tile) {
        super(civilization, tile);
        this.cost = 60;
        this.meleeStrength = 3;
        this.rangedStrength = 6;
        this.maxAttackRange = 2;
        this.maxMovement = 4;
    }
}
