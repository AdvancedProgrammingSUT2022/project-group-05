package controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static view.enums.Entity.*;

public class TestLoginMenuController{
    HashMap<String, String> command;
    @BeforeEach
    public void setUp() {
        command = new HashMap<>();
        command.put(USERNAME.getKey(), "Rogers");
        command.put(NICKNAME.getKey(), "Rogers");
        command.put(PASSWORD.getKey(), "Rogers");
    }

    @Test
    public void createUserTestFail() {
        LoginMenuController loginMenuController = new LoginMenuController();

        command.replace(USERNAME.getKey(), "babak");
        String response = loginMenuController.createUser(command);
    }

    @Test
    public void loginUserTestSuccess() {
        LoginMenuController loginMenuController = new LoginMenuController();

        String response = loginMenuController.loginUser(command);
    }
}
