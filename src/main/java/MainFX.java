
import graphics.view.PaneChanger;
import graphics.view.gameContents.MapFX;
import graphics.view.menus.*;
import graphics.view.popUp.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.map.Map;


public class MainFX extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setWidth(1920);
        primaryStage.setHeight(1080);
        primaryStage.setFullScreen(true);

        Scene primaryScene = new Scene(new Pane());

        PaneChanger.updateInstance(primaryStage, primaryScene);
        PaneChanger.getInstance().setPane(new LoginMenu());

        primaryStage.show();
    }

    public static void main(String[] args){
        launch();
    }
}
