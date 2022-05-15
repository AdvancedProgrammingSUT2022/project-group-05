package view.menu;

import controller.GameMenuController;

import java.util.HashMap;
import java.util.Scanner;

import static view.enums.GameMenuCommand.*;

public class GameMenu extends Menu {

    public GameMenu(Scanner scanner) {
        super(scanner);
    }

    @Override
    public MenuType run() {
        String input;
        HashMap<String, String> command;

        while (true) {
            input = scanner.nextLine();

            if ((command = getHashMap(input, EXIT)) != null)
                return MenuType.EXIT;
            else if ((command = getHashMap(input, MENU_SHOW_CURRENT)) != null)
                printMessage("game menu");
            else if ((command = getHashMap(input, END_TURN)) != null)
                printMessage(GameMenuController.getInstance().endOfTurn(command));
            else if ((command = getHashMap(input, MENU_EXIT)) != null) {
                printMessage(GameMenuController.getInstance().menuExit(command));
                return MenuType.MAIN;
            }

            //INFO COMMANDS
            else if ((command = getHashMap(input, INFO_RESEARCH)) != null)
                printMessage(GameMenuController.getInstance().infoResearch(command)); //TODO... add controller function
            else if ((command = getHashMap(input, INFO_UNITS)) != null)
                printMessage(GameMenuController.getInstance().infoUnits(command));
            else if ((command = getHashMap(input, INFO_CITIES)) != null)
                printMessage(GameMenuController.getInstance().infoCities(command));
            else if ((command = getHashMap(input, INFO_DIPLOMACY)) != null)
                printMessage(GameMenuController.getInstance().infoDiplomacy(command));
            else if ((command = getHashMap(input, INFO_VICTORY)) != null)
                printMessage(GameMenuController.getInstance().infoVictory(command));
            else if ((command = getHashMap(input, INFO_DEMOGRAPHICS)) != null)
                printMessage(GameMenuController.getInstance().infoDemographics(command));
            else if ((command = getHashMap(input, INFO_NOTIFICATIONS)) != null)
                printMessage(GameMenuController.getInstance().infoNotification(command));
            else if ((command = getHashMap(input, INFO_MILITARY)) != null)
                printMessage(GameMenuController.getInstance().infoMilitary(command));
            else if ((command = getHashMap(input, INFO_ECONOMIC)) != null)
                printMessage(GameMenuController.getInstance().infoEconomic(command));
            else if ((command = getHashMap(input, INFO_DIPLOMATIC)) != null)
                printMessage(GameMenuController.getInstance().infoDiplomatic(command));
            else if ((command = getHashMap(input, INFO_DEALS)) != null)
                printMessage(GameMenuController.getInstance().infoDeals(command));
            else if ((command = getHashMap(input, INFO_TILE)) != null)
                printMessage(GameMenuController.getInstance().infoTile(command));
            else if ((command = getHashMap(input, INFO_TILE_STATS)) != null)
                printMessage(GameMenuController.getInstance().infoTileStats(command));
            else if ((command = getHashMap(input, INFO_TILE_PROJECT)) != null)
                printMessage(GameMenuController.getInstance().infoTileProject(command));

            //SELECT COMMANDS
            else if ((command = getHashMap(input, SELECT_UNIT_SOLDIER)) != null)
                printMessage(GameMenuController.getInstance().selectUnitSoldier(command));
            else if ((command = getHashMap(input, SELECT_UNIT_CIVILIAN)) != null)
                printMessage(GameMenuController.getInstance().selectUnitCivilian(command));
            else if ((command = getHashMap(input, SELECT_CITY_POSITION)) != null)
                printMessage(GameMenuController.getInstance().selectCityPosition(command));
            else if ((command = getHashMap(input, SELECT_CITY_NAME)) != null)
                printMessage(GameMenuController.getInstance().selectCityName(command));

            //UNIT COMMANDS
            else if ((command = getHashMap(input, UNIT_MOVE)) != null)
                printMessage(GameMenuController.getInstance().unitMove(command));
            else if ((command = getHashMap(input, UNIT_SLEEP)) != null)
                printMessage(GameMenuController.getInstance().unitSleep(command));
            else if ((command = getHashMap(input, UNIT_ALERT)) != null)
                printMessage(GameMenuController.getInstance().unitAlert(command));
            else if ((command = getHashMap(input, UNIT_FORTIFY)) != null)
                printMessage(GameMenuController.getInstance().unitFortify(command));
            else if ((command = getHashMap(input, UNIT_RECOVER)) != null)
                printMessage(GameMenuController.getInstance().unitRecover(command));
            else if ((command = getHashMap(input, UNIT_GARRISON)) != null)
                printMessage(GameMenuController.getInstance().unitGarrison(command));
            else if ((command = getHashMap(input, UNIT_SETUP_RANGED)) != null)
                printMessage(GameMenuController.getInstance().unitSetupRanged(command));
            else if ((command = getHashMap(input, UNIT_ATTACK)) != null)
                printMessage(GameMenuController.getInstance().unitAttack(command));
            else if ((command = getHashMap(input, UNIT_CANCEL)) != null)
                printMessage(GameMenuController.getInstance().unitCancel(command));
            else if ((command = getHashMap(input, UNIT_WAKE)) != null)
                printMessage(GameMenuController.getInstance().unitWake(command));
            else if ((command = getHashMap(input, UNIT_DELETE)) != null)
                printMessage(GameMenuController.getInstance().unitDelete(command));

            else if ((command =getHashMap(input, UNIT_FOUND_CITY)) != null)
                printMessage(GameMenuController.getInstance().unitFoundCity(command));
            else if ((command = getHashMap(input, UNIT_BUILD_IMPROVEMENT)) != null)
                printMessage(GameMenuController.getInstance().unitBuildImprovement(command));
            else if ((command = getHashMap(input, UNIT_BUILD_ROUTE)) != null)
                printMessage(GameMenuController.getInstance().unitBuildRoute(command));
            else if ((command = getHashMap(input, UNIT_REMOVE_JUNGLE)) != null)
                printMessage(GameMenuController.getInstance().unitRemoveJungle(command));
            else if ((command = getHashMap(input, UNIT_REMOVE_FOREST)) != null)
                printMessage(GameMenuController.getInstance().unitRemoveForest(command));
            else if ((command = getHashMap(input, UNIT_REMOVE_MARSH)) != null)
                printMessage(GameMenuController.getInstance().unitRemoveMarsh(command));
            else if ((command = getHashMap(input, UNIT_REMOVE_ROUTE)) != null)
                printMessage(GameMenuController.getInstance().unitRemoveRoute(command));
            else if ((command = getHashMap(input, UNIT_REPAIR)) != null)
                printMessage(GameMenuController.getInstance().unitRepair(command));

            //CITY COMMANDS
            else if ((command = getHashMap(input, CITY_CREATE_UNIT)) != null)
                printMessage(GameMenuController.getInstance().cityCreateUnit(command));
            else if ((command = getHashMap(input, CITY_CREATE_BUILDING)) != null)
                printMessage(GameMenuController.getInstance().cityCreateBuilding(command));
            else if ((command = getHashMap(input, CITY_BUY_TILE)) != null)
                printMessage(GameMenuController.getInstance().buyTile(command));
            else if ((command = getHashMap(input, CITY_PURCHASE_UNIT)) != null)
                printMessage(GameMenuController.getInstance().purchaseUnit(command));
            else if ((command = getHashMap(input, CITY_PURCHASE_BUILDING)) != null)
                printMessage(GameMenuController.getInstance().purchaseBuilding(command));

            //MAP COMMANDS
            else if ((command = getHashMap(input, MAP_SHOW_ALL)) != null)
                printMessage(GameMenuController.getInstance().mapShowAll(command));
            else if ((command = getHashMap(input, MAP_SHOW_CITY)) != null)
                printMessage(GameMenuController.getInstance().mapShowCity(command));
            else if ((command = getHashMap(input, MAP_SHOW_POSITION)) != null)
                printMessage(GameMenuController.getInstance().mapShowPosition(command));
            else if ((command = getHashMap(input, MAP_MOVE)) != null)
                printMessage(GameMenuController.getInstance().mapMove(command));

            //RESEARCH COMMANDS
            else if ((command = getHashMap(input, RESEARCH_SET)) != null)
                printMessage(GameMenuController.getInstance().researchSet(command));

            //CHEAT CODES
            else if ((command = getHashMap(input, INCREASE_TURN)) != null)
                printMessage(GameMenuController.getInstance().increaseTurn(command));
            else if ((command = getHashMap(input, INCREASE_GOLD)) != null)
                printMessage(GameMenuController.getInstance().increaseGold(command));
            else if ((command = getHashMap(input, INCREASE_TURN)) != null)
                printMessage(GameMenuController.getInstance().increaseTurn(command));
            else if ((command = getHashMap(input, INCREASE_GOLD)) != null)
                printMessage(GameMenuController.getInstance().increaseGold(command));
            else if ((command = getHashMap(input, INCREASE_RESEARCH_POINT)) != null)
                printMessage(GameMenuController.getInstance().increaseResearchPoint(command));

            else if ((command = getHashMap(input, KILL_SOLDIER)) != null)
                printMessage(GameMenuController.getInstance().killSoldier(command));
            else if ((command = getHashMap(input, KILL_CIVILIAN)) != null)
                printMessage(GameMenuController.getInstance().killCivilian(command));
            else if ((command = getHashMap(input, SPAWN_UNIT)) != null)
                printMessage(GameMenuController.getInstance().spawnUnit(command));
            else if ((command = getHashMap(input, REVEAL_ALL)) != null)
                printMessage(GameMenuController.getInstance().revealAll(command));
            else if ((command = getHashMap(input, WELCOME_TO_UTOPIA)) != null)
                printMessage(GameMenuController.getInstance().welcomeToUtopia(command));

            //UNIT CHEAT CODES
            else if ((command = getHashMap(input, MARCOPOLO)) != null)
                printMessage(GameMenuController.getInstance().marcopolo(command));
            else if ((command = getHashMap(input, TERMINATOR)) != null)
                printMessage(GameMenuController.getInstance().terminator(command));
            else if ((command = getHashMap(input, INSTANT_HEAL)) != null)
                printMessage(GameMenuController.getInstance().instantHeal(command));

            //INVALID
            else
                printMessage("error: invalid command");
        }
    }
}
