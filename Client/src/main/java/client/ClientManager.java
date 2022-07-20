package client;

import com.google.gson.Gson;
import controller.UnitController;
import graphics.view.gameContents.MapFX;
import graphics.view.gameContents.UnitMenu;
import graphics.view.menus.Game;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Lobby;
import model.User;

import java.util.HashMap;

public class ClientManager{
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
        if (mainUser == null) return;
        this.mainUser = getUserByUsername(mainUser.getUsername());
    }

    public static User getUserByUsername(String username) {
        Response getUserResponse = Client.send(ClientAdapter.getUser(username));
        String userJson = getUserResponse.getMessage();
        Gson gson = new Gson();
        return gson.fromJson(userJson, User.class);
    }


    public static void update() {
        MapFX.getInstance().updateMapTextures();
        if (UnitController.getInstance() != null) {
            UnitMenu.getInstance().setVisible(UnitController.getInstance().getUnit() != null);
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

    //SINGLETON
    private static ClientManager instance;
    private ClientManager(Stage mainStage, Scene mainScene)
    {
        this.mainUser = null;

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

    public static void sendUpdatedLobbyToServer(Lobby lobby) { //use this after making change in lobby
        Request request = new Request("update");
        Gson gson = new Gson();
        String lobbyJson = gson.toJson(lobby);
        request.addParams("lobby", lobby);
        Client.send(request.convertToJson());
    }
}
