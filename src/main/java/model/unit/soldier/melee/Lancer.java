package model.unit.soldier.melee;

import model.game.Civilization;
import model.research.Research;
import model.resource.Resource;
import model.tile.Tile;

public class Lancer extends Melee {
    public Lancer(Civilization civilization, Tile tile) {
        super(civilization, tile);
        this.cost = 220;
        this.meleeStrength = 22;
        this.rangedStrength = 0;
        this.maxAttackRange = 0;
        this.maxMovement = 4;
        this.requiredResource = Resource.HORSE;
        this.requiredResearch = Research.METALLURGY;
    }

    @Override
    public String toString () {
    return "Lancer";
    }
}
