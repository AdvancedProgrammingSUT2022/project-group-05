package model.unit.soldier.melee;

import model.game.Civilization;
import model.research.Research;
import model.tile.Tile;
import model.unit.addOns.CanMoveAfterAttacking;
import model.unit.addOns.LimitedVisibility;
import model.unit.addOns.NoDefensiveBonus;
import model.unit.addOns.Tank;

public class Panzer extends Melee implements CanMoveAfterAttacking, Tank, NoDefensiveBonus, LimitedVisibility {
    public Panzer(Civilization civilization, Tile tile) {
        super(civilization, tile);
        this.cost = 450;
        this.initialCost = cost;
        this.meleeStrength = 60;
        this.rangedStrength = 0;
        this.maxAttackRange = 1;
        this.maxMovement = 5;
        this.requiredResearch = Research.COMBUSTION;

        this.textureAddress = "file:src/main/resources/images/units/melee/Panzer/";
    }

    @Override
    public String toString () {
        return "Panzer";
    }
}
