package model.unit.soldier.melee;

import model.game.Civilization;
import model.research.Research;
import model.tile.Tile;
import model.unit.addOns.*;

public class Tank extends Melee implements CanMoveAfterAttacking, model.unit.addOns.Tank, NoDefensiveBonus {
    public Tank(Civilization civilization, Tile tile) {
        super(civilization, tile);
        this.cost = 450;
        this.initialCost = cost;
        this.meleeStrength = 50;
        this.rangedStrength = 0;
        this.maxAttackRange = 0;
        this.maxMovement = 4;
        this.requiredResearch = Research.COMBUSTION;
    }

    @Override
    public String toString () {
        return "Tank";
    }
}
