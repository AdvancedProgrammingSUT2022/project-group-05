package model.tile;

import model.game.City;
import model.unit.Unit;

import java.sql.RowIdLifetime;

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
    private Unit unit;
    private boolean hasCitizen;

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
        this.unit = null;

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

        this.food += resource.food;
        this.gold += resource.gold;
        this.production += resource.production;
    }

    public void removeCitizen() {
        this.hasCitizen = false;
        this.city.setJoblessCitizenCount(this.city.getJoblessCitizenCount() + 1);

        this.food -= resource.food;
        this.gold -= resource.gold;
        this.production -= resource.production;
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
    public int getxPlace() {
        return xPlace;
    }

    public int getyPlace() {
        return yPlace;
    }

    public int getzPlace() {
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

    public Unit getUnit() {
        return this.unit;
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

    //SETTERS
    public void setCity(City city) {
        this.city = city;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
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

        return this.getxPlace() == tile.getxPlace() &&
               this.getyPlace() == tile.getyPlace() &&
               this.getzPlace() == tile.getzPlace();
    }
}
