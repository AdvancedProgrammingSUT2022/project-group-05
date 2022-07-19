package model.unit.civilian;

import model.game.City;
import model.game.Civilization;
import model.tile.Tile;

public class Settler extends Civilian {
    public Settler(Civilization civilization, Tile tile) {
        super(civilization, tile);

        this.cost = 89;
        this.initialCost = cost;
        this.meleeStrength = 0;
        this.rangedStrength = 0;
        this.maxAttackRange = 0;
        this.maxMovement = 2;

        this.textureAddress = "file:src/main/resources/images/units/civilian/Settler/";
    }

    public void foundCity(String cityName) {
        this.civilization.addCity(new City(cityName, this.tile, this.civilization));
    }

    @Override
    public String toString () {
        return  "Settler";
    }
}
