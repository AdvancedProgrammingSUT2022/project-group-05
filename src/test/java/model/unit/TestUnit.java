package model.unit;

import model.game.City;
import model.game.Civilization;
import model.map.Map;
import model.research.Research;
import model.resource.Resource;
import model.tile.Tile;
import model.unit.civilian.Worker;
import model.unit.soldier.melee.Warrior;
import model.unit.soldier.ranged.siege.Catapult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class TestUnit{

    Unit unit;

    @Mock
    Civilization civilization;

    @Mock
    Tile tile;



    @Mock
    City city;

    @BeforeEach
    public void initialize(){
        unit = new Warrior(civilization, tile);
    }


    @Test
    public void testSleep() {
        unit.sleep();
        UnitState result = unit.getUnitState();
        Assertions.assertEquals(UnitState.ASLEEP, result);
    }

    @Test
    public void testWake() {
        unit.wake();
        UnitState result = unit.getUnitState();
        Assertions.assertEquals(UnitState.AWAKE, result);
    }

    @Test
    public void testAlert() {
        unit.alert();
        UnitState result = unit.getUnitState();
        Assertions.assertEquals(UnitState.ALERTED, result);
    }

    @Test
    public void testFortify() {
        unit.fortify();
        UnitState result = unit.getUnitState();
        Assertions.assertEquals(UnitState.FORTIFY, result);
    }

    @Test
    public void testRecovering() {
        unit.recovering();
        UnitState result = unit.getUnitState();
        Assertions.assertEquals(UnitState.RECOVERING, result);
    }

    @Test
    public void testGarrison() {
        unit.garrison();
        UnitState result = unit.getUnitState();
        Assertions.assertEquals(UnitState.GARRISONED, result);
    }

    @Test
    public void testSetup() {
        unit = new Catapult(civilization, tile);
        unit.setup();
        Assertions.assertEquals(UnitState.SET_FOR_SIEGE, unit.getUnitState());
    }


//    @Test
//    public void testKillWithGold() {
//        unit.killWithGold();
//        Mockito.when(civilization.getGold()).thenReturn(10);
//        Mockito.verify(civilization).setGold();
//    }
    @Test
    public void testGetAttackStrength() {
        int result = unit.getAttackStrength();
        Assertions.assertEquals(6, result);
    }

    @Test
    public void testInitializeRemainingMovement() {
        unit.initializeRemainingMovement();
        int result = unit.getRemainingMovement();
        Assertions.assertEquals(2, result);
    }

    @Test
    public void testIsInFriendlyTile() {
        Mockito.when(tile.getCivilization()).thenReturn(civilization);
        boolean result = unit.isInFriendlyTile();
        Assertions.assertTrue(result);
    }

    @Test
    public void testHeal() {
        unit.setHealth(5);
        unit.setHealingSpeed(1);
        unit.heal();
        int result = unit.getHealth();
        Assertions.assertEquals(6, result);
    }

//    @Test //TODO how to mock singleton class???
//    public void testIsTileInRange() {
//
//        boolean result = unit.isTileInRange(tile);
//        Assertions.assertTrue(result);
//    }

    @Test
    public void testSetTile() {
        unit.setTile(tile);
        Tile result = unit.getTile();
        Assertions.assertEquals(tile, result);
    }

    @Test
    public void testSetRemainingMovement() {
        unit.setRemainingMovement(10);
        int result = unit.getRemainingMovement();
        Assertions.assertEquals(10, result);
    }

    @Test
    public void testSetHealth() {
        unit.setHealth(7);
        int result = unit.getHealth();
        Assertions.assertEquals(7, result);
    }

    @Test
    public void testSetTemporaryDefenceBonusPercentage() {
        unit.setTemporaryDefenceBonusPercentage(123);
        int result = unit.getTemporaryDefenceBonusPercentage();
        Assertions.assertEquals(123, result);
    }

    @Test
    public void testSetHealingSpeed() {
        unit.setHealingSpeed(3);
        unit.setHealth(1);
        unit.heal();
        int result = unit.getHealth();
        Assertions.assertEquals(4, result);
    }

    @Test
    public void testSetCost() {
        unit.setCost(123);
        int result = unit.getCost();
        Assertions.assertEquals(123, result);
    }

    @Test
    public void testSetStartingCity() {
        unit.setStartingCity(city);
        City result = unit.getStartingCity();
        Assertions.assertEquals(city, result);
    }

    @Test
    public void testGetCivilization() {
        Civilization result = unit.getCivilization();
        Assertions.assertEquals(civilization, result);
    }

    @Test
    public void testGetUnitState() {
        unit.sleep();
        Assertions.assertEquals(UnitState.ASLEEP, unit.getUnitState());
    }

    @Test
    public void testGetTotalMeleeStrength() {
        Mockito.when(tile.getCombatBoost()).thenReturn(100);
        unit.meleeStrength = 100;
        unit.setTemporaryDefenceBonusPercentage(100);
        int result = unit.getTotalMeleeStrength();
        Assertions.assertEquals(300, result);
    }

//    @Test
//    public void testKill() {
//        unit.kill();
//
//    }
    @Test
    public void testGetHealingBonus() {
        Mockito.when(tile.hasCity()).thenReturn(true);
        Mockito.when(tile.getCity()).thenReturn(city);
        Mockito.when(city.getCivilization()).thenReturn(civilization);
        Mockito.when(city.getCenter()).thenReturn(tile);
        int result = unit.getHealingBonus();
        Assertions.assertEquals(3, result);
    }

    @Test
    public void testGetRequiredResearch() {
        unit = new Catapult(civilization, tile);
        Research result = unit.getRequiredResearch();
        Assertions.assertEquals(Research.MATHEMATICS, result);
    }

    @Test
    public void testGetRequiredResource() {
        unit = new Catapult(civilization, tile);
        Resource result = unit.getRequiredResource();
        Assertions.assertEquals(Resource.IRON, result);
    }

    @Test
    public void testHasTask() {
        unit = new Worker(civilization, tile);
        Mockito.when(tile.hasProject()).thenReturn(true);
        boolean result = unit.hasTask();
        Assertions.assertFalse(result);
    }

    @Test
    public void testSetMaxMovement() {
        unit.setMaxMovement(123);
        int result = unit.getRemainingMovement();
        Assertions.assertEquals(123, result);
    }

    @Test
    public void testSetMeleeStrength() {
        unit.setMeleeStrength(123);
        int result = unit.meleeStrength;
        Assertions.assertEquals(123, result);
    }

    @Test
    public void testSetRangedStrength() {
        unit.setRangedStrength(123);
        int result = unit.rangedStrength;
        Assertions.assertEquals(123, result);
    }

}
