package model.unit.soldier;

import model.game.Civilization;
import model.tile.Tile;
import model.unit.Unit;

public abstract class Soldier extends Unit{
    public Soldier(Civilization civilization, Tile tile) {
        super(civilization, tile);
    }

    public boolean canAttackTile(Tile tile){
        if (!this.isTileInRange(tile))
            return false;
        if (tile.getSoldier() == null && tile.getCivilian() == null)
            return false;
        if (tile.getSoldier() == null)
            return tile.getCivilian().getCivilization() != this.getCivilization();
        
        return tile.getSoldier().getCivilization() != this.getCivilization();
    }

    @Override
    public String toString () {
        return "Soldier";
    }
}
