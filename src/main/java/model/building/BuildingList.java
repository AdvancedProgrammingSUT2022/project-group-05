package model.building;

import com.google.gson.Gson;
import javafx.scene.layout.BorderPane;
import model.game.City;
import model.game.Civilization;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class BuildingList implements Serializable {
    private HashMap<Building, Integer> listOfBuildings; // Integer could be 0 or 1

    public BuildingList() {
        this.listOfBuildings = new HashMap<>();
        for (Building building : Building.values()) {
            listOfBuildings.put(building, 0);
        }
    }

    public void addBuilding(Building building, City city) {
        for (int i = 0; i < listOfBuildings.size(); i++) {
            if (this.hasBuilding(building)) {
                // error : It is already built
            } else {
                // building boosts
                switch (building) {
                    case BARRACKS:
                        break;
                    case GRANARY:
                        city.setFood(city.getFood() + 2);
                        break;
                    case LIBRARY:
                        city.getCivilization().setResearchPoint(city.getCivilization().getResearchPoint() + (city.getTotalCitizenCount() / 2));
                        break;
                    case MONUMENT:
                        break;
                    case WALLS:
                        city.setDefenceStrength(city.getDefenceStrength() + 5);
                        break;
                    case WATER_MILL:
                        city.setFood(city.getFood() + 2);
                        break;
                    case ARMORY:
                        break;
                    case BURIAL_TOMB:
                        city.getCivilization().setHappiness(city.getCivilization().getHappiness() + 2);
                        break;
                    case CIRCUS:
                        city.getCivilization().setHappiness(city.getCivilization().getHappiness() + 3);
                        break;
                    case COLOSSEUM:
                        city.getCivilization().setHappiness(city.getCivilization().getHappiness() + 4);
                        break;
                    case COURTHOUSE:
                        if (city.getCivilization().getHappiness() < 0)
                            city.getCivilization().setHappiness(0);
                        break;
                    case STABLE:
                        break;
                    case TEMPLE:
                        break;
                    case CASTLE:
                        city.setDefenceStrength(city.getDefenceStrength() + 7);
                        break;
                    case FORGE:
                        break;
                    case GARDEN:
                        break;
                    case MARKET:
                        city.getCivilization().setGold(city.getCivilization().getGold() + city.getCivilization().getGold() / 4);
                        break;
                    case MINT:
                        break;
                    case MONASTERY:
                        break;
                    case UNIVERSITY:
                        city.getCivilization().setResearchPoint(city.getCivilization().getResearchPoint() + city.getCivilization().getResearchPoint() / 2);
                        break;
                    case WORKSHOP:
                        break;
                    case BANK:
                        city.getCivilization().setGold(city.getCivilization().getGold() + city.getCivilization().getGold() / 4);
                        break;
                    case MILITARY_ACADEMY:
                        break;
                    case OPERA_HOUSE:
                        break;
                    case MUSEUM:
                        break;
                    case PUBLIC_SCHOOL:
                        city.getCivilization().setResearchPoint(city.getCivilization().getResearchPoint() + city.getCivilization().getResearchPoint() / 2);
                        break;
                    case SATRAP_COURT:
                        city.getCivilization().setGold(city.getCivilization().getGold() + city.getCivilization().getGold() / 4);
                        city.getCivilization().setHappiness(city.getCivilization().getHappiness() + 2);
                        break;
                    case THEATER:
                        city.getCivilization().setHappiness(city.getCivilization().getHappiness() + 4);
                        break;
                    case WINDMILL:
                        break;
                    case ARSENAL:
                        break;
                    case BROADCAST_TOWER:
                        break;
                    case FACTORY:
                        break;
                    case HOSPITAL:
                        break;
                    case MILITARY_BASE:
                        city.setDefenceStrength(city.getDefenceStrength() + 12);
                        break;
                    case STOCK_EXCHANGE:
                        city.getCivilization().setGold(city.getCivilization().getGold() + city.getCivilization().getGold() / 3);
                        break;
                }
                listOfBuildings.replace(building, 1);
            }
        }
    }

    public void removeBuilding(Building building, City city) {
        if (!this.hasBuilding(building)) {
            //error : no such building exists
        } else {
            //removing boosts
            switch (building) {
                case BARRACKS:
                    break;
                case GRANARY:
                    city.setFood(city.getFood() - 2);
                    break;
                case LIBRARY:
                    city.getCivilization().setResearchPoint(city.getCivilization().getResearchPoint() - (city.getTotalCitizenCount() / 2));
                    break;
                case MONUMENT:
                    break;
                case WALLS:
                    city.setDefenceStrength(city.getDefenceStrength() - 5);
                    break;
                case WATER_MILL:
                    city.setFood(city.getFood() - 2);
                    break;
                case ARMORY:
                    break;
                case BURIAL_TOMB:
                    city.getCivilization().setHappiness(city.getCivilization().getHappiness() - 2);
                    break;
                case CIRCUS:
                    city.getCivilization().setHappiness(city.getCivilization().getHappiness() - 3);
                    break;
                case COLOSSEUM:
                    city.getCivilization().setHappiness(city.getCivilization().getHappiness() - 4);
                    break;
                case COURTHOUSE:
                    break;
                case STABLE:
                    break;
                case TEMPLE:
                    break;
                case CASTLE:
                    city.setDefenceStrength(city.getDefenceStrength() - 7);
                    break;
                case FORGE:
                    break;
                case GARDEN:
                    break;
                case MARKET:
                    city.getCivilization().setGold(city.getCivilization().getGold() - city.getCivilization().getGold() / 5);
                    break;
                case MINT:
                    break;
                case MONASTERY:
                    break;
                case UNIVERSITY:
                    city.getCivilization().setResearchPoint(city.getCivilization().getResearchPoint() - city.getCivilization().getResearchPoint() / 3);
                    break;
                case WORKSHOP:
                    break;
                case BANK:
                    city.getCivilization().setGold(city.getCivilization().getGold() - city.getCivilization().getGold() / 5);
                    break;
                case MILITARY_ACADEMY:
                    break;
                case OPERA_HOUSE:
                    break;
                case MUSEUM:
                    break;
                case PUBLIC_SCHOOL:
                    city.getCivilization().setResearchPoint(city.getCivilization().getResearchPoint() - city.getCivilization().getResearchPoint() / 3);
                    break;
                case SATRAP_COURT:
                    city.getCivilization().setGold(city.getCivilization().getGold() - city.getCivilization().getGold() / 5);
                    city.getCivilization().setHappiness(city.getCivilization().getHappiness() - 2);
                    break;
                case THEATER:
                    city.getCivilization().setHappiness(city.getCivilization().getHappiness() - 4);
                    break;
                case WINDMILL:
                    break;
                case ARSENAL:
                    break;
                case BROADCAST_TOWER:
                    break;
                case FACTORY:
                    break;
                case HOSPITAL:
                    break;
                case MILITARY_BASE:
                    city.setDefenceStrength(city.getDefenceStrength() - 12);
                    break;
                case STOCK_EXCHANGE:
                    city.getCivilization().setGold(city.getCivilization().getGold() - city.getCivilization().getGold() / 4);
                    break;
            }
            listOfBuildings.replace(building, 0);
        }
    }

    public boolean hasBuilding(Building building) {
        if (listOfBuildings.get(building) == 0) {
            return false;
        }
        return true;
    }

    public boolean hasBuildings(ArrayList<Building> buildings) {
        for (int i = 0; i < buildings.size(); i++) {
            if (listOfBuildings.get(buildings.get(i)) == 0) {
                return false;
            }
        }
        return true;
    }

    public boolean canBuild(Building building, City city) {
        Civilization civilization = city.getCivilization();
        if (!civilization.getResearchTree().isResearchDone(building.getResearchRequired())) {
            return false;
        } else if (!civilization.getResourceList().hasEnough(building.getResourceNeeded(), 1)) {
            return false;
        } else {
            ArrayList<Building> buildings = building.getBuildingsNeeded();
            for (int i = 0; i < buildings.size(); i++) {
                if (!city.getBuildingList().hasBuilding(buildings.get(i))) {
                    return false;
                }
            }
            return true;
        }
    }

    public HashMap<Building, Integer> getListOfBuildings() {
        return listOfBuildings;
    }

    public void save() {
        try {
            FileWriter fileWriter = new FileWriter("saves/BuildingList.json");
            Gson gson = new Gson();
            String data = gson.toJson(this);
            fileWriter.write(data);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static BuildingList load() {
        try {
            String json = new String(Files.readAllBytes(Paths.get("saves/BuildingList.json")));
            Gson gson = new Gson();
            return gson.fromJson(json, BuildingList.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
