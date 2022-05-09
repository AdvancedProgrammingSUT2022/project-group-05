package controller;

import model.game.City;
import model.game.Civilization;
import model.tile.Tile;
import model.unit.Unit;
import model.unit.soldier.ranged.Archer;

import java.util.ArrayList;
import java.util.HashMap;

public class CivilizationController {
    private Civilization civilization ;


    public CivilizationController(Civilization civilization) {
        this.civilization = civilization;
    }

    public void combat(HashMap<String, String> command) {
        //TODO...
    }

    public void move(HashMap<String, String> command) {
        //TODO...
    }

    public void research(HashMap<String, String> command) {
        //TODO...
    }

    //TODO... Complete this functions, info about them can be found on game.pdf page 7 or civManual page 25
    public String showResearch() {
        return civilization.getResearchTree().toString();
    }

    public String showUnitsPanel() {
        return "";
    }

    public String showCitiesPanel() {
        return "";
    }

    public String showDiplomacyPanel() {
        return "";
    }

    public String showVictoryProgress() {
        return "";
    }

    public String showDemographic() {
        return "";
    }

    public String showNotificationHistory() {
        return "";
    }

    public String showMilitaryOverview() {
        return "";
    }

    public String showEconomicOverview() {
        return "";
    }

    public String showDiplomaticOverview() {
        return "";
    }

    public String showTradeHistory() {
        return "";
    }
}
