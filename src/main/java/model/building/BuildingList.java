package model.building;

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

}
