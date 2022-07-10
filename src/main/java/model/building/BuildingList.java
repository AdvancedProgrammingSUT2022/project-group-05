package model.building;

import com.google.gson.Gson;
import javafx.scene.layout.BorderPane;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class BuildingList {
    private HashMap<Building, Integer> listOfBuildings; // Integer could be 0 or 1

    public BuildingList() {
        this.listOfBuildings = new HashMap<>();
        for (Building building : Building.values()) {
            listOfBuildings.put(building, 0);
        }
    }

    public void addBuilding(Building building) {
        for (int i = 0; i < listOfBuildings.size(); i++) {
            if (this.hasBuilding(building)) {
                // error : It is already built
            } else {
                listOfBuildings.replace(building, 1);
            }
        }
    }

    public void removeBuilding(Building building) {
        if (!this.hasBuilding(building)) {
            //error : no such building exists
        } else {
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

    public HashMap<Building, Integer> getListOfBuildings() {
        return listOfBuildings;
    }

    public String convertToJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static BuildingList convertFromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, BuildingList.class);
    }

    public void save() {
        try {
            FileWriter fileWriter = new FileWriter("database/BuildingList.json");
            fileWriter.write(this.convertToJson());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static BuildingList load() {
        try {
            String json = new String(Files.readAllBytes(Paths.get("database/BuildingList.json")));
            return convertFromJson(json);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
