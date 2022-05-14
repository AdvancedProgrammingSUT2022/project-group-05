package controller;

import model.User;
import model.game.Civilization;
import model.map.MapGeneration;
import utility.ListUtility;
import view.enums.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainMenuController {

    //Singleton definition
    private static MainMenuController instance;

    private MainMenuController() {
    }

    public static MainMenuController getInstance() {
        return instance;
    }

    public static void updateInstance() {
        /*
         * This function is not necessary if there is only a single instance of the Class throughout the program.
         * However, for handling singleton Classes that can be renewed or changes throughout the program, this
         * function can be quite useful. e.g. When you want to close the current game and make a new game.
         * and you don't want to run two games at the same time.
         */
        instance = new MainMenuController();
    }

    public String startGame(HashMap<String, String> command) {
        ArrayList<User> users = new ArrayList<>();
        ArrayList<Integer> oneToSize = new ArrayList<>();
        ArrayList<Integer> indices = new ArrayList<>();
        for (int i = 0; i < command.size(); i++) {
            oneToSize.add(i + 1);
        }

        for (Map.Entry<String, String> set: command.entrySet()) {
            indices.add(Integer.parseInt(set.getKey().substring(Entity.PLAYER.getKey().length())));
        }

        if (!ListUtility.isEqualInteger(oneToSize, indices)) {
            return "illegal player number";
        }

        //User catching phase
        ArrayList<String> usernames = new ArrayList<>(command.values());
        for (String username : usernames) {
            User user = UserDatabaseController.getUserByUsername(username);
            if (user == null) {
                return "username not found";
            } else {
                users.add(user);
            }
        }

        //Game initialization phase
        //map instantiation
        model.map.Map.updateInstance(10); //debugging purposes
        MapGeneration.mapCreator();

        //civilizations instantiation
        ArrayList<Civilization> civilizations = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            civilizations.add(new Civilization(users.get(i), i));
        }
        GameMenuController.updateInstance(usernames.size(), civilizations);
        return "game started successfully\n" +
                "__GAME MENU__\n" +
                GameMenuController.getInstance().nextCivilization();

    }

    //Setters

    //Getters
}
