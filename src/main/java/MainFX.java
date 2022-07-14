
import graphics.view.ClientManager;
import graphics.view.menus.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class MainFX extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setWidth(1920);
        primaryStage.setHeight(1080);
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
