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
    public ArrayList<String> showResearch() {
        return null;
    }

    public ArrayList<String> showUnitsPanel() {
        return null;
    }

    public ArrayList<String> showCitiesPanel() {
        return null;
    }

    public ArrayList<String> showDiplomacyPanel() {
        return null;
    }

    public ArrayList<String> showVictoryProgress() {
        return null;
    }

    public ArrayList<String> showDemographic() {
        return null;
    }

    public ArrayList<String> showNotificationHistory() {
        return null;
    }

    public ArrayList<String> showMilitaryOverview() {
        return null;
    }

    public ArrayList<String> showEconomicOverview() {
        return null;
    }

    public ArrayList<String> showDiplomaticOverview() {
        return null;
    }

    public ArrayList<String> showTradeHistory() {
        return null;
    }

    public void endTurn() {
        civilization.setTurn(civilization.getTurn() + 1);
    }
}
