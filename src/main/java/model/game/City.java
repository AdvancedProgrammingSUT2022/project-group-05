package model.game;


import model.building.Building;
import model.building.BuildingList;
import model.map.Map;
import model.tile.Tile;
import model.unit.Unit;
import model.unit.civilian.Civilian;
import model.unit.soldier.Soldier;
import utility.RandomGenerator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;


public class City implements Serializable {

    private ArrayList<Object> productionInQueue = new ArrayList<>();
    private Object productionInProgress;

    private final String name;
    private final Tile center;

    private int annexTime;

    private int food;
    //When food reached its threshold, population increases and Threshold doubles.

    private int health;
    private int defenceStrength;
    private int defenceBonusPercentage; // because of garrisoned unit and center tile

    private final ArrayList<Tile> tiles;
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
        this.annexTime = 0;

        this.totalCitizenCount = 1;
        this.joblessCitizenCount = 1;

        this.tiles = new ArrayList<>();
        this.addTile(center);
        for (Tile tile : Map.getInstance().findNeighbors(center)) {
            if (this.canAddTile(tile)) this.addTile(tile);
        }
    }

    public void addProductionToQueue(Object production) {
        this.productionInQueue.add(production);
    }

    public Unit getUnitFromQueue(Unit unit) {
        for (Object value : this.productionInQueue) {
            if (value.getClass() == unit.getClass()) {
                Unit foundedUnit = (Unit) value;
                if (foundedUnit.equals(unit)) {
                    return foundedUnit;
                }
            }
        }
        return null;
    }

    public Building getBuildingFromQueue(Building building) {
        for (Object value : this.productionInQueue) {
            if (value.getClass() == building.getClass()) {
                Building foundedBuilding = (Building) value;
                if (foundedBuilding.equals(building)) {
                    return foundedBuilding;
                }
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
        tile.assignCitizen();
    }

    public void removeCitizenFromTile(Tile tile) {
        this.setJoblessCitizenCount(this.getJoblessCitizenCount() + 1);
        tile.removeCitizen();
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
        this.spendProductionForProductionInProgress();

        //spend gold for maintenance of buildings in city
        this.spendGoldForMaintenanceOfBuildings();

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

        //decrease annexation
        if (this.isAnnexed()) this.annexTime -= 1;
    }

    //GETTERS
    public boolean isAnnexed() {
        return this.annexTime > 0;
    }

    public int getAnnexTime() {
        return this.annexTime;
    }

    public Unit getUnitInProgress() {
        if (productionInProgress.getClass() == Unit.class)
            return (Unit) productionInProgress;
        return null;
    }

    public Building getBuildingInProgress() {
        if (productionInProgress.getClass() == Building.class)
            return (Building) productionInProgress;
        return null;
    }

    public Object getProductionInProgress() {
        return productionInProgress;
    }

    public int getRemainingProductionTime() {
        if (productionInProgress == null) return 0;
        if (productionInProgress.getClass() == Unit.class) {
            Unit unit = (Unit) productionInProgress;
            return unit.getCost() / this.getCivilization().getProduction();
        } else if (productionInProgress.getClass() == Building.class) {
            Building building = (Building) productionInProgress;
            return building.getCost() / this.getCivilization().getProduction();
        } else {
            return 0;
        }
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

    public int getFood() {
        return food;
    }

    public BuildingList getBuildingList() {
        return buildingList;
    }

    //SETTER


    public void setFood(int food) {
        this.food = food;
    }

    public void annex() {
        this.annexTime = 4;

        HashMap<Building, Integer> list = buildingList.getListOfBuildings();
        for (Building building : list.keySet())
        {
            if (building.equals(Building.BARRACKS) || building.equals(Building.ARMORY) ||
                building.equals(Building.ARSENAL) || building.equals(Building.MILITARY_BASE) ||
                building.equals(Building.MILITARY_ACADEMY))
            {
                buildingList.removeBuilding(building, this);
                continue;
            }

            if (RandomGenerator.nextInt(3) == 0) buildingList.removeBuilding(building, this);
        }
    }

    public void setAnnexTime(int annexTime) {
        this.annexTime = annexTime;
    }

    public void setProductionInProgress(Object productionInProgress) {
        this.productionInProgress = productionInProgress;
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


    public void spendProductionForProductionInProgress() {
        if (this.productionInProgress == null) return ;
        if (this.productionInProgress.getClass() == Unit.class) {
            Unit unit = (Unit) productionInProgress;
            unit.setCost(unit.getCost() - this.getCivilization().getProduction());
            if (unit.getCost() <= 0) {
                unit.setTile(unit.getStartingCity().getCenter());
                this.getCivilization().addUnit(unit);
                this.productionInProgress = null;
            }
        } else if (this.productionInProgress.getClass() == Building.class){
            Building building = (Building) productionInProgress;
            building.setCostForSpending(building.getCostForSpending() - this.getCivilization().getProduction());
            if (building.getCostForSpending() <= 0) {
                this.buildingList.addBuilding(building, this);
                this.productionInProgress = null;
            }
        }
    }

    public void spendGoldForMaintenanceOfBuildings() {
        for (java.util.Map.Entry<Building, Integer> set : this.buildingList.getListOfBuildings().entrySet()) {
            if (set.getValue() == 1) {
                this.getCivilization().setGold(this.getCivilization().getGold() - set.getKey().getMaintenance());
            }
        }
    }

    public void removeProductionFromQueue(Object productionFromQueue) {
        this.productionInQueue.remove(productionFromQueue);
    }
}