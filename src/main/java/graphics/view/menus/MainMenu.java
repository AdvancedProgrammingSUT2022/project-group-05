package graphics.view.menus;

import graphics.view.mapFX.TileFX;
import javafx.animation.FadeTransition;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import graphics.objects.buttons.ButtonOne;
import graphics.objects.labels.LabelOne;
import graphics.statics.StaticFonts;

public class MainMenu {
    public MainMenu () {

    }


    public Pane mainMenu () {
        Pane menu = new Pane();

        //OBJECTS
        ButtonOne startGame = new ButtonOne("NEW GAME", StaticFonts.SeqoeLoad(60), Pos.CENTER,
                960, 200, 600, 80, menu);
        ButtonOne scoreBoard = new ButtonOne("SCOREBOARD", StaticFonts.SeqoeLoad(20), Pos.CENTER,
                960, 650, 200, 50, menu);
        ButtonOne profile = new ButtonOne("PROFILE", StaticFonts.SeqoeLoad(20), Pos.CENTER,
                960, 710, 200, 50, menu);
        ButtonOne chat = new ButtonOne("CHAT", StaticFonts.SeqoeLoad(20), Pos.CENTER,
                960, 770, 200, 50, menu);
        ButtonOne logout = new ButtonOne("LOGOUT", StaticFonts.SeqoeLoad(15), Pos.CENTER,
                960, 900, 100, 50, menu);

        Pane temp = new Pane();
        new TileFX(temp, 0, 0);
        new TileFX(temp, 0, 1);
        new TileFX(temp, 1, 0);
        new TileFX(temp, 1, 1);
        menu.getChildren().add(temp);

        menu.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                System.out.println("Asdgadg");
            }
        });

        FadeTransition transition = new FadeTransition();
        transition.setDuration(new Duration(2000));
        transition.setNode(startGame);
        transition.play();
        transition.setFromValue(0.8);
        transition.setToValue(0.1);

        //FUNCTIONS
        startGame.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //TODO... opening new game menu
            }
        });
        scoreBoard.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //TODO... opening scoreboard menu
            }
        });
        profile.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //TODO... opening profile menu
            }
        });
        chat.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //TODO... opening chat menu
            }
        });
        logout.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //TODO... logout and opening login menu
            }
        });


        return menu;
    }


    public Pane score () {
        Pane menu = new Pane();

        String[] nicknames = new String[10]; //TODO... get from database
        for (int i = 0; i < 10; i++) nicknames[i] = "i" + "mamamm";
        int[] scores = new int[10]; //TODO... get from database
        //OBJECTS
        ButtonOne back = new ButtonOne("back", StaticFonts.SeqoeLoad(15), Pos.CENTER,
                100, 1000, 100, 50, menu);

        LabelOne[] ranks = new LabelOne[10];
        LabelOne[] nicknameLabels = new LabelOne[10];
        LabelOne[] scoreLabels = new LabelOne[10];

        for (int i = 0; i < 10; i++) {
            ranks[i] = new LabelOne(i+1 + " :", StaticFonts.SeqoeLoad(15), Pos.CENTER_LEFT,
                    400, 50 * i + 400, 200, 30, menu);
            nicknameLabels[i] = new LabelOne(nicknames[i], StaticFonts.SeqoeLoad(15), Pos.CENTER_LEFT,
                    450, 50 * i + 400, 200, 30, menu);
            scoreLabels[i] = new LabelOne(scores[i] + "", StaticFonts.SeqoeLoad(15), Pos.CENTER_RIGHT,
                    1520, 50 * i + 400, 200, 30, menu);
        }

        //FUNCTIONS
        back.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //TODO... opening main menu
            }
        });

        return menu;
    }
}
