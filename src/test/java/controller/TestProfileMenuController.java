package controller;


import model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;

import static view.enums.Entity.*;

@ExtendWith(MockitoExtension.class)
public class TestProfileMenuController {

    ProfileMenuController profileMenuController;
    HashMap<String, String> command;
    String username;
    User user;

    @BeforeEach
    public void setup() {
        user = new User("u","n", "p");
        profileMenuController = new ProfileMenuController();
        command = new HashMap<>();
        command.put(NICKNAME.getKey(), "ni");
        command.put(OLD_PASSWORD.getKey(), "p");
        command.put(NEW_PASSWORD.getKey(), "pp");
        username = "u";
    }

//    @Test
//    public void testChangeNickname() {
//        profileMenuController.userDatabaseController.addUser(user);
//        profileMenuController.changeNickname(command, username);
//        User foundedUser = UserDatabaseController.getUserByUsername("u");
//        String result = foundedUser.getNickname();
//        Assertions.assertEquals("ni", result);
//        profileMenuController.userDatabaseController.removeUser(foundedUser);
//    }

//    @Test
//    public void testChangePassword() {
//        profileMenuController.userDatabaseController.addUser(user);
//        profileMenuController.changePassword(command, username);
//        User foundedUser = UserDatabaseController.getUserByUsername("u");
//        String result = foundedUser.getPassword();
//        Assertions.assertEquals("pp", result);
//        profileMenuController.userDatabaseController.removeUser(user);
//    }

}
