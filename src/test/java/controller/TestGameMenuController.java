package controller;

import model.User;
import model.game.City;
import model.game.Civilization;
import model.map.Map;
import model.tile.Tile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static view.enums.Entity.*;

public class TestGameMenuController{
    GameMenuController gameMenuController;

    HashMap<String, String> command;
    Civilization civilization1;
    Civilization civilization2;

    Tile tile1;
    Tile tile2;

    City city1;
    City city2;

    @BeforeEach
    public void setUp() {
        command = new HashMap<>();
        command.put(USERNAME.getKey(), "a");
        command.put(AMOUNT.getKey(), "3");
        command.put(X_POSITION.getKey(), "0");
        command.put(Y_POSITION.getKey(), "0");
        command.put(CITY_NAME.getKey(), "Babylon");
        command.put(UNIT_NAME.getKey(), "scout");
        command.put(IMPROVEMENT.getKey(), "farm");
        command.put(ROUTE.getKey(), "road");
        command.put(TECHNOLOGY.getKey(), "pottery");

        Map.updateInstance(6);

        civilization1 = new Civilization(new User("a", "a", "a"), 0);
        tile1 = new Tile(0, 0, 6);
        city1 = new City("Babylon", tile1, civilization1);

        civilization2 = new Civilization(new User("b", "b", "b"), 1);
        tile2 = new Tile(5, 5, 6);
        city2 = new City("London", tile2, civilization2);

        GameMenuController.updateInstance(2, new ArrayList<>(List.of(civilization1, civilization2)));
        gameMenuController = GameMenuController.getInstance();
        gameMenuController.nextCivilization();
    }

    @Test
    public void nextCivilizationTest() {
        gameMenuController.nextCivilization();


    }

    @Test
    public void whoseTurnIsItTest() {
        gameMenuController.whoseTurnIsIt();
    }

    @Test
    public void infoResearchTest() {
        gameMenuController.infoResearch(command);
    }

    @Test
    public void infoUnitsTest() {
        gameMenuController.infoUnits(command);
    }

    @Test
    public void infoCitiesTest() {
        gameMenuController.infoCities(command);
    }

    @Test
    public void infoDiplomacyTest() {
        gameMenuController.infoDiplomacy(command);
    }

    @Test
    public void infoVictoryTest() {
        gameMenuController.infoVictory(command);
    }

    @Test
    public void infoDemographicsTest() {
        gameMenuController.infoDemographics(command);
    }

    @Test
    public void infoNotificationTest() {
        gameMenuController.infoNotification(command);
    }

    @Test
    public void infoMilitaryTest() {
        gameMenuController.infoMilitary(command);
    }

    @Test
    public void infoEconomicTest() {
        gameMenuController.infoEconomic(command);
    }

    @Test
    public void infoDiplomaticTest() {
        gameMenuController.infoDiplomatic(command);
    }

    @Test
    public void infoDealsTest() {
        gameMenuController.infoDeals(command);
    }

    @Test
    public void infoTileTest() {
        gameMenuController.infoTile(command);
    }

    @Test
    public void infoTileStatsTest() {
        gameMenuController.infoTileStats(command);
    }

    @Test
    public void infoTileProjectTest() {
        gameMenuController.infoTileProject(command);
    }

    //MENU EXIT
    @Test
    public void menuExitTest() {
        gameMenuController.menuExit(command);
    }

    //SELECT
    @Test
    public void selectUnitSoldierTest() {
        gameMenuController.selectUnitSoldier(command);
    }

    @Test
    public void selectUnitCivilianTest() {
        gameMenuController.selectUnitCivilian(command);
    }

    @Test
    public void selectCityPositionFail() {
        gameMenuController.selectCityPosition(command);
    }

    @Test
    public void selectCityPosition() {
        command.put(UNIT_NAME.getKey(), "settler");
        gameMenuController.spawnUnit(command);
        gameMenuController.selectUnitCivilian(command);
        gameMenuController.unitFoundCity(command);
        gameMenuController.selectCityPosition(command);
    }

