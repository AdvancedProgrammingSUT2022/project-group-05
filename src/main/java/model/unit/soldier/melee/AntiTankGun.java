package model.unit.soldier.melee;

import model.game.Civilization;
import model.research.Research;
import model.tile.Tile;

public class AntiTankGun extends Melee {
    public AntiTankGun(Civilization civilization, Tile tile) {
        super(civilization, tile);
        this.cost = 300;
        this.initialCost = cost;
        this.meleeStrength = 32;
        this.rangedStrength = 0;
        this.maxAttackRange = 0;
        this.maxMovement = 2;
        this.requiredResearch = Research.REPLACEABLE_PARTS;

        this.textureAddress = "file:src/main/resources/images/units/melee/AntiTankGun/";
    }


    @Override
    public String toString () {
        return "AntiTankGun";
    }
}
