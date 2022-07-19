package model.unit.soldier.ranged.siege;

import model.game.Civilization;
import model.research.Research;
import model.resource.Resource;
import model.tile.Tile;
import model.unit.addOns.BonusVsCity;
import model.unit.addOns.LimitedVisibility;
import model.unit.addOns.NoDefensiveBonus;

public class Catapult extends Siege implements NoDefensiveBonus, BonusVsCity, LimitedVisibility {
    public Catapult(Civilization civilization, Tile tile) {
        super(civilization, tile);
        this.cost = 100;
        this.initialCost = cost;
        this.meleeStrength = 4;
        this.rangedStrength = 14;
        this.maxAttackRange = 2;
        this.maxMovement = 2;
        this.requiredResource = Resource.IRON;
        this.requiredResearch = Research.MATHEMATICS;

        this.textureAddress = "file:src/main/resources/images/units/siege/Catapult/";
    }

    @Override
    public String toString () {
        return "Catapult";
    }
}