    @Test
    public void selectCityNameFail() {
        gameMenuController.selectCityName(command);
    }

    @Test
    public void selectCityName() {
        command.put(UNIT_NAME.getKey(), "settler");
        gameMenuController.spawnUnit(command);
        gameMenuController.selectUnitCivilian(command);
        gameMenuController.unitFoundCity(command);
        gameMenuController.selectCityName(command);
    }

    //UNIT
    @Test
    public void unitMoveTestNotSelected() {
        gameMenuController.selectUnitCivilian(command);
        gameMenuController.unitMove(command);
    }
    @Test
    public void unitMoveTest() {
        command.put(UNIT_NAME.getKey(), "settler");
        gameMenuController.spawnUnit(command);
        gameMenuController.selectUnitCivilian(command);
        gameMenuController.unitMove(command);
    }

    @Test
    public void unitSleepTestNotSelected() {
        gameMenuController.selectUnitCivilian(command);
        gameMenuController.unitSleep(command);
    }

    @Test
    public void unitSleepTest() {
        command.put(UNIT_NAME.getKey(), "settler");
        gameMenuController.spawnUnit(command);
        gameMenuController.selectUnitCivilian(command);
        gameMenuController.unitSleep(command);
    }

    @Test
    public void unitAlertTestNotSelected() {
        gameMenuController.selectUnitCivilian(command);
        gameMenuController.unitAlert(command);
    }

    @Test
    public void unitAlertTest() {
        gameMenuController.spawnUnit(command);
        gameMenuController.selectUnitSoldier(command);
        gameMenuController.unitAlert(command);
    }

    @Test
    public void unitFortifyTestNotSelected() {
        gameMenuController.selectUnitCivilian(command);
        gameMenuController.unitFortify(command);
    }

    @Test
    public void unitFortifyTest() {
        gameMenuController.spawnUnit(command);
        gameMenuController.selectUnitSoldier(command);
        gameMenuController.unitFortify(command);
    }

    @Test
    public void unitRecoverTestNotSelected() {
        gameMenuController.selectUnitCivilian(command);
        gameMenuController.unitRecover(command);
    }

    @Test
    public void unitRecoverTest() {
        gameMenuController.spawnUnit(command);
        gameMenuController.selectUnitSoldier(command);
        gameMenuController.unitRecover(command);
    }

    @Test
    public void unitGarrisonTestNotSelected() {
        gameMenuController.selectUnitCivilian(command);
        gameMenuController.unitGarrison(command);
    }

    @Test
    public void unitGarrisonTest() {
        gameMenuController.spawnUnit(command);
        gameMenuController.selectUnitSoldier(command);
        gameMenuController.selectUnitCivilian(command);
        gameMenuController.unitGarrison(command);
    }

    @Test
    public void unitSetupRangedTest() {
        gameMenuController.selectUnitCivilian(command);
        gameMenuController.unitSetupRanged(command);
    }

    @Test
    public void unitAttackTest() {
        gameMenuController.spawnUnit(command);
        gameMenuController.selectUnitSoldier(command);
        gameMenuController.unitAttack(command);
    }

    @Test
    public void unitCancelTestNotSelected() {
        gameMenuController.selectUnitCivilian(command);
        gameMenuController.unitCancel(command);
    }

    @Test
    public void unitCancelTest() {
        gameMenuController.spawnUnit(command);
        gameMenuController.selectUnitSoldier(command);
        gameMenuController.unitCancel(command);
    }

    @Test
    public void unitWakeTestNotSelected() {
        gameMenuController.selectUnitCivilian(command);
        gameMenuController.unitWake(command);
    }

    @Test
    public void unitWakeTest() {
        gameMenuController.spawnUnit(command);
        gameMenuController.selectUnitSoldier(command);
        gameMenuController.unitWake(command);
    }

    @Test
    public void unitDeleteTestNotSelected() {
        gameMenuController.selectUnitCivilian(command);
        gameMenuController.unitDelete(command);
    }

    @Test
    public void unitDeleteTest() {
        gameMenuController.spawnUnit(command);
        gameMenuController.selectUnitSoldier(command);
        gameMenuController.unitDelete(command);
    }

