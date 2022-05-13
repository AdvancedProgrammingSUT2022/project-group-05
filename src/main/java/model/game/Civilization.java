package model.game;

import model.User;
import model.map.FogOfWarStates;
import model.research.Research;
import model.research.ResearchTree;
import model.resource.Resource;
import model.resource.ResourceList;
import model.resource.ResourceType;
import model.tile.Tile;
import model.unit.Unit;

import java.util.ArrayList;
import java.util.Collections;

public class Civilization{
    private static int baseHappiness;

    static {
        baseHappiness = 10;
    }

    private City capital;
    private ArrayList<City> cities;
    private ArrayList<City> annexedCities;
    private ArrayList<Unit> units;

    private ArrayList<Unit> unitsInQueue;
    private Unit unitInProgress;

    private final User player;
    private final int color;

    private int gold;
    private int happiness;
    private int production;
    private int researchPoint;

    private final ResourceList resourceList;
    private final ResearchTree researchTree;

    private FogOfWarStates[][] fogOfWar = null;

    public Civilization(User player, int color) {
        this.cities = new ArrayList<>();

        this.units = new ArrayList<>();

        this.player = player;
        this.color = color;

        this.gold = 0;
        this.production = 0;
        this.happiness = 10;

        this.resourceList = new ResourceList();
        this.researchTree = new ResearchTree();

        this.researchPoint = 0;
    }

    public Boolean hasCity(City city) {
        return this.cities.contains(city);
    }

    public void addUnit(Unit unit) {
        this.units.add(unit);
    }

    public void removeUnit(Unit unit) {
        units.removeAll(Collections.singleton(unit));
    }

    public void addUnitToQueue(Unit unit) {

    }

    public Unit getUnitFromQueue(Unit unit) {
        for (int i = 0; i < this.unitsInQueue.size(); i++) {
            if (this.unitsInQueue.get(i).equals(unit)) {
                return this.unitsInQueue.get(i);
            }
        }
        return null;
    }

    public void startResearch(Research research) {
        this.researchTree.startResearch(research);
    }

    public int calculateHappiness() {
        int result = baseHappiness;
        for (Resource resource : Resource.values()) {
            if (resource.getResourceType().equals(ResourceType.LUXURIOUS) &&
                    this.resourceList.hasEnough(resource, 1))
                result += 4;
        }
        for (City city : this.cities) {
            result -= 3 + city.getTotalCitizenCount();
        }
        return result;
    }
    //SETTERS

    public void setUnitInProgress(Unit unitInProgress) {
        this.unitInProgress = unitInProgress;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }

    public void setResearchPoint(int researchPoint) {
        this.researchPoint = researchPoint;
    }

    public void setFogOfWar(FogOfWarStates[][] fogOfWar) {
        this.fogOfWar = fogOfWar;
    }

    public void addCity(City city) {
        this.cities.add(city);
    }

    public void removeCity(City city) {
        this.cities.remove(city);
    }

    //GETTERS

    public Unit getUnitInProgress() {
        return unitInProgress;
    }

    public User getPlayer() {
        return player;
    }

    public int getColor() {
        return color;
    }

    public int getGold() {
        return gold;
    }

    public int getHappiness() {
        return happiness;
    }

    public int getResearchPoint() {
        return researchPoint;
    }

    public FogOfWarStates[][] getFogOfWar() {
        return fogOfWar;
    }

    public ResourceList getResourceList() {
        return resourceList;
    }

    public ResearchTree getResearchTree() {
        return researchTree;
    }

    public ArrayList<Unit> getUnits() {
        return this.units;
    }

    public boolean equals(Civilization civilization) {
        return civilization.getPlayer().getNickname().equals(this.getPlayer().getNickname());
    }

    public ArrayList<City> getCities() {
        return cities;
    }

    public ArrayList<Tile> getTiles() {
        ArrayList<Tile> result = new ArrayList<>();
        for (City city : this.cities) {
            result.addAll(city.getTiles());
        }

        return result;
    }

    //END OF GETTERS

    public void applyNewTurnChanges() {
        this.spendProductionForUnitInProgress(); // decrease cost of unit

        //update research (science is declined if negative gold)
        this.getResearchTree().continueResearch(this.gold >= 0 ? this.researchPoint : this.gold);

        //update production after changes
        this.production = 0;
        for (Tile tile : this.getTiles()) {
            this.production += tile.getProduction();
        }

        //update gold after changes
        for (Tile tile : this.getTiles()) {
            this.gold += tile.getGold() - tile.getRouteMaintenanceCost();

            tile.applyNewTurnChanges();
        }

        //update city status

        //update happiness after changes
        this.happiness = this.calculateHappiness();
    }

    public void spendProductionForUnitInProgress() {
        this.unitInProgress.setCost(this.unitInProgress.getCost() - this.production);
        this.production = 0;
        if (this.unitInProgress.getCost() <= 0) {
            this.unitInProgress.setTile(unitInProgress.getStartingCity().getCenter());
            this.addUnit(this.unitInProgress);
            this.unitInProgress = null;
        }
    }

    public void removeUnitFromQueue(Unit unitFromQueue) {
        this.unitsInQueue.remove(unitFromQueue);
    }

    //CHEAT
    public static void setBaseHappiness(int newBaseHappiness) {
        baseHappiness = newBaseHappiness;
    }
}
