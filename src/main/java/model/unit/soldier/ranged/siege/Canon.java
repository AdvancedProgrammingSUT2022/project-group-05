package model.unit.soldier.ranged.siege;

import model.game.Civilization;
import model.research.Research;
import model.tile.Tile;
import model.unit.addOns.BonusVsCity;
import model.unit.addOns.NoDefensiveBonus;

public class Canon extends Siege implements NoDefensiveBonus, BonusVsCity {
    public Canon(Civilization civilization, Tile tile) {
        super(civilization, tile);
        this.cost = 250;
        this.meleeStrength = 10;
        this.rangedStrength = 26;
        this.maxAttackRange = 2;
        this.maxMovement = 2;
        this.requiredResearch = Research.CHEMISTRY;
    }

    @Override
    public String toString () {

        return "Canon";
    }
}
