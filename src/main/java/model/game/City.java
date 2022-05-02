package model.game;

import model.game.Civilization;
import model.tile.Tile;
import model.unit.soldier.Soldier;

import java.util.ArrayList;
import java.util.HashMap;

public class City {
    private final String name;
    private final Tile center;

    private int health;
    private int defenceStrength;
    private int defenceBonusPercentage; // because of garrisoned unit
    //private ArrayList<Tile> tiles;
    private HashMap<Tile, TileStatus> tiles;
    private Civilization civilization;

    private int joblessCitizenCount;

    private boolean hasGarrisonedUnit;

    public City(String name, Tile center, Civilization civilization /*, ArrayList<Tile> tiles*/) {
        this.name = name;
        this.center = center;
        this.civilization = civilization;
        this.setHealth(20);
        this.setDefenceStrength(10); // TODO ??
        this.hasGarrisonedUnit = false;

        //TODO... get adjacent tiles from controller(map) and add it to the city. initialize tiles.
    }

    public boolean hasJoblessCitizen() {
        return this.joblessCitizenCount > 0;
    }

    public boolean isInTerritory(Tile tile) {
        return this.tiles.containsKey(tile);
    }

    public void increaseSize() { //function to increase city size after reaching a certain amount of food production.
        //TODO...
    }

    public void lockJoblessCitizenToTile(Tile tile) {
        if (!this.isInTerritory(tile)) {
            //TODO error
        } else if (!this.hasJoblessCitizen()) {
            //TODO error
        } else if (this.tiles.get(tile) == TileStatus.WORKING) {
            //TODO error
        } else if (this.tiles.get(tile) == TileStatus.DONE) {
            //TODO error
        } else {
            this.setJoblessCitizenCount(this.getJoblessCitizenCount() - 1);
            this.tiles.replace(tile, TileStatus.WORKING);
        }
    }

    public void removeCitizenFromTile(Tile tile) {
        if (!this.isInTerritory(tile)) {
            //TODO error
        } else if (this.tiles.get(tile) == TileStatus.INTACT || this.tiles.get(tile) == TileStatus.DONE) {
            //TODO error
        } else {
            this.setJoblessCitizenCount(this.getJoblessCitizenCount() + 1);
        }
    }

    private void changeTileStateWhenTheWordIsDone(Tile tile) {
        this.tiles.replace(tile, TileStatus.DONE);
    }

    public void addTileToCity(Tile tile) {
        //TODO check if tile could be added to city if not already checked
        this.tiles.put(tile, TileStatus.INTACT);
    }

    public void garrisonUnit(Soldier soldier) {
        if (ishasGarrisonedUnit()) {
            //TODO error
        } else {
            hasGarrisonedUnit = true;
            this.setDefenceStrength(33);
        }
    }

    public void removeGarrisonedUnit() {
        if (!ishasGarrisonedUnit()) {
            //TODO error
        } else {
            hasGarrisonedUnit = false;
            this.setDefenceStrength(0);
        }
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

    public boolean ishasGarrisonedUnit() {
        return hasGarrisonedUnit;
    }

    public int getDefenceStrength() {
        return defenceStrength + defenceStrength * (this.getDefenceBonusPercentage() / 100);
    }

    public int getDefenceBonusPercentage() {
        return defenceBonusPercentage;
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