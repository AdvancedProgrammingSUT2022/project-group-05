package controller;

import model.building.Building;
import model.game.City;
import model.map.Map;
import model.tile.Tile;
import model.unit.Unit;
import model.unit.civilian.Settler;

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
        if (newUnit instanceof Settler && this.city.getTotalCitizenCount() < 2)
            return "error: not enough citizen for creating settler";
        newUnit.setStartingCity(this.city);
        Unit unitFromQueue = this.city.getUnitFromQueue(newUnit);
        if (unitFromQueue != null) { // for units that were already in queue
            this.city.removeProductionFromQueue(unitFromQueue);
            if (this.city.getProductionInProgress() != null)
                this.city.addProductionToQueue(this.city.getProductionInProgress());
            this.city.setProductionInProgress(unitFromQueue);
        } else {
            if (newUnit.equals(this.city.getUnitInProgress())) { // building same unit that is being built
                return Responses.UNIT_IS_ALREADY_BEING_BUILT.getResponse();
            }
            if (!this.city.getCivilization().getResearchTree().isResearchDone(newUnit.getRequiredResearch())) { // check reserch
                return Responses.REQUIRED_RESEARCH_NOT_FOUND.getResponse();
            } else if (!this.city.getCivilization().getResourceList().hasEnough(newUnit.getRequiredResource(), 1)) {
                return Responses.NOT_ENOUGH_RESOURCE.getResponse();
            } else { // moving previous production to queue and set new unit
                if (this.city.getProductionInProgress() != null)
                    this.city.addProductionToQueue(this.city.getProductionInProgress());
                this.city.setProductionInProgress(newUnit);
            }
        }
        return Responses.CREATING_UNIT_STARTED.getResponse();
    }

    public String cityCreateBuilding(String buildingName) {
        Building newBuilding = Building.find(buildingName);
        if (newBuilding == null) {
            return Responses.NO_BUILDING_WITH_THIS_NAME.getResponse();
        }
        if (this.city.getBuildingList().hasBuilding(newBuilding)) {
            return "error: already have this building in city";
        }
        Building buildingFromQueue = this.city.getBuildingFromQueue(newBuilding);
        if (buildingFromQueue != null) { // for building that were already in queue
            this.city.removeProductionFromQueue(buildingFromQueue);
            if (this.city.getProductionInProgress() != null) {
                this.city.addProductionToQueue(this.city.getProductionInProgress());
            }
            this.city.setProductionInProgress(buildingFromQueue);
        } else {
            if (newBuilding.equals(this.city.getBuildingInProgress())) { // building same building that is being built woooow so many bI :)
                return Responses.BUILDING_IS_ALREADY_BEING_BUILT.getResponse();
            }
            if (!this.city.getCivilization().getResearchTree().isResearchDone(newBuilding.getResearchRequired())) { // check research
                return Responses.REQUIRED_RESEARCH_NOT_FOUND.getResponse();
            } else if (!this.city.getCivilization().getResourceList().hasEnough(newBuilding.getResourceNeeded(), 1)) {
                return Responses.NOT_ENOUGH_RESOURCE.getResponse();
            } else if (!this.city.getBuildingList().hasBuildings(newBuilding.getBuildingsNeeded())) {
                return Responses.REQUIRED_BUILDINGS_NOT_FOUND.getResponse();
            } else { // moving previous production to queue and set new building
                if (this.city.getProductionInProgress() != null)
                    this.city.addProductionToQueue(this.city.getProductionInProgress());
                this.city.setProductionInProgress(newBuilding);
            }
        }
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
        if (newUnit == null)
            return "error: there is no unit with this name";
        newUnit.setStartingCity(this.city);
        if (newUnit.getCost() > this.city.getCivilization().getGold()) {
            return Responses.NOT_ENOUGH_GOLD.getResponse();
        }
        this.city.getCivilization().addUnit(newUnit);
        return Responses.UNIT_PURCHASED_SUCCESSFULLY.getResponse();
    }

    public String purchaseBuilding(String buildingName) {
        Building newBuilding = Building.find(buildingName);
        if (newBuilding == null) {
            return "error: there is no building with this name";
        }
        if (this.city.getBuildingList().hasBuilding(newBuilding)) {
            return "error: already have this building in city";
        }
        if (newBuilding.getCost() > this.city.getCivilization().getGold()) {
            return Responses.NOT_ENOUGH_GOLD.getResponse();
        }
        this.city.getBuildingList().addBuilding(newBuilding, this.city);
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
