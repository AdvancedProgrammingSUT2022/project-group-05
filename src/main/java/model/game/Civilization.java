package model.game;

import model.User;
import model.map.FogOfWar;
import model.map.FogOfWarStates;
import model.research.ResearchTree;
import model.resource.ResourceList;
import model.unit.Unit;
import org.mockito.internal.matchers.Null;

import java.util.ArrayList;

public class Civilization {
    private City capital;
    private ArrayList<City> cities;
    private ArrayList<Unit> units;

    private User player;
    private int color;

    private int food;
    private int gold;
    private int happiness;
    private int production;
    private int researchPoint;

    private ResourceList resourceList;
    private ResearchTree researchTree;

    private FogOfWarStates[][] fogOfWar = null;

    public Civilization(User player, int color, int turn) {
        this.cities = new ArrayList<>();
        //this.cities.add(city);
        //this.capital = city;

        this.units = new ArrayList<>();
        //this.units.add(unit);

        this.player = player;
        this.color = color;

        //TODO... Reconfigure the initial values of "food, gold, happiness, ..." based on doc
        this.food = 0;
        this.gold = 0;
        this.production = 0;
        this.happiness = 0;
        this.researchPoint = 0;

        this.resourceList = new ResourceList();
        this.researchTree = new ResearchTree();
    }

    public Boolean hasCity(City city) {
        return this.cities.contains(city);
    }

    public void addUnit(Unit unit) {
        this.units.add(unit);
    }

    public void removeUnit(Unit unit) {
        for (int i = 0; i < units.size(); i++) {
            if (units.get(i).equals(unit)) {
                units.remove(i);
            }
        }
    }

    //SETTERS
    public void setFood(int food) {
        this.food = food;
    }

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

    public int getFood() {
        return food;
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

    public void applyNewTurnChanges() {
        //TODO increase production , decrease required production needed for units that are being created
    }
}
