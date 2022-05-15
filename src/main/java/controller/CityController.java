package controller;

import model.game.City;
import model.map.Map;
import model.tile.Tile;
import model.unit.Unit;
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
            return Responses.NO_UNIT_WITH_THIS_NAME.getResponse();
        newUnit.setStartingCity(this.city);
        Unit unitFromQueue = this.city.getUnitFromQueue(newUnit);
        if (unitFromQueue != null) { // for units that were already in queue
            this.city.removeUnitFromQueue(unitFromQueue);
            if (this.city.getUnitInProgress() != null)
                this.city.addUnitToQueue(this.city.getUnitInProgress());
            this.city.setUnitInProgress(unitFromQueue);
        } else {
            if (newUnit.equals(this.city.getUnitInProgress())) { // building same unit that is being built
                return Responses.UNIT_IS_ALREADY_BEING_BUILT.getResponse();
            }
            if (!this.city.getCivilization().getResearchTree().isResearchDone(newUnit.getRequiredResearch())) { // check reserch
                return Responses.REQUIRED_RESEARCH_NOT_FOUND.getResponse();
            } else if (!this.city.getCivilization().getResourceList().hasEnough(newUnit.getRequiredResource(), 1)) {
                return Responses.NOT_ENOUGH_RESOURCE.getResponse();
            } else { // moving previous unit to queue and set new unit
                if (this.city.getUnitInProgress() != null)
                    this.city.addUnitToQueue(this.city.getUnitInProgress());
                this.city.setUnitInProgress(newUnit);
            }
        }
        return Responses.CREATING_UNIT_STARTED.getResponse();
    }

    public String cityCreateBuilding(String buildingName) {
        //TODO find building and check conditions and add newBuilding to queue
        return Responses.CREATING_BUILDING_STARTED.getResponse();
    }

    public String buyTile(int x, int y) {
        Tile tile = Map.getInstance().getTileFromMap(x, y);
        if (!city.canAddTile(tile))
            return Responses.CANT_ADD_TILE_TO_CITY.getResponse();
        if (this.city.getCivilization().getGold() < 100)
            return Responses.NOT_ENOUGH_GOLD.getResponse();

        this.city.addTile(tile);
        return Responses.TILE_BOUGHT_SUCCESSFULLY.getResponse();
    }

    public String purchaseUnit(String unitName) {
        Unit newUnit = GenerateUnit.StringToUnit(this.city.getCivilization(), this.city.getCenter(), unitName);
        newUnit.setStartingCity(this.city);
        if (newUnit.getCost() > this.city.getCivilization().getGold()) {
            return Responses.NOT_ENOUGH_GOLD.getResponse();
        } else {
            this.city.getCivilization().addUnit(newUnit);
            return Responses.UNIT_PURCHASED_SUCCESSFULLY.getResponse();
        }
    }

    public String purchaseBuilding(String buildingName) {
        //TODO find building and check conditions and add building to city
        return Responses.BUILDING_PURCHASED_SUCCESSFULLY.getResponse();
    }

    public String assignCitizen(Tile tile) {
        if (!this.city.isInTerritory(tile))
            return "error: selected tile is out of city territory";
        if (tile.hasCitizen())
            return "error: selected tile already has a citizen";
        if (!this.city.hasJoblessCitizen())
            return "error: not enough jobless citizens";

        this.city.assignCitizenToTile(tile);
        return "citizen assigned to tile successfully";
    }

    public String removeCitizen(Tile tile) {
        if (!this.city.isInTerritory(tile))
            return "error: selected tile is out of city territory";
        if (!tile.hasCitizen())
            return "error: selected tile has no assigned citizen";

        this.city.removeCitizenFromTile(tile);
        return "citizen removed from tile successfully";
    }

    //GETTERS
    public City getCity() {
        return city;
    }
}
