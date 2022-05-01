package model.unit.soldier.ranged.siege;

import model.game.civilization.Civilization;
import model.tile.Tile;
import model.unit.UnitState;
import model.unit.soldier.ranged.Ranged;

public abstract class Siege extends Ranged {
    public Siege(Civilization civilization, Tile tile) {
        super(civilization, tile);
    }

    public void setup() { //changes State to set for siege
        unitState = UnitState.SET_FOR_SIEGE;
    }

    public boolean isSetup() { //checks if siege unit is ready for attack
        return unitState == UnitState.SET_FOR_SIEGE;
    }
}
