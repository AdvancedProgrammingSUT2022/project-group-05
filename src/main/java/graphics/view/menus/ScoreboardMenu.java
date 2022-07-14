package graphics.view.menus;

import controller.UserDatabaseController;
import graphics.objects.buttons.ButtonOne;
import graphics.objects.labels.LabelOne;
import graphics.statics.StaticFonts;
import graphics.view.ClientManager;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class ScoreboardMenu extends Pane{

    private ArrayList<HashMap<String, String>> sort(ArrayList<HashMap<String, String>> users) {
        for (int i = 1; i < users.size(); i++) {
            if (Integer.parseInt(users.get(i).get("score")) < Integer.parseInt(users.get(i - 1).get("score"))) {
                HashMap<String, String> user = users.get(i);
                users.set(i, users.get(i - 1));
                users.set(i - 1, user);
                if (i >= 2) {
                    i--;
                }
            }
        }
        return users;
    }

    public ScoreboardMenu () throws FileNotFoundException {
        ArrayList<HashMap<String, String>> users = UserDatabaseController.loadDatabase();
        System.out.println(users.get(0).get("score"));
        users.sort(new Comparator<HashMap<String, String>>() {
            @Override
            public int compare(HashMap<String, String> o1, HashMap<String, String> o2) {
                return Integer.parseInt(o1.get("score")) - Integer.parseInt(o2.get("score"));
            }
        });
        String[] nicknames = new String[users.size()];
        int[] scores = new int[users.size()];
        for (int i = 0; i < users.size(); i++) {
            nicknames[i] = users.get(i).get("nickname");
            scores[i] = Integer.parseInt(users.get(i).get("score"));
        }


        //OBJECTS
        ButtonOne back = new ButtonOne("back", StaticFonts.segoeLoad(15), Pos.CENTER,
                1000, 1000, 100, 50, this);

        LabelOne[] ranks = new LabelOne[users.size()];
        LabelOne[] nicknameLabels = new LabelOne[users.size()];
        LabelOne[] scoreLabels = new LabelOne[users.size()];

        for (int i = 0; i < users.size(); i++) {
            Circle circle = new Circle();
            FileInputStream fileInputStream = new FileInputStream(users.get(i).get("image"));
            ImagePattern imagePattern = new ImagePattern(new Image(fileInputStream));
            circle.setFill(imagePattern);
            circle.setCenterX(380);
            circle.setCenterY(50 * i + 400);
            circle.setRadius(25);
            this.getChildren().add(circle);
            ranks[i] = new LabelOne(i+1 + " :", StaticFonts.segoeLoad(15), Pos.CENTER_LEFT,
                    400, 50 * i + 400, 200, 30, this);
            nicknameLabels[i] = new LabelOne(nicknames[i], StaticFonts.segoeLoad(15), Pos.CENTER_LEFT,
                    510, 50 * i + 400, 200, 30, this);
            scoreLabels[i] = new LabelOne(scores[i] + "", StaticFonts.segoeLoad(15), Pos.CENTER_RIGHT,
                    1520, 50 * i + 400, 200, 30, this);
        }

        //FUNCTIONS
        back.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ClientManager.getInstance().setPane(new MainMenu());
            }
        });
    }
}
