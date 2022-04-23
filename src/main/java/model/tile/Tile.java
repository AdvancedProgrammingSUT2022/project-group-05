package model.tile;

import model.game.City;
import model.unit.Unit;

import java.util.ArrayList;

public class Tile {
    private final int xPlace;
    private final int yPlace;
    private final int zPlace;

    private City city; //This tile belongs to city
    private final Terrain terrain;
    private final Feature feature;
    private final ArrayList<Resource> resources;

    private Unit unit;
    private boolean hasCitizen;

    //TODO... Implement improvements
    //private Improvement improvement

    private int foodBoost;
    private int goldBoost;
    private int productionBoost;
    private int combatBoost;
    private int movementCost;

    public Tile(int xPlace, int yPlace, int zPlace, City city, Terrain terrain, Feature feature, ArrayList<Resource> resources) {
        this.xPlace = xPlace;
        this.yPlace = yPlace;
        this.zPlace = zPlace;
        this.city = city;
        this.terrain = terrain;
        this.feature = feature;
        this.resources = resources;

        this.unit = null;

        //TODO... Set "foodBoost, goldBoost, ..." based on "terrain, feature, resource, improvement".
    }

    //SETTERS
    public void setCity(City city) {
        this.city = city;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
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

    public Unit getUnit() {
        return this.unit;
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
}
