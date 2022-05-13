package model.unit.soldier.ranged;

import model.game.Civilization;
import model.research.Research;
import model.tile.Tile;

public class ChariotArcher extends Ranged {
    public ChariotArcher(Civilization civilization, Tile tile) {
        super(civilization, tile);
        this.cost = 60;
        this.meleeStrength = 3;
        this.rangedStrength = 6;
        this.maxAttackRange = 2;
        this.maxMovement = 4;
        this.requiredResearch = Research.THE_WHEEL;
    }

    @Override
    public String toString () {
        //return "ChariotArcher";
        //TODO we can edit after map printing beacause its too long to print
        return "ChArcher";
    }
}
