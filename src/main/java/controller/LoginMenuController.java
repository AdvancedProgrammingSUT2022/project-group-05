package controller;

import model.User;
import static view.enums.Entity.*;

import java.util.HashMap;


public class LoginMenuController {

    UserDatabaseController userDatabaseController;

    public String createUser(HashMap<String, String> command) {
        String username = command.get(USERNAME.getKey());
        String password = command.get(PASSWORD.getKey());
        String nickname = command.get(NICKNAME.getKey());
        User user  = new User(username, password, nickname);
        if (userDatabaseController.getUserIndexByUsername(user.getUsername()) != -1) {
            return "user with username " + user.getUsername() + " already exists";
        } else if (userDatabaseController.getUserIndexByNickname(user.getNickname()) != -1) {
            return "user with nickname " + user.getNickname() + " already exists";
        } else {
            userDatabaseController.addUser(user);
            return "user created successfully!";
        }
    }

    public String loginUser(HashMap<String, String> command) {
        String username = command.get(USERNAME.getKey());
        String password = command.get(PASSWORD.getKey());
        int userIndex = userDatabaseController.getUserIndexByUsername(username);
        if (userIndex == -1) {
            return "Username and password didn't match!";
        } else {
            if (!userDatabaseController.isPasswordCorrect(userIndex, password)) {
                return "Username and password didn't match!";
            } else {
                //TODO.. goto game menu
                return "user logged in successfully!";
            }
        }
    }

}
