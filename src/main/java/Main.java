import view.menu.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        //TODO... remove this stuff from main for cleaner code (idk move it to some menu traverser function)

        Scanner scanner = new Scanner(System.in);

        ProfileMenu profileMenu = new ProfileMenu(scanner);
        LoginMenu loginMenu = new LoginMenu(scanner);
        MainMenu mainMenu = new MainMenu(scanner);
        GameMenu gameMenu = new GameMenu(scanner);

        MenuType currentMenu = MenuType.LOGIN;

        while (currentMenu != MenuType.EXIT) {
            if (currentMenu == MenuType.PROFILE) currentMenu = profileMenu.run();
            else if (currentMenu == MenuType.LOGIN) currentMenu = loginMenu.run();
            else if (currentMenu == MenuType.MAIN) currentMenu = mainMenu.run();
            else if (currentMenu == MenuType.GAME) currentMenu = gameMenu.run();
        }
    }
}
