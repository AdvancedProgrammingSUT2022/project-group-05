package controller;

import model.game.City;
import model.map.Map;
import model.tile.Tile;
import model.unit.Unit;
import model.unit.civilian.Settler;
import model.unit.soldier.Soldier;

public class CityController {

    private final City city;

    //singleton definition
    private static CityController instance;

    private CityController(City city) {
        this.city = city;
    }

    public static CityController getInstance() {
        return instance;
    }

    public static void updateInstance(City city) {
        instance = new CityController(city);
    }

    //End of singleton definition

    public String cityCreateUnit(String unitName) {
        Unit newUnit = GenerateUnit.StringToUnit(this.city.getCivilization(), this.city.getCenter(), unitName);
        if (newUnit == null)
            return "error: there is no unit with this name";
        if (newUnit instanceof Settler && this.city.getTotalCitizenCount() < 2)
            return "error: not enough citizen for creating settler";
        newUnit.setStartingCity(this.city);
        Unit unitFromQueue = this.city.getUnitFromQueue(newUnit);
        if (unitFromQueue != null) { // for units that were already in queue
            this.city.removeUnitFromQueue(unitFromQueue);
            if (this.city.getUnitInProgress() != null)
                this.city.addUnitToQueue(this.city.getUnitInProgress());
            this.city.setUnitInProgress(unitFromQueue);
        } else {
            if (newUnit.equals(this.city.getUnitInProgress())) { // building same unit that is being built
                return "this unit is already being built";
            }
            if (!this.city.getCivilization().getResearchTree().isResearchDone(newUnit.getRequiredResearch())) { // check reserch
                return "required research not found";
            } else if (!this.city.getCivilization().getResourceList().hasEnough(newUnit.getRequiredResource(), 1)) {
                return "not enough resource";
            } else { // moving previous unit to queue and set new unit
                if (this.city.getUnitInProgress() != null)
                    this.city.addUnitToQueue(this.city.getUnitInProgress());
                this.city.setUnitInProgress(newUnit);
            }
        }
        return "Creating Unit started";
    }

    public String cityCreateBuilding(String buildingName) {
        //TODO find building and check conditions and add newBuilding to queue
        return "Creating Building started";
    }

    public String buyTile(int x, int y) {
        Tile tile = Map.getInstance().getTileFromMap(x, y);
        if (!city.canAddTile(tile))
            return "can't add tile to city";
        if (this.city.getCivilization().getGold() < 100)
            return "not enough gold";

        this.city.addTile(tile);
        return "tile bought successfully";
    }

    public String purchaseUnit(String unitName) {
        Unit newUnit = GenerateUnit.StringToUnit(this.city.getCivilization(), this.city.getCenter(), unitName);
        if (newUnit == null)
            return "error: there is no unit with this name";
        newUnit.setStartingCity(this.city);
        if (newUnit.getCost() > this.city.getCivilization().getGold()) {
            return "error: not enough gold";
        }
        this.city.getCivilization().addUnit(newUnit);
        return "unit purchased successfully";
    }

    public String purchaseBuilding(String buildingName) {
        //TODO find building and check conditions and add building to city
        return "";
    }

    public City getCity() {
        return city;
    }
}
