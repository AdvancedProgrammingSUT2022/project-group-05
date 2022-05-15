package model.unit.soldier.melee;

import model.game.Civilization;
import model.research.Research;
import model.resource.Resource;
import model.tile.Tile;
import model.unit.addOns.CanMoveAfterAttacking;
import model.unit.addOns.Mounted;
import model.unit.addOns.NoDefensiveBonus;

public class Horseman extends Melee implements Mounted, CanMoveAfterAttacking, NoDefensiveBonus {
    public Horseman(Civilization civilization, Tile tile) {
        super(civilization, tile);
        this.cost = 80;
        this.initialCost = cost;
        this.meleeStrength = 12;
        this.rangedStrength = 0;
        this.maxAttackRange = 0;
        this.maxMovement = 4;
        this.requiredResource = Resource.HORSE;
        this.requiredResearch = Research.HORSEBACK_RIDING;
    }

    @Override
    public String toString () {
        return "Horseman";
    }
}
