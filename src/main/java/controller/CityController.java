package controller;

import model.game.City;
import model.map.Map;
import model.tile.Tile;
import model.unit.Unit;
import model.unit.soldier.Soldier;

public class CityController {

    private City city;

    public CityController(City city) {
        this.city = city;
    }

    public String cityCreateUnit(String unitName) {
        Unit newUnit = GenerateUnit.StringToUnit(this.city.getCivilization(), this.city.getCenter(), unitName);
        //TODO check conditions and add newUnit to queue
        return "Creating Unit started";
    }

    public String cityCreateBuilding(String buildingName) {
        //TODO find building and check conditions and add newBuilding to queue
        return "Creating Building started";
    }

    public String buyTile(int x, int y) {
        Tile tile = Map.getInstance().getTileFromMap(x, y);
        //TODO check required gold for purchase , check adjacency of tile with city, ...
        return "tile bought successfully";
    }

    public String purchaseUnit(String unitName) {
        Unit newUnit = GenerateUnit.StringToUnit(this.city.getCivilization(), this.city.getCenter(), unitName);
        if (newUnit.getCost() > this.city.getCivilization().getGold()) {
            return "error: not enough gold";
        } else {
            this.city.getCivilization().addUnit(newUnit);
            return "unit purchased successfully";
        }
    }

    public String purchaseBuilding(String buildingName) {
        //TODO find building and check conditions and add building to city
        return "";
    }
}
