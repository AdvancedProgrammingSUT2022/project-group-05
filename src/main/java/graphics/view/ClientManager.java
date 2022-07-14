package graphics.view;

import controller.UserDatabaseController;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
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

    public void setMainUser(User mainUser)
    {
        this.mainUser =  mainUser;
    }

    public void updateMainUser()
    {
        if (mainUser == null) return;
        this.mainUser = UserDatabaseController.getUserByUsername(mainUser.getUsername());
    }

    //GETTER
    public User getMainUser()
    {
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
    }
    public static ClientManager getInstance()
    {
        return instance;
    }
    public static void updateInstance(Stage mainStage, Scene mainScene)
    {
        instance = new ClientManager(mainStage, mainScene);
    }
}