package model.unit.soldier.ranged.siege;

import model.game.Civilization;
import model.research.Research;
import model.resource.Resource;
import model.tile.Tile;
import model.unit.addOns.BonusVsCity;
import model.unit.addOns.LimitedVisibility;
import model.unit.addOns.NoDefensiveBonus;

public class Trebuchet extends Siege implements NoDefensiveBonus, BonusVsCity, LimitedVisibility {
    public Trebuchet(Civilization civilization, Tile tile) {
        super(civilization, tile);
        this.cost = 170;
        this.meleeStrength = 6;
        this.rangedStrength = 20;
        this.maxAttackRange = 2;
        this.maxMovement = 2;
        this.requiredResource = Resource.IRON;
        this.requiredResearch = Research.PHYSICS;
    }

    @Override
    public String toString () {
        //return "Trebuchet";
        //TODO we can edit after map printing beacause its too long to print
        return "trebuch";
    }
}
