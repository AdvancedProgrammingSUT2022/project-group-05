package controller;

import com.google.gson.Gson;
import model.User;
import model.building.BuildingList;
import model.game.City;
import model.game.Civilization;
import model.improvement.Improvement;
import model.map.Map;
import model.resource.Resource;
import model.tile.Feature;
import model.tile.Route;
import model.tile.Terrain;
import model.tile.Tile;
import model.tile.project.ProjectManager;
import model.unit.Unit;
import model.unit.UnitState;

import java.util.ArrayList;
import java.util.Scanner;

public class GameData{

    //WRITING DATA
    public static String write() {
        StringBuilder result = new StringBuilder();

        GameMenuController gameMenuController = GameMenuController.getInstance();
        Map map = Map.getInstance();
        int mapSize = map.getSizeOfMap();
        ArrayList<Civilization> civilizations = gameMenuController.getCivilizations();
        ArrayList<City> cities = gameMenuController.getCities();
        ArrayList<Unit> units = gameMenuController.getUnits();

        //CIVILIZATIONS
        result.append(civilizations.size()).append("\n");
        for (Civilization civilization : civilizations) {
            result.append(writeCivilization(civilization));
        }

        //CITIES
        result.append(cities.size()).append("\n");
        for (City city : cities) {
            result.append(writeCity(city));
        }

        //UNITS
        result.append(units.size()).append("\n");
        for (Unit unit : units) {
            result.append(writeUnit(unit));
        }

        //TILES
        result.append(mapSize).append("\n");
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                result.append(writeTile(Map.getInstance().getTileFromMap(i, j)));
            }
        }

        return result.toString();
    }

    public static String writeCivilization(Civilization civilization) {

        return new Gson().toJson(civilization.getPlayer()) + "----" +
                civilization.getColor() + "\n";
    }

    public static String writeCity(City city) {
        //TODO production and queue

        return city.getName() + "----" +
                city.getCenter().getXPlace() + "----" +
                city.getCenter().getYPlace() + "----" +
                city.getCivilization().getPlayer().getUsername() + "----" +
                city.getHealth() + "----" +
                city.getFood() + "----" +
                city.isAnnexed() + "----" +
                city.getAnnexTime() + "----" +
                new Gson().toJson(city.getBuildingList()) + "----" +
                city.getTotalCitizenCount() + "----" +
                city.getJoblessCitizenCount() + "----" +
                city.hasGarrisonedUnit() + "\n";
    }

    public static String writeUnit(Unit unit) {
        return  unit.toString() + "----" +
                unit.getTile().getXPlace() + "----" +
                unit.getTile().getYPlace() + "----" +
                unit.getCivilization().getPlayer().getUsername() + "----" +
                unit.getUnitState().ordinal() + "----" +
                unit.getRemainingMovement() + "----" +
                unit.getHealth() + "\n";
    }

    public static String writeTile(Tile tile) {

        return tile.getRoute().ordinal() + "----" +
                tile.getTerrain().ordinal() + "----" +
                tile.getFeature().ordinal() + "----" +
                tile.getResource().ordinal() + "----" +
                tile.getImprovement().ordinal() + "----" +
                new Gson().toJson(tile.getProjectManager()) + "----" +
                ((tile.getCity() == null)? "null" : tile.getCity().getName()) + "----" +
                tile.isRuin() + "----" +
                tile.isRepaired() + "----" +
                tile.hasCitizen() + "\n";
    }

    //READING DATA
    public static void read(String data) {
        Scanner scanner = new Scanner(data);

        int civilizationCount = Integer.parseInt(scanner.nextLine());
        ArrayList<String> civilizationData = new ArrayList<>();
        for (int i = 0; i < civilizationCount; i++) {
            civilizationData.add(scanner.nextLine());
        }

        int cityCount = Integer.parseInt(scanner.nextLine());
        ArrayList<String> cityData = new ArrayList<>();
        for (int i = 0; i < cityCount; i++) {
            cityData.add(scanner.nextLine());
        }

        int unitCount = Integer.parseInt(scanner.nextLine());
        ArrayList<String> unitData = new ArrayList<>();
        for (int i = 0; i < unitCount; i++) {
            unitData.add(scanner.nextLine());
        }

        int sizeOfMap = Integer.parseInt(scanner.nextLine());
        ArrayList<String> tileData = new ArrayList<>();
        for (int i = 0; i < sizeOfMap; i++) {
            for (int j = 0; j < sizeOfMap; j++) {
                tileData.add(scanner.nextLine());
            }
        }

        //INITIALIZE TILES
        initializeTiles(sizeOfMap, tileData);

        //INITIALIZE CIVILIZATIONS
        initializeCivilizations(civilizationCount, civilizationData);

        //INITIALIZE CITIES
        initializeCities(cityData);

        //INITIALIZE UNITS
        initializeUnits(unitData);

        //FINALIZE TILES
        finalizeTiles(sizeOfMap, tileData);
    }

    //INITIALIZE TILES
    public static ArrayList<Tile> initializeTiles(int sizeOfMap, ArrayList<String> tileData) {
        ArrayList<Tile> result = new ArrayList<>();

        for (int i = 0; i < sizeOfMap; i++) {
            for (int j = 0; j < sizeOfMap; j++) {
                result.add(initializeTile(sizeOfMap, i, j, tileData.get(i)));
            }
        }

        Map.updateInstance(sizeOfMap, result);

        City temp = new City("temp", Map.getInstance().getTileFromMap(0, 0), new Civilization(null, -1));
        for (Tile tile : result) {
            tile.setCity(temp);
        }

        return result;
    }

    public static Tile initializeTile(int sizeOfMap, int x, int y, String tileData) {
        String[] split = tileData.split("----");

        Route route = Route.values()[Integer.parseInt(split[0])];
        Terrain terrain = Terrain.values()[Integer.parseInt(split[1])];
        Feature feature = Feature.values()[Integer.parseInt(split[2])];
        Resource resource = Resource.values()[Integer.parseInt(split[3])];
        Improvement improvement = Improvement.values()[Integer.parseInt(split[4])];
        ProjectManager projectManager = new Gson().fromJson(split[5], ProjectManager.class);
        boolean isRuin = Boolean.parseBoolean(split[7]);
        boolean isRepaired = Boolean.parseBoolean(split[8]);
        boolean hasCitizen = Boolean.parseBoolean(split[9]);

        Tile tile = new Tile(x, y, sizeOfMap);
        tile.setRoute(route);
        tile.setTerrain(terrain);
        tile.setFeature(feature);
        tile.setResource(resource);
        tile.setImprovement(improvement);
        tile.setProjectManager(projectManager);
        tile.setIsRuin(isRuin);
        tile.setIsRepaired(isRepaired);
        if (hasCitizen) tile.assignCitizen(); else tile.removeCitizen();

        return tile;
    }

    //INITIALIZE CIVILIZATIONS
    public static ArrayList<Civilization> initializeCivilizations(int civilizationCount, ArrayList<String> civilizationData) {
        ArrayList<Civilization> result = new ArrayList<>(civilizationCount);

        for (int i = 0; i < civilizationCount; i++) {
            result.add(initializeCivilization(civilizationData.get(i)));
        }

        GameMenuController.updateInstance(result);

        return result;
    }

    public static Civilization initializeCivilization(String civilizationData) {
        String[] split = civilizationData.split("----");

        return new Civilization(new Gson().fromJson(split[0], User.class), Integer.parseInt(split[1]));
    }

    //INITIALIZE CITIES
    public static ArrayList<City> initializeCities(ArrayList<String> cityData) {
        ArrayList<City> result = new ArrayList<>();

        for (String data : cityData) {
            result.add(initializeCity(data));
        }

        return result;
    }

    public static City initializeCity(String cityData) {
        String[] split = cityData.split("----");
        //TODO production

        String name = split[0];
        int x = Integer.parseInt(split[1]);
        int y = Integer.parseInt(split[2]);
        Civilization civilization = findCivilization(split[3], GameMenuController.getInstance().getCivilizations());
        int health = Integer.parseInt(split[4]);
        int food = Integer.parseInt(split[5]);
        boolean isAnnexed = Boolean.parseBoolean(split[6]);
        int annexTime = Integer.parseInt(split[7]);
        BuildingList buildingList = new Gson().fromJson(split[8], BuildingList.class);
        int totalCount = Integer.parseInt(split[9]);
        int joblessCount = Integer.parseInt(split[10]);
        boolean hasGarrisoned = Boolean.parseBoolean(split[11]);

        City city = new City(name, Map.getInstance().getTileFromMap(x, y), civilization);
        city.setHealth(health);
        city.setFood(food);
        if (isAnnexed) city.annex();
        city.setAnnexTime(annexTime);
        city.setBuildingList(buildingList);
        city.setTotalCitizenCount(totalCount);
        city.setJoblessCitizenCount(joblessCount);
        if (hasGarrisoned) city.garrisonUnit();

        assert civilization != null;
        civilization.addCity(city);
        return city;
    }

    //INITIALIZE UNITS
    public static ArrayList<Unit> initializeUnits(ArrayList<String> unitData) {
        ArrayList<Unit> result = new ArrayList<>();

        for (String data : unitData) {
            result.add(initializeUnit(data));
        }

        return result;
    }

    public static Unit initializeUnit(String unitData) {
        String[] split = unitData.split("----");

        String type = split[0];
        int x = Integer.parseInt(split[1]);
        int y = Integer.parseInt(split[2]);
        Civilization civilization = findCivilization(split[3], GameMenuController.getInstance().getCivilizations());
        UnitState unitState = UnitState.values()[Integer.parseInt(split[4])];
        int remainingMovement = Integer.parseInt(split[5]);
        int health = Integer.parseInt(split[6]);

        Unit unit = GenerateUnit.StringToUnit(civilization, Map.getInstance().getTileFromMap(x, y), type);
        unit.setUnitState(unitState);
        unit.setRemainingMovement(remainingMovement);
        unit.setHealth(health);

        assert civilization != null;
        civilization.addUnit(unit);
        return unit;
    }

    //FINALIZE TILES
    public static ArrayList<Tile> finalizeTiles(int sizeOfMap, ArrayList<String> tileData) {
        ArrayList<Tile> result = new ArrayList<>();

        for (int i = 0; i < sizeOfMap; i++) {
            for (int j = 0; j < sizeOfMap; j++) {
                result.add(finalizeTile(i, j, tileData.get(i)));
            }
        }

        return result;
    }

    public static Tile finalizeTile(int x, int y, String tileData) {
        String[] split = tileData.split("----");

        Tile tile = Map.getInstance().getTileFromMap(x, y);
        City city = findCity(split[6], GameMenuController.getInstance().getCities());
        tile.setCity(city);

        if (city != null ) city.addTile(tile);

        return tile;
    }

    //UTIL
    public static Civilization findCivilization(String playerUsername, ArrayList<Civilization> civilizations) {
        for (Civilization civilization : civilizations) {
            if (civilization.getPlayer().getUsername().equals(playerUsername)) return civilization;
        }

        return null;
    }

    public static City findCity(String cityName, ArrayList<City> cities) {
        for (City city : cities) {
            if (city.getName().equals(cityName)) return city;
        }

        return null;
    }
}