    @Test
    public void unitFoundCityTestNotSelected() {
        gameMenuController.selectUnitCivilian(command);
        gameMenuController.unitFoundCity(command);
    }

    @Test
    public void unitFoundCityTest() {
        command.put(UNIT_NAME.getKey(), "settler");
        gameMenuController.spawnUnit(command);
        gameMenuController.selectUnitCivilian(command);
        gameMenuController.unitFoundCity(command);
    }

    //WORKER
    @Test
    public void unitBuildImprovementTestNotSelected() {
        gameMenuController.killCivilian(command);
        gameMenuController.selectUnitCivilian(command);
        gameMenuController.unitBuildImprovement(command);
    }

    @Test
    public void unitBuildImprovementTest() {
        gameMenuController.killCivilian(command);
        command.replace(UNIT_NAME.getKey(), "worker");
        gameMenuController.spawnUnit(command);
        gameMenuController.selectUnitCivilian(command);
        gameMenuController.unitBuildImprovement(command);
    }

    @Test
    public void unitBuildRouteTestNotSelected() {
        gameMenuController.killCivilian(command);
        gameMenuController.selectUnitCivilian(command);
        gameMenuController.unitBuildRoute(command);
    }

    @Test
    public void unitBuildRouteTest() {
        gameMenuController.killCivilian(command);
        command.replace(UNIT_NAME.getKey(), "worker");
        gameMenuController.spawnUnit(command);
        gameMenuController.selectUnitCivilian(command);
        gameMenuController.unitBuildRoute(command);
    }

    @Test
    public void unitRemoveJungleTestNotSelected() {
        gameMenuController.selectUnitCivilian(command);
        gameMenuController.unitRemoveJungle(command);
    }

    @Test
    public void unitRemoveJungleTest() {
        gameMenuController.killCivilian(command);
        command.replace(UNIT_NAME.getKey(), "worker");
        gameMenuController.spawnUnit(command);
        gameMenuController.selectUnitCivilian(command);
        gameMenuController.unitRemoveJungle(command);
    }

    @Test
    public void unitRemoveForestTestNotSelected() {
        gameMenuController.selectUnitCivilian(command);
        gameMenuController.unitRemoveForest(command);
    }

    @Test
    public void unitRemoveForestTest() {
        gameMenuController.killCivilian(command);
        command.replace(UNIT_NAME.getKey(), "worker");
        gameMenuController.spawnUnit(command);
        gameMenuController.selectUnitCivilian(command);
        gameMenuController.unitRemoveForest(command);
    }

    @Test
    public void unitRemoveMarchTest() {
        gameMenuController.killCivilian(command);
        command.replace(UNIT_NAME.getKey(), "worker");
        gameMenuController.spawnUnit(command);
        gameMenuController.selectUnitCivilian(command);
        gameMenuController.unitRemoveMarsh(command);
    }

    @Test
    public void unitRemoveImprovementTest() {
        gameMenuController.killCivilian(command);
        command.replace(UNIT_NAME.getKey(), "worker");
        gameMenuController.spawnUnit(command);
        gameMenuController.selectUnitCivilian(command);
        gameMenuController.unitRemoveImprovement(command);
    }

    @Test
    public void unitRemoveRouteTest() {
        gameMenuController.killCivilian(command);
        command.replace(UNIT_NAME.getKey(), "worker");
        gameMenuController.spawnUnit(command);
        gameMenuController.selectUnitCivilian(command);
        gameMenuController.unitRemoveRoute(command);
    }

    @Test
    public void unitRepairTest() {
        gameMenuController.killCivilian(command);
        command.replace(UNIT_NAME.getKey(), "worker");
        gameMenuController.spawnUnit(command);
        gameMenuController.selectUnitCivilian(command);
        gameMenuController.unitRepair(command);
    }

    //CITY
    @Test
    public void cityCreateUnit() {
        gameMenuController.selectUnitCivilian(command);
        gameMenuController.unitFoundCity(command);
        gameMenuController.selectCityName(command);
        gameMenuController.cityCreateUnit(command);
    }

