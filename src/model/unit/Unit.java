package model.unit;

import model.game.Civilization;
import model.tile.Tile;

public abstract class Unit {
    protected Civilization civilization;
    protected int cost;

    protected Tile tile;
    protected int maxMovement;
    protected int remainingMovement;

    protected int meleeStrength; //also defence
    protected int rangedStrength;
    protected int experience;
    protected int health;
    protected int level;

    protected UnitState unitState;

    public Unit(Civilization civilization, Tile tile) {
        this.civilization = civilization;
        this.unitState = UnitState.DONE;

        this.tile = tile;

        this.experience = 0;
        this.health = 10;
        this.level = 0;
    }

    //TODO... Read game.pdf page 21 and implement the functions below
    public boolean canMoveTo(Tile tile) { //checks if unit can move to a given tile
        return true;
    }

    public void sleep() { //sets unit to asleep state

    }

    public void alert() { //sets unit to alerted state

    }

    public void heal() { //sets unit to healing state

    }

    public void garrison() { //garrisons the unit if on a city tile

    }

    public void cancel() { //cancels the last order in unit command query?? WTH... check game.pdf page 21

    }

    public int getAttackStrength()
    {
        return boost(this.meleeStrength);
    }

    public int getDefenseStrength()
    {
        return boost(this.meleeStrength);
    }

    protected int boost(int initialStrength) { //boosts initial Strength based on current tile stats
        //TODO...
        return 0;
    }

    //SETTERS


    //GETTERS


}
