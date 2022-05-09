package model.unit.soldier;

import model.game.Civilization;
import model.map.Map;
import model.tile.Tile;
import model.unit.Unit;

public abstract class Soldier extends Unit{
    public Soldier(Civilization civilization, Tile tile) {
        super(civilization, tile);
    }

    public abstract boolean canAttackTile(Tile tile, Map map); //Checks if this unit is in range to attack given tile

    @Override
    public String toString () {
        return "Soldier";
    }
}
