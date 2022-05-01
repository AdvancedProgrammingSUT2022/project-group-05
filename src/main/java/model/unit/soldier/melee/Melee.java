package model.unit.soldier.melee;

import model.game.Civilization;
import model.map.Map;
import model.tile.Tile;
import model.unit.soldier.Soldier;

public abstract class Melee extends Soldier{

    public Melee(Civilization civilization, Tile tile) {
        super(civilization, tile);
    }

    @Override
    public boolean canAttackTile(Tile tile, Map map) { //checks if a tile can be attacked by this unit
        //TODO... Check necessary conditions and handle error separately
        if (map.findDistance(this.tile, tile) == 1 && tile.getSoldier().getCivilization() != this.civilization) {
            return true;
        }
        return false;
    }
}
