package view;

import java.util.Scanner;

public class MainMenu extends Menu{
    public MainMenu(Scanner scanner) {
        super(scanner);
    }

    @Override
    public MenuType run() {
        return MenuType.EXIT;
    }
}
