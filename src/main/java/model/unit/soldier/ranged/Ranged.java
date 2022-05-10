package model.unit.soldier.ranged;

import model.game.Civilization;
import model.map.Map;
import model.tile.Tile;
import model.unit.UnitState;
import model.unit.soldier.Soldier;

public abstract class Ranged extends Soldier{
    private int range;

    public Ranged(Civilization civilization, Tile tile) {
        super(civilization, tile);
    }


    public void attackFromDistance() { // changes state for attacking from distance
        this.unitState = UnitState.ATTACKING_FROM_DISTANCE;
    }

    @Override
    public int getAttackStrength() {
        return boost(this.rangedStrength);
    }

    @Override
    public boolean canAttackTile(Tile tile) {
        //TODO... if(distance(this, tile) > attackRange) return false
        if (Map.getInstance().findDistance(this.tile, tile) > this.maxAttackRange || !this.tile.canSeeThrough(tile)) {
            return false;
        } else {
            return true;
        }
    }
}