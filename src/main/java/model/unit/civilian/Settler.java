package model.unit.civilian;

import model.game.City;
import model.game.Civilization;
import model.tile.Tile;

public class Settler extends Civilian {
    public Settler(Civilization civilization, Tile tile) {
        super(civilization, tile);

        this.cost = 89;
        this.meleeStrength = 0;
        this.rangedStrength = 0;
        this.maxAttackRange = 0;
        this.maxMovement = 2;
    }

    public void foundCity() {
        this.civilization.addCity(new City(this.tile, this.civilization));
    }


}
