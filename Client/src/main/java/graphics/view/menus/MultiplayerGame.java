package graphics.view.menus;

import controller.GameMenuController;
//import controller.UserDatabaseController;
import graphics.objects.buttons.ButtonOne;
import graphics.objects.buttons.ButtonTwo;
import graphics.objects.labels.LabelOne;
import graphics.objects.textFields.TextFieldOne;
import graphics.statics.StaticFonts;
import client.ClientManager;
import graphics.view.gameContents.MapFX;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.game.Civilization;
import model.map.Map;

import java.util.ArrayList;

public class MultiplayerGame extends Pane {
    LabelOne title;

    ButtonTwo small;
    ButtonTwo medium;
    ButtonTwo big;
    ButtonTwo huge;

    TextFieldOne inviteText;
    ButtonOne invite;

    LabelOne firstPlayer;
    LabelOne secondPlayer;
    LabelOne thirdPlayer;
    LabelOne fourthPlayer;

    ButtonOne createGame;
    ButtonOne back;

    public MultiplayerGame() {
        title = new LabelOne("NEW MULTIPLAYER GAME", StaticFonts.segoeLoad(50), Pos.CENTER,
                960, 200, 1000, 70, this);

        small = new ButtonTwo("SMALL", StaticFonts.segoeLoad(40), Pos.CENTER,
                500, 400, 400, 60, this);
        medium = new ButtonTwo("MEDIUM", StaticFonts.segoeLoad(40), Pos.CENTER,
                500, 500, 400, 60, this);
        big = new ButtonTwo("BIG", StaticFonts.segoeLoad(40), Pos.CENTER,
                500, 600, 400, 60, this);
        huge = new ButtonTwo("HUGE", StaticFonts.segoeLoad(40), Pos.CENTER,
                500, 700, 400, 60, this);

        inviteText = new TextFieldOne("TYPE USERNAME", StaticFonts.segoeLoad(20), Pos.CENTER,
                960, 500, 400, 40, this);
        invite = new ButtonOne("INVITE", StaticFonts.segoeLoad(30), Pos.CENTER,
                960, 600, 200, 60, this);


        firstPlayer = new LabelOne("YOU", StaticFonts.segoeLoad(40), Pos.CENTER,
                1420, 400, 400, 60, this);
        secondPlayer = new LabelOne("NO PLAYER", StaticFonts.segoeLoad(40), Pos.CENTER,
                1420, 500, 400, 60, this);
        thirdPlayer = new LabelOne("NO PLAYER", StaticFonts.segoeLoad(40), Pos.CENTER,
                1420, 600, 400, 60, this);
        fourthPlayer = new LabelOne("NO PLAYER", StaticFonts.segoeLoad(40), Pos.CENTER,
                1420, 700, 400, 60, this);

        createGame = new ButtonOne("START GAME", StaticFonts.segoeLoad(50), Pos.CENTER,
                960, 900, 600, 70, this);
        back = new ButtonOne("back", StaticFonts.segoeLoad(15), Pos.CENTER,
                960, 1000, 100, 50, this);

        createGame.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //Debugging purposes
                Map.updateInstance(10);
                Civilization civilization1 = new Civilization(ClientManager.getInstance().getMainUser(), 0);
                Civilization civilization2 = new Civilization(ClientManager.getUserByUsername("sam"), 1);
                ArrayList<Civilization> civilizations = new ArrayList<>();
                civilizations.add(civilization1);
                civilizations.add(civilization2);
                GameMenuController.updateInstance(2, civilizations);
                ClientManager.getInstance().setPane(new Game(civilization1));
                GameMenuController.getInstance().nextCivilization();
                MapFX.getInstance().updateMapTextures();
            }
        });

        back.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                ClientManager.getInstance().setPane(new MainMenu());
            }
        });
    }
}
