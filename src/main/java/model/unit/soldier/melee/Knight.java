package model.unit.soldier.melee;

import model.game.Civilization;
import model.research.Research;
import model.resource.Resource;
import model.tile.Tile;
import model.unit.addOns.CanMoveAfterAttacking;
import model.unit.addOns.Mounted;
import model.unit.addOns.NoDefensiveBonus;

public class Knight extends Melee implements Mounted, CanMoveAfterAttacking, NoDefensiveBonus {
    public Knight(Civilization civilization, Tile tile) {
        super(civilization, tile);
        this.cost = 150;
        this.initialCost = cost;
        this.meleeStrength = 18;
        this.rangedStrength = 0;
        this.maxAttackRange = 0;
        this.maxMovement = 3;
        this.requiredResource = Resource.HORSE;
        this.requiredResearch = Research.CHIVALRY;
    }

    @Override
    public String toString () {
        return "Knight";
    }
}
