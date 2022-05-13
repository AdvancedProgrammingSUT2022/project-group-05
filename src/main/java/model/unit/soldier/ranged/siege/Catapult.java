package model.unit.soldier.ranged.siege;

import model.game.Civilization;
import model.research.Research;
import model.resource.Resource;
import model.tile.Tile;

public class Catapult extends Siege {
    public Catapult(Civilization civilization, Tile tile) {
        super(civilization, tile);
        this.cost = 100;
        this.meleeStrength = 4;
        this.rangedStrength = 14;
        this.maxAttackRange = 2;
        this.maxMovement = 2;
        this.requiredResource = Resource.IRON;
        this.requiredResearch = Research.MATHEMATICS;
    }

    @Override
    public String toString () {
        return "Catapult";
    }
}
