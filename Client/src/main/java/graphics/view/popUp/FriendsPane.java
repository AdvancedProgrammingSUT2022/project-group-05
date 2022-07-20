package graphics.view.popUp;

import client.Client;
import client.ClientManager;
import graphics.objects.labels.LabelOne;
import graphics.statics.StaticFonts;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import model.User;

import java.util.ArrayList;

public class FriendsPane extends Pane {

    private String friendUsername;

    public FriendsPane(String friendUsername) {
        this.friendUsername = friendUsername;

        this.setPrefHeight(25);
        this.setPrefWidth(200);

        new LabelOne("name: " + friendUsername, StaticFonts.segoeLoad(15), Pos.CENTER_LEFT,
                120, 12, 200, 25, this);

    }

    public static ArrayList<FriendsPane> getFriendsPane() {
        User user = ClientManager.getInstance().getMainUser();
        ArrayList<String> friends = user.getFriends();
        ArrayList<FriendsPane> result = new ArrayList<>();
        for (int i = 0; i < friends.size(); i++) {
            result.add(new FriendsPane(friends.get(i)));
        }
        return result;
    }


}
