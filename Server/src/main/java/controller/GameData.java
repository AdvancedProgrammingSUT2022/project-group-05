package controller;

import com.google.gson.Gson;
import model.User;
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

        return city.getName() + "----" +
                city.getCenter().getXPlace() + "----" +
                city.getCenter().getYPlace() + "----" +
                city.getCivilization().getPlayer().getUsername() + "----" +
                city.isAnnexed() + "----" +
                new Gson().toJson(city.getBuildingList()) + "----" +
                new Gson().toJson(city.getProductionInProgress()) + "\n";
    }

    public static String writeTile(Tile tile) {

        return tile.getRoute().ordinal() + "----" +
                tile.getTerrain().ordinal() + "----" +
                tile.getFeature().ordinal() + "----" +
                tile.getResource().ordinal() + "----" +
                tile.getImprovement().ordinal() + "----" +
                new Gson().toJson(tile.getProjectManager()) + "----" +
                tile.getCity().getName() + "----" +
                tile.getSoldier().toString() + "----" +
                tile.getSoldier().getUnitState().ordinal() + "----" +
                tile.getSoldier().getCivilization().getPlayer().getUsername() + "----" +
                tile.getCivilian().toString() + "----" +
                tile.getCivilian().getUnitState().ordinal() + "----" +
                tile.getCivilian().getCivilization().getPlayer().getUsername() + "\n";
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

        //INITIALIZE MAP
        ArrayList<Tile> tiles = initializeMap(sizeOfMap);

        //INITIALIZE CIVILIZATIONS
        ArrayList<Civilization> civilizations = initializeCivilizations(civilizationCount, civilizationData);

        //INITIALIZE CITIES

    }

    public static ArrayList<Tile> initializeMap(int sizeOfMap) {
        ArrayList<Tile> result = new ArrayList<>();

        for (int i = 0; i < sizeOfMap; i++) {
            for (int j = 0; j < sizeOfMap; j++) {
                result.add(new Tile(i, j, sizeOfMap));
            }
        }

        Map.updateInstance(sizeOfMap, result);
        return result;
    }

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
    
    public static void finalizeMap() {
        
    }
    
    public static void finalizeTile() {
        
    }
}
