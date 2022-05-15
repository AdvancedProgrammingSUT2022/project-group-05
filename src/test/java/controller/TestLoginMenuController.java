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
public class TestLoginMenuController {

    LoginMenuController loginMenuController;
    HashMap<String, String> command;

    @BeforeEach
    public void setup() {
        loginMenuController = new LoginMenuController();
        command = new HashMap<>();
        command.put(USERNAME.getKey(), "u");
        command.put(PASSWORD.getKey(), "p");
        command.put(NICKNAME.getKey(), "n");
    }

    @Test
    public void testCreateUser() {
        loginMenuController.createUser(command);
        User user = UserDatabaseController.getUserByUsername("u");
        Assertions.assertNotNull(user);
        loginMenuController.userDatabaseController.removeUser(user);
    }

    @Test
    public void testLoginUser() {
        loginMenuController.createUser(command);
        loginMenuController.loginUser(command);
        User user = UserDatabaseController.getUserByUsername("u");
        Assertions.assertNotNull(user);
        loginMenuController.userDatabaseController.removeUser(user);
    }
}
