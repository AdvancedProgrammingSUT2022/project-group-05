package model.unit.soldier.melee;

import model.game.Civilization;
import model.tile.Tile;

public class Spearman extends Melee {
    public Spearman(Civilization civilization, Tile tile) {
        super(civilization, tile);
        this.cost = 50;
        this.meleeStrength = 7;
        this.rangedStrength = 0;
        this.maxAttackRange = 0;
        this.maxMovement = 2;
    }

    @Override
    public String toString () {
        return "Spearman";
    }
}
