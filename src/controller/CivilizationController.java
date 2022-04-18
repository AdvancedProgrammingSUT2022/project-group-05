package controller;

import model.game.Civilization;

import java.util.ArrayList;
import java.util.HashMap;

public class CivilizationController{
    private Civilization civilization;

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
    public ArrayList<String> showResearch() {}

    public ArrayList<String> showUnitsPanel() {}

    public ArrayList<String> showCitiesPanel() {}

    public ArrayList<String> showDiplomacyPanel() {}

    public ArrayList<String> showVictoryProgress() {}

    public ArrayList<String> showDemographic() {}

    public ArrayList<String> showNotificationHistory() {}

    public ArrayList<String> showMilitaryOverview() {}

    public ArrayList<String> showEconomicOverview() {}

    public ArrayList<String> showDiplomaticOverview() {}

    public ArrayList<String> showTradeHistory() {}

    public void endTurn() {
        civilization.setTurn(civilization.getTurn() + 1);
    }
}
