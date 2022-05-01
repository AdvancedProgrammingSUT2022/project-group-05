package model.tile;

import model.game.City;
import model.map.NeighbourType;
import model.unit.civilian.Civilian;
import model.unit.soldier.Soldier;
import model.resource.Resource;

public class Tile{
    private final int xPlace;
    private final int yPlace;
    private final int zPlace;
    //place of tile in MAP
    private final int fromLeft;
    private final int fromTop;

    private final int ID;

    private Terrain terrain;
    private Feature feature;
    private Resource resource;
    private City city; //This tile belongs to city
    private Civilian civilian;
    private Soldier soldier;
    private boolean hasCitizen;
    private boolean hasRoute;
    private boolean hasRiver;
    private boolean isRepaired; // if tile is repaired
    
    //TODO... Implement improvements
    //private Improvement improvement

    private int food;
    private int gold;
    private int production;
    private int combatBoost;
    private int movementCost;

    public Tile(int ID,
                Terrain terrain, Feature feature, Resource resource,
                int xPlace, int yPlace, int zPlace, int fromLeft, int fromTop) {
        this.ID = ID;

        this.city = null;
        this.civilian = null;
        this.soldier = null; //sam: why?

        this.xPlace = xPlace;
        this.yPlace = yPlace;
        this.zPlace = zPlace;

        this.fromLeft = fromLeft;
        this.fromTop = fromTop;

        this.terrain = terrain;
        this.feature = feature;
        this.resource = resource;
    }

    public void assignCitizen() { //assigns a citizen from this tile's city to work on this tile
        this.city.setJoblessCitizenCount(this.city.getJoblessCitizenCount() - 1);
        this.hasCitizen = true;

        this.food += resource.getFood();
        this.gold += resource.getGold();
        this.production += resource.getProduction();
    }

    public void removeCitizen() {
        this.hasCitizen = false;
        this.city.setJoblessCitizenCount(this.city.getJoblessCitizenCount() + 1);

        this.food -= resource.getFood();
        this.gold -= resource.getGold();
        this.production -= resource.getProduction();
    }

    public boolean hasCity() {
        return this.city != null;
    }

    public boolean isCityCenter() {
        return this.hasCity() && this.equals(this.city.getCenter());
    }

    //returns move points needed to enter this tile
    public int movePointsNeededToEnterFrom(Tile currentTile) {
        //TODO... return the needed mp for this tile
        return 0;
    }

    //GETTERS
    public int getXPlace() {
        return xPlace;
    }

    public int getYPlace() {
        return yPlace;
    }

    public int getZPlace() {
        return zPlace;
    }

    public int getFromLeft() {
        return fromLeft;
    }

    public int getFromTop() {
        return fromTop;
    }

    public int getID() {
        return ID;
    }


    public City getCity() {
        return this.city;
    }

    public Civilian getCivilian() {
        return this.civilian;
    }

    public Soldier getSoldier() {
        return this.soldier;
    }

    public Terrain getTerrain() {
        return this.terrain;
    }

    public Feature getFeature() {
        return this.feature;
    }

    public Resource getResource() {
        return this.resource;
    }

    public boolean hasCitizen() {
        return this.hasCitizen;
    }

    public int getFood() {
        return this.food;
    }

    public int getGold() {
        return this.gold;
    }

    public int getProduction() {
        return this.production;
    }

    public int getCombatBoost() {
        return this.combatBoost;
    }

    public int getMovementCost() {
        return this.movementCost;
    }

    public boolean hasRoute() {
        return this.hasRoute;
    }

    public boolean isRepaired() {
        return this.isRepaired;
    }

    //SETTERS
    public void setCity(City city) {
        this.city = city;
    }

    public void setCivilian(Civilian civilian) {
        this.civilian = civilian;
    }

    public void setSoldier(Soldier soldier) {
        this.soldier = soldier;
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public void setHasCitizen(boolean hasCitizen) {
        this.hasCitizen = hasCitizen;
    }

    //Default Overrides
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Tile)) return false;
        Tile tile = (Tile) object;

        return this.getXPlace() == tile.getXPlace() &&
               this.getYPlace() == tile.getYPlace() &&
               this.getZPlace() == tile.getZPlace();
    }


    public void removeCivilian() {
        this.civilian = null;
    }

    public void removeSoldier() {
        this.soldier = null;
    }

    //returns if a unit on thie tile cann see through given tile
    public boolean canSeeThrough(Tile tile) {
        //TODO..
        if (this.terrain == Terrain.HILL) return true;

        return this.terrain != Terrain.MOUNTAIN && tile.feature != Feature.FOREST;
    }
}
