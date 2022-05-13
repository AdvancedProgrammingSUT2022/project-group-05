package model.tile;

import model.game.City;
import model.game.Civilization;
import model.improvement.Improvement;
import model.resource.Resource;
import model.tile.project.ProjectManager;
import model.tile.project.ProjectType;
import model.unit.civilian.Civilian;
import model.unit.civilian.Worker;
import model.unit.soldier.Soldier;

import java.util.Arrays;

public class Tile{
    private final int xPlace;
    private final int yPlace;
    private final int zPlace;
    private final int fromTop;
    private final int fromLeft;

    private final int ID;

    private Route route;
    private Terrain terrain;
    private Feature feature;
    private Resource resource;
    private Improvement improvement;

    private City city;
    private Soldier soldier;
    private Civilian civilian;
    private boolean hasCitizen;
    private final boolean[] rivers;

    private boolean isRepaired;
    private final ProjectManager projectManager;

    private int food;
    private int gold;
    private int production;
    private int combatBoost;
    private int movementCost;

    public Tile(int xPlace, int yPlace, int sizeOfMap) {
        this.ID = xPlace * sizeOfMap + yPlace;

        this.xPlace = xPlace;
        this.yPlace = yPlace;
        this.zPlace = 2 * (sizeOfMap - 1) - (xPlace + yPlace);

        this.fromTop = fromTopFinder(xPlace, yPlace, sizeOfMap);
        this.fromLeft = fromLeftFinder(xPlace, yPlace, sizeOfMap);

        this.route = Route.NO_ROUTE;
        this.terrain = Terrain.NO_TERRAIN;
        this.feature = Feature.NO_FEATURE;
        this.resource = Resource.NO_RESOURCE;
        this.improvement = Improvement.NO_IMPROVEMENT;

        this.rivers = new boolean[6];
        Arrays.fill(rivers, false);

        this.projectManager = new ProjectManager();

        this.city = null;
        this.civilian = null;
        this.soldier = null;
        this.hasCitizen = false;
    }

    //Citizen stuff
    public void assignCitizen() { //assigns a citizen from this tile's city to work on this tile
        this.hasCitizen = true;
    }

    public void removeCitizen() {
        this.hasCitizen = false;
    }

    //Project stuff
    public void startImprovementConstruction(Improvement improvement) {
        this.projectManager.closeProject();
        this.projectManager.startImprovementConstruction(improvement);
    }

    public  void startRouteConstruction(Route route) {
        this.projectManager.closeProject();
        this.projectManager.startRouteConstruction(route);
    }

    public void startImprovementRemoval() {
        this.projectManager.closeProject();
        this.projectManager.startImprovementRemoval();
    }

    public void startRouteRemoval() {
        this.projectManager.closeProject();
        this.projectManager.startRouteRemoval();
    }

    public void startFeatureRemoval(Feature feature) {
        this.projectManager.closeProject();
        this.projectManager.startFeatureRemoval(feature);
    }

    public void startRepair() {
        this.projectManager.closeProject();
        this.projectManager.startRepair();
    }

    public void applyNewTurnChanges() { //progresses the current project on this tile
        //Gold calculation


        //WorkerStuff
        if (this.civilian instanceof Worker) this.projectManager.continueProject();

        if (this.projectManager.hasFinishedProject()) {
            ProjectType type = this.projectManager.getProjectType();

            if (type == ProjectType.FEATURE_REMOVAL)
                this.removeFeature();
            else if (type == ProjectType.REPAIR)
                this.setIsRepaired(true);

            else if (type == ProjectType.ROUTE_CONSTRUCTION)
                this.setRoute(this.projectManager.getRouteProject());
            else if (type == ProjectType.IMPROVEMENT_CONSTRUCTION)
                this.setImprovement(this.projectManager.getImprovementProject());

            else if (type == ProjectType.ROUTE_REMOVAL)
                this.removeRoute();
            else if (type == ProjectType.IMPROVEMENT_REMOVAL)
                this.removeImprovement();

            this.projectManager.closeProject();
        }
    }

    //Map stuff
    public int movePointsNeededToEnterFrom(Tile currentTile) { //returns move points needed to enter this tile
        //TODO... return the needed mp for this tile MRB MRB MRB MRB
        return 0;
    }

    public boolean canSeeThrough(Tile tile) { //returns if a unit on this tile can see through given tile
        if (this.terrain == Terrain.HILL) return true;

        return tile.terrain != Terrain.MOUNTAIN && tile.feature != Feature.FOREST && tile.feature != Feature.JUNGLE;
    }

    private int fromLeftFinder(int xPlace, int yPlace, int mapSize) { //places in print map
        return 3 * (xPlace + yPlace);
    }