    @Test
    public void cityCreateBuilding() {
        gameMenuController.selectUnitCivilian(command);
        gameMenuController.unitFoundCity(command);
        gameMenuController.selectCityName(command);
        gameMenuController.cityCreateBuilding(command);
    }

    @Test
    public void buyTileTest() {
        gameMenuController.selectUnitCivilian(command);
        gameMenuController.unitFoundCity(command);
        gameMenuController.selectCityName(command);
        gameMenuController.buyTile(command);
    }

    @Test
    public void purchaseUnitTest() {
        gameMenuController.selectUnitCivilian(command);
        gameMenuController.unitFoundCity(command);
        gameMenuController.selectCityName(command);
        gameMenuController.purchaseUnit(command);
    }

    @Test
    public void purchaseBuildingTest() {
        gameMenuController.selectUnitCivilian(command);
        gameMenuController.unitFoundCity(command);
        gameMenuController.selectCityName(command);
        gameMenuController.purchaseBuilding(command);
    }

    @Test
    public void cityAssignCitizenTest() {
        gameMenuController.selectUnitCivilian(command);
        gameMenuController.unitFoundCity(command);
        gameMenuController.selectCityName(command);
        gameMenuController.cityAssignCitizen(command);
    }

    @Test
    public void cityRemoveCitizenTest() {
        gameMenuController.selectUnitCivilian(command);
        gameMenuController.unitFoundCity(command);
        gameMenuController.selectCityName(command);
        gameMenuController.cityRemoveCitizen(command);
    }

    @Test
    public void cityShowTilesStatsTest() {
        gameMenuController.selectUnitCivilian(command);
        gameMenuController.unitFoundCity(command);
        gameMenuController.selectCityName(command);
        gameMenuController.cityShowTilesStats(command);
    }

    @Test
    public void mapShowAllTest() {
        gameMenuController.mapShowAll(command);
    }

    @Test
    public void mapShowCityTest() {
        gameMenuController.mapShowCity(command);
    }

    @Test
    public void mapShowPositionTest() {
        gameMenuController.mapShowPosition(command);
    }

    @Test
    public void mapMoveTest() {
        gameMenuController.mapMove(command);
    }

    @Test
    public void researchSetTest() {
        gameMenuController.researchSet(command);
    }

    @Test
    public void endOfTurnTest() {
        gameMenuController.endOfTurn(command);
    }

    @Test
    public void increaseTurn() {
        gameMenuController.increaseTurn(command);
    }

    @Test
    public void increaseGold() {
        gameMenuController.increaseGold(command);
    }

    @Test
    public void revealAllTest() {
        gameMenuController.revealAll(command);
    }

    @Test
    public void industrialRevolutionTest() {
        gameMenuController.industrialRevolution(command);
    }

    @Test
    public void welcomeToUtopiaTest() {
        gameMenuController.welcomeToUtopia(command);
    }

    @Test
    public void bigBrainTest() {
        gameMenuController.bigBrain(command);
    }

    @Test
    public void marcopoloTest() {
        gameMenuController.spawnUnit(command);
        gameMenuController.selectUnitSoldier(command);
        gameMenuController.marcopolo(command);
    }

    @Test
    public void terminatorTest() {
        gameMenuController.spawnUnit(command);
        gameMenuController.selectUnitSoldier(command);
        gameMenuController.terminator(command);
    }

    @Test
    public void instantHealTest() {
        gameMenuController.spawnUnit(command);
        gameMenuController.selectUnitSoldier(command);
        gameMenuController.instantHeal(command);
    }

    @Test
    public void killSoldierTest() {
        gameMenuController.spawnUnit(command);
        gameMenuController.selectUnitSoldier(command);
        gameMenuController.killSoldier(command);
    }

    @Test
    public void killCivilianTest() {
        gameMenuController.spawnUnit(command);
        gameMenuController.selectUnitSoldier(command);
        gameMenuController.killCivilian(command);
    }

    @Test
    public void spawnUnitTest() {
        gameMenuController.spawnUnit(command);
    }
}
