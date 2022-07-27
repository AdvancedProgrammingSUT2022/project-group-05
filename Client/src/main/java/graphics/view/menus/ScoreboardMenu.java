package graphics.view.menus;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import graphics.objects.buttons.ButtonOne;
import graphics.objects.labels.LabelOne;
import graphics.statics.StaticFonts;
import client.ClientManager;
import javafx.animation.ParallelTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import client.Client;
import client.ClientAdapter;
import client.Response;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class ScoreboardMenu extends Pane{

    public ScoreboardMenu () throws FileNotFoundException {
        Response getUsersResponse = Client.send(ClientAdapter.getUsers());
        String usersJson = getUsersResponse.getMessage();
        Gson gson = new Gson();
        ArrayList<HashMap<String, String>> users = gson.fromJson(usersJson,  new TypeToken<List<HashMap<String, String>>>() {
        }.getType());
        //ArrayList<HashMap<String, String>> users = UserDatabaseController.loadDatabase();
        System.out.println(users.get(0).get("score"));
        users.sort(new Comparator<HashMap<String, String>>() {
            @Override
            public int compare(HashMap<String, String> o1, HashMap<String, String> o2) {
                return Integer.parseInt(o2.get("score")) - Integer.parseInt(o1.get("score"));
            }
        });
        String[] nicknames = new String[users.size()];
        int[] scores = new int[users.size()];
        for (int i = 0; i < users.size(); i++) {
            nicknames[i] = users.get(i).get("nickname");
            scores[i] = Integer.parseInt(users.get(i).get("score"));
        }

        int fromLeft = (int) ClientManager.getInstance().getMainStage().getWidth() / 2 - 560;
        int fromTop = (int) ClientManager.getInstance().getMainStage().getHeight() / 2;

        //OBJECTS
        ButtonOne back = new ButtonOne("back", StaticFonts.segoeLoad(15), Pos.CENTER,
                fromLeft + 560, fromTop + 300, 100, 50, this);

        LabelOne[] ranks = new LabelOne[users.size()];
        LabelOne[] nicknameLabels = new LabelOne[users.size()];
        LabelOne[] scoreLabels = new LabelOne[users.size()];

        for (int i = 0; i < users.size(); i++) {
            Circle circle = new Circle();
            FileInputStream fileInputStream = new FileInputStream(users.get(i).get("image"));
            ImagePattern imagePattern = new ImagePattern(new Image(fileInputStream));
            circle.setFill(imagePattern);
            circle.setCenterX(fromLeft - 30);
            circle.setCenterY(50 * i + 100);
            circle.setRadius(25);
            this.getChildren().add(circle);
            ranks[i] = new LabelOne(i+1 + " :", StaticFonts.segoeLoad(15), Pos.CENTER_LEFT,
                    fromLeft, 50 * i + 100, 200, 30, this);
            nicknameLabels[i] = new LabelOne(nicknames[i], StaticFonts.segoeLoad(15), Pos.CENTER_LEFT,
                    fromLeft + 110, 50 * i + 100, 200, 30, this);
            scoreLabels[i] = new LabelOne(scores[i] + "", StaticFonts.segoeLoad(15), Pos.CENTER_RIGHT,
                    fromLeft + 1120, 50 * i + 100, 200, 30, this);
        }

        //ANIMATION
        ParallelTransition start = AnimatedPane.getStartAnimation(this);
        ParallelTransition end = AnimatedPane.getEndAnimation(this);
        start.play();

        //FUNCTIONS
        back.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                end.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        ClientManager.getInstance().setPane(new MainMenu());
                    }
                });
                end.play();
            }
        });
    }
}
