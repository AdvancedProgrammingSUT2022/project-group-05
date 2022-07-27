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
import javafx.animation.ParallelTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.User;
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

    LabelOne numberOfPlayer;
    LabelOne mapSize;

    public LocalGame() {

        int fromLeft = (int) ClientManager.getInstance().getMainStage().getWidth() / 2;

        title = new LabelOne("NEW LOCAL GAME", StaticFonts.segoeLoad(50), Pos.CENTER,
                fromLeft, 100, 600, 70, this);

        small = new ButtonTwo("SMALL", StaticFonts.segoeLoad(40), Pos.CENTER,
                fromLeft - 270, 200, 400, 60, this);
        medium = new ButtonTwo("MEDIUM", StaticFonts.segoeLoad(40), Pos.CENTER,
                fromLeft - 270, 300, 400, 60, this);
        big = new ButtonTwo("BIG", StaticFonts.segoeLoad(40), Pos.CENTER,
                fromLeft - 270, 400, 400, 60, this);

        two = new ButtonTwo("2 PLAYERS", StaticFonts.segoeLoad(40), Pos.CENTER,
                fromLeft + 280, 200, 400, 60, this);
        three = new ButtonTwo("3 PLAYERS", StaticFonts.segoeLoad(40), Pos.CENTER,
                fromLeft + 280, 300, 400, 60, this);
        four = new ButtonTwo("4 PLAYERS", StaticFonts.segoeLoad(40), Pos.CENTER,
                fromLeft + 280, 400, 400, 60, this);

        mapSize = new LabelOne("Medium map 15x15", StaticFonts.segoeLoad(20), Pos.CENTER,
                680, 250, 1000, 60, this);
        numberOfPlayer = new LabelOne("Three player", StaticFonts.segoeLoad(20), Pos.CENTER,
                680, 320, 1000, 60, this);

        createGame = new ButtonOne("CREATE GAME", StaticFonts.segoeLoad(50), Pos.CENTER,
                fromLeft, 500, 600, 70, this);
        back = new ButtonOne("back", StaticFonts.segoeLoad(15), Pos.CENTER,
                fromLeft, 600, 100, 50, this);

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
                        if (size == 1) {
                            Map.updateInstance(10);
                        } else if (size == 2) {
                            Map.updateInstance(15);
                        } else if (size == 3) {
                            Map.updateInstance(20);
                        }
                        Civilization civilization1 = new Civilization(ClientManager.getInstance().getMainUser(), 0);
                        Civilization civilization2 = new Civilization(new User("player2", "player2", "player2", null, 0), 1);
                        Civilization civilization3 = new Civilization(new User("player3", "player3", "player3", null, 0), 2);
                        Civilization civilization4 = new Civilization(new User("player4", "player4", "player4", null, 0), 3);
                        ArrayList<Civilization> civilizations = new ArrayList<>();
                        civilizations.add(civilization1);
                        if (players >= 2)
                            civilizations.add(civilization2);
                        if (players >= 3)
                            civilizations.add(civilization3);
                        if (players >= 4)
                            civilizations.add(civilization4);
                        GameMenuController.updateInstance(civilizations);
                        GameMenuController.getInstance().nextCivilization();
                        ClientManager.getInstance().setPane(new Game(civilization1, true));
                        MapFX.getInstance().updateMapTextures(true);
                    }
                });
                end.play();
            }
        });

        two.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                LocalGame.this.players = 2;
                numberOfPlayer.setText("Two player");
            }
        });

        three.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                LocalGame.this.players = 3;
                numberOfPlayer.setText("Three player");
            }
        });

        four.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                LocalGame.this.players = 4;
                numberOfPlayer.setText("Four player");
            }
        });

        small.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                LocalGame.this.size = 1;
                mapSize.setText("Small map 10x10");
            }
        });

        medium.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                LocalGame.this.size = 2;
                mapSize.setText("Medium map 15x15");
            }
        });

        big.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                LocalGame.this.size = 3;
                mapSize.setText("Big map 20x20");
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
