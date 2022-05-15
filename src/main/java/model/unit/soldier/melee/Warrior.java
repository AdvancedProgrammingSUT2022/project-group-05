package model.unit.soldier.melee;

import model.game.Civilization;
import model.tile.Tile;

public class Warrior extends Melee {
    public Warrior(Civilization civilization, Tile tile) {
        super(civilization, tile);
        this.cost = 40;
        this.initialCost = cost;
        this.meleeStrength = 6;
        this.rangedStrength = 0;
        this.maxAttackRange = 0;
        this.maxMovement = 2;
    }

    @Override
    public String toString () {
        return "Warrior";
    }
}
