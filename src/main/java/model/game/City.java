package model.game;

import model.building.BuildingList;
import model.map.Map;
import model.tile.Tile;
import model.unit.civilian.Civilian;
import model.unit.soldier.Soldier;

import java.util.ArrayList;

public class City {
    private final String name;
    private final Tile center;

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

        this.addTile(center);
        for (Tile tile : Map.getInstance().findNeighbors(center)) {
            if (this.canAddTile(tile)) this.addTile(tile);
        }
    }

    public boolean hasJoblessCitizen() {
        return this.joblessCitizenCount > 0;
    }

    public boolean isInTerritory(Tile tile) {
        return this.tiles.contains(tile);
    }

    public void assignCitizenToTile(Tile tile) {
            this.setJoblessCitizenCount(this.getJoblessCitizenCount() - 1);
    }

    public void removeCitizenFromTile(Tile tile) {
        this.setJoblessCitizenCount(this.getJoblessCitizenCount() + 1);
    }

    public void addTile(Tile tile) {
        this.tiles.add(tile);
    }

    public boolean canAddTile(Tile tile) {
        if (tile.hasCity()) return false;

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

    //GETTERS
    public int getHealth() {
        return this.health;
    }

    public int getJoblessCitizenCount() {
        return this.joblessCitizenCount;
    }

    public Tile getCenter() {
        return this.center;
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
    public void setHealth(int health)
    {
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
}