
import graphics.view.ClientManager;
import graphics.view.menus.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;


public class MainFX extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // find size of screen
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        primaryStage.setWidth(size.width);
        primaryStage.setHeight(size.height);
        primaryStage.setFullScreen(true);

        Scene primaryScene = new Scene(new Pane());

        ClientManager.updateInstance(primaryStage, primaryScene);
        ClientManager.getInstance().setPane(new LoginMenu());

        primaryStage.show();
    }

    public static void main(String[] args){
        launch();
    }
}
