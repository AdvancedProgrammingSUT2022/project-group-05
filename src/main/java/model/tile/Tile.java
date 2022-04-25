package model.tile;

import model.game.City;
import model.unit.Unit;

import java.util.ArrayList;

public class Tile{
    private final int xPlace;
    private final int yPlace;
    private final int zPlace;
    private final Terrain terrain;
    private final Feature feature;
    private final ArrayList<Resource> resources;
    private City city; //This tile belongs to city
    private Unit unit;
    private boolean hasCitizen;

    //TODO... Implement improvements
    //private Improvement improvement

    private int foodIncrease;
    private int goldIncrease;
    private int productionIncrease;
    private int combatPercentage;
    private int movementCost;

    public Tile(int xPlace, int yPlace, int zPlace, City city, Terrain terrain, Feature feature, ArrayList<Resource> resources) {
        this.xPlace = xPlace;
        this.yPlace = yPlace;
        this.zPlace = zPlace;
        this.city = city;
        this.terrain = terrain;
        this.feature = feature;
        this.resources = resources;

        this.foodIncrease = terrain.foodIncrease + feature.foodIncrease;
        this.goldIncrease = terrain.goldIncrease + feature.foodIncrease;
        this.productionIncrease = terrain.productionIncrease + feature.productionIncrease;
        this.combatPercentage = terrain.combatPercentage + feature.combatPercentage;

        this.unit = null;
    }

    public void setHasCitizen(boolean hasCitizen) {
        this.hasCitizen = hasCitizen;
    }

    //GETTERS
    public int getxPlace() {
        return xPlace;
    }

    public int getyPlace() {
        return yPlace;
    }

    public int getzPlace() {
        return zPlace;
    }

    public City getCity() {
        return this.city;
    }

    //SETTERS
    public void setCity(City city) {
        this.city = city;
    }

    public Unit getUnit() {
        return this.unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Terrain getTerrain() {
        return this.terrain;
    }

    public Feature getFeature() {
        return this.feature;
    }

    public ArrayList<Resource> getResources() {
        return this.resources;
    }

    public boolean hasCitizen() {
        return this.hasCitizen;
    }

    //returns move points needed to enter this tile
    public int movePointsNeededToEnterFrom(Tile currentTile) {
        //TODO... return the needed mp for this tile
        return 0;
    }

    //returns if a unit on this tile can see through given tile
    public boolean canSeeThrough(Tile tile) {
        if (this.terrain == Terrain.HILL) return true;

        return tile.terrain != Terrain.MOUNTAIN && tile.feature != Feature.FOREST;
    }
}
