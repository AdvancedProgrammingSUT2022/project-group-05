package graphics.view.menus;

import graphics.objects.buttons.ButtonOne;
import graphics.objects.labels.LabelOne;
import graphics.statics.StaticFonts;
import graphics.view.ClientManager;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class ScoreboardMenu extends Pane{
    public ScoreboardMenu () {
        String[] nicknames = new String[10]; //TODO... get from database
        int[] scores = new int[10]; //TODO... get from database



        //OBJECTS
        ButtonOne back = new ButtonOne("back", StaticFonts.segoeLoad(15), Pos.CENTER,
                100, 1000, 100, 50, this);

        LabelOne[] ranks = new LabelOne[10];
        LabelOne[] nicknameLabels = new LabelOne[10];
        LabelOne[] scoreLabels = new LabelOne[10];

        for (int i = 0; i < 10; i++) {
            ranks[i] = new LabelOne(i+1 + " :", StaticFonts.segoeLoad(15), Pos.CENTER_LEFT,
                    400, 50 * i + 400, 200, 30, this);
            nicknameLabels[i] = new LabelOne(nicknames[i], StaticFonts.segoeLoad(15), Pos.CENTER_LEFT,
                    450, 50 * i + 400, 200, 30, this);
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
