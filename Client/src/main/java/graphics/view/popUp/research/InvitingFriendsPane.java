package graphics.view.popUp.research;

import graphics.objects.buttons.ButtonOne;
import graphics.objects.labels.LabelOne;
import graphics.statics.StaticFonts;
import graphics.view.popUp.FriendsPane;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import model.User;

import java.util.ArrayList;

public class InvitingFriendsPane extends Pane {

    private String invitingFriendUsername;

    private ButtonOne acceptButton;
    private ButtonOne rejectButton;

    public InvitingFriendsPane(String friendUsername) {
        this.invitingFriendUsername = friendUsername;

        this.setPrefHeight(480);
        this.setPrefWidth(75);

        new LabelOne("name: " + invitingFriendUsername, StaticFonts.segoeLoad(15), Pos.CENTER_LEFT,
                120, 12, 200, 25, this);
        this.acceptButton = new ButtonOne("accept", StaticFonts.segoeLoad(15), Pos.CENTER,
                360, 20, 200, 30, this);
        this.rejectButton = new ButtonOne("reject", StaticFonts.segoeLoad(15), Pos.CENTER,
                360, 20, 200, 30, this);
    }

    public static ArrayList<InvitingFriendsPane> getInvitingFriendsPane(User user) {
        ArrayList<String> friends = user.getInvitingFriends();
        ArrayList<InvitingFriendsPane> result = new ArrayList<>();
        for (int i = 0; i < friends.size(); i++) {
            result.add(new InvitingFriendsPane(friends.get(i)));
        }
        return result;
    }

}
