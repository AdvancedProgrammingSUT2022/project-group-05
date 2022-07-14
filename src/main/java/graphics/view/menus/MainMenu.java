package graphics.view.menus;

import graphics.view.ClientManager;
import javafx.animation.FadeTransition;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import graphics.objects.buttons.ButtonOne;
import graphics.statics.StaticFonts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MainMenu extends Pane{
    public MainMenu () {
        //OBJECTS

        Circle circle = new Circle();
        String imageAddress = ClientManager.getInstance().getMainUser().getImageAddress();
        try {
            FileInputStream image = new FileInputStream(imageAddress);
            ImagePattern imagePattern = new ImagePattern(new Image(image));
            circle.setFill(imagePattern);
            circle.setRadius(100);
            circle.setCenterX(400);
            circle.setCenterY(200);
            this.getChildren().add(circle);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ButtonOne startGame = new ButtonOne("NEW GAME", StaticFonts.segoeLoad(60), Pos.CENTER,
                960, 200, 600, 80, this);
        ButtonOne scoreBoard = new ButtonOne("SCOREBOARD", StaticFonts.segoeLoad(20), Pos.CENTER,
                960, 650, 200, 50, this);
        ButtonOne profile = new ButtonOne("PROFILE", StaticFonts.segoeLoad(20), Pos.CENTER,
                960, 710, 200, 50, this);
        ButtonOne chat = new ButtonOne("CHAT", StaticFonts.segoeLoad(20), Pos.CENTER,
                960, 770, 200, 50, this);
        ButtonOne logout = new ButtonOne("LOGOUT", StaticFonts.segoeLoad(15), Pos.CENTER,
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
                ClientManager.getInstance().setPane(new LocalGame());
            }
        });
        scoreBoard.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    ClientManager.getInstance().setPane(new ScoreboardMenu());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        profile.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ClientManager.getInstance().setPane(new ProfileMenu());
            }
        });
        chat.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ClientManager.getInstance().setPane(new ChatMenu());
            }
        });
        logout.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ClientManager.getInstance().setMainUser(null);
                ClientManager.getInstance().setPane(new LoginMenu());
            }
        });
    }
}
