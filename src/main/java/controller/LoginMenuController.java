package controller;

import model.User;
import static view.enums.Entity.*;

import java.util.HashMap;


public class LoginMenuController {

    UserDatabaseController userDatabaseController = new UserDatabaseController();


    public String createUser(HashMap<String, String> command) {
        String username = command.get(USERNAME.getKey());
        String password = command.get(PASSWORD.getKey());
        String nickname = command.get(NICKNAME.getKey());
        User user  = new User(username, nickname, password);
        if (UserDatabaseController.getUserByUsername(user.getUsername()) != null)
            return "user with username " + user.getUsername() + " already exists";
        if (UserDatabaseController.getUserByNickname(user.getNickname()) != null)
            return "user with nickname " + user.getNickname() + " already exists";
        userDatabaseController.addUser(user);
        return "user created successfully!";
    }

    public String loginUser(HashMap<String, String> command) {
        String username = command.get(USERNAME.getKey());
        String password = command.get(PASSWORD.getKey());
        User user = UserDatabaseController.getUserByUsername(username);
        if (user == null) {
            return Responses.USERNAME_PASSWORD_DIDNT_MATCH.getResponse();
        }
        if (!user.getPassword().equals(password)) {
            return Responses.USERNAME_PASSWORD_DIDNT_MATCH.getResponse();
        }
        return Responses.USER_LOGGED_IN.getResponse();
    }

}
