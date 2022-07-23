package controller;

import model.game.City;
import model.game.CityName;
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
import java.util.Collections;
import java.util.HashMap;

import static view.enums.Entity.*;

public class GameMenuController {
    //FIELDS
    private int civilizationCount;
    private ArrayList<CivilizationController> civilizationControllers = new ArrayList<>();
    private int currentTurn;
    private int currentYear;
    private CivilizationController currentCivilizationController;
    private ArrayList<String> cityNames;
    private boolean autoSave;


    //singleton
    private static GameMenuController instance;

    private GameMenuController(ArrayList<Civilization> civilizations) {
        this.civilizationCount = civilizations.size();
        this.cityNames = CityName.getCityNames();

        this.autoSave = true; //TODO add autoSave option in settings

        this.currentTurn = -1;
        this.currentYear = -1;

        for (int i = 0; i < civilizationCount; i++) {
            this.civilizationControllers.add(new CivilizationController(civilizations.get(i)));
        }
    }

    public static GameMenuController getInstance() {
        return instance;
    }

    public static void updateInstance(ArrayList<Civilization> civilizations) {
        instance = new GameMenuController(civilizations);
    }

    public static void updateInstance(GameMenuController gameMenuController) {
        instance = gameMenuController;
    }

    public static void destroyInstance() {
        instance = null;
    }
    // end of singleton design pattern

    public boolean isGameOver() {
        int notLostCount = 0;
        for (Civilization civilization : this.getCivilizations()) {
            if (!civilization.isLost()) notLostCount++;
        }

        if (notLostCount <= 1) return true;

        return currentYear >= 2500;
    }

    public int getCurrentYear() {
        return this.currentYear;
    }

    public String getRandomCityName() {
        Collections.shuffle(this.cityNames);
        String cityName = this.cityNames.get(0);
        this.cityNames.remove(0);
        return cityName;
    }

    public String nextCivilization() {
        if (currentTurn > -1)
            this.currentCivilizationController.searchForRequiredActions(); // search if there is any required actions left
        if (currentTurn > -1 && currentCivilizationController.hasRequiredAction()) { // check conditions for changing turn
            return "error: " + currentCivilizationController.getRequiredActions();
        }
        this.currentTurn++;
        this.currentTurn %= this.civilizationCount;
        if (this.currentTurn == 0) this.currentYear += 25;

        this.currentCivilizationController = civilizationControllers.get(currentTurn); // change civilization for new turn
        this.currentCivilizationController.getCivilization().applyNewTurnChanges(currentYear); // add production and gold and ... and progress productions

        CityController.updateInstance(null); // deselect city in new turn
        UnitController.updateInstance(null); // deselect unit in new turn

        //autoSaving
        if (autoSave) {
            Map.getInstance().save();
            this.currentCivilizationController.getCivilization().save();
        }

        return this.whoseTurnIsIt();
    }

    public String whoseTurnIsIt() {
        return  "Year " + this.currentYear + ": " + currentCivilizationController.getCivilization().getPlayer().getNickname() + "'s turn";
    }

    //INFO COMMANDS
    public String infoResearch() {
        return currentCivilizationController.showResearch();
    }

    public String infoUnits() {
        return currentCivilizationController.showUnitsPanel();
    }

    public String infoCities() {
        return currentCivilizationController.showCitiesPanel();
    }

    public String infoDiplomacy() {
        return currentCivilizationController.showDiplomacyPanel();
    }

    public String infoVictory() {
        return currentCivilizationController.showVictoryProgress();
    }

    public String infoDemographics() {
        return currentCivilizationController.showDemographic();
    }

    public String infoNotification() {
        return currentCivilizationController.showNotificationHistory();
    }

    public String infoMilitary() {
        return currentCivilizationController.showMilitaryOverview();
    }

    public String infoEconomic() {
        return currentCivilizationController.showEconomicOverview();
    }

