package model.tile;

import model.game.City;

import java.util.ArrayList;

public class Tile {
    private City city; //This tile belongs to city
    private final Terrain terrain;
    private final Feature feature;
    private final ArrayList<Resource> resources;

    private int foodBoost;
    private int goldBoost;
    private int productionBoost;
    private int combatBoost;
    private int movementCost;

    public Tile(City city, Terrain terrain, Feature feature, ArrayList<Resource> resources) {
        this.city = city;
        this.terrain = terrain;
        this.feature = feature;
        this.resources = resources;

        //TODO... Set "foodBoost, goldBoost, ..." based on "terrain, feature, ...".
    }

    //SETTERS
    public void setCity(City city) {
        this.city = city;
    }

    //GETTERS
    public City getCity() {
        return this.city;
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
