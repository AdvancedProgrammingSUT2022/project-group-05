
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
        MainMenu mainMenu = new MainMenu();
        ProfileMenu profileMenu = new ProfileMenu();
        LoginMenu loginMenu = new LoginMenu();
        ChatMenu chatMenu = new ChatMenu();
        Pane pane = new Pane();
        primaryStage.setWidth(1920);
        primaryStage.setHeight(1080);
        Map.updateInstance(5);
        pane.getChildren().add(MapFX.getInstance());
        MapFX.getInstance().setLayoutY(300);
        MapFX.getInstance().setLayoutX(0);
        Scene scene = new Scene(pane);
        Pane paneTemp = new Pane();
        Rectangle rectangle = new Rectangle(300, 400);
        rectangle.setFill(Color.RED);
        paneTemp.setPrefWidth(300);
        paneTemp.setPrefHeight(400);
        paneTemp.getChildren().add(rectangle);
        new PopUp(pane, new CheatCode());
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    public static void main(String[] args){
        launch();
    }
}
