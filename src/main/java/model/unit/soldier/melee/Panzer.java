package model.unit.soldier.melee;

import model.game.Civilization;
import model.tile.Tile;

public class Panzer extends Melee {
    public Panzer(Civilization civilization, Tile tile) {
        super(civilization, tile);
        this.cost = 450;
        this.meleeStrength = 60;
        this.rangedStrength = 0;
        this.maxAttackRange = 0;
        this.maxMovement = 5;
    }
}
