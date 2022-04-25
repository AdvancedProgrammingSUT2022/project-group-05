package model.game;

import model.User;
import model.map.Map;

import java.util.ArrayList;

public class Game {

    private final int civilizationCount;
    private final ArrayList<Civilization> civilizations;

    private Map map;

    //Singleton pattern definition
    private static Game instance;

    private Game(int civilizationCount, ArrayList<User> players) {
        //TODO... what I've wrote here is (a bit of a) complete nonsense, fix this later
        this.civilizationCount = civilizationCount;
        this.civilizations = new ArrayList<>();

        for (int i = 0; i < players.size(); i++) {
            this.civilizations.add(new Civilization(null, null, players.get(i), i, 0));
        }
    }

    public static Game getInstance() {
        return instance;
    }

    public static void updateInstance(int civilizationCount, ArrayList<User> players) {
        instance = new Game(civilizationCount, players);
    }

    //end of Singleton pattern definition
}
