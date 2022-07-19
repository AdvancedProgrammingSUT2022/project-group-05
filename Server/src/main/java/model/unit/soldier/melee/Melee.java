package model.unit.soldier.melee;

import model.game.Civilization;
import model.tile.Tile;
import model.unit.soldier.Soldier;

public abstract class Melee extends Soldier{

    public Melee(Civilization civilization, Tile tile) {
        super(civilization, tile);
    }


    @Override
    public String toString () {
        return "Melee";
    }
}
