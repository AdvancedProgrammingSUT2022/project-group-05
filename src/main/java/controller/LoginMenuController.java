package controller;

import javafx.scene.layout.Pane;
import main.Main;
import model.User;
import view.menu.ErrorBox;
import view.menu.MainMenu;

import static view.enums.Entity.*;

import java.util.HashMap;


public class LoginMenuController {

    UserDatabaseController userDatabaseController = new UserDatabaseController();


    public void createUser(String username, String password, String repeatPassword, String nickname, Pane father) {
        User user  = new User(username, nickname, password, null);
        if (UserDatabaseController.getUserByUsername(user.getUsername()) != null) {
            ErrorBox.getErrorBox("user with username " + user.getUsername() + " already exists", father, true);
        } else if (UserDatabaseController.getUserByNickname(user.getNickname()) != null) {
            ErrorBox.getErrorBox("user with nickname " + user.getNickname() + " already exists", father, true);
        } else if (!password.equals(repeatPassword)) {
            ErrorBox.getErrorBox("repeatedPassword incorrect", father, true);
        } else {
            userDatabaseController.addUser(user);
            MainMenu.username = username;
            Main.showMenu("mainMenu");
        }
    }

    public void loginUser(String username, String password, Pane father) {
        User user = UserDatabaseController.getUserByUsername(username);
        if (user == null) {
            ErrorBox.getErrorBox(Responses.USERNAME_PASSWORD_DIDNT_MATCH.getResponse(), father, true);
        } else if (!user.getPassword().equals(password)) {
            ErrorBox.getErrorBox(Responses.USERNAME_PASSWORD_DIDNT_MATCH.getResponse(), father, true);
        } else {
            MainMenu.username = username;
            Main.showMenu("mainMenu");
        }
    }

}
