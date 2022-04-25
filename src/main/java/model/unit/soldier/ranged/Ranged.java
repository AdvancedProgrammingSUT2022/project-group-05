package model.unit.soldier.ranged;

import model.game.Civilization;
import model.tile.Tile;
import model.unit.soldier.Soldier;

public abstract class Ranged extends Soldier{
    private int range;

    public Ranged(Civilization civilization, Tile tile) {
        super(civilization, tile);
    }

    @Override
    public int getAttackStrength() {
        return boost(this.rangedStrength);
    }

    @Override
    public boolean canAttackTile(Tile tile) {
        //TODO... if(distance(this, tile) > attackRange) return false

        return this.tile.canSeeThrough(tile);
    }
}
