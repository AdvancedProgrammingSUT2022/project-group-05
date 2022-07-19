package graphics.view.menus;

import graphics.objects.buttons.ButtonOne;
import graphics.statics.StaticFonts;
import client.ClientManager;
import javafx.animation.FadeTransition;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MainMenu extends Pane{
    public MainMenu () {

        int fromLeft = (int) ClientManager.getInstance().getMainStage().getWidth() / 2;
        int fromTop = (int) ClientManager.getInstance().getMainStage().getHeight() / 2 - 300;

        //OBJECTS

        Circle circle = new Circle();
            String imageAddress = ClientManager.getInstance().getMainUser().getImageAddress();
        try {
            FileInputStream image = new FileInputStream(imageAddress);
            ImagePattern imagePattern = new ImagePattern(new Image(image));
            circle.setFill(imagePattern);
            circle.setRadius(100);
            circle.setCenterX(100);
            circle.setCenterY(100);
            this.getChildren().add(circle);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ButtonOne startGame = new ButtonOne("NEW LOCAL GAME", StaticFonts.segoeLoad(40), Pos.CENTER,
                fromLeft, fromTop - 50, 600, 80, this);
        ButtonOne startMultiplayerGame = new ButtonOne("NEW MULTIPLAYER GAME", StaticFonts.segoeLoad(40), Pos.CENTER,
                fromLeft, fromTop + 50, 600, 80, this);
        ButtonOne scoreBoard = new ButtonOne("SCOREBOARD", StaticFonts.segoeLoad(20), Pos.CENTER,
                fromLeft, fromTop + 350, 200, 50, this);
        ButtonOne profile = new ButtonOne("PROFILE", StaticFonts.segoeLoad(20), Pos.CENTER,
                fromLeft, fromTop + 410, 200, 50, this);
        ButtonOne chat = new ButtonOne("CHAT", StaticFonts.segoeLoad(20), Pos.CENTER,
                fromLeft, fromTop + 470, 200, 50, this);
        ButtonOne logout = new ButtonOne("LOGOUT", StaticFonts.segoeLoad(15), Pos.CENTER,
                fromLeft, fromTop + 600, 100, 50, this);


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
        startMultiplayerGame.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ClientManager.getInstance().setPane(new MultiplayerGame());
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
