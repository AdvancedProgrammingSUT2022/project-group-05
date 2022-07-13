import graphics.view.gameContents.MapFX;
import graphics.view.gameContents.TileMenu;
import graphics.view.menus.ChatMenu;
import graphics.view.menus.LoginMenu;
import graphics.view.menus.MainMenu;
import graphics.view.menus.ProfileMenu;
import graphics.view.popUp.PopUp;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
        pane.getChildren().add(TileMenu.getInstance(null));
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.A)
                    new PopUp(pane, paneTemp);
            }
        });

        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    public static void main(String[] args){
        launch();
    }
}
