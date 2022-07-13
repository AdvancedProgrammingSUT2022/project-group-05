package controller;

import model.game.City;
import model.game.Civilization;
import model.tile.Tile;
import model.unit.Unit;
import model.unit.civilian.Settler;
import model.unit.civilian.Worker;
import utility.RandomGenerator;

import java.util.ArrayList;
import java.util.HashMap;

public class CivilizationController {
    private Civilization civilization;
    private boolean hasRequiredAction;

    private ArrayList<String> requiredActions;

    public CivilizationController(Civilization civilization) {
        this.civilization = civilization;
    }

    public String showResearch() {
        return civilization.getResearchTree().toString();
    }

    public String showUnitsPanel() {
        StringBuilder result = new StringBuilder();

        result.append("Units panel:\n");
        for (Unit unit : civilization.getUnits()) {
            result.append(InfoController.getUnitInfo(unit)).append("\n");
        }

        return result.toString();
    }

    public String showCitiesPanel() {
        StringBuilder result = new StringBuilder();

        result.append("Cities panel:\n");
        for (City city : civilization.getCities()) {
            result.append(InfoController.getCityInfo(city)).append("\n");
        }

        return result.toString();
    }

    public String showDiplomacyPanel() {
        return "Not needed yet"; //TODO
    }

    public String showVictoryProgress() {
        return "Not needed yet"; //TODO
    }

    public String showDemographic() {
        StringBuilder result = new StringBuilder();

        result.append("Demographic:\n");

        result.append("Soldier count: ").append(InfoController.getSoldierCount(civilization)).append("\n");
        result.append("Civilian count: ").append(InfoController.getCivilianCount(civilization)).append("\n");
        result.append("Total area: ").append(InfoController.getTileCount(civilization)).append(" square km\n");

        result.append("Total gold: ").append(civilization.getGold()).append("\n");
        result.append("Total happiness: ").append(civilization.getHappiness()).append("\n");

        result.append("Luxury resource count: ").append(InfoController.getLuxuryResourceCount(civilization)).append("\n");

        return result.toString();
    }

    public String showNotificationHistory() {
        return "notification history(needs pop up notification)"; //TODO when notifications exist
    }

    public String showMilitaryOverview() {
        StringBuilder result = new StringBuilder();

        result.append("Military Overview:\n");
        for (Unit unit : civilization.getUnits()) {
            result.append(InfoController.getUnitInfoInDepth(unit)).append("\n");
        }

        return result.toString();
    }

    public String showEconomicOverview() {
        StringBuilder result = new StringBuilder();

        result.append("Economic overview:\n");
        for (City city : civilization.getCities()) {
            result.append(InfoController.getCityInfoInDepth(city)).append("\n");
        }

        return result.toString();
    }

    public String showDiplomaticOverview() {
        return "Not needed yet"; //TODO
    }

    public String showTradeHistory() {
        return "Not needed yet"; //TODO
    }

    //GETTERS
    public Civilization getCivilization() {
        return civilization;
    }

    public String getRequiredActions() {
        return requiredActions.get(0);
    }

    public boolean hasRequiredAction() {
        return hasRequiredAction;
    }

    public void searchForRequiredActions() {
        //find problems such as stacked unit , units without orders, no research chosen, ... and add their comments in required actions
        //requiredActions.add(" comment ");...

        this.requiredActions = new ArrayList<>();
        for (int i = 0; i < this.civilization.getUnits().size(); i++) {
            if (this.civilization.getUnits().get(i).hasTask()) {
                requiredActions.add("Unit needs order");
            }
        }
        for (City city : this.civilization.getCities()) {
            if (city.getUnitInProgress() == null && city.getBuildingInProgress() == null) {
                requiredActions.add("Choose production");
            }
        }

        if (!this.civilization.getResearchTree().hasResearch())
            requiredActions.add("Choose a research");

        this.hasRequiredAction = requiredActions.size() != 0;
    }
}
