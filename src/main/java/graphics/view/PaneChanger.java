package graphics.view;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.HashMap;

public class PaneChanger{
    private final HashMap<String, Pane> panes;
    private final Stage mainStage;
    private final Scene mainScene;

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

    private static PaneChanger instance;
    private PaneChanger(Stage mainStage, Scene mainScene)
    {
        this.panes = new HashMap<>();

        this.mainStage = mainStage;
        this.mainScene = mainScene;
    }
    public static PaneChanger getInstance()
    {
        return instance;
    }
    public static void updateInstance(Stage mainStage, Scene mainScene)
    {
        instance = new PaneChanger(mainStage, mainScene);
    }
}