    public String infoDiplomatic() {
        return currentCivilizationController.showDiplomaticOverview();
    }

    public String infoDeals() {
        return currentCivilizationController.showTradeHistory();
    }

//    public String infoTile(HashMap<String, String> command) {
//        int x = Integer.parseInt(command.get(X_POSITION.getKey()));
//        int y = Integer.parseInt(command.get(Y_POSITION.getKey()));
//
//        if (x < 0 || x > Map.getInstance().getSizeOfMap() - 1 || y < 0 || y > Map.getInstance().getSizeOfMap() - 1)
//            return "error: out of bound";
//
//        Tile tile = Map.getInstance().getTileFromMap(x, y);
//
//        if (tile == null) return Responses.TILE_OUT_OF_BOUND.getResponse();
//
//        return InfoController.getTileInfo(tile);
//    }
//
//    public String infoTileStats(HashMap<String, String> command) {
//        int x = Integer.parseInt(command.get(X_POSITION.getKey()));
//        int y = Integer.parseInt(command.get(Y_POSITION.getKey()));
//
//        if (x < 0 || x > Map.getInstance().getSizeOfMap() - 1 || y < 0 || y > Map.getInstance().getSizeOfMap() - 1)
//            return "error: out of bound";
//
//        Tile tile = Map.getInstance().getTileFromMap(x, y);
//
//        if (tile == null) return Responses.TILE_OUT_OF_BOUND.getResponse();
//
//        return InfoController.getTileStats(tile);
//    }
//
//    public String infoTileProject(HashMap<String, String> command) {
//        int x = Integer.parseInt(command.get(X_POSITION.getKey()));
//        int y = Integer.parseInt(command.get(Y_POSITION.getKey()));
//
//        if (x < 0 || x > Map.getInstance().getSizeOfMap() - 1 || y < 0 || y > Map.getInstance().getSizeOfMap() - 1)
//            return "error: out of bound";
//
//        Tile tile = Map.getInstance().getTileFromMap(x, y);
//
//        if (tile == null) return Responses.TILE_OUT_OF_BOUND.getResponse();
//
//        return InfoController.getTileProjectInfo(tile);
//    }

    //SELECT COMMANDS
    public String menuExit(HashMap<String, String> command) {
        destroyInstance();
        return "exiting Game";
    }

    public String selectUnitSoldier(HashMap<String, String> command) {
        int x = Integer.parseInt(command.get(X_POSITION.getKey()));
        int y = Integer.parseInt(command.get(Y_POSITION.getKey()));

        if (x < 0 || x > Map.getInstance().getSizeOfMap() - 1 || y < 0 || y > Map.getInstance().getSizeOfMap() - 1)
            return "error: out of bound";

        Soldier soldier = Map.getInstance().getTileFromMap(x, y).getSoldier();
        if (soldier == null)
            return Responses.NO_SOLDIER_ON_TILE.getResponse();
        if (soldier.getCivilization() != currentCivilizationController.getCivilization()) {
            return Responses.UNIT_NOT_FROM_CIVILIZATION.getResponse();
        } else {
            UnitController.updateInstance(soldier);
            return Responses.SOLDIER_SELECTED_SUCCESSFULLY.getResponse();
        }
    }

    public String selectUnitSoldier(int x, int y) {
        if (x < 0 || x > Map.getInstance().getSizeOfMap() - 1 || y < 0 || y > Map.getInstance().getSizeOfMap() - 1)
            return "error: out of bound";

        Soldier soldier = Map.getInstance().getTileFromMap(x, y).getSoldier();
        if (soldier == null)
            return Responses.NO_SOLDIER_ON_TILE.getResponse();
        if (soldier.getCivilization() != currentCivilizationController.getCivilization()) {
            return Responses.UNIT_NOT_FROM_CIVILIZATION.getResponse();
        } else {
            UnitController.updateInstance(soldier);
            return Responses.SOLDIER_SELECTED_SUCCESSFULLY.getResponse();
        }
    }

