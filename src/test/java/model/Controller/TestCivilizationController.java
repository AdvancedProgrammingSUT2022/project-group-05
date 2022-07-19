package model.Controller;

import controller.CivilizationController;
import model.game.City;
import model.game.Civilization;
import model.research.ResearchTree;
import model.unit.Unit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class TestCivilizationController {
    @Mock
    Civilization civilizationMock;

    @Mock
    ResearchTree researchTreeMock;

    ArrayList<Unit> unitsMock = new ArrayList<>();
    ArrayList<City> citiesMock = new ArrayList<>();

    @Mock
    Unit unit;

    @Mock
    City city;

    @Test
    public void constructorTest () {
        CivilizationController civilizationController = new CivilizationController(civilizationMock);
        Assertions.assertNotNull(civilizationController.getCivilization());
    }

    @Test
    public void showResearchTest() {
        CivilizationController civilizationController = new CivilizationController(civilizationMock);
        Mockito.when(civilizationMock.getResearchTree()).thenReturn(researchTreeMock);
        Mockito.when(researchTreeMock.toString()).thenReturn("hello");
        String output = civilizationController.showResearch().toString();
        Assertions.assertEquals("hello", output);
    }

    @Test
    public void unitPanelTester () {
        CivilizationController civilizationController = new CivilizationController(civilizationMock);
        Mockito.when(civilizationMock.getUnits()).thenReturn(unitsMock);
        String output = civilizationController.showUnitsPanel();
        Assertions.assertEquals("Units panel:\n", output);
    }

    @Test
    public void cityPanelTester () {
        CivilizationController civilizationController = new CivilizationController(civilizationMock);
        Mockito.when(civilizationMock.getCities()).thenReturn(citiesMock);
        String output = civilizationController.showCitiesPanel();
        Assertions.assertEquals("Cities panel:\n", output);
    }

    @Test
    public void diplomacyTester () {
        CivilizationController civilizationController = new CivilizationController(civilizationMock);
        String output = civilizationController.showDiplomacyPanel();
        Assertions.assertEquals("Not needed yet", output);
    }

    @Test
    public void victoryTester () {
        CivilizationController civilizationController = new CivilizationController(civilizationMock);
        String output = civilizationController.showVictoryProgress();
        Assertions.assertEquals("Not needed yet", output);
    }

    @Test
    public void howNotificationHistoryTester () {
        CivilizationController civilizationController = new CivilizationController(civilizationMock);
        String output = civilizationController.showNotificationHistory();
        Assertions.assertEquals("notification history(needs pop up notification)", output);
    }

    @Test
    public void showMilitaryOverviewTester () {
        CivilizationController civilizationController = new CivilizationController(civilizationMock);
        Mockito.when(civilizationMock.getUnits()).thenReturn(unitsMock);
        String output = civilizationController.showMilitaryOverview();
        Assertions.assertEquals(output, "Military Overview:\n");
    }

    @Test
    public void showEconomicOverviewTester() {
        CivilizationController civilizationController = new CivilizationController(civilizationMock);
        Mockito.when(civilizationMock.getCities()).thenReturn(citiesMock);
        String output = civilizationController.showEconomicOverview();
        Assertions.assertEquals("Economic overview:\n", output);
    }

    @Test
    public void showDiplomaticOverviewTester () {
        CivilizationController civilizationController = new CivilizationController(civilizationMock);
        String output = civilizationController.showDiplomaticOverview();
        Assertions.assertEquals("Not needed yet", output);
    }

    @Test
    public void showTradeHistoryTester () {
        CivilizationController civilizationController = new CivilizationController(civilizationMock);
        String output = civilizationController.showTradeHistory();
        Assertions.assertEquals("Not needed yet", output);
    }

    @Test
    public void getCivilizationTester () {
        CivilizationController civilizationController = new CivilizationController(civilizationMock);
        Assertions.assertEquals(civilizationMock, civilizationController.getCivilization());
    }

    @Test
    public void searchForRequiredActionsTrue() {
        CivilizationController civilizationController = new CivilizationController(civilizationMock);
        Mockito.when(civilizationMock.getUnits()).thenReturn(unitsMock);
        Mockito.when(civilizationMock.getCities()).thenReturn(citiesMock);
        Mockito.when(civilizationMock.getResearchTree()).thenReturn(researchTreeMock);
        civilizationController.searchForRequiredActions();
        Assertions.assertTrue(civilizationController.hasRequiredAction());
    }

    @Test
    public void searchForRequiredActionsResearch() {
        CivilizationController civilizationController = new CivilizationController(civilizationMock);
        Mockito.when(civilizationMock.getUnits()).thenReturn(unitsMock);
        Mockito.when(civilizationMock.getCities()).thenReturn(citiesMock);
        Mockito.when(civilizationMock.getResearchTree()).thenReturn(researchTreeMock);
        Mockito.when(researchTreeMock.hasResearch()).thenReturn(true);
        civilizationController.searchForRequiredActions();
        Assertions.assertFalse(civilizationController.hasRequiredAction());
    }

    @Test
    public void searchForRequiredActionsUnit() {
        CivilizationController civilizationController = new CivilizationController(civilizationMock);
        Mockito.when(civilizationMock.getUnits()).thenReturn(unitsMock);
        unitsMock.add(unit);
        Mockito.when(unit.hasTask()).thenReturn(true);
        Mockito.when(civilizationMock.getCities()).thenReturn(citiesMock);
        Mockito.when(civilizationMock.getResearchTree()).thenReturn(researchTreeMock);
        Mockito.when(researchTreeMock.hasResearch()).thenReturn(false);
        civilizationController.searchForRequiredActions();
        Assertions.assertTrue(civilizationController.hasRequiredAction());
    }

    @Test
    public void searchForRequiredActionsCity() {
        CivilizationController civilizationController = new CivilizationController(civilizationMock);
        Mockito.when(civilizationMock.getUnits()).thenReturn(unitsMock);
        citiesMock.add(city);
        Mockito.when(city.getUnitInProgress()).thenReturn(null);
        Mockito.when(civilizationMock.getCities()).thenReturn(citiesMock);
        Mockito.when(civilizationMock.getResearchTree()).thenReturn(researchTreeMock);
        Mockito.when(researchTreeMock.hasResearch()).thenReturn(false);
        civilizationController.searchForRequiredActions();
        Assertions.assertTrue(civilizationController.hasRequiredAction());
        System.out.println(civilizationController.getRequiredActions());
    }
}
