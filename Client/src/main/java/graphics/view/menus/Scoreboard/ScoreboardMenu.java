package graphics.view.menus.Scoreboard;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import graphics.objects.buttons.ButtonOne;
import graphics.objects.labels.LabelOne;
import graphics.statics.StaticFonts;
import client.ClientManager;
import graphics.view.menus.MainMenu;
import graphics.view.popUp.Error;
import graphics.view.popUp.PopUp;
import graphics.view.popUp.Successful;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import client.Client;
import client.ClientAdapter;
import client.Response;
import model.User;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class ScoreboardMenu extends Pane{

    private ArrayList<HashMap<String, String>> users;
    private int fromLeft;
    private int fromTop;
    private ListView<ScoreboardPane> scoreboardControl;
    private ButtonOne back;

    public ScoreboardMenu () throws FileNotFoundException {
        fromLeft = (int) ClientManager.getInstance().getMainStage().getWidth() / 2 - 560;
        fromTop = (int) ClientManager.getInstance().getMainStage().getHeight() / 2;

        this.updateScoreBoard();


        back = new ButtonOne("back", StaticFonts.segoeLoad(15), Pos.CENTER,
                fromLeft + 560, fromTop + 300, 100, 50, this);

        back.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ClientManager.getInstance().setPane(new MainMenu());
            }
        });
    }

    public void updateScoreBoard(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                ScoreboardMenu.this.updateElements();
            }
        });
    }

    private void updateElements() {
        Response getUsersResponse = Client.send(ClientAdapter.getUsers());
        String usersJson = getUsersResponse.getMessage();
        System.out.println(usersJson);
        Gson gson = new Gson();
        users = gson.fromJson(usersJson,  new TypeToken<List<HashMap<String, String>>>() {
        }.getType());
        users.sort(new Comparator<HashMap<String, String>>() {
            @Override
            public int compare(HashMap<String, String> o1, HashMap<String, String> o2) {
                return Integer.parseInt(o1.get("score")) - Integer.parseInt(o2.get("score"));
            }
        });

        this.getChildren().remove(this.scoreboardControl);


        this.scoreboardControl = new ListView<>();

        this.scoreboardControl.setLayoutX(10);
        this.scoreboardControl.setLayoutY(110);

        this.scoreboardControl.setPrefWidth(ClientManager.getInstance().getMainStage().getWidth());
        this.scoreboardControl.setPrefHeight(ClientManager.getInstance().getMainStage().getHeight() - 100);

        Response getOnlineUsersResponse = Client.send(ClientAdapter.getOnlineUsers());
        ArrayList<String> onlineUsers = new Gson().fromJson(getOnlineUsersResponse.getMessage(), new TypeToken<List<String>>() {
        }.getType());

        System.out.println(onlineUsers + " are online users now");

        ArrayList<ScoreboardPane> scoreboardPanes = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            ScoreboardPane scoreboardPane = new ScoreboardPane(users.get(i).get("username"),
                    users.get(i).get("image"),users.get(i).get("nickname"), users.get(i).get("score"), i, onlineUsers);
            int finalI = i;
            scoreboardPane.getInviteButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    String friendUsernameText = users.get(finalI).get("username");
                    Response response = Client.send(ClientAdapter.inviteFriend(friendUsernameText, ClientManager.getInstance().getMainUser().getUsername()));
                    if (response.getMessage().startsWith("error")) {
                        new PopUp(ScoreboardMenu.this, new Error(response.getMessage()));
                        return;
                    }
                    new PopUp(ScoreboardMenu.this, new Successful(response.getMessage()));
                }
            });
            scoreboardPanes.add(scoreboardPane);
        }
        ScoreboardMenu.this.scoreboardControl.setItems(FXCollections.observableArrayList(scoreboardPanes));
        this.getChildren().add(this.scoreboardControl);

        this.getChildren().remove(back);

        back = new ButtonOne("back", StaticFonts.segoeLoad(15), Pos.CENTER,
                fromLeft + 560, fromTop + 300, 100, 50, this);

        back.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ClientManager.getInstance().setPane(new MainMenu());
            }
        });

    }
}
