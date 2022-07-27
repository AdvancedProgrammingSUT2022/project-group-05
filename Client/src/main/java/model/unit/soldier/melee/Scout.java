package model.unit.soldier.melee;

import model.game.Civilization;
import model.tile.Tile;

public class Scout extends Melee {
    public Scout(Civilization civilization, Tile tile) {
        super(civilization, tile);
        this.cost = 25;
        this.initialCost = cost;
        this.meleeStrength = 4;
        this.rangedStrength = 0;
        this.maxAttackRange = 1;
        this.maxMovement = 2;

        this.textureAddress = "file:src/main/resources/images/units/melee/Scout/";
    }

    @Override
    public String toString () {
        return "Scout";
    }
}