    public String selectUnitCivilian(HashMap<String, String> command) {
        int x = Integer.parseInt(command.get(X_POSITION.getKey()));
        int y = Integer.parseInt(command.get(Y_POSITION.getKey()));

        if (x < 0 || x > Map.getInstance().getSizeOfMap() - 1 || y < 0 || y > Map.getInstance().getSizeOfMap() - 1)
            return "error: out of bound";

        Civilian civilian = Map.getInstance().getTileFromMap(x, y).getCivilian();

        if (civilian == null)
            return Responses.NO_CIVILIAN_IN_TILE.getResponse();
        if (civilian.getCivilization() != currentCivilizationController.getCivilization()) {
            return Responses.UNIT_NOT_FROM_CIVILIZATION.getResponse();
        } else {
            UnitController.updateInstance(civilian);
            return Responses.CIVILIAN_SELECTED_SUCCESSFULLY.getResponse();
        }
    }

    public String selectUnitCivilian(int x, int y) {
        if (x < 0 || x > Map.getInstance().getSizeOfMap() - 1 || y < 0 || y > Map.getInstance().getSizeOfMap() - 1)
            return "error: out of bound";

        Civilian civilian = Map.getInstance().getTileFromMap(x, y).getCivilian();

        if (civilian == null)
            return Responses.NO_CIVILIAN_IN_TILE.getResponse();
        if (civilian.getCivilization() != currentCivilizationController.getCivilization()) {
            return Responses.UNIT_NOT_FROM_CIVILIZATION.getResponse();
        } else {
            UnitController.updateInstance(civilian);
            return Responses.CIVILIAN_SELECTED_SUCCESSFULLY.getResponse();
        }
    }

    public String selectCityPosition(HashMap<String, String> command) {
        int x = Integer.parseInt(command.get(X_POSITION.getKey()));
        int y = Integer.parseInt(command.get(Y_POSITION.getKey()));

        if (x < 0 || x > Map.getInstance().getSizeOfMap() - 1 || y < 0 || y > Map.getInstance().getSizeOfMap() - 1)
            return "error: out of bound";

        Tile tile = Map.getInstance().getTileFromMap(x, y);

        if (tile == null) return Responses.TILE_OUT_OF_BOUND.getResponse();
        if (!tile.isCityCenter()) return Responses.NOT_CITY_CENTER.getResponse();

        City city = tile.getCity();
        if (city.getCivilization() != currentCivilizationController.getCivilization()) {
            return Responses.CITY_NOT_FROM_CIVILIZATION.getResponse();
        } else {
            CityController.updateInstance(city);
            return Responses.CITY_SELECTED_SUCCESSFULLY.getResponse();
        }
    }

    public String selectCityPosition(int x, int y) {
        if (x < 0 || x > Map.getInstance().getSizeOfMap() - 1 || y < 0 || y > Map.getInstance().getSizeOfMap() - 1)
            return "error: out of bound";

        Tile tile = Map.getInstance().getTileFromMap(x, y);

        if (tile == null) return Responses.TILE_OUT_OF_BOUND.getResponse();
        if (!tile.isCityCenter()) return Responses.NOT_CITY_CENTER.getResponse();

        City city = tile.getCity();
        if (city.getCivilization() != currentCivilizationController.getCivilization()) {
            return Responses.CITY_NOT_FROM_CIVILIZATION.getResponse();
        } else {
            CityController.updateInstance(city);
            return Responses.CITY_SELECTED_SUCCESSFULLY.getResponse();
        }
    }

    public String selectCityName(HashMap<String, String> command) {
        String cityName = command.get(CITY_NAME.getKey());
        ArrayList<City> cities = currentCivilizationController.getCivilization().getCities();
        for (City city : cities) {
            if (city.getName().equals(cityName)) {
                CityController.updateInstance(city);
                return Responses.CITY_SELECTED_SUCCESSFULLY.getResponse();
            }
        }
        return Responses.NO_CITY_WITH_NAME.getResponse();
    }

