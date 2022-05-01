package model.game;

import model.game.Civilization;
import model.tile.Tile;

import java.util.ArrayList;

public class City {
    private final Tile center;

    private int health;
    private ArrayList<Tile> tiles;
    private Civilization civilization;

    private int joblessCitizenCount;

    public City(Tile center, Civilization civilization /*, ArrayList<Tile> tiles*/) {
        this.civilization = civilization;
        this.center = center;

        //TODO... get adjacent tiles from controller(map) and add it to the city. initialize tiles.
    }

    public boolean hasJoblessCitizen() {
        return this.joblessCitizenCount > 0;
    }

    public boolean isInTerritory(Tile tile) {
        return this.tiles.contains(tile);
    }

    public void increaseSize() { //function to increase city size after reaching a certain amount of food production.
        //TODO...
    }

    //GETTERS
    public int getHealth() {
        return this.health;
    }

    public int getJoblessCitizenCount() {
        return this.joblessCitizenCount;
    }

    public Tile getCenter() {
        return this.center;
    }

    public Civilization getCivilization() {
        return civilization;
    }

    //SETTER
    public void setHealth(int health)
    {
        this.health = health;
    }

    public void setCivilization(Civilization civilization) {
        this.civilization = civilization;
    }

    public void setJoblessCitizenCount(int joblessCitizenCount) {
        this.joblessCitizenCount = joblessCitizenCount;
    }

}