package view.menu;

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
                printMessage(""); //TODO... add controller function
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
                printMessage(""); //TODO... add controller function
            else if ((command = getHashMap(input, SELECT_UNIT_CIVILIAN)) != null)
                printMessage(""); //TODO... add controller function
            else if ((command = getHashMap(input, SELECT_CITY_POSITION)) != null)
                printMessage(""); //TODO... add controller function
            else if ((command = getHashMap(input, SELECT_CITY_NAME)) != null)
                printMessage(""); //TODO... add controller function

            //UNIT COMMANDS
            else if ((command = getHashMap(input, UNIT_MOVE)) != null)
                printMessage(""); //TODO... add controller function
            else if ((command = getHashMap(input, UNIT_SLEEP)) != null)
                printMessage(""); //TODO... add controller function
            else if ((command = getHashMap(input, UNIT_ALERT)) != null)
                printMessage(""); //TODO... add controller function
            else if ((command = getHashMap(input, UNIT_FORTIFY)) != null)
                printMessage(""); //TODO... add controller function
            else if ((command = getHashMap(input, UNIT_RECOVER)) != null)
                printMessage(""); //TODO... add controller function
            else if ((command = getHashMap(input, UNIT_GARRISON)) != null)
                printMessage(""); //TODO... add controller function
            else if ((command = getHashMap(input, UNIT_SETUP_RANGED)) != null)
                printMessage(""); //TODO... add controller function
            else if ((command = getHashMap(input, UNIT_ATTACK)) != null)
                printMessage(""); //TODO... add controller function
            else if ((command = getHashMap(input, UNIT_CANCEL)) != null)
                printMessage(""); //TODO... add controller function\
            else if ((command = getHashMap(input, UNIT_WAKE)) != null)
                printMessage(""); //TODO... add controller function
            else if ((command = getHashMap(input, UNIT_DELETE)) != null)
                printMessage(""); //TODO... add controller function
            else if ((command =getHashMap(input, UNIT_FOUND_CITY)) != null)
                printMessage(""); //TODO... add controller function
            else if ((command = getHashMap(input, UNIT_BUILD)) != null)
                printMessage(""); //TODO... add controller function
            else if ((command = getHashMap(input, UNIT_REMOVE_FEATURE)) != null)
                printMessage(""); //TODO... add controller function
            else if ((command = getHashMap(input, UNIT_REMOVE_ROUTE)) != null)
                printMessage(""); //TODO... add controller function
            else if ((command = getHashMap(input, UNIT_REPAIR)) != null)
                printMessage(""); //TODO... add controller function

            //CITY COMMANDS


            //MAP COMMANDS
            else if ((command = getHashMap(input, MAP_SHOW_CITY)) != null)
                printMessage(""); //TODO... add controller function
            else if ((command = getHashMap(input, MAP_SHOW_POSITION)) != null)
                printMessage(""); //TODO... add controller function
            else if ((command = getHashMap(input, MAP_MOVE)) != null)
                printMessage(""); //TODO... add controller function

            //CHEAT CODES
            else if ((command = getHashMap(input, INCREASE_TURN)) != null)
                printMessage(""); //TODO... add controller function
            else if ((command = getHashMap(input, INCREASE_GOLD)) != null)
                printMessage(""); //TODO... add controller function

            //INVALID
            else
                printMessage("error: invalid command");
        }
    }
}
