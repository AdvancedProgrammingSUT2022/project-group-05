package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import view.menu.ChatMenu;
import view.menu.LoginMenu;
import view.menu.MainMenu;
import view.menu.ProfileMenu;

import java.util.HashMap;

public class Main extends Application {

    public static HashMap<String, Pane> panes = new HashMap<>();
    public static Stage mainStage;
    public static Scene mainScene;

    @Override
    public void start(Stage stage) throws Exception {
        MainMenu mainMenu = new MainMenu();
        ProfileMenu profileMenu = new ProfileMenu();
        LoginMenu loginMenu = new LoginMenu();
        ChatMenu chatMenu = new ChatMenu();
        panes.put("mainMenu", mainMenu.mainMenu());
        panes.put("scoreBoard", mainMenu.score());
        panes.put("profileMenu", profileMenu.profileMenu());
        panes.put("loginMenuLogin", loginMenu.login());
        panes.put("loginMenuRegister", loginMenu.register());
        panes.put("chatMenu", chatMenu.chatMenu());
        stage.setWidth(1920);
        stage.setHeight(1080);
        stage.setFullScreen(true);
        mainStage = stage;
        mainScene = new Scene(panes.get("loginMenuLogin"));
        showMenu("loginMenuLogin");
    }

    public static void showMenu(String menuName) {
        Pane thisPane = panes.get(menuName);
        mainScene.setRoot(thisPane);
        mainStage.setScene(mainScene);
        mainStage.show();
    }

    public static void main(String[] args){
        launch();
    }
}
