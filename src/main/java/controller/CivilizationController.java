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
    private boolean hasRequiredAction;

    private ArrayList<String> requiredActions;

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

    //GETTERS


    public Civilization getCivilization() {
        return civilization;
    }

    public String getRequiredActions() {
        return requiredActions.get(0);
    }

    public boolean isHasRequiredAction() {
        return hasRequiredAction;
    }

    public void searchForRequiredActions() {
        //TODO find problems such as stacked unit , units without orders, no research chosen, ... and add their comments in required actions
        // requiredActions.add(" comment ");...
        if (requiredActions.size() == 0) {
            this.hasRequiredAction = false;
        } else {
            this.hasRequiredAction = true;
        }
    }
}
