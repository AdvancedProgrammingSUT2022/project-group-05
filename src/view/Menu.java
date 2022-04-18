package view;

import java.util.Scanner;

public abstract class Menu{
    protected Scanner scanner;

    public Menu(Scanner scanner) {
        this.scanner = scanner;
    }

    public abstract MenuType run();

    public void printMessage(String message) {
        System.out.println(message);
    }

}