    //UNIT COMMANDS
    public String unitMove(HashMap<String, String> command) {
        if (UnitController.getInstance().getUnit() == null) {
            return Responses.NO_UNIT_SELECTED.getResponse();
        }
        int xPlace = Integer.parseInt(command.get(X_POSITION.getKey()));
        int yPlace = Integer.parseInt(command.get(Y_POSITION.getKey()));

        if (xPlace < 0 || xPlace > Map.getInstance().getSizeOfMap() - 1 || yPlace < 0 || yPlace > Map.getInstance().getSizeOfMap() - 1)
            return "error: out of bound";

        return UnitController.getInstance().unitMove(xPlace, yPlace);
    }

    public String unitSleep(HashMap<String, String> command) {
        if (UnitController.getInstance().getUnit() == null) {
            return "error : no unit selected";
        }
        return UnitController.getInstance().unitSleep();

    }

    public String unitAlert(HashMap<String, String> command) {
        if (UnitController.getInstance().getUnit() == null) {
            return Responses.NO_UNIT_SELECTED.getResponse();
        }
        return UnitController.getInstance().unitAlert();

    }

    public String unitFortify(HashMap<String, String> command) {
        if (UnitController.getInstance().getUnit() == null) {
            return Responses.NO_UNIT_SELECTED.getResponse();
        }
        return UnitController.getInstance().unitFortify();
    }

    public String unitRecover(HashMap<String, String> command) {
        if (UnitController.getInstance().getUnit() == null) {
            return Responses.NO_UNIT_SELECTED.getResponse();
        }
        return UnitController.getInstance().unitRecover();
    }

    public String unitGarrison(HashMap<String, String> command) {
        if (UnitController.getInstance().getUnit() == null) {
            return Responses.NO_UNIT_SELECTED.getResponse();
        }
        return UnitController.getInstance().unitGarrison();
    }

    public String unitSetupRanged(HashMap<String, String> command) {
        if (UnitController.getInstance().getUnit() == null) {
            return Responses.NO_UNIT_SELECTED.getResponse();
        }
        return UnitController.getInstance().unitSetupRanged();
    }

    public String unitAttack(HashMap<String, String> command) {
        if (UnitController.getInstance().getUnit() == null) {
            return Responses.NO_UNIT_SELECTED.getResponse();
        }
        int xPlace = Integer.parseInt(command.get(X_POSITION.getKey()));
        int yPlace = Integer.parseInt(command.get(Y_POSITION.getKey()));

        if (xPlace < 0 || xPlace > Map.getInstance().getSizeOfMap() - 1 || yPlace < 0 || yPlace > Map.getInstance().getSizeOfMap() - 1)
            return "error: out of bound";

        return UnitController.getInstance().unitAttack(xPlace, yPlace);
    }

    public String unitCancel(HashMap<String, String> command) {
        if (UnitController.getInstance().getUnit() == null) {
            return Responses.NO_UNIT_SELECTED.getResponse();
        }
        return UnitController.getInstance().unitCancel();
    }

    public String unitWake(HashMap<String, String> command) {
        if (UnitController.getInstance().getUnit() == null) {
            return Responses.NO_UNIT_SELECTED.getResponse();
        }
        return UnitController.getInstance().unitWake();
    }

    public String unitDelete(HashMap<String, String> command) {
        if (UnitController.getInstance().getUnit() == null) {
            return Responses.NO_UNIT_SELECTED.getResponse();
        }
        return UnitController.getInstance().unitDelete();
    }


    public String unitFoundCity(HashMap<String, String> command) {
        String cityName = command.get(CITY_NAME.getKey());

        if (cityName.length() > 7) return Responses.CITY_NAME_CAB.getResponse();
        if (UnitController.getInstance().getUnit() == null) {
            return Responses.NO_UNIT_SELECTED.getResponse();
        }
        return UnitController.getInstance().unitFoundCity();
    }


