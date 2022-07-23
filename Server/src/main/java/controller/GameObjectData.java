package controller;

import model.map.Map;

import java.io.Serializable;

public class GameObjectData implements Serializable {

    private GameMenuController gameMenuController;
    private Map map;

    //singleton

    private static GameObjectData instance;

    private GameObjectData() {
        this.gameMenuController = GameMenuController.getInstance();
        this.map = Map.getInstance();
    }

    public static GameObjectData getInstance() {
        instance = new GameObjectData();
        return instance;
    }



    public GameMenuController getGameMenuController() {
        return gameMenuController;
    }

    public void setGameMenuController(GameMenuController gameMenuController) {
        this.gameMenuController = gameMenuController;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }
}
