package controller;

import model.User;

import javax.xml.stream.events.EntityReference;
import java.util.ArrayList;
import java.util.HashMap;

import static view.enums.Entity.*;

public class ProfileMenuController {

    public UserDatabaseController userDatabaseController = new UserDatabaseController();

    public String changeNickname(HashMap<String, String> command, String username) {
        String newNickname = command.get(NICKNAME.getKey());
        User user = UserDatabaseController.getUserByUsername(username);
        if (UserDatabaseController.getUserByNickname(newNickname) != null) {
            return "user with nickname " + newNickname + " already exists";
        } else {
            userDatabaseController.changeNickname(user, newNickname);
            return Responses.NICKNAME_CHANGED.getResponse();
        }
    }

    public String changePassword(HashMap<String, String> command, String username) {
        String oldPassword = command.get(OLD_PASSWORD.getKey());
        String newPassword = command.get(NEW_PASSWORD.getKey());
        User user = UserDatabaseController.getUserByUsername(username);
        if (!user.getPassword().equals(oldPassword)) {
            return Responses.INVALID_CURRENT_PASSWORD.getResponse();
        } else if (oldPassword.equals(newPassword)) {
            return Responses.DUPLICATED_PASSWORD.getResponse();
        } else {
            userDatabaseController.changePassword(user, newPassword);
            return Responses.PASSWORD_CHANGED.getResponse();
        }
    }
}
