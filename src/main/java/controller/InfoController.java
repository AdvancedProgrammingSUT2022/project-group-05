package controller;

import model.game.City;
import model.game.Civilization;
import model.improvement.Improvement;
import model.map.NeighbourType;
import model.resource.Resource;
import model.resource.ResourceType;
import model.tile.Route;
import model.tile.Tile;
import model.tile.project.ProjectManager;
import model.unit.Unit;
import model.unit.civilian.Civilian;
import model.unit.soldier.Soldier;

public class InfoController{
    //MODEL INFO GRABBERS
    public static int getLuxuryResourceCount(Civilization civilization) {
        int count = 0;
        for (Resource resource : Resource.values()) {
            if (resource.getResourceType().equals(ResourceType.LUXURIOUS) &&
                    civilization.getResourceList().hasEnough(resource, 1))
                count++;
        }

        return count;
    }

    public static int getSoldierCount(Civilization civilization) {
        int count = 0;
        for (Unit unit : civilization.getUnits()) {
            if (unit instanceof Soldier) count++;
        }

        return count;
    }

    public static int getCivilianCount(Civilization civilization) {
        int count = 0;
        for (Unit unit : civilization.getUnits()) {
            if (unit instanceof Civilian) count++;
        }

        return count;
    }

    public static int getTileCount(Civilization civilization) {
        int count = 0;
        for (City city : civilization.getCities()) {
            count += city.getTiles().size();
        }

        return count;
    }

    public static String getCityInfo(City city) {
        StringBuilder result = new StringBuilder();

        if (city == null) return result.toString();

        result.append("Name: ").append(city.getName()).append("\n");
        result.append("Population: ").append(city.getTotalCitizenCount()).append("\n");
        result.append("DefenceStrength: ").append(city.getDefenceStrength()).append("\n");

        return result.toString();
    }

    public static String getCityInfoInDepth(City city) {
        StringBuilder result = new StringBuilder();

        if (city == null) return result.toString();

        result.append(getCityInfo(city));
        result.append("CenterX: ").append(city.getCenter().getXPlace()).append(" CenterY: ").append(city.getCenter().getYPlace()).append("\n");
        result.append("Jobless population: ").append(city.getJoblessCitizenCount()).append("\n");
        result.append("Currently producing: ").append(city.getUnitInProgress() != null? city.getUnitInProgress() : "none").append("\n");
        result.append("Remaining production time: ").append(city.getRemainingProductionTime()).append("\n");

        return result.toString();
    }

    public static String getCityTilesStats(City city) {
        StringBuilder result = new StringBuilder();

        if (city == null) return result.toString();

        result.append("City tiles: \n");
        for (Tile tile : city.getTiles()) {
            result.append(getTileStats(tile)).append("\n");
        }
        result.append("\n");

        return result.toString();
    }

    public static String getUnitInfo(Unit unit) {
        StringBuilder result = new StringBuilder();

        if (unit == null) return result.toString();

        result.append("Name: ").append(unit.toString()).append("\n");
        result.append("Status: ").append(unit.getUnitState()).append("\n");

        return result.toString();
    }

    public static String getUnitInfoInDepth(Unit unit) {
        StringBuilder result = new StringBuilder();

        if (unit == null) return result.toString();

        result.append(getUnitInfo(unit));
        result.append("Health: ").append(unit.getHealth()).append("\n");
        result.append("Position: ").append(unit.getTile().getXPlace()).append(" ").append(unit.getTile().getYPlace()).append("\n");
        result.append("Remaining moves: ").append(unit.getRemainingMovement()).append("\n");

        return result.toString();
    }

    public static String getTileInfo(Tile tile) {
        StringBuilder result = new StringBuilder();

        if (tile == null) return result.toString();

        Civilization civilization = tile.getCivilization();
        boolean isRepaired = tile.isRepaired();
        boolean hasCitizen = tile.hasCitizen();
        boolean isCenter = tile.isCityCenter();
        City city = tile.getCity();

        Route route = tile.getRoute();
        Improvement improvement = tile.getImprovement();

        result.append("Civilization: ").append(civilization == null ? "NONE" : civilization.getPlayer().getNickname()).append("\n");
        result.append("City: ").append(city == null ? "NONE" : city.getName()).append("\n");
        result.append("HasCitizen: ").append(hasCitizen).append("\n");
        result.append("IsCityCenter: ").append(isCenter).append("\n");
        result.append("Route: ").append(route).append("\n");
        result.append("Improvement: ").append(improvement).append("\n");
        result.append("IsRepaired: ").append(isRepaired).append("\n");
        result.append(getTileRivers(tile));


        return result.toString();
    }

    public static String getTileStats(Tile tile) {
        return  "Location: " + tile.getXPlace() + ", " + tile.getYPlace() + "\n" +
                "Gold: " + tile.getGold() + "\n" +
                "Food: " + tile.getFood() + "\n" +
                "Production: " + tile.getProduction() + "\n" +
                "CombatBoost: " + tile.getCombatBoost() + "\n" +
                "MovementCost: " + tile.getMovementCost() + "\n";
    }

    public static String getTileProjectInfo(Tile tile) {
        return tile.getProjectStatus();
    }

    public static String getTileRivers(Tile tile) {
        StringBuilder result = new StringBuilder();

        boolean hasRiver = tile.hasRiver();
        boolean[] rivers = tile.getRivers();

        result.append("Rivers: ");
        for (NeighbourType neighbourType : NeighbourType.values()) {
            if (rivers[neighbourType.ordinal()]) result.append(neighbourType).append(", ");
        }

        if (!hasRiver) result.append("NONE");
        else result.replace(result.length() - 2, result.length(), "");

        return result.append("\n").toString();
    }
}
