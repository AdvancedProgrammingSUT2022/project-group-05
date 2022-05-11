package model.unit.soldier.ranged.siege;

import model.game.Civilization;
import model.tile.Tile;

public class Trebuchet extends Siege {
    public Trebuchet(Civilization civilization, Tile tile) {
        super(civilization, tile);
        this.cost = 170;
        this.meleeStrength = 6;
        this.rangedStrength = 20;
        this.maxAttackRange = 2;
        this.maxMovement = 2;
    }

    @Override
    public String toString () {
        //return "Trebuchet";
        //TODO we can edit after map printing beacause its too long to print
        return "trebuch";
    }
}
