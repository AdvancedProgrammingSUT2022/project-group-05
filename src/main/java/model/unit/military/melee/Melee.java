package model.unit.military.melee;

import model.game.Civilization;
import model.tile.Tile;
import model.unit.Unit;

public abstract class Melee extends Unit {

    public Melee(Civilization civilization, Tile tile) {
        super(civilization, tile);
    }

    public boolean canAttack(Tile tile) { //checks if a tile can be attacked by this unit
        //TODO... Check necessary conditions

        return false;
    }
}
