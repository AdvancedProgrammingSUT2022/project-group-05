package controller;

import model.game.City;
import model.game.Civilization;
import model.map.Map;
import model.unit.civilian.Civilian;
import model.unit.soldier.Soldier;

import java.util.ArrayList;
import java.util.HashMap;

import static view.enums.Entity.*;

public class GameMenuController {
    //FIELDS
    private int civilizationCount;
    private ArrayList<CivilizationController> civilizationControllers = new ArrayList<>();
    private int currentTurn;
    private CivilizationController currentCivilizationController;
    private UnitController selectedUnitController; //TODO it should be null while using other methods
    private CityController selectedCityController; //TODO it should be null while using other methods

    //singleton
    private static GameMenuController instance;

    private GameMenuController(int civilizationCount, ArrayList<Civilization> civilizations) {
        this.currentTurn = 0;
        for (int i = 0; i < civilizationCount; i++) {
            this.civilizationControllers.add(new CivilizationController(civilizations.get(i)));
        }
    }

    public String nextCivilization() {
        if (currentCivilizationController.isHasRequiredAction()) {
            return "error: " + currentCivilizationController.getRequiredActions();
        } else {
            this.currentTurn++;
            this.currentTurn %= this.civilizationCount;
            this.currentCivilizationController = civilizationControllers.get(currentTurn);
            this.currentCivilizationController.getCivilization().applyNewTurnChanges();
            return currentCivilizationController.getCivilization().getPlayer().getNickname() + "'s turn";
        }
    }

    public static GameMenuController getInstance() {
        return instance;
    }

    public static void updateInstance(int civilizationCount, ArrayList<Civilization> civilizations) {
        instance = new GameMenuController(civilizationCount, civilizations);
    }

    // end of singleton design pattern

