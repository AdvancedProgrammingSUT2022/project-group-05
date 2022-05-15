package controller;


import model.game.City;
import model.game.Civilization;
import model.improvement.Improvement;
import model.research.Research;
import model.research.ResearchTree;
import model.tile.Feature;
import model.tile.Route;
import model.tile.Terrain;
import model.tile.Tile;
import model.unit.Unit;
import model.unit.UnitState;
import model.unit.civilian.Settler;
import model.unit.civilian.Worker;
import model.unit.soldier.melee.Warrior;
import model.unit.soldier.ranged.siege.Artillery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TestUnitController {

    Unit unit;
    UnitController unitController;

    @Mock
    Civilization civilization;

    @Mock
    Tile tile;

    @Mock
    City city;

    @Mock
    Improvement improvement;

    @Mock
    Terrain terrain;

    @Mock
    Feature feature;

    @Mock
    ResearchTree researchTree;

    @Mock
    Route route;

    @BeforeEach
    public void setup() {
        unit = new Warrior(civilization, tile);
        UnitController.updateInstance(unit);
        unitController = UnitController.getInstance();
    }

    @Test
    public void testApplyUnitStateForNewTurn() {
        unit.fortify();
        unitController.applyUnitStateForNewTurn();
        int result = unit.getTemporaryDefenceBonusPercentage();
        Assertions.assertEquals(50, result);
        unit.recovering();
        unitController.applyUnitStateForNewTurn();
        Assertions.assertEquals(UnitState.AWAKE, unit.getUnitState());
    }

    @Test
    public void testUnitSleep() {
        unitController.unitSleep();
        Assertions.assertEquals(UnitState.ASLEEP, unit.getUnitState());
    }

    @Test
    public void testUnitAlert() {
        unitController.unitAlert();
        Assertions.assertEquals(UnitState.ALERTED, unit.getUnitState());
    }

    @Test
    public void testUnitFortify() {
        unitController.unitFortify();
        Assertions.assertEquals(UnitState.FORTIFY, unit.getUnitState());
        Assertions.assertEquals(25, unit.getTemporaryDefenceBonusPercentage());
    }

    @Test
    public void testUnitRecover() {
        unitController.unitRecover();
        Assertions.assertEquals(UnitState.RECOVERING, unit.getUnitState());
    }

    @Test
    public void testUnitGarrison() {
        Mockito.when(tile.hasCity()).thenReturn(true);
        Mockito.when(tile.getCity()).thenReturn(city);
        Mockito.when(city.getCivilization()).thenReturn(civilization);
        Mockito.when(city.hasGarrisonedUnit()).thenReturn(false);
        unitController.unitGarrison();
        Assertions.assertEquals(UnitState.GARRISONED, unit.getUnitState());
    }

    @Test
    public void testUnitSetupRanged() {
        unit = new Artillery(civilization, tile);
        UnitController.updateInstance(unit);
        unitController = UnitController.getInstance();
        unitController.unitSetupRanged();
        Assertions.assertEquals(UnitState.SET_FOR_SIEGE, unit.getUnitState());
    }

    @Test
    public void testUnitWake() {
        unitController.unitWake();
        Assertions.assertEquals(UnitState.AWAKE, unit.getUnitState());
    }

    @Test
    public void testUnitDelete() {
        unitController.unitDelete();
        Assertions.assertNull(UnitController.getInstance().getUnit());
    }

//    @Test
//    public void testUnitFoundCity() {
//        unit = new Settler(civilization, tile);
//        UnitController.updateInstance(unit);
//        unitController = UnitController.getInstance();
//        unitController.unitFoundCity("Tehran");
//        Mockito.verify(civilization).removeUnit(unit);
//    }

    @Test
    public void testUnitBuildImprovement() {
        unit = new Worker(civilization, tile);
        UnitController.updateInstance(unit);
        unitController = UnitController.getInstance();
        Mockito.when(tile.getCivilization()).thenReturn(civilization);
        Mockito.when(tile.hasRoute()).thenReturn(false);
        Mockito.when(tile.getTerrain()).thenReturn(terrain);
        Mockito.when(improvement.matchesTerrain(unit.getTile().getTerrain())).thenReturn(true);
        Mockito.when(civilization.getResearchTree()).thenReturn(researchTree);
        Mockito.when(improvement.getNeededResearch()).thenReturn(null);
        Mockito.when(researchTree.isResearchDone(improvement.getNeededResearch())).thenReturn(true);
        unitController.unitBuildImprovement(improvement);
        Assertions.assertEquals(UnitState.WORKING, unit.getUnitState());
    }

    @Test
    public void testUnitBuildRoute() {
        unit = new Worker(civilization, tile);
        UnitController.updateInstance(unit);
        unitController = UnitController.getInstance();
        Mockito.when(tile.getCivilization()).thenReturn(civilization);
        Mockito.when(tile.hasRoute()).thenReturn(false);
        Mockito.when(civilization.getResearchTree()).thenReturn(researchTree);
        Mockito.when(researchTree.isResearchDone(route.getNeededResearch())).thenReturn(true);
        Mockito.when(route.getNeededResearch()).thenReturn(null);
        unitController.unitBuildRoute(route);
        Assertions.assertEquals(UnitState.WORKING, unit.getUnitState());

    }

    @Test
    public void testUnitRemoveForest() {
        unit = new Worker(civilization, tile);
        UnitController.updateInstance(unit);
        unitController = UnitController.getInstance();
        Mockito.when(tile.getCivilization()).thenReturn(civilization);
        Mockito.when(tile.getFeature()).thenReturn(Feature.FOREST);
        Mockito.when(tile.hasImprovement()).thenReturn(false);
        Mockito.when(civilization.getResearchTree()).thenReturn(researchTree);
        Mockito.when(researchTree.isResearchDone(Research.BRONZE_WORKING)).thenReturn(true);
        unitController.unitRemoveForest();
        Assertions.assertEquals(UnitState.WORKING, unit.getUnitState());
    }

    @Test
    public void testUnitRemoveJungle() {
        unit = new Worker(civilization, tile);
        UnitController.updateInstance(unit);
        unitController = UnitController.getInstance();
        Mockito.when(tile.getCivilization()).thenReturn(civilization);
        Mockito.when(tile.getFeature()).thenReturn(Feature.JUNGLE);
        Mockito.when(tile.hasImprovement()).thenReturn(false);
        Mockito.when(civilization.getResearchTree()).thenReturn(researchTree);
        Mockito.when(researchTree.isResearchDone(Research.BRONZE_WORKING)).thenReturn(true);
        unitController.unitRemoveJungle();
        Assertions.assertEquals(UnitState.WORKING, unit.getUnitState());
    }

    @Test
    public void testUnitRemoveMarsh() {
        unit = new Worker(civilization, tile);
        UnitController.updateInstance(unit);
        unitController = UnitController.getInstance();
        Mockito.when(tile.getCivilization()).thenReturn(civilization);
        Mockito.when(tile.getFeature()).thenReturn(Feature.MARSH);
        Mockito.when(tile.hasImprovement()).thenReturn(false);
        Mockito.when(civilization.getResearchTree()).thenReturn(researchTree);
        Mockito.when(researchTree.isResearchDone(Research.MASONRY)).thenReturn(true);
        unitController.unitRemoveMarsh();
        Assertions.assertEquals(UnitState.WORKING, unit.getUnitState());
    }

    @Test
    public void testUnitRemoveRoute() {
        unit = new Worker(civilization, tile);
        UnitController.updateInstance(unit);
        unitController = UnitController.getInstance();
        Mockito.when(tile.getCivilization()).thenReturn(civilization);
        Mockito.when(tile.hasRoute()).thenReturn(true);
        unitController.unitRemoveRoute();
        Assertions.assertEquals(UnitState.WORKING, unit.getUnitState());
    }

    @Test
    public void testUnitRemoveImprovement() {
        unit = new Worker(civilization, tile);
        UnitController.updateInstance(unit);
        unitController = UnitController.getInstance();
        Mockito.when(tile.getCivilization()).thenReturn(civilization);
        Mockito.when(tile.hasImprovement()).thenReturn(true);
        unitController.unitRemoveImprovement();
        Assertions.assertEquals(UnitState.WORKING, unit.getUnitState());
    }

    @Test
    public void testUnitRepair() {
        unit = new Worker(civilization, tile);
        UnitController.updateInstance(unit);
        unitController = UnitController.getInstance();
        Mockito.when(tile.getCivilization()).thenReturn(civilization);
        Mockito.when(tile.isRepaired()).thenReturn(false);
        unitController.unitRepair();
        Assertions.assertEquals(UnitState.WORKING, unit.getUnitState());
    }

    @Test
    public void testGetUnit() {
        Unit result = unitController.getUnit();
        Assertions.assertEquals(unit, result);
    }






}