package model.unit;

import model.game.Civilization;
import model.map.Map;
import model.tile.Tile;
import model.unit.civilian.Civilian;
import model.unit.soldier.Soldier;

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

    protected int temporaryDefenceBonusPercentage;
    protected int healingSpeed;
    protected int healingBonus;

    protected int remainingProductionNeeded;

    protected boolean createdYet;

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
        if (Map.getInstance().bestPathFinder(this.tile, tile, remainingMovement) == null) {
            return false;
        }
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

    public void fortify() { //sets unit to strengthening state to increase meleeStrength
        this.unitState = UnitState.FORTIFY;
    }

    public void recovering() { //sets unit to recover state to increase health
        this.unitState = UnitState.RECOVERING;
    }

    public void garrison() { //garrisons the unit if on a city tile
        this.unitState = UnitState.GARRISONED;
    }

    public void setup() {
        //TODO..
    }

    public void pillaging() { // pillage a tile
        this.unitState = UnitState.PILLAGING;
    }

    public void makingCity() { // only works for settler
        this.unitState = UnitState.MAKING_CITY;
    }

    public void cancel() { //cancels the last order in unit command query?? WTH... check game.pdf page 21
        this.unitState = UnitState.NOTHING;
    }

    public void killWithGold() { // will get coins
        civilization.setGold(civilization.getGold() + (this.cost * 10) / 100);
        this.kill();
    }


    public int getAttackStrength() {
        return boost(this.meleeStrength);
    }

    public int getDefenseStrength() {
        return boost(this.meleeStrength);
    }

    protected int boost(int initialStrength) { //boosts initial Strength based on current tile stats
        //TODO...
        int combatPercentage = this.tile.getCombatBoost();
        return initialStrength + (initialStrength * combatPercentage) / 100 + (initialStrength * this.temporaryDefenceBonusPercentage) / 100;
    }

    public void initializeRemainingMovement() {
        this.remainingMovement = this.maxMovement;
    }

    public void heal() {
        this.health  = this.health += healingSpeed;
    }

    //SETTERS

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public void setRemainingMovement(int MP) {
        this.remainingMovement = MP;
    }

    public void setHealth(int amount) {
        if (amount < 0) {
            this.health = 0;
        } else {
            this.health = amount;
        }
    }

    public void setTemporaryDefenceBonusPercentage(int temporaryDefenceBonusPercentage) {
        this.temporaryDefenceBonusPercentage = temporaryDefenceBonusPercentage;
    }

    public void setHealingSpeed(int healingSpeed) {
        this.healingSpeed = healingSpeed;
    }

    //GETTERS

    public int getRemainingMovement() {
        return remainingMovement;
    }

    public Civilization getCivilization() {
        return civilization;
    }

    public Tile getTile() {
        return this.tile;
    }

    public UnitState getUnitState() {
        return unitState;
    }

    public int getTotalMeleeStrength() {
        return 0;
    }

    public int getHealth() {
        return this.health;
    }

    public void kill() { // without gold
        civilization.removeUnit(this);
        if (this instanceof Civilian) {
            tile.removeCivilian();
        } else if (this instanceof Soldier) {
            tile.removeSoldier();
        }
    }

    public int getHealingBonus() {
        return healingBonus;
    }

    public int getCost() {
        return cost;
    }
}
