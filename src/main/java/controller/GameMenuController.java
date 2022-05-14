package controller;

import model.game.City;
import model.game.Civilization;
import model.improvement.Improvement;
import model.map.Map;
import model.tile.Route;
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


    //singleton
    private static GameMenuController instance;

    private GameMenuController(int civilizationCount, ArrayList<Civilization> civilizations) {
        this.currentTurn = 0;
        for (int i = 0; i < civilizationCount; i++) {
            this.civilizationControllers.add(new CivilizationController(civilizations.get(i)));
        }
    }

    public String nextCivilization() {
        if (currentCivilizationController.isHasRequiredAction()) { // check conditions for changing turn
            this.currentCivilizationController.searchForRequiredActions();
            return "error: " + currentCivilizationController.getRequiredActions();
        } else {
            CityController.updateInstance(null); // deselect city in new turn
            this.currentTurn++;
            this.currentTurn %= this.civilizationCount;
            this.currentCivilizationController = civilizationControllers.get(currentTurn); // change civilization for new turn
            //TODO increase production and gold and ... for new Turn
            for (int i = 0; i < this.currentCivilizationController.getCivilization().getCities().size(); i++) { // increase city health if it is under 20
                City temp = this.currentCivilizationController.getCivilization().getCities().get(i);
                if (temp.getHealth() < 20)
                    temp.setHealth(temp.getHealth() + 1);
            }
            this.currentCivilizationController.getCivilization().applyNewTurnChanges(); // add production, decrease cost of units
            for (int i = 0; i < this.currentCivilizationController.getCivilization().getUnits().size(); i++) { // apply unit state effects for new turn
                UnitController.updateInstance(this.currentCivilizationController.getCivilization().getUnits().get(i));
                UnitController.getInstance().applyUnitStateForNewTurn();
            }
            UnitController.updateInstance(null); // deselect unit in new turn
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
            UnitController.updateInstance(soldier);
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
            UnitController.updateInstance(civilian);
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
            CityController.updateInstance(city);
            return "City selected successfully";
        }
    }

    public String selectCityName(HashMap<String, String> command) {
        String cityName = command.get(CITY_NAME.getKey());
        ArrayList<City> cities = currentCivilizationController.getCivilization().getCities();
        for (City city : cities) {
            if (city.getName().equals(cityName)) {
                CityController.updateInstance(city);
                return "City selected successfully";
            }
        }
        return "There is no city with this name";
    }

    //UNIT COMMANDS

    public String unitMove(HashMap<String, String> command) {
        if (UnitController.getInstance().getUnit() == null) {
            return "error : no unit selected";
        } else {
            int xPlace = Integer.parseInt(command.get(X_POSITION.getKey()));
            int yPlace = Integer.parseInt(command.get(Y_POSITION.getKey()));
            return UnitController.getInstance().unitMove(xPlace, yPlace);
        }
    }

    public String unitSleep(HashMap<String, String> command) {
        if (UnitController.getInstance().getUnit() == null) {
            return "error : no unit selected";
        } else {
            return UnitController.getInstance().unitSleep();
        }
    }

    public String unitAlert(HashMap<String, String> command) {
        if (UnitController.getInstance().getUnit() == null) {
            return "error : no unit selected";
        } else {
            return UnitController.getInstance().unitAlert();
        }
    }

    public String unitFortify(HashMap<String, String> command) {
        if (UnitController.getInstance().getUnit() == null) {
            return "error : no unit selected";
        } else {
            return UnitController.getInstance().unitFortify();
        }
    }

    public String unitRecover(HashMap<String, String> command) {
        if (UnitController.getInstance().getUnit() == null) {
            return "error : no unit selected";
        } else {
            return UnitController.getInstance().unitRecover();
        }
    }

    public String unitGarrison(HashMap<String, String> command) {
        if (UnitController.getInstance().getUnit() == null) {
            return "error : no unit selected";
        } else {
            return UnitController.getInstance().unitGarrison();
        }
    }

    public String unitSetupRanged(HashMap<String, String> command) {
        if (UnitController.getInstance().getUnit() == null) {
            return "error : no unit selected";
        } else {
            return UnitController.getInstance().unitSetupRanged();
        }
    }

    public String unitAttack(HashMap<String, String> command) {
        if (UnitController.getInstance().getUnit() == null) {
            return "error : no unit selected";
        } else {
            int xPlace = Integer.parseInt(command.get(X_POSITION.getKey()));
            int yPlace = Integer.parseInt(command.get(Y_POSITION.getKey()));
            return UnitController.getInstance().unitAttack(xPlace, yPlace);
        }
    }

    public String unitCancel(HashMap<String, String> command) {
        if (UnitController.getInstance().getUnit() == null) {
            return "error : no unit selected";
        } else {
            return UnitController.getInstance().unitCancel();
        }
    }

    public String unitWake(HashMap<String, String> command) {
        if (UnitController.getInstance().getUnit() == null) {
            return "error : no unit selected";
        } else {
            return UnitController.getInstance().unitWake();
        }
    }

    public String unitDelete(HashMap<String, String> command) {
        if (UnitController.getInstance().getUnit() == null) {
            return "error : no unit selected";
        } else {
            return UnitController.getInstance().unitDelete();
        }
    }


    public String unitFoundCity(HashMap<String, String> command) {
        if (UnitController.getInstance().getUnit() == null) {
            return "error : no unit selected";
        } else {
            return UnitController.getInstance().unitFoundCity();
        }
    }


    public String unitBuildImprovement(HashMap<String, String> command) {
        if (UnitController.getInstance().getUnit() == null) {
            return "error : no unit selected";
        } else {
            String improvementName = command.get(IMPROVEMENT.getKey());
            Improvement improvement = Improvement.valueOf(improvementName.toUpperCase());
            return UnitController.getInstance().unitBuildImprovement(improvement);
        }
    }

    public String unitBuildRoute(HashMap<String, String> command) {
        if (UnitController.getInstance().getUnit() == null) {
            return "error: no unit selected";
        } else {
            String routeName = command.get(ROUTE.getKey());
            Route route = Route.valueOf(routeName.toUpperCase());
            return UnitController.getInstance().unitBuildRoute(route);
        }
    }

    public String unitRemoveJungle(HashMap<String, String> command) {
        if (UnitController.getInstance().getUnit() == null) {
            return "error : no unit selected";
        } else {
            return UnitController.getInstance().unitRemoveJungle();
        }
    }

    public String unitRemoveForest(HashMap<String, String> command) {
        if (UnitController.getInstance().getUnit() == null) {
            return "error : no unit selected";
        } else {
            return UnitController.getInstance().unitRemoveForest();
        }
    }

    public String unitRemoveMarsh(HashMap<String, String> command) {
        if (UnitController.getInstance().getUnit() == null) {
            return "error : no unit selected";
        } else {
            return UnitController.getInstance().unitRemoveMarsh();
        }
    }

    public String unitRemoveRoute(HashMap<String, String> command) {
        if (UnitController.getInstance().getUnit() == null) {
            return "error : no unit selected";
        } else {
            return UnitController.getInstance().unitRemoveRoute();
        }
    }

    public String unitRepair(HashMap<String , String> command) {
        if (UnitController.getInstance().getUnit() == null) {
            return "error : no unit selected";
        } else {
            return UnitController.getInstance().unitRepair();
        }
    }
    
    //CITY COMMANDS
    
    public String cityCreateUnit(HashMap<String, String> command) {
        if (CityController.getInstance().getCity() == null) {
            return "error : no city selected";
        } else {
            String unitName = command.get(UNIT_NAME.getKey());
            return CityController.getInstance().cityCreateUnit(unitName);
        }
    }
    
    public String cityCreateBuilding(HashMap<String, String> command) {
        if (CityController.getInstance().getCity() == null) {
            return "error : no city selected";
        } else {
            String buildingName = command.get(BUILDING.getKey());
            return CityController.getInstance().cityCreateBuilding(buildingName);
        }
    }
    
    public String buyTile(HashMap<String, String> command) {
        if (CityController.getInstance().getCity() == null) {
            return "error : no city selected";
        } else {
            int x = Integer.parseInt(command.get(X_POSITION.getKey()));
            int y = Integer.parseInt(command.get(Y_POSITION.getKey()));
            return CityController.getInstance().buyTile(x, y);
        }
    }

    public String purchaseUnit(HashMap<String, String> command) {
        if (CityController.getInstance().getCity() == null) {
            return "error: no city selected";
        } else {
            String unitName = command.get(UNIT_NAME.getKey());
            return CityController.getInstance().purchaseUnit(unitName);
        }
    }

    public String purchaseBuilding(HashMap<String, String> command) {
        if (CityController.getInstance().getCity() == null) {
            return "error: no city selected";
        } else {
            String buildingName = command.get(BUILDING.getKey());
            return CityController.getInstance().purchaseBuilding(buildingName);
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
