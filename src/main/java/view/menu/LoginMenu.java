//package view.menu;
//
//import controller.LoginMenuController;
//import controller.MainMenuController;
//import controller.Responses;
//import controller.UserDatabaseController;
//
//import java.util.HashMap;
//import java.util.Scanner;
//
//import static view.enums.LoginMenuCommand.*;
//import static view.enums.Entity.*;
//
//public class LoginMenu extends Menu {
//
//    private LoginMenuController loginMenuController = new LoginMenuController();
//    private UserDatabaseController userDatabaseController = new UserDatabaseController();
//
//    public LoginMenu(Scanner scanner) {
//        super(scanner);
//    }
//
//    @Override
//    public MenuType run() {
//        String input;
//        HashMap<String, String> command;
//
//        printMessage("__LOGIN MENU__");
//
//        while(true) {
//            input = scanner.nextLine();
//
//            if ((command = getHashMap(input, EXIT)) != null)
//                return MenuType.EXIT;
//            else if ((command = getHashMap(input, MENU_SHOW_CURRENT)) != null)
//                printMessage("login menu");
//            else if ((command = getHashMap(input, USER_CREATE)) != null) {
//                printMessage(loginMenuController.createUser(command));
//            }
//            else if ((command = getHashMap(input, USER_LOGIN)) != null) {
//                String message = loginMenuController.loginUser(command);
//                printMessage(message);
//                if (message.equals("user logged in successfully!")) {
//                    MainMenu.setUsername(command.get(USERNAME.getKey()));
//                    MainMenuController.updateInstance();
//                    return MenuType.MAIN;
//                }
//            }
//            else
//                printMessage(Responses.INVALID_COMMAND.getResponse());
//        }
//    }
//}


package view.menu;

import controller.LoginMenuController;
import controller.Responses;
import controller.UserDatabaseController;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import main.Main;
import model.graphicObjects.ButtonOne;
import model.graphicObjects.TextFieldOne;
import statics.StaticFonts;

public class LoginMenu {

    private LoginMenuController loginMenuController = new LoginMenuController();
    private UserDatabaseController userDatabaseController = new UserDatabaseController();


    public LoginMenu () {

    }

    public Pane login() {
        Pane menu = new Pane();

        //OBJECTS
        TextFieldOne username = new TextFieldOne("username", StaticFonts.SeqoeLoad(20), Pos.CENTER,
                960, 450, 400, 30, menu);
        TextFieldOne password = new TextFieldOne("password", StaticFonts.SeqoeLoad(20), Pos.CENTER,
                960, 500, 400, 30, menu);
        ButtonOne login = new ButtonOne("LOGIN", StaticFonts.SeqoeLoad(30), Pos.CENTER,
                960, 600, 300, 50, menu);

        ButtonOne signUp = new ButtonOne("SIGN UP", StaticFonts.SeqoeLoad(30), Pos.CENTER,
                960, 700, 300, 50, menu);

        ButtonOne exit = new ButtonOne("EXIT", StaticFonts.SeqoeLoad(15), Pos.CENTER,
                960, 900, 100, 50, menu);

        //FUNCTIONS
        login.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String usernameGot = username.getText();
                String passwordGot = password.getText();
                loginMenuController.loginUser(usernameGot, passwordGot, menu);
            }
        });
        signUp.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Main.showMenu("loginMenuRegister");
            }
        });
        exit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //TODO Exit
            }
        });

        return menu;
    }

    public Pane register () {
        Pane menu = new Pane();

        //OBJECTS
        TextFieldOne username = new TextFieldOne("username", StaticFonts.SeqoeLoad(20), Pos.CENTER,
                960, 400, 400, 30, menu);
        TextFieldOne password = new TextFieldOne("password", StaticFonts.SeqoeLoad(20), Pos.CENTER,
                960, 450, 400, 30, menu);
        TextFieldOne repeatPassword = new TextFieldOne("repeat password", StaticFonts.SeqoeLoad(20), Pos.CENTER,
                960, 500, 400, 30, menu);
        TextFieldOne nickname = new TextFieldOne("nickname", StaticFonts.SeqoeLoad(20), Pos.CENTER,
                960, 550, 400, 30, menu);

        ButtonOne signUp = new ButtonOne("SIGN UP", StaticFonts.SeqoeLoad(30), Pos.CENTER,
                960, 650, 300, 50, menu);

        ButtonOne back = new ButtonOne("back", StaticFonts.SeqoeLoad(15), Pos.CENTER,
                100, 1000, 100, 50, menu);

        //FUNCTIONS
        signUp.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String usernameGot = username.getText();
                String passwordGot = password.getText();
                String repeatPasswordGot = repeatPassword.getText();
                String nicknameGot = nickname.getText();
                //TODO... handling register and if true getting back to login menu
                loginMenuController.createUser(usernameGot, passwordGot, repeatPasswordGot, nicknameGot, menu);
            }
        });
        back.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Main.showMenu("loginMenuLogin");
            }
        });

        return menu;
    }
}
