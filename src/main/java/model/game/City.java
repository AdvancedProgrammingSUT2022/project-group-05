package model.game;

import model.building.BuildingList;
import model.map.Map;
import model.tile.Tile;
import model.unit.Unit;
import model.unit.civilian.Civilian;
import model.unit.soldier.Soldier;

import java.util.ArrayList;

public class City {

    private ArrayList<Unit> unitsInQueue = new ArrayList<>();
    private Unit unitInProgress;

    private final String name;
    private final Tile center;

    private int food;
    //When food reached its threshold, population increases and Threshold doubles.

    private int health;
    private int defenceStrength;
    private int defenceBonusPercentage; // because of garrisoned unit and center tile

    private ArrayList<Tile> tiles;
    private BuildingList buildingList;
    private Civilization civilization;

    private int totalCitizenCount;
    private int joblessCitizenCount;

    private boolean hasGarrisonedUnit;
    private boolean hasCivilianUnit;

    public City(String name, Tile center, Civilization civilization) {
        this.name = name;
        this.center = center;
        this.civilization = civilization;

        this.setHealth(20);
        this.setDefenceStrength(10); // TODO set later
        this.setDefenceBonusPercentage(center.getCombatBoost());
        this.hasGarrisonedUnit = false;

        this.food = 0;

        this.totalCitizenCount = 1;
        this.joblessCitizenCount = 1;

        this.tiles = new ArrayList<>();
        this.addTile(center);
        for (Tile tile : Map.getInstance().findNeighbors(center)) {
            if (this.canAddTile(tile)) this.addTile(tile);
        }
    }

    public void addUnitToQueue(Unit unit) {
        this.unitsInQueue.add(unit);
    }

    public Unit getUnitFromQueue(Unit unit) {
        for (Unit value : this.unitsInQueue) {
            if (value.equals(unit)) {
                return value;
            }
        }
        return null;
    }

    public boolean hasJoblessCitizen() {
        return this.getJoblessCitizenCount() > 0;
    }

    public boolean isInTerritory(Tile tile) {
        return this.tiles.contains(tile);
    }

    public void assignCitizenToTile(Tile tile) {
        this.setJoblessCitizenCount(this.getJoblessCitizenCount() - 1);
        tile.removeCitizen();
    }

    public void removeCitizenFromTile(Tile tile) {
        this.setJoblessCitizenCount(this.getJoblessCitizenCount() + 1);
        tile.assignCitizen();
    }

    public void addTile(Tile tile) {
        this.tiles.add(tile);
        tile.setCity(this);
    }

    public boolean canAddTile(Tile tile) { //checks if tile is neighbouring city and is not owned by anybody
        if (tile == null || tile.hasCity()) return false;

        for (Tile territory : this.tiles) {
            if (Map.getInstance().findDistance(territory, tile) == 1) return true;
        }

        return false;
    }

    public void garrisonUnit(Soldier soldier) {
        this.hasGarrisonedUnit = true;
        this.setDefenceBonusPercentage(this.getDefenceBonusPercentage() + 33);
    }

    public void removeGarrisonedUnit() {
        this.hasGarrisonedUnit = false;
        this.setDefenceBonusPercentage(this.getDefenceBonusPercentage() - 33);
    }

    public void stayCivilianUnitInCity(Civilian civilian) {
        hasCivilianUnit = true;
        center.setCivilian(civilian);
   }

    public void applyNewTurnChanges() {

        //spend production for unitInProgress
        this.spendProductionForUnitInProgress();

        //calculate foodSurplus
        int foodSurplus = 0;

        for (Tile tile : this.tiles) {
            foodSurplus += tile.getFood();
        }
        foodSurplus -= this.getTotalCitizenCount() * 2;

        //add foodSurplus or remove citizen
        this.food += foodSurplus;
        if (this.food < 0) this.food = 0;

        if (foodSurplus < 0 && totalCitizenCount > 1) this.removeCitizen();
        else if (foodSurplus > 0 && this.food >= this.nextThreshold()) this.addCitizen();

        //increase city health
        if (this.health < 20) this.setHealth(this.getHealth() + 1);
    }

    //GETTERS

    public Unit getUnitInProgress() {
        return unitInProgress;
    }

    public int getRemainingProductionTime() {
        if (unitInProgress == null) return 0;
        return unitInProgress.getCost() / this.getCivilization().getProduction();
    }

    public int getHealth() {
        return this.health;
    }

    public int getTotalCitizenCount() {
        return this.totalCitizenCount;
    }

    public int getJoblessCitizenCount() {
        return this.joblessCitizenCount;
    }

    public Tile getCenter() {
        return this.center;
    }

    public ArrayList<Tile> getTiles() {
        return this.tiles;
    }

    public Civilization getCivilization() {
        return civilization;
    }

    public boolean hasGarrisonedUnit() {
        return hasGarrisonedUnit;
    }

    public int getDefenceStrength() {
        return defenceStrength + defenceStrength * (this.getDefenceBonusPercentage() / 100);
    }

    public int getDefenceBonusPercentage() {
        return defenceBonusPercentage;
    }

    public boolean hasCivilianUnit() {
        return hasCivilianUnit;
    }

    //SETTER

    public void setUnitInProgress(Unit unitInProgress) {
        this.unitInProgress = unitInProgress;
    }

    public void addCitizen() {
        this.totalCitizenCount++;
        this.joblessCitizenCount++;
    }

    public void removeCitizen() {
        this.totalCitizenCount--;

        if (joblessCitizenCount > 0) {
            this.joblessCitizenCount--;
            return;
        }
        for (Tile tile : this.tiles) {
            if (tile.hasCitizen()) {
                tile.removeCitizen();
                break;
            }
        }
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setCivilization(Civilization civilization) {
        this.civilization = civilization;
    }

    public void setJoblessCitizenCount(int joblessCitizenCount) {
        this.joblessCitizenCount = joblessCitizenCount;
    }

    public void setDefenceStrength(int defenceStrength) {
        this.defenceStrength = defenceStrength;
    }

    public void setDefenceBonusPercentage(int defenceBonusPercentage) {
        this.defenceBonusPercentage = defenceBonusPercentage;
    }

    public String getName() {
        return name;
    }

    //UTIL
    private int nextThreshold() {
        int n = this.totalCitizenCount;

        return 15 + 6 * (n - 1) + ((int) Math.pow(n - 1, 1.5));
    }


    public void spendProductionForUnitInProgress() {
        if (this.unitInProgress == null) return ;
        this.unitInProgress.setCost(this.unitInProgress.getCost() - this.getCivilization().getProduction());
        if (this.unitInProgress.getCost() <= 0) {
            this.unitInProgress.setTile(unitInProgress.getStartingCity().getCenter());
            this.getCivilization().addUnit(this.unitInProgress);
            this.unitInProgress = null;
        }
    }

    public void removeUnitFromQueue(Unit unitFromQueue) {
        this.unitsInQueue.remove(unitFromQueue);
    }
}