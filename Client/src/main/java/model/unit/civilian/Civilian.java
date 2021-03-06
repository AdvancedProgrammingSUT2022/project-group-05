package model.unit.civilian;

import model.game.Civilization;
import model.tile.Tile;
import model.unit.Unit;

public abstract class Civilian extends Unit {

    public Civilian(Civilization civilization, Tile tile) {
        super(civilization, tile);
    }

    @Override
    public String toString () {
        return "Civilian";
    }
}
