package controller;

import com.google.gson.Gson;
import model.User;
import model.building.BuildingList;
import model.game.City;
import model.game.Civilization;
import model.map.Map;
import model.tile.Tile;
import model.unit.Unit;

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

    public static String writeTile(Tile tile) {

        return tile.getRoute().ordinal() + "----" +
                tile.getTerrain().ordinal() + "----" +
                tile.getFeature().ordinal() + "----" +
                tile.getResource().ordinal() + "----" +
                tile.getImprovement().ordinal() + "----" +
                new Gson().toJson(tile.getProjectManager()) + "----" +
                (tile.getCity() == null? "null" : tile.getCity().getName()) + "----" +
                (
                tile.getSoldier() == null?
                tile.getSoldier().toString() + "----" +
                tile.getSoldier().getUnitState().ordinal() + "----" +
                tile.getSoldier().getCivilization().getPlayer().getUsername() + "----"
                :
                "null----null----null----"
                ) +
                (
                tile.getCivilian() == null?
                tile.getCivilian().toString() + "----" +
                tile.getCivilian().getUnitState().ordinal() + "----" +
                tile.getCivilian().getCivilization().getPlayer().getUsername() + "\n"
                :
                "null----null----null\n"
                );
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

        int sizeOfMap = Integer.parseInt(scanner.nextLine());
        ArrayList<String> tileData = new ArrayList<>();
        for (int i = 0; i < sizeOfMap; i++) {
            for (int j = 0; j < sizeOfMap; j++) {
                tileData.add(scanner.nextLine());
            }
        }

        //INITIALIZE TILES
        ArrayList<Tile> tiles = initializeTiles(sizeOfMap, tileData);

        //INITIALIZE CIVILIZATIONS
        ArrayList<Civilization> civilizations = initializeCivilizations(civilizationCount, civilizationData);

        //INITIALIZE CITIES
        ArrayList<City> cities = initializeCities(cityData);

        //INITIALIZE UNITS


        //FINALIZE TILES
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

        return result;
    }

    public static Tile initializeTile(int sizeOfMap, int x, int y, String tileData) {
        String[] split = tileData.split("----");


        return null;
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
        String playerUsername = split[3];
        int health = Integer.parseInt(split[4]);
        int food = Integer.parseInt(split[5]);
        boolean isAnnexed = Boolean.parseBoolean(split[6]);
        int annexTime = Integer.parseInt(split[7]);
        BuildingList buildingList = new Gson().fromJson(split[8], BuildingList.class);
        int totalCount = Integer.parseInt(split[9]);
        int joblessCount = Integer.parseInt(split[10]);
        boolean hasGarrisoned = Boolean.parseBoolean(split[11]);

        City city = new City(name, Map.getInstance().getTileFromMap(x, y), findCivilization(playerUsername, GameMenuController.getInstance().getCivilizations()));
        city.setHealth(health);
        city.setFood(food);
        if (isAnnexed) city.annex();
        city.setAnnexTime(annexTime);
        city.setBuildingList(buildingList);
        city.setTotalCitizenCount(totalCount);
        city.setJoblessCitizenCount(joblessCount);
        if (hasGarrisoned) city.garrisonUnit();

        return city;
    }

    //INITIALIZE UNITS
    public static ArrayList<Unit> initializeUnits() {
        return null;
    }

    public static Unit initializeUnit() {
        return null;
    }

    //FINALIZE TILES
    public static void finalizeTiles() {
        
    }
    
    public static void finalizeTile() {
        
    }

    //
    //UTIL
    public static Civilization findCivilization(String playerUsername, ArrayList<Civilization> civilizations) {
        for (Civilization civilization : civilizations) {
            if (civilization.getPlayer().getUsername().equals(playerUsername)) return civilization;
        }

        return null;
    }
}
