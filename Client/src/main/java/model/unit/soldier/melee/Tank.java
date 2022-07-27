package model.unit.soldier.melee;

import model.game.Civilization;
import model.research.Research;
import model.tile.Tile;
import model.unit.addOns.CanMoveAfterAttacking;
import model.unit.addOns.NoDefensiveBonus;

public class Tank extends Melee implements CanMoveAfterAttacking, model.unit.addOns.Tank, NoDefensiveBonus {
    public Tank(Civilization civilization, Tile tile) {
        super(civilization, tile);
        this.cost = 450;
        this.initialCost = cost;
        this.meleeStrength = 50;
        this.rangedStrength = 0;
        this.maxAttackRange = 1;
        this.maxMovement = 4;
        this.requiredResearch = Research.COMBUSTION;

        this.textureAddress = "file:src/main/resources/images/units/melee/Tank/";
    }

    @Override
    public String toString () {
        return "Tank";
    }
}
