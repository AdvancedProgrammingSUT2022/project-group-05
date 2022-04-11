package controller;

public class MainMenuController
{
    //Fields
    int number;

    //Singleton definition
    private static MainMenuController instance;
    private MainMenuController(int number)
    {
        this.number = number;
    }

    public static MainMenuController getInstance()
    {
        return instance;
    }

    public static void updateInstance(int number)
    {
        /*
         * This function is not necessary if there is only a single instance of the Class throughout the program.
         * However, for handling singleton Classes that can be renewed or changes throughout the program, this
         * function can be quite useful. e.g. When you want to close the current game and make a new game.
         * and you don't want to run two games at the same time.
         */
        instance = new MainMenuController(number);
    }

    //Setters

    //Getters
}
