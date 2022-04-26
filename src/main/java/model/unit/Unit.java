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
    protected int maxAttackRange;
    protected int experience;
    protected int health;
    protected int level;



    protected UnitState unitState;

    public Unit(Civilization civilization, Tile tile) {
        this.civilization = civilization;
        this.unitState = UnitState.NOTHING;

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
        this.unitState = UnitState.ASLEEP;
    }

    public void wake() { //sets unit to awake state
        this.unitState = UnitState.AWAKE;
    }

    public void alert() { //sets unit to alerted state
        this.unitState = UnitState.ALERTED;
    }

    public void strengthening() { //sets unit to strengthening state to increase meleeStrength
        this.unitState = UnitState.STRENGTHENING;
    }

    public void recovering() { //sets unit to recover state to increase health
        this.unitState = UnitState.RECOVERING;
    }

    public void garrison() { //garrisons the unit if on a city tile
        this.unitState = UnitState.FORTIFIED;
    }

    public void cancel() { //cancels the last order in unit command query?? WTH... check game.pdf page 21
        this.unitState = UnitState.NOTHING;
    }

    public int getAttackStrength() {
        return boost(this.meleeStrength);
    }

    public int getDefenseStrength() {
        return boost(this.meleeStrength);
    }

    protected int boost(int initialStrength) { //boosts initial Strength based on current tile stats
        //TODO...
        return 0;
    }

    public void initializeRemainingMovement() {
        this.remainingMovement = this.maxMovement;
    }

    //SETTERS

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    //GETTERS

    public int getRemainingMovement() {
        return remainingMovement;
    }
}
