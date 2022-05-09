package view.menu;

import controller.MainMenuController;

import java.util.HashMap;
import java.util.Scanner;

import static view.enums.MainMenuCommand.*;

public class MainMenu extends Menu {

    private static String username;

    public MainMenu(Scanner scanner) {
        super(scanner);
    }

    @Override
    public MenuType run() {
        String input;
        HashMap<String, String> command;

        printMessage("__MAIN MENU__");

        while (true) {
            input = scanner.nextLine();

            if ((command = getHashMap(input, EXIT)) != null)
                return MenuType.EXIT;
            else if ((command = getHashMap(input, MENU_EXIT)) != null) {
                printMessage(""); //TODO... add controller function
                return MenuType.LOGIN;
            }
            else if ((command = getHashMap(input, MENU_SHOW_CURRENT)) != null)
                printMessage("main menu");
            else if ((command = getHashMap(input, SHOW_PROFILE)) != null) {
                ProfileMenu.setUsername(this.username);
                printMessage(""); //TODO... add controller function
                return MenuType.PROFILE;
            } else if ((command = getHashMap(input, USER_LOGOUT)) != null) {
                printMessage("user logged out successfully!");
                return MenuType.LOGIN;
            }
            else if ((command = getHashMap(input, PLAY_GAME)) != null) {
                String response = MainMenuController.getInstance().startGame(command);
                printMessage(response);
                if (response == "game started successfully") // TODO clean programming...
                    return MenuType.GAME;
            }
            else
                printMessage("error: invalid command");
        }
    }

    public static void setUsername(String username) {
        MainMenu.username = username;
    }
}
