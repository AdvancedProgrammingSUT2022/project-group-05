package view.menu;

import java.util.Scanner;

public class ProfileMenu extends Menu {
    public ProfileMenu(Scanner scanner) {
        super(scanner);
    }

    @Override
    public MenuType run() {
        return MenuType.EXIT;
    }
}