    //SELECT COMMANDS


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
            this.selectedCityController = new CityController(city);
            return "City selected successfully";
        }
    }

    public String selectCityName(HashMap<String, String> command) {
        String cityName = command.get(CITY_NAME.getKey());
        ArrayList<City> cities = currentCivilizationController.getCivilization().getCities();
        boolean hasCity = false;
        for (int i = 0; i < cities.size(); i++) {
            if (cities.get(i).getName().equals(cityName)) {
                this.selectedCityController = new CityController(cities.get(i));
                return "City selected successfully";
            }
        }
        return "There is no city with this name";
    }

    //UNIT COMMANDS

    public String unitMove(HashMap<String, String> command) {
        if (this.selectedUnitController == null) {
            return "error : no unit selected";
        } else {
            int xPlace = Integer.parseInt(command.get(X_POSITION.getKey()));
            int yPlace = Integer.parseInt(command.get(Y_POSITION.getKey()));
            return selectedUnitController.unitMove(xPlace, yPlace);
        }
    }

    public String unitSleep(HashMap<String, String> command) {
        if (this.selectedUnitController == null) {
            return "error : no unit selected";
        } else {
            return selectedUnitController.unitSleep();
        }
    }

    public String unitAlert(HashMap<String, String> command) {
        if (this.selectedUnitController == null) {
            return "error : no unit selected";
        } else {
            return selectedUnitController.unitAlert();
        }
    }

    public String unitFortify(HashMap<String, String> command) {
        if (this.selectedUnitController == null) {
            return "error : no unit selected";
        } else {
            return selectedUnitController.unitFortify();
        }
    }

    public String unitRecover(HashMap<String, String> command) {
        if (this.selectedUnitController == null) {
            return "error : no unit selected";
        } else {
            return selectedUnitController.unitRecover();
        }
    }

    public String unitGarrison(HashMap<String, String> command) {
        if (this.selectedUnitController == null) {
            return "error : no unit selected";
        } else {
            return selectedUnitController.unitGarrison();
        }
    }

    public String unitSetupRanged(HashMap<String, String> command) {
        if (this.selectedUnitController == null) {
            return "error : no unit selected";
        } else {
            return selectedUnitController.unitSetupRanged();
        }
    }

    public String unitAttack(HashMap<String, String> command) {
        if (this.selectedUnitController == null) {
            return "error : no unit selected";
        } else {
            int xPlace = Integer.parseInt(command.get(X_POSITION.getKey()));
            int yPlace = Integer.parseInt(command.get(Y_POSITION.getKey()));
            return selectedUnitController.unitAttack(xPlace, yPlace);
        }
    }

    public String unitCancel(HashMap<String, String> command) {
        if (this.selectedUnitController == null) {
            return "error : no unit selected";
        } else {
            return selectedUnitController.unitCancel();
        }
    }

    public String unitWake(HashMap<String, String> command) {
        if (this.selectedUnitController == null) {
            return "error : no unit selected";
        } else {
            return selectedUnitController.unitWake();
        }
    }

    public String unitDelete(HashMap<String, String> command) {
        if (this.selectedUnitController == null) {
            return "error : no unit selected";
        } else {
            return selectedUnitController.unitDelete();
        }
    }


    public String unitFoundCity(HashMap<String, String> command) {
        if (this.selectedUnitController == null) {
            return "error : no unit selected";
        } else {
            return selectedUnitController.unitFoundCity();
        }
    }


    public String unitBuild(HashMap<String, String> command) {
        if (this.selectedUnitController == null) {
            return "error : no unit selected";
        } else {
            String building = command.get(BUILDING.getKey());
            return selectedUnitController.unitBuild(building);
        }
    }

    public String unitRemoveFeature(HashMap<String, String> command) { //TODO
        if (selectedUnitController == null) {
            return "error : no unit selected";
        } else {
            return "";
        }
    }

    public String unitRemoveRoute(HashMap<String, String> command) {
        if (selectedUnitController == null) {
            return "error : no unit selected";
        } else {
            return selectedUnitController.unitRemoveRoute();
        }
    }

    public String unitRepair(HashMap<String , String> command) {
        if (selectedUnitController == null) {
            return "error : no unit selected";
        } else {
            return selectedUnitController.unitRepair();
        }
    }
    
    //CITY COMMANDS
    
    public String cityCreateUnit(HashMap<String, String> command) {
        if (selectedCityController == null) {
            return "error : no city selected";
        } else {
            String unitName = command.get(UNIT_NAME.getKey());
            return selectedCityController.cityCreateUnit(unitName);
        }
    }
    
    public String cityCreateBuilding(HashMap<String, String> command) {
        if (selectedCityController == null) {
            return "error : no city selected";
        } else {
            String buildingName = command.get(BUILDING.getKey());
            return selectedCityController.cityCreateBuilding(buildingName);
        }
    }
    
    public String buyTile(HashMap<String, String> command) {
        if (selectedCityController == null) {
            return "error : no city selected";
        } else {
            int x = Integer.parseInt(command.get(X_POSITION.getKey()));
            int y = Integer.parseInt(command.get(Y_POSITION.getKey()));
            return selectedCityController.buyTile(x, y);
        }
    }

    public String purchaseUnit(HashMap<String, String> command) {
        if (selectedCityController == null) {
            return "error: no city selected";
        } else {
            String unitName = command.get(UNIT_NAME.getKey());
            return selectedCityController.purchaseUnit(unitName);
        }
    }

    public String purchaseBuilding(HashMap<String, String> command) {
        if (selectedCityController == null) {
            return "error: no city selected";
        } else {
            String buildingName = command.get(BUILDING.getKey());
            return selectedCityController.purchaseBuilding(buildingName);
        }
    }

    // MAP COMMAND

    public String mapShowCity(HashMap<String, String> command) {
        String cityName = command.get(CITY_NAME.getKey());
        //TODO find city by cityName in Map (city must have been discovered before) and print Map
        return "";
    }

    public String mapShowPosition(HashMap<String, String> command) {
        int x = Integer.parseInt(command.get(X_POSITION.getKey()));
        int y = Integer.parseInt(command.get(Y_POSITION.getKey()));
        //TODO check validity of x and y and print Map
        return "";
    }

    public String mapMove(HashMap<String, String> command) {
        String direction = command.get(DIRECTION.getKey());
        //TODO move Map to desired direction and print Map
        return "";
    }

    // CHEAT CODE COMMANDS

    public String increaseTurn(HashMap<String, String> command) {
        int amount = Integer.parseInt(command.get(AMOUNT.getKey()));
        //TODO change variables with respect to amount of turn
        return "turn increased";
    }

    public String increaseGold(HashMap<String, String> command) {
        int amount = Integer.parseInt(command.get(AMOUNT.getKey()));
        this.currentCivilizationController.getCivilization().setGold(this.currentCivilizationController.getCivilization().getGold() + amount);
        return "turn increased";
    }

    // END OF TURN

    public String endOfTurn(HashMap<String, String> command) {
        return this.nextCivilization();
    }

}
