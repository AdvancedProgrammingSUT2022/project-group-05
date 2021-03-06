package client;

import com.google.gson.Gson;
import controller.GameMenuController;
import controller.GameObjectData;
import controller.UnitController;
import graphics.view.gameContents.MainPanel;
import graphics.view.gameContents.MapFX;
import graphics.view.gameContents.UnitMenu;
import graphics.view.menus.Game;
import graphics.view.menus.GameOver;
import graphics.view.menus.ProfileMenu;
import graphics.view.menus.Scoreboard.ScoreboardMenu;
import graphics.view.menus.multiplayer.LobbyGuest;
import graphics.view.menus.multiplayer.LobbyHost;
import graphics.view.menus.multiplayer.MultiplayerGame;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Lobby;
import model.User;

import java.util.ArrayList;
import java.util.HashMap;

public class ClientManager{
    private final ArrayList<Lobby> invitedLobbies;

    private final HashMap<String, Pane> panes;
    private final Stage mainStage;
    private final Scene mainScene;
    private EventHandler<MouseEvent> handler = MouseEvent::consume;

    private User mainUser;

    public void updateScoreboard() {
        if (this.getMainScene().getRoot() instanceof ScoreboardMenu)
            ((ScoreboardMenu) this.getMainScene().getRoot()).updateScoreBoard();
    }

    public void updateProfileMenu() {
        if (this.getMainScene().getRoot() instanceof ProfileMenu) {
            ((ProfileMenu) this.getMainScene().getRoot()).update();
        }

    }

    public void addPane(String name, Pane pane) {
        panes.put(name, pane);
    }

    public synchronized void setPane(String name) {
        mainScene.setRoot(panes.get(name));
        mainStage.setScene(mainScene);
    }

    public void setPane(Pane pane) {
        mainScene.setRoot(pane);
        mainStage.setScene(mainScene);
    }

    public void setMainUser(User mainUser) {
        this.mainUser = mainUser;
    }

    public void updateMainUser() {
        if (mainUser == null) {
            return;
        }

        this.mainUser = getUserByUsername(mainUser.getUsername());
    }

    public static User getUserByUsername(String username) {
        Response getUserResponse = Client.send(ClientAdapter.getUser(username));
        String userJson = getUserResponse.getMessage();
        Gson gson = new Gson();
        return gson.fromJson(userJson, User.class);
    }


    public void update(boolean isLocal) {
        MapFX.getInstance().updateMapTextures(isLocal);
        if (UnitController.getInstance() != null) {
            UnitMenu.getInstance().update();
            //UnitMenu.getInstance().setVisible(UnitController.getInstance().getUnit() != null);
        }

        if (getMainScene().getRoot() instanceof Game && GameMenuController.getInstance().isGameOver()) {
            setPane(new GameOver());
            int score = GameMenuController.getInstance().getCivilizationByUsername(ClientManager.getInstance().getMainUser().getUsername()).calculateScore();
            Client.send(ClientAdapter.changeScore(score, ClientManager.getInstance().getMainUser().getUsername()));

            if (!GameMenuController.getInstance().getCivilizationByUsername(mainUser.getUsername()).isLost()) {
                Client.send("sending");
                Client.sendObject(GameObjectData.getInstance());
            }
        }

        for (Node node : ((Pane) mainScene.getRoot()).getChildren()) {
            if (node instanceof MainPanel) {
                ((MainPanel) node).updatePanel();
            }
        }
    }

    public void updateAll() {
        if (getMainScene().getRoot() instanceof Game) {
            Client.send("sending");
            Client.sendObject(GameObjectData.getInstance());
        }
    }

    public void updateLobby(Lobby lobby) {
        if (this.getMainScene().getRoot() instanceof LobbyHost) {
            ((LobbyHost) this.getMainScene().getRoot()).updateLobby(lobby);
        } else if (this.getMainScene().getRoot() instanceof LobbyGuest) {
            ((LobbyGuest) this.getMainScene().getRoot()).updateLobby(lobby);
        }
    }

    public void updateLobbyInvites() {
        if (this.getMainScene().getRoot() instanceof MultiplayerGame) {
            Platform.runLater(new Runnable(){
                @Override
                public void run() {
                    ClientManager.this.setPane(new MultiplayerGame());
                }
            });
        }
    }

    //GETTER
    public User getMainUser() {
        //debugging purpose
        //this.updateMainUser();
        return this.mainUser;
    }

    public Scene getMainScene() {
        return this.mainScene;
    }

    public Stage getMainStage() {
        return this.mainStage;
    }

    public ArrayList<Lobby> getInvitedLobbies() {
        return this.invitedLobbies;
    }

    //SINGLETON
    private static ClientManager instance;

    private ClientManager(Stage mainStage, Scene mainScene) {
        this.mainUser = null;

        this.invitedLobbies = new ArrayList<>();
        this.panes = new HashMap<>();

        this.mainStage = mainStage;
        this.mainScene = mainScene;

        mainScene.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                if (mainScene.getRoot() instanceof Game) {
                    update(((Game) mainScene.getRoot()).isLocal);
                    if (GameMenuController.getInstance().getCurrentCivilizationController().getCivilization()
                            .getPlayer().getUsername().equals(ClientManager.getInstance().getMainUser().getUsername())) {
                        if (!((Game) mainScene.getRoot()).isLocal) {
                            mainScene.getRoot().removeEventFilter(MouseEvent.ANY, handler);
                        }
                    } else {
                        if (!((Game) mainScene.getRoot()).isLocal) {
                            mainScene.getRoot().addEventFilter(MouseEvent.ANY, handler);
                        }
                    }
                }
            }
        });
    }

    public static ClientManager getInstance() {
        return instance;
    }

    public static void updateInstance(Stage mainStage, Scene mainScene) {
        instance = new ClientManager(mainStage, mainScene);
    }

    public void sendUpdatedLobbyToServer(Lobby lobby, String whoSendIt) { //use this after making change in lobby
        Request request = new Request("update");
        request.addParams("lobby", new Gson().toJson(lobby));
        request.addParams("whoSendIt", whoSendIt);
        Client.send(request.convertToJson());
    }

    public void closeLobby() {
        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                ClientManager.getInstance().setPane(new MultiplayerGame());
            }
        });
    }
}
