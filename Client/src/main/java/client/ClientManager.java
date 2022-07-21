package client;

import com.google.gson.Gson;
import controller.UnitController;
import graphics.view.gameContents.MapFX;
import graphics.view.gameContents.UnitMenu;
import graphics.view.menus.Game;
import graphics.view.menus.multiplayer.LobbyGuest;
import graphics.view.menus.multiplayer.LobbyHost;
import graphics.view.menus.multiplayer.MultiplayerGame;
import javafx.event.EventHandler;
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

    private User mainUser;

    public void addPane(String name, Pane pane)
    {
        panes.put(name, pane);
    }

    public void setPane(String name)
    {
        mainScene.setRoot(panes.get(name));
        mainStage.setScene(mainScene);
    }
    public void setPane(Pane pane)
    {
        mainScene.setRoot(pane);
        mainStage.setScene(mainScene);
    }

    public void setMainUser(User mainUser) {
        this.mainUser =  mainUser;
    }

    public void updateMainUser() {
        if (mainUser == null) {
            return;
        }

        this.mainUser = getUserByUsername(mainUser.getUsername());
    }

    public User getUserByUsername(String username) {
        Response getUserResponse = Client.send(ClientAdapter.getUser(username));
        System.out.println(getUserResponse.getMessage() + " received response");
        String userJson = getUserResponse.getMessage();
        Gson gson = new Gson();
        return gson.fromJson(userJson, User.class);
    }


    public void update() {
        MapFX.getInstance().updateMapTextures();
        if (UnitController.getInstance() != null) {
            UnitMenu.getInstance().setVisible(UnitController.getInstance().getUnit() != null);
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
            this.setPane(new MultiplayerGame());
        }
    }

    //GETTER
    public User getMainUser() {
        this.updateMainUser();
        return this.mainUser;
    }

    public Scene getMainScene()
    {
        return this.mainScene;
    }

    public Stage getMainStage()
    {
        return this.mainStage;
    }

    public ArrayList<Lobby> getInvitedLobbies() {
        return this.invitedLobbies;
    }

    //SINGLETON
    private static ClientManager instance;
    private ClientManager(Stage mainStage, Scene mainScene)
    {
        this.mainUser = null;

        this.invitedLobbies = new ArrayList<>();
        this.panes = new HashMap<>();

        this.mainStage = mainStage;
        this.mainScene = mainScene;

        mainScene.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                if (mainScene.getRoot() instanceof Game) update();
            }
        });
    }
    public static ClientManager getInstance()
    {
        return instance;
    }
    public static void updateInstance(Stage mainStage, Scene mainScene)
    {
        instance = new ClientManager(mainStage, mainScene);
    }

    public void sendUpdatedLobbyToServer(Lobby lobby, String whoSendIt) { //use this after making change in lobby
        Request request = new Request("update");
        request.addParams("lobby", new Gson().toJson(lobby));
        request.addParams("whoSendIt", whoSendIt);
        Client.send(request.convertToJson());
    }
}
