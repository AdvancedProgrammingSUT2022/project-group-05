package main;
//import view.menu.*;
//
//import java.util.Scanner;
//
//public class main.Main {
//    public static void main(String[] args)
//    {
//        Scanner scanner = new Scanner(System.in);
//
//        ProfileMenu profileMenu = new ProfileMenu(scanner);
//        LoginMenu loginMenu = new LoginMenu(scanner);
//        MainMenu mainMenu = new MainMenu(scanner);
//        GameMenu gameMenu = new GameMenu(scanner);
//
//        MenuType currentMenu = MenuType.LOGIN;
//
//        while (currentMenu != MenuType.EXIT) {
//            if (currentMenu == MenuType.PROFILE) currentMenu = profileMenu.run();
//            else if (currentMenu == MenuType.LOGIN) currentMenu = loginMenu.run();
//            else if (currentMenu == MenuType.MAIN) currentMenu = mainMenu.run();
//            else if (currentMenu == MenuType.GAME) currentMenu = gameMenu.run();
//        }
//    }
//}


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
        showMenu("loginMenuLogin");
    }

    public static void showMenu(String menuName) {
        Pane thisPane = panes.get(menuName);
        Scene thisScene = new Scene(thisPane);
        mainStage.setScene(thisScene);
        mainStage.show();
    }

    public static void main(String[] args){
        launch();
    }
}
