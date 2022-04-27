package model.tile;

import model.game.City;
import model.unit.Unit;
import model.unit.civilian.Civilian;
import model.unit.soldier.Soldier;

public class Tile{
    private final int xPlace;
    private final int yPlace;
    private final int zPlace;
    private final int ID;
    private final Terrain terrain;
    private final Feature feature;
    private final Resource resource;
    private City city; //This tile belongs to city
    private Civilian civilian;
    private Soldier soldier;
    private boolean hasCitizen;
    private boolean hasRoute;
    private boolean isRepaired; // if tile is repaired

    //TODO... Implement improvements
    //private Improvement improvement

    private int foodIncrease;
    private int goldIncrease;
    private int productionIncrease;
    private final int combatPercentage;
    private int movementCost;

    public Tile(int xPlace, int yPlace, int zPlace, int ID, Terrain terrain, Feature feature, Resource resource) {
        this.city = null;

        this.xPlace = xPlace;
        this.yPlace = yPlace;
        this.zPlace = zPlace;
        this.ID = ID;

        this.terrain = terrain;
        this.feature = feature;
        this.resource = resource;

        this.foodIncrease = terrain.foodIncrease + feature.foodIncrease;
        this.goldIncrease = terrain.goldIncrease + feature.foodIncrease;
        this.productionIncrease = terrain.productionIncrease + feature.productionIncrease;
        this.combatPercentage = terrain.combatPercentage + feature.combatPercentage;
        this.movementCost = terrain.movementCost + feature.movementCost;

        this.civilian = null;
        this.soldier = null;
    }

    public void assignCitizen() {
        this.city.setJoblessCitizenCount(this.city.getJoblessCitizenCount() - 1);
        this.hasCitizen = true;

        this.foodIncrease += resource.foodIncrease;
        this.goldIncrease += resource.goldIncrease;
        this.productionIncrease += resource.productionIncrease;
    }

    public void removeCitizen() {
        this.hasCitizen = false;
        this.city.setJoblessCitizenCount(this.city.getJoblessCitizenCount() + 1);

        this.foodIncrease -= resource.foodIncrease;
        this.goldIncrease -= resource.goldIncrease;
        this.productionIncrease -= resource.productionIncrease;
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

    public int getFoodIncrease() {
        return this.foodIncrease;
    }

    public int getGoldIncrease() {
        return this.goldIncrease;
    }

    public int getProductionIncrease() {
        return this.productionIncrease;
    }

    public int getCombatPercentage() {
        return this.combatPercentage;
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
