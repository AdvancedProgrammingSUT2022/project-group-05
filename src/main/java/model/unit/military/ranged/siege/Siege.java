package model.unit.military.ranged.siege;

import model.game.Civilization;
import model.tile.Tile;
import model.unit.UnitState;
import model.unit.military.ranged.Ranged;

public abstract class Siege extends Ranged {
    public Siege(Civilization civilization, Tile tile) {
        super(civilization, tile);
    }

    public void setup() { //changes State to set for siege

    }

    public boolean isSetup() { //checks if siege unit is ready for attack
        return unitState == UnitState.SET_FOR_SIEGE;
    }
}
