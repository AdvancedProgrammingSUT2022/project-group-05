package model.unit.soldier.melee;

import model.game.Civilization;
import model.research.Research;
import model.resource.Resource;
import model.tile.Tile;
import model.unit.addOns.CanMoveAfterAttacking;
import model.unit.addOns.Mounted;
import model.unit.addOns.NoDefensiveBonus;

public class Lancer extends Melee implements Mounted, CanMoveAfterAttacking, NoDefensiveBonus {
    public Lancer(Civilization civilization, Tile tile) {
        super(civilization, tile);
        this.cost = 220;
        this.initialCost = cost;
        this.meleeStrength = 22;
        this.rangedStrength = 0;
        this.maxAttackRange = 1;
        this.maxMovement = 4;
        this.requiredResource = Resource.HORSE;
        this.requiredResearch = Research.METALLURGY;

        this.textureAddress = "file:src/main/resources/images/units/melee/Lancer/";
    }

    @Override
    public String toString () {
    return "Lancer";
    }
}
