package model.game;


import model.map.Map;
import model.tile.Tile;
import model.unit.Unit;
import model.unit.civilian.Civilian;
import model.unit.soldier.Soldier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TestCity {

    City city;



    @Mock
    Civilization civilization;

    @Mock
    Unit unit;

    @Mock
    Tile tile;

    @Mock
    Soldier soldier;

    @Mock
    Civilian civilian;


    @BeforeEach
    public void setup() {
        Map.updateInstance(10);
        city = new City("Tehran", tile, civilization);
    }

//    @Test
//    public void testAddUnitToQueue() {
//        city.addUnitToQueue(unit);
//        Unit result = city.getUnitFromQueue(unit);
//        Assertions.assertEquals(unit, result);
//    }

    @Test
    public void testHasJoblessCitizen() {
        city.setJoblessCitizenCount(10);
        boolean result = city.hasJoblessCitizen();
        Assertions.assertTrue(result);
    }

    @Test
    public void testIsInTerritory() {
        city.addTile(tile);
        boolean result = city.isInTerritory(tile);
        Assertions.assertTrue(result);
    }

    @Test
    public void testAssignCitizenToTile() {
        city.setJoblessCitizenCount(10);
        city.assignCitizenToTile(tile);
        int result = city.getJoblessCitizenCount();
        Assertions.assertEquals(9, result);
    }

    @Test
    public void testRemoveCitizenFromTile() {
        city.setJoblessCitizenCount(10);
        city.removeCitizenFromTile(tile);
        int result = city.getJoblessCitizenCount();
        Assertions.assertEquals(11, result);
    }

    @Test
    public void testCanAddTile() {
        Mockito.when(tile.hasCity()).thenReturn(true);
        boolean result = city.canAddTile(tile);
        Assertions.assertFalse(result);
    }

    @Test
    public void testGarrisonUnit() {
        city.garrisonUnit(soldier);
        boolean result = city.hasGarrisonedUnit();
        Assertions.assertTrue(result);
        int result2 = city.getDefenceBonusPercentage();
        Assertions.assertEquals(33, result2);
    }

    @Test
    public void testRemovedGarrisonedUnit() {
        city.garrisonUnit(soldier);
        city.removeGarrisonedUnit();
        boolean result = city.hasGarrisonedUnit();
        Assertions.assertFalse(result);
        int result2 = city.getDefenceBonusPercentage();
        Assertions.assertEquals(0, result2);
    }

    @Test
    public void testStayCivilianUnitInCity() {
        city.stayCivilianUnitInCity(civilian);
        boolean result = city.hasCivilianUnit();
        Assertions.assertTrue(result);
    }

    @Test
    public void testApplyNewTurnChanges() {
        Mockito.when(tile.getFood()).thenReturn(0);
        city.setHealth(5);
        city.applyNewTurnChanges();
        int result = city.getFood();
        Assertions.assertEquals(0, result);
        int health = city.getHealth();
        Assertions.assertEquals(6, health);
    }

//    @Test
//    public void testGetUnitInProgress() {
//        city.setUnitInProgress(unit);
//        Unit result = city.getUnitInProgress();
//        Assertions.assertEquals(unit, result);
//    }

//    @Test
//    public void testGetRemainingProductionTime() {
//        city.setUnitInProgress(unit);
//        Mockito.when(unit.getCost()).thenReturn(10);
//        Mockito.when(civilization.getProduction()).thenReturn(5);
//        int result = city.getRemainingProductionTime();
//        Assertions.assertEquals(2, result);
//    }

    @Test
    public void testGetTotalCitizenCount() {
        int result = city.getTotalCitizenCount();
        Assertions.assertEquals(1, result);
    }

    @Test
    public void testGetCenter() {
        Tile center = city.getCenter();
        boolean result = (center == tile);
        Assertions.assertTrue(result);
    }

//    @Test
//    public void testGetTiles() {
//        ArrayList<Tile> result = city.getTiles();
//        Assertions.assertEquals();
//    }

    @Test
    public void testGetCivilization() {
        Civilization result = city.getCivilization();
        Assertions.assertEquals(civilization, result);
    }

    @Test
    public void testGetDefenceStrength() {
        city.setDefenceBonusPercentage(100);
        city.setDefenceStrength(100);
        int result = city.getDefenceStrength();
        Assertions.assertEquals(200, result);
    }

    @Test
    public void testAddCitizen() {
        city.setJoblessCitizenCount(10);
        city.addCitizen();
        int result = city.getTotalCitizenCount();
        Assertions.assertEquals(2, result);
        int result2 = city.getJoblessCitizenCount();
        Assertions.assertEquals(11, result2);
    }

    @Test
    public void testRemoveCitizen() {
        city.setJoblessCitizenCount(10);
        city.removeCitizen();
        int result = city.getJoblessCitizenCount();
        Assertions.assertEquals(9, result);
    }

    @Test
    public void testSetCivilization() {
        city.setCivilization(civilization);
        Civilization result = city.getCivilization();
        Assertions.assertEquals(civilization, result);
    }

    @Test
    public void testGetName() {
        String result = city.getName();
        Assertions.assertEquals("Tehran", result);
    }

//    @Test
//    public void testSpendProductionForUnitInProgress() {
//        city.setUnitInProgress(unit);
//        Mockito.when(unit.getCost()).thenReturn(10);
//        Mockito.when(civilization.getProduction()).thenReturn(4);
//        //Mockito.verify(unit).setCost(6);
//    }

//    @Test
//    public void testRemoveUnitFromQueue() {
//        city.addUnitToQueue(unit);
//        city.removeUnitFromQueue(unit);
//        boolean result = city.getUnitFromQueue(unit) == null;
//        Assertions.assertTrue(result);
//    }


}
