package view.menu;

import java.util.HashMap;
import java.util.Scanner;

import static view.enums.ProfileMenuCommand.*;

public class ProfileMenu extends Menu {
    public ProfileMenu(Scanner scanner) {
        super(scanner);
    }

    @Override
    public MenuType run() {
        String input;
        HashMap<String, String> command;

        printMessage("__PROFILE MENU__");

        while (true) {
            input = scanner.nextLine();

            if ((command = getHashMap(input, EXIT)) != null)
                return MenuType.EXIT;
            else if ((command = getHashMap(input, MENU_EXIT)) != null) {
                printMessage(""); //TODO... add controller function
                return MenuType.MAIN;
            }
            else if ((command = getHashMap(input, MENU_SHOW_CURRENT)) != null)
                printMessage("profile menu");
            else if ((command = getHashMap(input, PROFILE_CHANGE_PASSWORD)) != null)
                printMessage(""); //TODO... add controller function
            else if ((command = getHashMap(input, PROFILE_CHANGE_NICKNAME)) != null)
                printMessage(""); //TODO... add controller function
            else
                printMessage("error: invalid command");
        }
    }
}
