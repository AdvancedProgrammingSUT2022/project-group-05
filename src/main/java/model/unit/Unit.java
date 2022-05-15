package model.unit;

import model.game.City;
import model.game.Civilization;
import model.map.Map;
import model.research.Research;
import model.resource.Resource;
import model.tile.Feature;
import model.tile.Terrain;
import model.tile.Tile;
import model.unit.addOns.NoDefensiveBonus;
import model.unit.civilian.Civilian;
import model.unit.soldier.Soldier;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;

public abstract class Unit {
    protected Civilization civilization;
    protected int cost;

    protected City startingCity; // where to spawn when created


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


    protected Resource requiredResource;
    protected Research requiredResearch;

    protected UnitState unitState;

    public Unit(Civilization civilization, Tile tile) {
        this.civilization = civilization;
        this.unitState = UnitState.AWAKE;

        this.tile = tile;

        this.experience = 0;
        this.health = 10;
        this.level = 0;
        this.requiredResource = Resource.NO_RESOURCE;
        this.requiredResearch = Research.NO_RESEARCH;
        this.startingCity = null;
    }

    public boolean canMoveTo(Tile tile) { //checks if unit can move to a given tile
        if (tile == null || tile.getTerrain().equals(Terrain.MOUNTAIN) ||
            tile.getTerrain().equals(Terrain.OCEAN) || tile.getFeature().equals(Feature.ICE))
            return false;
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

    }

    public void pillaging() { // pillage a tile
        this.unitState = UnitState.PILLAGING;
    }

    public void makingCity() { // only works for settler
        this.unitState = UnitState.MAKING_CITY;
    }

    public void cancel() { //cancels the last order in unit command query?? WTH... check game.pdf page 21
        this.unitState = UnitState.AWAKE;
    }

    public void killWithGold() { // will get coins
        civilization.setGold(civilization.getGold() + (this.cost * 10) / 100);
        this.kill();
    }


    public int getAttackStrength() {
        return boost(this.meleeStrength);
    }

    protected int boost(int initialStrength) { //boosts initial Strength based on current tile stats
        int combatPercentage;
        if (!(this instanceof NoDefensiveBonus))
            combatPercentage = this.tile.getCombatBoost();
        else
            combatPercentage = 0;
        return initialStrength + (initialStrength * combatPercentage) / 100 + (initialStrength * this.temporaryDefenceBonusPercentage) / 100;
    }

    public void initializeRemainingMovement() {
        this.remainingMovement = this.maxMovement;
    }

    public boolean isInFriendlyTile() {
        return this.getCivilization() == this.getTile().getCivilization();
    }

    public void heal() {
        this.setHealth(this.getHealth() + healingSpeed);
    }

    public boolean isTileInRange(Tile tile) {
        if (Map.getInstance().findDistance(this.getTile(), tile) <= this.maxAttackRange)
            return true;
        return false;
    }


    //SETTERS
    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public void setRemainingMovement(int MP) {
        this.remainingMovement = MP;
    }

    public void setHealth(int amount) {
        this.setHealth(Math.max(amount, 0));
    }

    public void setTemporaryDefenceBonusPercentage(int temporaryDefenceBonusPercentage) {
        this.temporaryDefenceBonusPercentage = temporaryDefenceBonusPercentage;
    }

    public void setHealingSpeed(int healingSpeed) {
        this.healingSpeed = healingSpeed;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
    public void setStartingCity(City startingCity) {
        this.startingCity = startingCity;
    }

    //GETTERS
    public City getStartingCity() {
        return startingCity;
    }

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
        this.getCivilization().removeUnit(this);
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

    public Research getRequiredResearch() {
        return requiredResearch;
    }

    public Resource getRequiredResource() {
        return requiredResource;
    }

    public boolean hasTask() {
        if (this.getUnitState() == UnitState.AWAKE) {
            return true;
        }
        return false;
    }


    //CHEAT
    public void setMaxMovement(int maxMovement) {
        this.maxMovement = maxMovement;
        this.remainingMovement = maxMovement;
    }

    public void setMeleeStrength(int meleeStrength) {
        this.meleeStrength = meleeStrength;
    }
    public void setRangedStrength(int rangedStrength) {
        this.rangedStrength = rangedStrength;
    }
}
