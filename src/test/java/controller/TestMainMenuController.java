package controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;

import static view.enums.Entity.*;

@ExtendWith(MockitoExtension.class)
public class TestMainMenuController {

    MainMenuController mainMenuController;
    HashMap<String, String> command;


    @BeforeEach
    public void setup() {
        MainMenuController.updateInstance();
        mainMenuController = MainMenuController.getInstance();
        command = new HashMap<>();
        command.put("player1", PLAYER.getKey());
        command.put("player2", PLAYER.getKey());
        command.put("player3",PLAYER.getKey());
    }

    @Test
    public void testStartGame() {
        mainMenuController.startGame(command);
    }

}
