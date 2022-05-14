package view.menu;

import controller.LoginMenuController;
import controller.MainMenuController;
import controller.UserDatabaseController;

import java.util.HashMap;
import java.util.Scanner;

import static view.enums.LoginMenuCommand.*;
import static view.enums.Entity.*;

public class LoginMenu extends Menu {

    private LoginMenuController loginMenuController = new LoginMenuController();
    private UserDatabaseController userDatabaseController = new UserDatabaseController();

    public LoginMenu(Scanner scanner) {
        super(scanner);
    }

    @Override
    public MenuType run() {
        String input;
        HashMap<String, String> command;

        printMessage("__LOGIN MENU__");

        while(true) {
            input = scanner.nextLine();

            if ((command = getHashMap(input, EXIT)) != null)
                return MenuType.EXIT;
            else if ((command = getHashMap(input, MENU_SHOW_CURRENT)) != null)
                printMessage("login menu");
            else if ((command = getHashMap(input, USER_CREATE)) != null) {
                printMessage(loginMenuController.createUser(command));
            }
            else if ((command = getHashMap(input, USER_LOGIN)) != null) {
                String message = loginMenuController.loginUser(command);
                printMessage(message);
                if (message.equals("user logged in successfully!")) {
                    MainMenu.setUsername(command.get(USERNAME.getKey()));
                    MainMenuController.updateInstance();
                    return MenuType.MAIN;
                }
            }
            else
                printMessage("error: invalid command");
        }
    }
}
