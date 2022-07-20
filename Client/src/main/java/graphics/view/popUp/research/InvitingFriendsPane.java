package graphics.view.popUp.research;

import client.Client;
import client.ClientAdapter;
import client.ClientManager;
import graphics.objects.buttons.ButtonOne;
import graphics.objects.labels.LabelOne;
import graphics.statics.StaticFonts;
import graphics.view.popUp.FriendsPane;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.User;

import java.util.ArrayList;

public class InvitingFriendsPane extends Pane {

    private String invitingFriendUsername;

    private ButtonOne acceptButton;
    private ButtonOne rejectButton;

    public InvitingFriendsPane(String friendUsername) {
        this.invitingFriendUsername = friendUsername;

        this.setPrefHeight(30);
        this.setPrefWidth(250);

        new LabelOne("name: " + invitingFriendUsername, StaticFonts.segoeLoad(15), Pos.CENTER,
                50, 20, 120, 25, this);
        this.acceptButton = new ButtonOne("accept", StaticFonts.segoeLoad(15), Pos.CENTER,
                130, 20, 50, 25, this);
        this.rejectButton = new ButtonOne("reject", StaticFonts.segoeLoad(15), Pos.CENTER,
                190, 20, 50, 25, this);
    }

    public static ArrayList<InvitingFriendsPane> getInvitingFriendsPane() {
        User user = ClientManager.getInstance().getMainUser();
        ArrayList<String> friends = user.getInvitingFriends();
        ArrayList<InvitingFriendsPane> result = new ArrayList<>();
        for (int i = 0; i < friends.size(); i++) {
            result.add(new InvitingFriendsPane(friends.get(i)));
        }
        return result;
    }

    public ButtonOne getAcceptButton() {
        return acceptButton;
    }

    public ButtonOne getRejectButton() {
        return rejectButton;
    }

    public String getInvitingFriendUsername() {
        return invitingFriendUsername;
    }
}
