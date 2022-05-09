package model.unit.soldier.melee;

import model.game.Civilization;
import model.tile.Tile;

public class AntiTankGun extends Melee {
    public AntiTankGun(Civilization civilization, Tile tile) {
        super(civilization, tile);
        this.cost = 300;
        this.meleeStrength = 32;
        this.rangedStrength = 0;
        this.maxAttackRange = 0;
        this.maxMovement = 2;
    }

    @Override
    public String toString () {
        //return "AntiTankGun";
        //TODO we can edit after map printing beacause its too long to print
        return "AntTank";
    }
}
