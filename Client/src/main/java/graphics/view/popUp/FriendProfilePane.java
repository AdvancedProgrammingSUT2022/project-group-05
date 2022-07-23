package graphics.view.popUp;

import client.Client;
import client.ClientAdapter;
import client.ClientManager;
import client.Response;
import graphics.objects.buttons.ButtonOne;
import graphics.objects.labels.LabelOne;
import graphics.statics.StaticFonts;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.User;

public class FriendProfilePane extends Pane {

    public FriendProfilePane(User friendUser) {
        this.getChildren().add(new Rectangle(600, 300, Color.WHITE));
        this.setPrefHeight(300);
        this.setPrefWidth(600);
        new LabelOne("ProfileInfo", StaticFonts.segoeLoad(30), Pos.CENTER,
                200 + 120, 20, 200, 60, this);
        new LabelOne("Username : " + friendUser.getUsername(), StaticFonts.segoeLoad(30), Pos.CENTER,
                25 + 300, 85, 500, 60, this);
        new LabelOne("Nickname : " + friendUser.getNickname(), StaticFonts.segoeLoad(30), Pos.CENTER,
                25 + 300, 150, 500, 60, this);
        new LabelOne("Score : " + friendUser.getScore(), StaticFonts.segoeLoad(30), Pos.CENTER,
                25 + 300, 215, 500, 60, this);
        ButtonOne invite = new ButtonOne("invite", StaticFonts.segoeLoad(15), Pos.CENTER,
                250 + 80,  280, 100, 50, this);
        invite.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Response response = Client.send(ClientAdapter.inviteFriend(friendUser.getUsername(), ClientManager.getInstance().getMainUser().getUsername()));
                if (response.getMessage().startsWith("error")) {
                    new PopUp((Pane) FriendProfilePane.this.getParent(), new Error(response.getMessage()));
                    return;
                }
                new PopUp((Pane) FriendProfilePane.this.getParent(), new Successful(response.getMessage()));
            }
        });
    }

}
