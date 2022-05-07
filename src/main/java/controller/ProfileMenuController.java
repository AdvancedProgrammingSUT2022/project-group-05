package controller;

import model.User;

import java.util.ArrayList;
import java.util.HashMap;

import static view.enums.Entity.*;

public class ProfileMenuController {

    UserDatabaseController userDatabaseController;

    public String changeNickname(HashMap<String, String> command) {
        String newNickname = command.get(NICKNAME.getKey());
        int userIndex;
        if ((userIndex = userDatabaseController.getUserIndexByNickname(newNickname)) != -1) {
            return "user with nickname " + newNickname + " already exists";
        } else {
            userDatabaseController.changeNickname(userIndex, newNickname);
            return "nickname changed successfully!";
        }
    }

    public String changePassword(HashMap<String, String> command, String username) {
        String oldPassword = command.get(OLD_PASSWORD.getKey());
        String newPassword = command.get(NEW_PASSWORD.getKey());
        int userIndex = userDatabaseController.getUserIndexByUsername(username);
        if (userDatabaseController.getPasswordByIndex(userIndex).equals(oldPassword)) {
            return "current password is invalid";
        } else if (oldPassword.equals(newPassword)) {
            return "please enter a new password";
        } else {
            userDatabaseController.changePassword(userIndex, newPassword);
            return "password changed successfully!";
        }
    }
}
