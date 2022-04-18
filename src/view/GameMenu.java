package view;

import java.util.Scanner;

public class GameMenu extends Menu{
    public GameMenu(Scanner scanner) {
        super(scanner);
    }

    @Override
    public MenuType run() {
        return MenuType.EXIT;
    }
}
