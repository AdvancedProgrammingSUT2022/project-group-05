package model.unit.civilian;

import model.game.City;
import model.game.Civilization;
import model.tile.Tile;
import model.unit.Unit;

public class Settler extends Unit {
    public Settler(Civilization civilization, Tile tile) {
        super(civilization, tile);

        this.cost = 89;
        this.meleeStrength = 0;
        this.rangedStrength = 0;
        this.maxMovement = 2;
        this.remainingMovement = 0;
    }

    public void foundCity() {
        this.civilization.addCity(new City(this.tile, this.civilization));
    }

}
