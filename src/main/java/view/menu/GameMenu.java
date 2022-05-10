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

        printMessage("__GAME MENU__");

        while (true) {
            input = scanner.nextLine();

            if ((command = getHashMap(input, EXIT)) != null)
                return MenuType.EXIT;
            else if ((command = getHashMap(input, MENU_SHOW_CURRENT)) != null)
                printMessage("game menu");
            else if ((command = getHashMap(input, END_TURN)) != null)
                printMessage(GameMenuController.getInstance().endOfTurn(command));
            else if ((command = getHashMap(input, MENU_EXIT)) != null) {
                printMessage(""); //TODO... add controller function
                return MenuType.MAIN;
            }

            //INFO COMMANDS
            else if ((command = getHashMap(input, INFO_RESEARCH)) != null)
                printMessage(""); //TODO... add controller function
            else if ((command = getHashMap(input, INFO_UNITS)) != null)
                printMessage(""); //TODO... add controller function
            else if ((command = getHashMap(input, INFO_CITIES)) != null)
                printMessage(""); //TODO... add controller function
            else if ((command = getHashMap(input, INFO_DIPLOMACY)) != null)
                printMessage(""); //TODO... add controller function
            else if ((command = getHashMap(input, INFO_VICTORY)) != null)
                printMessage(""); //TODO... add controller function
            else if ((command = getHashMap(input, INFO_DEMOGRAPHICS)) != null)
                printMessage(""); //TODO... add controller function
            else if ((command = getHashMap(input, INFO_NOTIFICATIONS)) != null)
                printMessage(""); //TODO... add controller function
            else if ((command = getHashMap(input, INFO_MILITARY)) != null)
                printMessage(""); //TODO... add controller function
            else if ((command = getHashMap(input, INFO_ECONOMIC)) != null)
                printMessage(""); //TODO... add controller function
            else if ((command = getHashMap(input, INFO_DIPLOMATIC)) != null)
                printMessage(""); //TODO... add controller function
            else if ((command = getHashMap(input, INFO_DEALS)) != null)
                printMessage(""); //TODO... add controller function

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
            else if ((command = getHashMap(input, UNIT_BUILD)) != null)
                printMessage(GameMenuController.getInstance().unitBuild(command));
            else if ((command = getHashMap(input, UNIT_REMOVE_FEATURE)) != null)
                printMessage(GameMenuController.getInstance().unitRemoveFeature(command));
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
            else if ((command = getHashMap(input, MAP_SHOW_CITY)) != null)
                printMessage(GameMenuController.getInstance().mapShowCity(command));
            else if ((command = getHashMap(input, MAP_SHOW_POSITION)) != null)
                printMessage(GameMenuController.getInstance().mapShowPosition(command));
            else if ((command = getHashMap(input, MAP_MOVE)) != null)
                printMessage(GameMenuController.getInstance().mapMove(command));

            //CHEAT CODES
            else if ((command = getHashMap(input, INCREASE_TURN)) != null)
                printMessage(GameMenuController.getInstance().increaseTurn(command));
            else if ((command = getHashMap(input, INCREASE_GOLD)) != null)
                printMessage(GameMenuController.getInstance().increaseGold(command));
            //TODO add more cheat codes

            //INVALID
            else
                printMessage("error: invalid command");
        }
    }
}