    public String unitBuildImprovement(HashMap<String, String> command) {
        String improvementName = command.get(IMPROVEMENT.getKey());
        Improvement improvement;

        if (UnitController.getInstance().getUnit() == null) return Responses.NO_UNIT_SELECTED.getResponse();;

        try {
            improvement = Improvement.valueOf(improvementName.toUpperCase());
        } catch (IllegalArgumentException iae) {
            return Responses.NO_IMPROVEMENT_WITH_NAME.getResponse();
        }

        return UnitController.getInstance().unitBuildImprovement(improvement);
    }

    public String unitBuildRoute(HashMap<String, String> command) {
        String routeName = command.get(ROUTE.getKey());
        Route route;

        if (UnitController.getInstance().getUnit() == null) return Responses.NO_UNIT_SELECTED.getResponse();

        try {
            route = Route.valueOf(routeName.toUpperCase());
        } catch (IllegalArgumentException iae) {
            return Responses.NO_ROUTE_WITH_NAME.getResponse();
        }

        return UnitController.getInstance().unitBuildRoute(route);
    }

    public String unitRemoveJungle(HashMap<String, String> command) {
        if (UnitController.getInstance().getUnit() == null) {
            return Responses.NO_UNIT_SELECTED.getResponse();
        } else {
            return UnitController.getInstance().unitRemoveJungle();
        }
    }

    public String unitRemoveForest(HashMap<String, String> command) {
        if (UnitController.getInstance().getUnit() == null) {
            return Responses.NO_UNIT_SELECTED.getResponse();
        } else {
            return UnitController.getInstance().unitRemoveForest();
        }
    }

    public String unitRemoveMarsh(HashMap<String, String> command) {
        if (UnitController.getInstance().getUnit() == null) {
            return Responses.NO_UNIT_SELECTED.getResponse();
        } else {
            return UnitController.getInstance().unitRemoveMarsh();
        }
    }

    public String unitRemoveImprovement(HashMap<String, String> command) {
        if (UnitController.getInstance().getUnit() == null) {
            return Responses.NO_UNIT_SELECTED.getResponse();
        } else {
            return UnitController.getInstance().unitRemoveImprovement();
        }
    }

    public String unitRemoveRoute(HashMap<String, String> command) {
        if (UnitController.getInstance().getUnit() == null) {
            return Responses.NO_UNIT_SELECTED.getResponse();
        } else {
            return UnitController.getInstance().unitRemoveRoute();
        }
    }

    public String unitRepair(HashMap<String , String> command) {
        if (UnitController.getInstance().getUnit() == null) {
            return Responses.NO_UNIT_SELECTED.getResponse();
        } else {
            return UnitController.getInstance().unitRepair();
        }
    }

    //CITY COMMANDS
    public String cityCreateUnit(HashMap<String, String> command) {
        if (CityController.getInstance().getCity() == null) {
            return Responses.NO_CITY_SELECTED.getResponse();
        } else {
            String unitName = command.get(UNIT_NAME.getKey());
            return CityController.getInstance().cityCreateUnit(unitName);
        }
    }

    public String cityCreateBuilding(HashMap<String, String> command) {
        if (CityController.getInstance().getCity() == null) {
            return Responses.NO_CITY_SELECTED.getResponse();
        } else {
            String buildingName = command.get(BUILDING.getKey());
            return CityController.getInstance().cityCreateBuilding(buildingName);
        }
    }

    public String buyTile(HashMap<String, String> command) {
        if (CityController.getInstance().getCity() == null) {
            return Responses.NO_CITY_SELECTED.getResponse();
        }
        int x = Integer.parseInt(command.get(X_POSITION.getKey()));
        int y = Integer.parseInt(command.get(Y_POSITION.getKey()));

        if (x < 0 || x > Map.getInstance().getSizeOfMap() - 1 || y < 0 || y > Map.getInstance().getSizeOfMap() - 1)
            return "error: out of bound";

        return CityController.getInstance().buyTile(x, y);
    }

