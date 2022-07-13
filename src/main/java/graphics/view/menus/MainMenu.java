package graphics.view.menus;

import javafx.animation.FadeTransition;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import graphics.objects.buttons.ButtonOne;
import graphics.objects.labels.LabelOne;
import graphics.statics.StaticFonts;

public class MainMenu extends Pane{
    public MainMenu () {
        //OBJECTS
        ButtonOne startGame = new ButtonOne("NEW GAME", StaticFonts.SeqoeLoad(60), Pos.CENTER,
                960, 200, 600, 80, this);
        ButtonOne scoreBoard = new ButtonOne("SCOREBOARD", StaticFonts.SeqoeLoad(20), Pos.CENTER,
                960, 650, 200, 50, this);
        ButtonOne profile = new ButtonOne("PROFILE", StaticFonts.SeqoeLoad(20), Pos.CENTER,
                960, 710, 200, 50, this);
        ButtonOne chat = new ButtonOne("CHAT", StaticFonts.SeqoeLoad(20), Pos.CENTER,
                960, 770, 200, 50, this);
        ButtonOne logout = new ButtonOne("LOGOUT", StaticFonts.SeqoeLoad(15), Pos.CENTER,
                960, 900, 100, 50, this);


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
    }
}
