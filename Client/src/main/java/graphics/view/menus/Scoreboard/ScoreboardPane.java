package graphics.view.menus.Scoreboard;

import client.Client;
import client.ClientAdapter;
import client.ClientManager;
import client.Response;
import graphics.objects.buttons.ButtonOne;
import graphics.objects.labels.LabelOne;
import graphics.statics.StaticFonts;
import graphics.view.popUp.Error;
import graphics.view.popUp.PopUp;
import graphics.view.popUp.Successful;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import model.User;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ScoreboardPane extends Pane {

    private ButtonOne invite;

    public ScoreboardPane(User user, int i, ArrayList<String> onlineUsers) {

        int fromLeft = (int) ClientManager.getInstance().getMainStage().getWidth() / 2;


        this.setPrefWidth(ClientManager.getInstance().getMainStage().getWidth());
        this.setPrefHeight(50);

        Circle onlineDot = new Circle();
        onlineDot.setRadius(5);
        onlineDot.setCenterX(50);
        onlineDot.setCenterY(5);
        if (onlineUsers.contains(user.getUsername()))
            onlineDot.setFill(Color.GREEN);
        else
            onlineDot.setFill(Color.RED);
        this.getChildren().add(onlineDot);

        Circle circle = new Circle();
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(user.getImageAddress());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ImagePattern imagePattern = new ImagePattern(new Image(fileInputStream));
        circle.setFill(imagePattern);
        circle.setCenterX(70);
        circle.setCenterY(25);
        circle.setRadius(25);
        this.getChildren().add(circle);
        new LabelOne(i+1 + " :", StaticFonts.segoeLoad(15), Pos.CENTER_LEFT,
                100, 25, 200, 30, this);
        new LabelOne(user.getNickname(), StaticFonts.segoeLoad(15), Pos.CENTER_LEFT,
                210, 25, 200, 30, this);
        new LabelOne(user.getScore() + "", StaticFonts.segoeLoad(15), Pos.CENTER_RIGHT,
                1220, 25, 200, 30, this);
        //invite Button
        invite = new ButtonOne("invite", StaticFonts.segoeLoad(15), Pos.CENTER,
                1000, 25, 200, 30, this);
    }

    public ButtonOne getInviteButton() {
        return invite;
    }
}
