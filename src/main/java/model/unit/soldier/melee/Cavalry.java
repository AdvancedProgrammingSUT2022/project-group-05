package model.unit.soldier.melee;

import model.game.Civilization;
import model.research.Research;
import model.resource.Resource;
import model.tile.Tile;
import model.unit.addOns.CanMoveAfterAttacking;
import model.unit.addOns.Mounted;
import model.unit.addOns.NoDefensiveBonus;

public class Cavalry extends Melee implements Mounted, CanMoveAfterAttacking, NoDefensiveBonus {
    public Cavalry(Civilization civilization, Tile tile) {
        super(civilization, tile);
        this.cost = 260;
        this.initialCost = cost;
        this.meleeStrength = 25;
        this.rangedStrength = 0;
        this.maxAttackRange = 0;
        this.maxMovement = 3;
        this.requiredResource = Resource.HORSE;
        this.requiredResearch = Research.MILITARY_SCIENCE;
    }

    @Override
    public String toString () {
        return "Cavalry";
    }
}
