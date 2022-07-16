package model.unit.soldier.ranged.siege;

import model.game.Civilization;
import model.research.Research;
import model.tile.Tile;
import model.unit.addOns.BonusVsCity;
import model.unit.addOns.NoDefensiveBonus;

public class Artillery extends Siege implements NoDefensiveBonus, BonusVsCity {
    public Artillery(Civilization civilization, Tile tile) {
        super(civilization, tile);
        this.cost = 420;
        this.initialCost = cost;
        this.meleeStrength = 16;
        this.rangedStrength = 32;
        this.maxAttackRange = 3;
        this.maxMovement = 2;
        this.requiredResearch = Research.DYNAMITE;

        this.textureAddress = "file:src/main/resources/images/units/siege/Artillery/";
    }

    @Override
    public String toString () {
        return "Artillery";
    }
}
