package view.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public abstract class Menu {
    protected Scanner scanner;

    public Menu(Scanner scanner) {
        this.scanner = scanner;
    }

    //RUN
    public abstract MenuType run();

    //PRINT
    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printMessage(ArrayList<String> message) {
        for (String line : message) {
            System.out.println(line);
        }
    }

}
