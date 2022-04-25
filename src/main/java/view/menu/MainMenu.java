package view.menu;

import java.util.HashMap;
import java.util.Scanner;

public class MainMenu extends Menu {
    public MainMenu(Scanner scanner) {
        super(scanner);
    }

    @Override
    public MenuType run() {
        return MenuType.EXIT;
    }

    @Override
    public HashMap<String, String> scanCommand(String command) {
        //TODO...
        return null;
    }
}
