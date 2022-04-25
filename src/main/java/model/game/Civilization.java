package model.game;

import model.User;
import model.tile.ResourceType;
import model.unit.Unit;

import java.util.ArrayList;
import java.util.HashMap;

public class Civilization {
    private City capital;
    private ArrayList<City> cities;
    private ArrayList<Unit> units;

    private User player;
    private int color;
    private int turn;

    private int food;
    private int gold;
    private int production;
    private int happiness;
    private int researchPoint;

    HashMap<ResourceType, Integer> resourcesCount; //Maps how many of each resource the civilization has

    public Civilization(City city, Unit unit, User player, int color, int turn) {
        this.cities = new ArrayList<>();
        this.cities.add(city);
        this.capital = city;

        this.units = new ArrayList<>();
        this.units.add(unit);

        this.player = player;
        this.color = color;
        this.turn = turn;

        //TODO... Reconfigure the initial values of "food, gold, happiness, ..." based on doc
        this.food = 0;
        this.gold = 0;
        this.production = 0;
        this.happiness = 0;
        this.researchPoint = 0;

        this.resourcesCount = new HashMap<>();
    }

    //SETTERS
    public void setTurn(int turn) {
        this.turn = turn;
    }

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

    public int getTurn() {
        return turn;
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

    public Boolean hasCity(City city) {
        return this.cities.contains(city);
    }
}