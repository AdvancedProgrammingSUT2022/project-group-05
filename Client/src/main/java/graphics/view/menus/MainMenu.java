package graphics.view.menus;

import client.Client;
import client.ClientAdapter;
import graphics.objects.buttons.ButtonOne;
import graphics.objects.labels.LabelOne;
import graphics.statics.StaticFonts;
import client.ClientManager;
import graphics.view.menus.Scoreboard.ScoreboardMenu;
import graphics.view.menus.multiplayer.MultiplayerGame;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.event.ActionEvent;
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
        LabelOne loginUserName = new LabelOne(ClientManager.getInstance().getMainUser().getNickname(), StaticFonts.segoeLoad(20), Pos.CENTER,
                960, 250, 400, 40, this);
        String imageAddress = ClientManager.getInstance().getMainUser().getImageAddress();
        try {
            FileInputStream image = new FileInputStream(imageAddress);
            ImagePattern imagePattern = new ImagePattern(new Image(image));
            circle.setFill(imagePattern);
            circle.setLayoutX(860);
            circle.setLayoutY(20);
            circle.setRadius(100);
            circle.setCenterX(100);
            circle.setCenterY(100);
            this.getChildren().add(circle);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ButtonOne startGame = new ButtonOne("LOCAL", StaticFonts.segoeLoad(40), Pos.CENTER,
                fromLeft, fromTop + 100, 400, 80, this);
        ButtonOne startMultiplayerGame = new ButtonOne("MULTIPLAYER", StaticFonts.segoeLoad(40), Pos.CENTER,
                fromLeft, fromTop + 200, 400, 80, this);
        ButtonOne scoreBoard = new ButtonOne("SCOREBOARD", StaticFonts.segoeLoad(20), Pos.CENTER,
                fromLeft, fromTop + 450, 200, 50, this);
        ButtonOne profile = new ButtonOne("PROFILE", StaticFonts.segoeLoad(20), Pos.CENTER,
                fromLeft, fromTop + 510, 200, 50, this);
        ButtonOne chat = new ButtonOne("CHAT", StaticFonts.segoeLoad(20), Pos.CENTER,
                fromLeft, fromTop + 570, 200, 50, this);
        ButtonOne logout = new ButtonOne("LOGOUT", StaticFonts.segoeLoad(15), Pos.CENTER,
                fromLeft, fromTop + 750, 100, 50, this);

        //ANIMATION
        ParallelTransition start = AnimatedPane.getStartAnimation(this);
        ParallelTransition end = AnimatedPane.getEndAnimation(this);
        start.play();

        //FUNCTIONS
        startGame.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                end.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        ClientManager.getInstance().setPane(new LocalGame());
                    }
                });
                end.play();
            }
        });
        startMultiplayerGame.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                end.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        ClientManager.getInstance().setPane(new MultiplayerGame());
                    }
                });
                end.play();
            }
        });
        scoreBoard.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                end.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            ClientManager.getInstance().setPane(new ScoreboardMenu());
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                });
                end.play();
            }
        });
        profile.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                end.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        ClientManager.getInstance().updateMainUser();
                        ClientManager.getInstance().setPane(new ProfileMenu());
                    }
                });
                end.play();
            }
        });
        chat.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                end.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        ClientManager.getInstance().setPane(new ChatMenu());
                    }
                });
                end.play();
            }
        });
        logout.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                end.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Client.send(ClientAdapter.userLoggedOut(ClientManager.getInstance().getMainUser().getUsername()));
                        ClientManager.getInstance().setMainUser(null);
                        ClientManager.getInstance().setPane(new LoginMenu());
                    }
                });
                end.play();
            }
        });
    }
}
