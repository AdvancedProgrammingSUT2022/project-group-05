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
    public void testConvertToString() {

        String result = buildingList.convertToJson();
        System.out.println(result);

    }

    @Test
    public void testConvertFromJson() {

        BuildingList newBuildingList = buildingList.convertFromJson("{\"listOfBuildings\":{\"ARSENAL\":0,\"MONUMENT\":0,\"UNIVERSITY\":0,\"MONASTERY\":0,\"COLOSSEUM\":0,\"WORKSHOP\":0,\"MINT\":0,\"MILITARY_ACADEMY\":0,\"GRANARY\":0,\"OPERA_HOUSE\":0,\"CIRCUS\":0,\"TEMPLE\":0,\"MILITARY_BASE\":0,\"FORGE\":0,\"LIBRARY\":0,\"BURIAL_TOMB\":0,\"BANK\":1,\"HOSPITAL\":0,\"THEATER\":0,\"WINDMILL\":0,\"STOCK_EXCHANGE\":0,\"ARMORY\":1,\"WALLS\":0,\"CASTLE\":0,\"BROADCAST_TOWER\":0,\"FACTORY\":0,\"COURTHOUSE\":0,\"GARDEN\":0,\"PUBLIC_SCHOOL\":0,\"BARRACKS\":0,\"MUSEUM\":0,\"SATRAP_COURT\":0,\"MARKET\":0,\"WATER_MILL\":0,\"STABLE\":0}}");
        System.out.println(newBuildingList.hasBuilding(Building.BARRACKS));

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
