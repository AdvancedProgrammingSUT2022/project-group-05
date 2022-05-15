package controller;

import model.game.City;
import model.game.Civilization;
import model.improvement.Improvement;
import model.map.FogOfWar;
import model.map.Map;
import model.research.Research;
import model.tile.Route;
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
    private ArrayList<CivilizationController> civilizationControllers = new ArrayList<>();
    private int currentTurn;
    private int currentYear;
    private CivilizationController currentCivilizationController;


    //singleton
    private static GameMenuController instance;

    private GameMenuController(int civilizationCount, ArrayList<Civilization> civilizations) {
        this.civilizationCount = civilizationCount;

        this.currentTurn = -1;
        this.currentYear = -1;

        for (int i = 0; i < civilizationCount; i++) {
            this.civilizationControllers.add(new CivilizationController(civilizations.get(i)));
        }
    }

    public static GameMenuController getInstance() {
        return instance;
    }

    public static void updateInstance(int civilizationCount, ArrayList<Civilization> civilizations) {
        instance = new GameMenuController(civilizationCount, civilizations);
    }

    public static void destroyInstance() {
        instance = null;
    }
    // end of singleton design pattern

    public String nextCivilization() {
        if (currentTurn > -1)
            this.currentCivilizationController.searchForRequiredActions(); // search if there is any required actions left
        if (currentTurn > -1 && currentCivilizationController.hasRequiredAction()) { // check conditions for changing turn
            return "error: " + currentCivilizationController.getRequiredActions();
        }
        this.currentTurn++;
        this.currentTurn %= this.civilizationCount;
        if (this.currentTurn == 0) this.currentYear++;

        this.currentCivilizationController = civilizationControllers.get(currentTurn); // change civilization for new turn
        this.currentCivilizationController.getCivilization().applyNewTurnChanges(currentYear); // add production and gold and ... and progress productions

        CityController.updateInstance(null); // deselect city in new turn
        UnitController.updateInstance(null); // deselect unit in new turn

        return this.whoseTurnIsIt();
    }

    public String whoseTurnIsIt() {
        return  "Year " + this.currentYear + ": " + currentCivilizationController.getCivilization().getPlayer().getNickname() + "'s turn";
    }

    //INFO COMMANDS
    public String infoResearch(HashMap<String, String> command) {
        return currentCivilizationController.showResearch();
    }

    public String infoUnits(HashMap<String, String> command) {
        return currentCivilizationController.showUnitsPanel();
    }

    public String infoCities(HashMap<String, String> command) {
        return currentCivilizationController.showCitiesPanel();
    }

    public String infoDiplomacy(HashMap<String, String> command) {
        return currentCivilizationController.showDiplomacyPanel();
    }

    public String infoVictory(HashMap<String, String> command) {
        return currentCivilizationController.showVictoryProgress();
    }

    public String infoDemographics(HashMap<String, String> command) {
        return currentCivilizationController.showDemographic();
    }

    public String infoNotification(HashMap<String, String> command) {
        return currentCivilizationController.showNotificationHistory();
    }

    public String infoMilitary(HashMap<String, String> command) {
        return currentCivilizationController.showMilitaryOverview();
    }

    public String infoEconomic(HashMap<String, String> command) {
        return currentCivilizationController.showEconomicOverview();
    }

    public String infoDiplomatic(HashMap<String, String> command) {
        return currentCivilizationController.showDiplomaticOverview();
    }

    public String infoDeals(HashMap<String, String> command) {
        return currentCivilizationController.showTradeHistory();
    }

    public String infoTile(HashMap<String, String> command) {
        int x = Integer.parseInt(command.get(X_POSITION.getKey()));
        int y = Integer.parseInt(command.get(Y_POSITION.getKey()));

        Tile tile = Map.getInstance().getTileFromMap(x, y);

        if (tile == null) return "error: out of bounds";

        return InfoController.getTileInfo(tile);
    }

    public String infoTileStats(HashMap<String, String> command) {
        int x = Integer.parseInt(command.get(X_POSITION.getKey()));
        int y = Integer.parseInt(command.get(Y_POSITION.getKey()));

        Tile tile = Map.getInstance().getTileFromMap(x, y);

        if (tile == null) return "error: out of bounds";

        return InfoController.getTileStats(tile);
    }

    public String infoTileProject(HashMap<String, String> command) {
        int x = Integer.parseInt(command.get(X_POSITION.getKey()));
        int y = Integer.parseInt(command.get(Y_POSITION.getKey()));

        Tile tile = Map.getInstance().getTileFromMap(x, y);

        if (tile == null) return "error: out of bounds";

        return InfoController.getTileProjectInfo(tile);
    }

    //SELECT COMMANDS
    public String menuExit(HashMap<String, String> command) {
        destroyInstance();
        return "exiting Game";
    }

    public String selectUnitSoldier(HashMap<String, String> command) {
        int x = Integer.parseInt(command.get(X_POSITION.getKey()));
        int y = Integer.parseInt(command.get(Y_POSITION.getKey()));
        Soldier soldier = Map.getInstance().getTileFromMap(x, y).getSoldier();
        if (soldier == null)
            return "error: no soldier on selected tile";
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

        if (civilian == null)
            return "error: no civilian on selected tile";
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
        Tile tile = Map.getInstance().getTileFromMap(x, y);

        if (tile == null) return "error: out of bounds";
        if (!tile.isCityCenter()) return "error: not a city center";

        City city = tile.getCity();
        if (city.getCivilization() != currentCivilizationController.getCivilization()) {
            return "error: this city is not from your civilization";
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
        String cityName = command.get(CITY_NAME.getKey());

        if (cityName.length() > 7) return "error: city name cannot exceed 7 characters";
        if (UnitController.getInstance().getUnit() == null) {
            return "error : no unit selected";
        } else {
            return UnitController.getInstance().unitFoundCity(cityName);
        }
    }


    public String unitBuildImprovement(HashMap<String, String> command) {
        String improvementName = command.get(IMPROVEMENT.getKey());
        Improvement improvement;

        if (UnitController.getInstance().getUnit() == null) return "error : no unit selected";

        try {
            improvement = Improvement.valueOf(improvementName.toUpperCase());
        } catch (IllegalArgumentException iae) {
            return "error: no improvement exists with given name";
        }

        return UnitController.getInstance().unitBuildImprovement(improvement);
    }

    public String unitBuildRoute(HashMap<String, String> command) {
        String routeName = command.get(ROUTE.getKey());
        Route route;

        if (UnitController.getInstance().getUnit() == null) return "error : no unit selected";

        try {
            route = Route.valueOf(routeName.toUpperCase());
        } catch (IllegalArgumentException iae) {
            return "error: no route exists with given name";
        }

        return UnitController.getInstance().unitBuildRoute(route);
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

    public String cityAssignCitizen(HashMap<String, String> command) {
        int x = Integer.parseInt(command.get(X_POSITION.getKey()));
        int y = Integer.parseInt(command.get(Y_POSITION.getKey()));
        Tile tile = Map.getInstance().getTileFromMap(x, y);

        if (tile == null) return "error: tile out of bounds";
        if (CityController.getInstance().getCity() == null) return "error: no city selected";

        return CityController.getInstance().assignCitizen(tile);
    }

    public String cityRemoveCitizen(HashMap<String, String> command) {
        int x = Integer.parseInt(command.get(X_POSITION.getKey()));
        int y = Integer.parseInt(command.get(Y_POSITION.getKey()));
        Tile tile = Map.getInstance().getTileFromMap(x, y);

        if (CityController.getInstance().getCity() == null) return "error: no city selected";
        if (tile == null) return "error: tile out of bounds";

        return CityController.getInstance().removeCitizen(tile);
    }

    // MAP COMMAND
    public ArrayList<String> mapShowAll(HashMap<String, String> command) {
        Civilization currentCivilization = this.currentCivilizationController.getCivilization();

        return Map.getInstance().updateAndPrintMap(currentCivilization);
    }

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

    // RESEARCH COMMANDS
    public String researchSet(HashMap<String, String> command) {
        String techName = command.get(TECHNOLOGY.getKey());
        Research research;

        try {
            research = Research.valueOf(techName.toUpperCase());
        } catch (IllegalArgumentException iae) {
            return "error: no research exists with given name";
        }

        Civilization civilization = currentCivilizationController.getCivilization();
        if (civilization.getResearchTree().isResearchLocked(research))
            return "error: research still locked";
        if (civilization.getResearchTree().isResearchDone(research))
            return "error: research already done";

        civilization.startResearch(research);
        return "started researching " + research;
    }

    // END OF TURN
    public String endOfTurn(HashMap<String, String> command) {
        return this.nextCivilization();
    }


    // LET THE FUN BEGIN
    // CHEAT CODE COMMANDS
    public String increaseTurn(HashMap<String, String> command) {
        int amount = Integer.parseInt(command.get(AMOUNT.getKey()));
        Civilization currentCivilization = this.currentCivilizationController.getCivilization();

        if (amount < 0) return "error: Illegal amount of turns";

        for (int i = 0; i < amount; i++) {
            currentCivilization.applyNewTurnChanges(currentYear);
            this.currentYear++;
        }

        return "turn increased";
    }

    public String increaseGold(HashMap<String, String> command) {
        int amount = Integer.parseInt(command.get(AMOUNT.getKey()));
        Civilization currentCivilization = this.currentCivilizationController.getCivilization();

        currentCivilization.setGold(currentCivilization.getGold() + amount);
        return "gold increased";
    }

    public String increaseResearchPoint(HashMap<String, String> command) {
        int amount = Integer.parseInt(command.get(AMOUNT.getKey()));
        Civilization currentCivilization = this.currentCivilizationController.getCivilization();

        currentCivilization.setResearchPoint(currentCivilization.getResearchPoint() + amount);
        return "research point increased";
    }

    public ArrayList<String> revealAll(HashMap<String, String> command) {
        Civilization currentCivilization = this.currentCivilizationController.getCivilization();

        FogOfWar.fogOfWarRevealAll(currentCivilization);

        ArrayList<String> result = Map.getInstance().printMap(currentCivilization);
        result.add("now you know where you're going");

        return result;
    }

    public String welcomeToUtopia(HashMap<String, String> command) {
        Civilization currentCivilization = this.currentCivilizationController.getCivilization();

        currentCivilization.setBaseHappiness(4000);
        currentCivilization.setHappiness(currentCivilization.calculateHappiness());

        switch (currentYear % 3) {
            case 0: return "so that's what living in Tehran feels like";
            case 1: return "now everybody loves you :)";
            case 2: return "here, you dropped this \uD83D\uDC51"; //the character is crown emoji
        }
        return "";
    }

    //UNIT CHEAT CODES
    public String marcopolo(HashMap<String, String> command) {
        Unit currentUnit = UnitController.getInstance().getUnit();
        if (currentUnit == null) return "error: no unit selected";

        currentUnit.setMaxMovement(4000);
        return "what souvenirs have you brought along Marco?";
    }

    public String terminator(HashMap<String, String> command) {
        Unit currentUnit = UnitController.getInstance().getUnit();
        if (currentUnit == null) return "error: no unit selected";

        currentUnit.setMeleeStrength(4000);
        currentUnit.setRangedStrength(4000);
        return "hasta la vista baby!";
    }

    public String instantHeal(HashMap<String, String> command) {
        Unit currentUnit = UnitController.getInstance().getUnit();
        if (currentUnit == null) return "error: no unit selected";

        currentUnit.setHealth(10);
        return "unit healed completely";
    }
}