    public String purchaseUnit(HashMap<String, String> command) {
        if (CityController.getInstance().getCity() == null) {
            return Responses.NO_CITY_SELECTED.getResponse();
        } else {
            String unitName = command.get(UNIT_NAME.getKey());
            return CityController.getInstance().purchaseUnit(unitName);
        }
    }

    public String purchaseBuilding(HashMap<String, String> command) {
        if (CityController.getInstance().getCity() == null) {
            return Responses.NO_CITY_SELECTED.getResponse();
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

    public String cityShowTilesStats(HashMap<String, String> command) {
        City city = CityController.getInstance().getCity();

        if (city == null) return "error: no city selected";

        return InfoController.getCityTilesStats(city);
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

        if (x < 0 || x > Map.getInstance().getSizeOfMap() - 1 || y < 0 || y > Map.getInstance().getSizeOfMap() - 1)
            return "error: out of bound";

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
            return Responses.NO_RESEARCH_WITH_NAME.getResponse();
        }

        Civilization civilization = currentCivilizationController.getCivilization();
        if (civilization.getResearchTree().isResearchLocked(research))
            return Responses.LOCKED_RESEARCH.getResponse();
        if (civilization.getResearchTree().isResearchDone(research))
            return Responses.RESEARCH_ALREADY_DONE.getResponse();

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

        if (amount < 0) return Responses.ILLEGAL_TURNS.getResponse();

        for (int i = 0; i < amount; i++) {
            currentCivilization.applyNewTurnChanges(currentYear);
            this.currentYear++;
        }

        return Responses.TURN_INCREASED.getResponse();
    }

    public String increaseGold(HashMap<String, String> command) {
        int amount = Integer.parseInt(command.get(AMOUNT.getKey()));

        if (amount < 0) return "error: Illegal amount of golds";

        Civilization currentCivilization = this.currentCivilizationController.getCivilization();

        currentCivilization.setGold(currentCivilization.getGold() + amount);
        return Responses.GOLD_INCREASED.getResponse();
    }



    public String revealAll(HashMap<String, String> command) {
        Civilization currentCivilization = this.currentCivilizationController.getCivilization();

        FogOfWar.fogOfWarRevealAll(currentCivilization);

        ArrayList<String> result = Map.getInstance().printMap(currentCivilization);

        return Responses.REVEAL_MAP.getResponse();
    }

    public String industrialRevolution(HashMap<String, String> command) {
        Civilization currentCivilization = this.currentCivilizationController.getCivilization();

        currentCivilization.setBaseProduction(4000);
        currentCivilization.setProduction(currentCivilization.calculateProduction());

        return "do you feel like taking over the world?";
    }

    public String welcomeToUtopia(HashMap<String, String> command) {
        Civilization currentCivilization = this.currentCivilizationController.getCivilization();

        currentCivilization.setBaseHappiness(4000);
        currentCivilization.setHappiness(currentCivilization.calculateHappiness());

        switch (currentYear % 3) {
            case 0: return Responses.UTOPIA1.getResponse();
            case 1: return Responses.UTOPIA2.getResponse();
            case 2: return Responses.UTOPIA3.getResponse();
        }
        return "";
    }

    public String bigBrain(HashMap<String, String> command) {
        Civilization currenCivilization = this.currentCivilizationController.getCivilization();

        currenCivilization.setBaseResearchPoint(4000);
        currenCivilization.setResearchPoint(currenCivilization.calculateResearchPoint());

        return "wait, now this place kinda feels like Sharif university of technology";
    }

    //UNIT CHEAT CODES
    public String marcopolo(HashMap<String, String> command) {
        Unit currentUnit = UnitController.getInstance().getUnit();
        if (currentUnit == null) return Responses.NO_UNIT_SELECTED.getResponse();

        currentUnit.setMaxMovement(4000);
        return Responses.MARCO.getResponse();
    }

    public String terminator(HashMap<String, String> command) {
        Unit currentUnit = UnitController.getInstance().getUnit();
        if (currentUnit == null) return Responses.NO_UNIT_SELECTED.getResponse();

        currentUnit.setMeleeStrength(4000);
        currentUnit.setRangedStrength(4000);
        return Responses.TERMINATOR.getResponse();
    }

    public String instantHeal(HashMap<String, String> command) {
        Unit currentUnit = UnitController.getInstance().getUnit();
        if (currentUnit == null) return Responses.NO_UNIT_SELECTED.getResponse();

        currentUnit.setHealth(10);
        return Responses.HEALING_UNIT.getResponse();
    }

    public String killSoldier(HashMap<String, String> command) {
        int x = Integer.parseInt(command.get(X_POSITION.getKey()));
        int y = Integer.parseInt(command.get(Y_POSITION.getKey()));

        if (x < 0 || x > Map.getInstance().getSizeOfMap() - 1 || y < 0 || y > Map.getInstance().getSizeOfMap() - 1)
            return "error: out of bound";

        Tile tile = Map.getInstance().getTileFromMap(x, y);
        if (!tile.hasSoldier())
            return "error: no soldier in tile";
        UnitController.updateInstance(null);
        tile.getSoldier().getCivilization().removeUnit(tile.getSoldier());
        return "unit killed successfully";
    }

    public String killCivilian(HashMap<String, String> command) {
        int x = Integer.parseInt(command.get(X_POSITION.getKey()));
        int y = Integer.parseInt(command.get(Y_POSITION.getKey()));

        if (x < 0 || x > Map.getInstance().getSizeOfMap() - 1 || y < 0 || y > Map.getInstance().getSizeOfMap() - 1)
            return "error: out of bound";

        Tile tile = Map.getInstance().getTileFromMap(x, y);
        if (!tile.hasCivilian())
            return "error: no civilian in tile";
        UnitController.updateInstance(null);
        tile.getCivilian().getCivilization().removeUnit(tile.getCivilian());
        return "unit killed successfully";
    }

    public String spawnUnit(HashMap<String, String> command) {
        int x = Integer.parseInt(command.get(X_POSITION.getKey()));
        int y = Integer.parseInt(command.get(Y_POSITION.getKey()));

        if (x < 0 || x > Map.getInstance().getSizeOfMap() - 1 || y < 0 || y > Map.getInstance().getSizeOfMap() - 1)
            return "error: out of bound";

        Tile tile = Map.getInstance().getTileFromMap(x, y);

        String unitName = command.get(UNIT_NAME.getKey());
        Unit newUnit = GenerateUnit.StringToUnit(this.currentCivilizationController.getCivilization(), tile, unitName);
        if (newUnit == null)
            return "error: there is no unit with this name";
        this.currentCivilizationController.getCivilization().addUnit(newUnit);
        return "unit spawned successfully";

    }

    public CivilizationController getCurrentCivilizationController () {
        return currentCivilizationController;
    }

    //DATA
    public ArrayList<Civilization> getCivilizations() {
        ArrayList<Civilization> result = new ArrayList<>();

        for (CivilizationController civilizationController : this.civilizationControllers) {
            result.add(civilizationController.getCivilization());
        }

        return result;
    }

    public ArrayList<City> getCities() {
        ArrayList<City> result = new ArrayList<>();

        for (Civilization civilization : this.getCivilizations()) {
            result.addAll(civilization.getCities());
        }

        return result;
    }

    public ArrayList<Unit> getUnits() {
        ArrayList<Unit> result = new ArrayList<>();

        for (Civilization civilization : this.getCivilizations()) {
            result.addAll(civilization.getUnits());
        }

        return result;
    }
}
