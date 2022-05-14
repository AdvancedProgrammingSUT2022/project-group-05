package model.game;

import controller.UnitController;
import model.User;
import model.map.FogOfWar;
import model.map.FogOfWarStates;
import model.map.Map;
import model.research.Research;
import model.research.ResearchTree;
import model.resource.Resource;
import model.resource.ResourceList;
import model.resource.ResourceType;
import model.tile.Tile;
import model.unit.Unit;
import model.unit.civilian.Civilian;
import model.unit.civilian.Settler;
import model.unit.soldier.Soldier;

import java.util.ArrayList;
import java.util.Collections;

public class Civilization{
    private City capital;
    private ArrayList<City> cities;
    private ArrayList<City> annexedCities;
    private ArrayList<Unit> units;


    private final User player;
    private final int color;

    private int happiness;
    private int baseHappiness;

    private int turn;
    private int gold;
    private int production;
    private int baseProduction;
    private int researchPoint;

    private final ResourceList resourceList;
    private final ResearchTree researchTree;

    private FogOfWarStates[][] fogOfWar;

    public Civilization(User player, int color) {
        this.cities = new ArrayList<>();

        this.units = new ArrayList<>();
        this.initializeUnits();

        this.player = player;
        this.color = color;

        this.turn = 0;
        this.gold = 0;
        this.production = 10;
        this.baseProduction = 10;
        this.happiness = 10;

        this.resourceList = new ResourceList();
        this.researchTree = new ResearchTree();

        this.researchPoint = 0;

        FogOfWar.updateFogOfWar(this);
    }

    private void initializeUnits() {
        Tile tile = Map.getInstance().randomEmptyTile();
        this.addUnit(new Settler(this, tile));
    }

    public Boolean hasCity(City city) {
        return this.cities.contains(city);
    }

    public void addUnit(Unit unit) {
        this.units.add(unit);
        if (unit instanceof Civilian) unit.getTile().setCivilian((Civilian) unit);

        if (unit instanceof Soldier) unit.getTile().setSoldier((Soldier) unit);
    }

    public void removeUnit(Unit unit) {
        units.removeAll(Collections.singleton(unit));
        if (unit instanceof Civilian) unit.getTile().removeCivilian();
        if (unit instanceof Soldier) unit.getTile().removeSoldier();
    }

    public void startResearch(Research research) {
        this.researchTree.startResearch(research);
    }

    public int calculateHappiness() {
        int result = this.baseHappiness;
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

    public int calculateUnitMaintenance() {
        int unitCount = this.units.size();
        int currentTurn = this.turn;

        return ((unitCount + 1) / 2) * ((currentTurn) / 30 + 1);
    }

    //SETTERS

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void setProduction(int production) {
        this.production = production;
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

    public User getPlayer() {
        return player;
    }

    public int getColor() {
        return color;
    }

    public int getGold() {
        return gold;
    }

    public int getProduction() {
        return production;
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

    public void applyNewTurnChanges(int currentYear) {
        this.turn = currentYear;

        //update unit state for new turn
        for (Unit unit : this.units) {
            unit.initializeRemainingMovement();

            UnitController.updateInstance(unit);
            UnitController.getInstance().applyUnitStateForNewTurn();
        }

        //update research (science is declined if negative gold)
        this.getResearchTree().continueResearch(this.gold >= 0 ? this.researchPoint : this.gold);

        //update production after changes
        this.production = this.baseProduction;
        for (Tile tile : this.getTiles()) {
            this.setProduction(this.getProduction() + tile.getProduction());
        }

        //update gold and tiles after changes
        for (Tile tile : this.getTiles()) {
            this.gold += tile.getGold() - tile.getRouteMaintenanceCost();

            tile.applyNewTurnChanges();
        }
        this.gold -= this.calculateUnitMaintenance();

        //update city status
        for (City city : this.getCities()) {
            city.applyNewTurnChanges();
        }

        //update happiness after changes
        this.setHappiness(this.calculateHappiness());
    }

    //CHEAT
    public void setBaseHappiness(int newBaseHappiness) {
        this.baseHappiness = newBaseHappiness;
    }
}
