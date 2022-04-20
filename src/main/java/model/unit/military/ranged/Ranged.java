package model.unit.military.ranged;

import model.game.Civilization;
import model.tile.Terrain;
import model.tile.Tile;
import model.unit.Unit;

public abstract class Ranged extends Unit {
    public Ranged(Civilization civilization, Tile tile) {
        super(civilization, tile);
    }

    @Override
    public int getAttackStrength() {
        return boost(this.rangedStrength);
    }

    public boolean canSee(Tile tile) {
        Terrain sourceTerrain = this.tile.getTerrain();
        if (sourceTerrain == Terrain.HILL || sourceTerrain == Terrain.MOUNTAIN) return true;

        //TODO... Check other conditions as well

        return false;
    }
}