    private int fromTopFinder(int xPlace, int yPlace, int mapSize) { //places in print map
        return yPlace - xPlace + mapSize - 1;
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

    public Civilization getCivilization() {
        if (!this.hasCity()) return null;
        return this.city.getCivilization();
    }

    public Civilian getCivilian() {
        return this.civilian;
    }

    public Soldier getSoldier() {
        return this.soldier;
    }

    public boolean hasCitizen() {
        return this.hasCitizen;
    }

    public boolean hasRoute() {
        return this.route != null && this.route != Route.NO_ROUTE;
    }

    public boolean hasTerrain() {
        return this.terrain != null && this.terrain != Terrain.NO_TERRAIN;
    }

    public boolean hasFeature() {
        return this.feature != null && this.feature != Feature.NO_FEATURE;
    }

    public boolean hasResource() {
        return this.resource != null && this.resource != Resource.NO_RESOURCE;
    }

    public boolean hasImprovement() {
        return this.improvement != null && this.improvement != Improvement.NO_IMPROVEMENT;
    }

    public int getFood() {
        return this.hasCitizen ? this.food : 0;
    }

    public int getGold() {
        return this.hasCitizen ? this.gold : 0;
    }

    public int getProduction() {
        return this.hasCitizen ? this.production : 0;
    }

    public int getCombatBoost() {
        return this.combatBoost;
    }

    public int getMovementCost() {
        return this.movementCost;
    }

    public Route getRoute() {
        return this.route;
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

    public Improvement getImprovement() {
        if (!this.hasCity()) return Improvement.NO_IMPROVEMENT;

        if (this.getCivilization().getResearchTree().isResearchDone(improvement.getNeededResearch()))
            return this.improvement;
        return Improvement.NO_IMPROVEMENT;
    }

    public boolean hasCity() {
        return this.city != null;
    }

    public int getRiverCount() {
        int count = 0;
        for (boolean hasRiver : this.rivers) {
            if (hasRiver) count++;
        }
        return count;
    }

    public int getRouteMaintenanceCost() {
        return this.route.getMaintenanceCost();
    }

    public boolean hasRiver() {
        return this.getRiverCount() > 0;
    }

    public boolean isCityCenter() {
        return this.hasCity() && this.equals(this.city.getCenter());
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

    public void removeCivilian() {
        this.civilian = null;
    }

    public void removeSoldier() {
        this.soldier = null;
    }

    public void setRoute(Route route) {
        if (this.hasRoute()) this.removeRoute();
        this.route = route;

        //TODO... movement point stuff
    }
    public void removeRoute() {
        //TODO... movement point stuff
        this.route = Route.NO_ROUTE;
    }

    public void setTerrain(Terrain terrain) {
        if (this.hasTerrain()) this.removeTerrain();
        this.terrain = terrain;

        this.gold += terrain.getGold();
        this.food += terrain.getFood();
        this.production += terrain.getProduction();
        this.combatBoost += terrain.getCombatBoost();
        this.movementCost += terrain.getMovementCost();
    }
    public void removeTerrain() {
        this.gold -= this.terrain.getGold();
        this.food -= this.terrain.getFood();
        this.production -= this.terrain.getProduction();
        this.combatBoost -= this.terrain.getCombatBoost();
        this.movementCost -= this.terrain.getMovementCost();

        this.terrain = Terrain.NO_TERRAIN;
    }

    public void setFeature(Feature feature) {
        if (this.hasFeature()) this.removeFeature();
        this.feature = feature;

        this.gold += feature.getGold();
        this.food += feature.getFood();
        this.production += feature.getProduction();
        this.combatBoost += feature.getCombatBoost();
        this.movementCost += feature.getMovementCost();
    }
    public void removeFeature() {
        this.gold -= this.feature.getGold();
        this.food -= this.feature.getFood();
        this.production -= this.feature.getProduction();
        this.combatBoost -= this.feature.getCombatBoost();
        this.movementCost -= this.feature.getMovementCost();
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public void setImprovement(Improvement improvement) {
        if (this.hasImprovement()) this.removeImprovement();
        this.improvement = improvement;
        this.isRepaired = true;

        this.applyImprovementEffects();
    }
    public void removeImprovement() {
        this.disableImprovementEffects();

        this.improvement = Improvement.NO_IMPROVEMENT;
    }

    private void applyImprovementEffects() {
        this.gold += this.improvement.getGold();
        this.food += this.improvement.getFood();
        this.production += this.improvement.getProduction();

        if (this.resource.getNeededImprovement() == this.improvement) {
            this.gold += this.resource.getGold();
            this.food += this.resource.getFood();
            this.production += this.resource.getProduction();
        }
    }
    private void disableImprovementEffects() {
        this.gold -= this.improvement.getGold();
        this.food -= this.improvement.getFood();
        this.production -= this.improvement.getProduction();

        if (this.resource.getNeededImprovement() == this.improvement) {
            this.gold -= this.resource.getGold();
            this.food -= this.resource.getFood();
            this.production -= this.resource.getProduction();
        }
    }

    public void setHasCitizen(boolean hasCitizen) {
        this.hasCitizen = hasCitizen;
    }

    public void setIsRepaired(boolean isRepaired) {
        if (isRepaired) {
            this.applyImprovementEffects();
        } else {
            this.disableImprovementEffects();
        }
    }

    public void addRiver(int i) {
        if (rivers[i]) return;

        rivers[i] = true;
        this.gold += 1;
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
}
