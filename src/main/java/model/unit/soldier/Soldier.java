package model.unit.soldier;

import model.game.City;
import model.game.Civilization;
import model.tile.Tile;
import model.unit.Unit;

public abstract class Soldier extends Unit{
    public Soldier(Civilization civilization, Tile tile) {
        super(civilization, tile);
    }

    public abstract boolean canAttackTile(Tile tile); //Checks if this unit is in range to attack given tile
}
