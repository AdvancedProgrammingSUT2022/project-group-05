package model.BuildingList;


import model.building.Building;
import model.building.BuildingList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TestBuildingList {

    BuildingList buildingList;


    @BeforeEach
    public void setup() {
        buildingList = new BuildingList();
        buildingList.addBuilding(Building.ARMORY);
        buildingList.addBuilding(Building.BANK);
    }


    @Test
    public void testSave() {
        buildingList.save();
    }

    @Test
    public void testLoad() {
        BuildingList loadedBuildingList = BuildingList.load();
        System.out.println(loadedBuildingList.getListOfBuildings());

    }


}
