package model.game;

import model.tile.Tile;

import java.util.ArrayList;

public class City {
    private int health;
    private ArrayList<Tile> tiles;
    private Tile center;
    private Civilization civilization;
    private boolean isCombatable;

    private int joblessCitizenCount;

    public City(Tile center, Civilization civilization, /*ArrayList<Tile> tiles*/) {
        this.civilization = civilization;
        this.center = center;

        //TODO... get adjacent tiles from controller(map) and add it to the city. initialize tiles.
    }

    public boolean hasJoblessCitizen() {
        return this.joblessCitizenCount > 0;
    }

    public void assignCitizen(Tile tile) {
        this.joblessCitizenCount--;
        tile.setHasCitizen(true);
    }

    public void removeCitizen(Tile tile) {
        this.joblessCitizenCount++;
        tile.setHasCitizen(false);
    }
}