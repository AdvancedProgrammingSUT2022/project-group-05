package model.unit.soldier.melee;

import model.game.Civilization;
import model.tile.Tile;

public class Musketman extends Melee {
    public Musketman(Civilization civilization, Tile tile) {
        super(civilization, tile);
        this.cost = 120;
        this.meleeStrength = 16;
        this.rangedStrength = 0;
        this.maxAttackRange = 0;
        this.maxMovement = 2;
    }

    @Override
    public String toString () {
        //return "Musketman";
        //TODO we can edit after map printing beacause its too long to print
        return "Musket";
    }
}
