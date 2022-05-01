package model.unit.soldier.ranged;

import model.game.civilization.Civilization;
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
    public boolean canAttackTile(Tile tile, Map map) {
        //TODO... if(distance(this, tile) > attackRange) return false
        if (map.findDistance(this.tile, tile) > this.maxAttackRange || !this.tile.canSeeThrough(tile)) {
            return false;
        } else {
            return true;
        }
    }
}
