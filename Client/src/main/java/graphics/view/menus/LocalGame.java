package graphics.view.menus;

import controller.GameMenuController;
//import controller.UserDatabaseController;
import graphics.objects.buttons.ButtonOne;
import graphics.objects.buttons.ButtonTwo;
import graphics.objects.labels.LabelOne;
import graphics.statics.StaticFonts;
import client.ClientManager;
import graphics.view.gameContents.MapFX;
import javafx.animation.ParallelTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.game.Civilization;
import model.map.Map;

import java.util.ArrayList;

//TODO add functions

public class LocalGame extends Pane {
    int size = 2;
    int players = 2;

    LabelOne title;

    ButtonTwo small;
    ButtonTwo medium;
    ButtonTwo big;

    ButtonTwo two;
    ButtonTwo three;
    ButtonTwo four;

    ButtonOne createGame;
    ButtonOne back;

    public LocalGame() {

        int fromLeft = (int) ClientManager.getInstance().getMainStage().getWidth() / 2;

        title = new LabelOne("NEW LOCAL GAME", StaticFonts.segoeLoad(50), Pos.CENTER,
                fromLeft, 200, 600, 70, this);

        small = new ButtonTwo("SMALL", StaticFonts.segoeLoad(40), Pos.CENTER,
                fromLeft - 270, 400, 400, 60, this);
        medium = new ButtonTwo("MEDIUM", StaticFonts.segoeLoad(40), Pos.CENTER,
                fromLeft - 270, 500, 400, 60, this);
        big = new ButtonTwo("BIG", StaticFonts.segoeLoad(40), Pos.CENTER,
                fromLeft - 270, 600, 400, 60, this);

        two = new ButtonTwo("2 PLAYERS", StaticFonts.segoeLoad(40), Pos.CENTER,
                fromLeft + 280, 400, 400, 60, this);
        three = new ButtonTwo("3 PLAYERS", StaticFonts.segoeLoad(40), Pos.CENTER,
                fromLeft + 280, 500, 400, 60, this);
        four = new ButtonTwo("4 PLAYERS", StaticFonts.segoeLoad(40), Pos.CENTER,
                fromLeft + 280, 600, 400, 60, this);

        createGame = new ButtonOne("CREATE GAME", StaticFonts.segoeLoad(50), Pos.CENTER,
                fromLeft, 800, 600, 70, this);
        back = new ButtonOne("back", StaticFonts.segoeLoad(15), Pos.CENTER,
                fromLeft, 950, 100, 50, this);

        //ANIMATION
        ParallelTransition start = AnimatedPane.getStartAnimation(this);
        ParallelTransition end = AnimatedPane.getEndAnimation(this);
        start.play();

        createGame.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                end.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        //Debugging purposes
                        Map.updateInstance(10);
                        Civilization civilization1 = new Civilization(ClientManager.getInstance().getMainUser(), 0);
                        Civilization civilization2 = new Civilization(ClientManager.getUserByUsername("sam"), 1);
                        ArrayList<Civilization> civilizations = new ArrayList<>();
                        civilizations.add(civilization1);
                        civilizations.add(civilization2);
                        GameMenuController.updateInstance(civilizations);
                        ClientManager.getInstance().setPane(new Game(civilization1));
                        GameMenuController.getInstance().nextCivilization();
                        MapFX.getInstance().updateMapTextures();
                    }
                });
                end.play();
            }
        });

        back.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                end.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        ClientManager.getInstance().setPane(new MainMenu());
                    }
                });
                end.play();
            }
        });

    }
}
