package controller;

import model.game.City;
import model.game.Civilization;
import model.game.Game;
import model.map.Map;
import model.tile.Tile;
import model.unit.Unit;
import model.unit.civilian.Civilian;
import model.unit.soldier.Soldier;

import java.util.ArrayList;
import java.util.HashMap;

import static view.enums.Entity.*;

public class GameMenuController {
    //FIELDS
    private int civilizationCount;
    private ArrayList<CivilizationController> civilizationControllers;
    private int currentTurn;
    private CivilizationController currentCivilizationController;
    private UnitController selectedUnitController; //TODO it should be null while using other methods
    private City selectedCity; //TODO it should be null while using other methods

    //singleton
    private static GameMenuController instance;

    private GameMenuController(int civilizationCount, ArrayList<Civilization> civilizations) {
        this.currentTurn = 0;
        for (int i = 0; i < civilizationCount; i++) {
            this.civilizationControllers.add(new CivilizationController(civilizations.get(i)));
        }
    }

    public String nextCivilization() {
        this.currentTurn++;
        this.currentTurn %= this.civilizationCount;
        this.currentCivilizationController = civilizationControllers.get(currentTurn);
        return currentCivilizationController.getCivilization().getPlayer().getNickname() + "'s turn";
    }

    public static GameMenuController getInstance() {
        return instance;
    }

    public static void updateInstance(int civilizationCount, ArrayList<Civilization> civilizations) {
        instance = new GameMenuController(civilizationCount, civilizations);
    }

    // end of singleton design pattern

    //GAME COMMAND VERIFICATION (METHODS)
    public String selectUnitSoldier(HashMap<String, String> command) {
        int x = Integer.parseInt(command.get(X_POSITION.getKey()));
        int y = Integer.parseInt(command.get(Y_POSITION.getKey()));
        Soldier soldier = Map.getInstance().getTileFromMap(x, y).getSoldier();
        if (soldier.getCivilization() != currentCivilizationController.getCivilization()) {
            return "This unit is not from your civilization";
        } else {
            this.selectedUnitController = new UnitController(soldier);
            return "Soldier selected successfully";
        }
    }

    public String selectUnitCivilian(HashMap<String, String> command) {
        int x = Integer.parseInt(command.get(X_POSITION.getKey()));
        int y = Integer.parseInt(command.get(Y_POSITION.getKey()));
        Civilian civilian = Map.getInstance().getTileFromMap(x, y).getCivilian();
        if (civilian.getCivilization() != currentCivilizationController.getCivilization()) {
            return "This unit is not from your civilization";
        } else {
            this.selectedUnitController = new UnitController(civilian);
            return "Civilian selected successfully";
        }
    }

    public String selectCityPosition(HashMap<String, String> command) {
        int x = Integer.parseInt(command.get(X_POSITION.getKey()));
        int y = Integer.parseInt(command.get(Y_POSITION.getKey()));
        City city = Map.getInstance().getTileFromMap(x, y).getCity();
        if (city.getCivilization() != currentCivilizationController.getCivilization()) {
            return "This city is not from your civilization";
        } else {
            this.selectedCity = city;
            return "City selected successfully";
        }
    }

    public String selectCityName(HashMap<String, String> command) {
        String cityName = command.get(CITY_NAME.getKey());
        ArrayList<City> cities = currentCivilizationController.getCivilization().getCities();
        boolean hasCity = false;
        for (int i = 0; i < cities.size(); i++) {
            if (cities.get(i).getName().equals(cityName)) {
                this.selectedCity = cities.get(i);
                return "City selected successfully";
            }
        }
        return "There is no city with this name";
    }

    public String unitMove(HashMap<String, String> command) {
        if (this.selectedUnitController == null) {
            return "No unit selected";
        } else {
            return selectedUnitController.unitMove(command);
        }
    }

    public String unitSleep(HashMap<String, String> command) {
        if (this.selectedUnitController == null) {
            return "No unit selected";
        } else {
            return selectedUnitController.unitSleep();
        }
    }

    public String unitAlert(HashMap<String, String> command) {
        if (this.selectedUnitController == null) {
            return "No unit selected";
        } else {
            return selectedUnitController.unitAlert();
        }
    }

    public String unitFortify(HashMap<String, String> command) {
        if (this.selectedUnitController == null) {
            return "No unit selected";
        } else {
            return selectedUnitController.unitFortify();
        }
    }

    public String unitRecover(HashMap<String, String> command) {
        if (this.selectedUnitController == null) {
            return "No unit selected";
        } else {
            return selectedUnitController.unitRecover();
        }
    }

    public String unitGarrison(HashMap<String, String> command) {
        if (this.selectedUnitController == null) {
            return "No unit selected";
        } else {
            return selectedUnitController.unitGarrison();
        }
    }
}
