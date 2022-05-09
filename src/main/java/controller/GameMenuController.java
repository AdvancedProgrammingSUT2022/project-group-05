package controller;

import model.game.Civilization;
import model.game.Game;

import java.util.ArrayList;

public class GameMenuController {
    //FIELDS
    private int civilizationCount;
    private ArrayList<CivilizationController> civilizationControllers;
    private int nowTurn;
    private Civilization currentCivilization;

    //singleton
    private static GameMenuController instance;

    private GameMenuController(int civilizationCount, ArrayList<Civilization> civilizations) {
        this.nowTurn = 0;
        for (int i = 0; i < civilizationCount; i++) {
            this.civilizationControllers.add(new CivilizationController(civilizations.get(i)));
        }
    }

    public void nextCivilization() {
        int numberOfPlayers = Game.getInstance().getCivilizationCount();
        this.nowTurn++;
        this.nowTurn %= numberOfPlayers;
    }

    public static GameMenuController getInstance() {
        return instance;
    }

    public static void updateInstance(int civilizationCount, ArrayList<Civilization> civilizations) {
        instance = new GameMenuController(civilizationCount, civilizations);
    }

    // end of singleton design pattern

    //GAME COMMAND VERIFICATION (METHODS)


    {
        // how to get current civilization
        Civilization currentCivilization = Game.getInstance().getCivilizations().get(nowTurn);
    }
}
