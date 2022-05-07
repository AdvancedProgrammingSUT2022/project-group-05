package view.menu;

import controller.LoginMenuController;
import controller.UserDatabaseController;

import java.util.HashMap;
import java.util.Scanner;

import static view.enums.LoginMenuCommand.*;

public class LoginMenu extends Menu {

    private LoginMenuController loginMenuController;
    private UserDatabaseController userDatabaseController;

    public LoginMenu(Scanner scanner) {
        super(scanner);
    }

    @Override
    public MenuType run() {
        String input;
        HashMap<String, String> command;

        printMessage("__LOGIN MENU__");

        while(true) { //TODO... call functions from controllers
            input = scanner.nextLine();

            if ((command = getHashMap(input, EXIT)) != null)
                return MenuType.EXIT;
            else if ((command = getHashMap(input, MENU_SHOW_CURRENT)) != null)
                printMessage("login menu");
            else if ((command = getHashMap(input, USER_CREATE)) != null) {
                printMessage(loginMenuController.createUser(command));
            }
            else if ((command = getHashMap(input, USER_LOGIN)) != null) {
                printMessage(loginMenuController.loginUser(command));
                return MenuType.MAIN;
            }
            else
                printMessage("error: invalid command");
        }
    }
}
