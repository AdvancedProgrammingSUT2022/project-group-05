import graphics.view.mapFX.MapFX;
import graphics.view.menus.ChatMenu;
import graphics.view.menus.LoginMenu;
import graphics.view.menus.MainMenu;
import graphics.view.menus.ProfileMenu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
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
        primaryStage.setWidth(1720);
        primaryStage.setHeight(800);
        Map.updateInstance(5);
        pane.getChildren().add(MapFX.getInstance());
        MapFX.getInstance().setLayoutY(300);
        MapFX.getInstance().setLayoutX(0);
        Scene scene = new Scene(pane);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args){
        launch();
    }
}
