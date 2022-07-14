package graphics.view.gameContents;

import graphics.objects.buttons.ButtonOne;
import graphics.objects.labels.LabelTwo;
import graphics.statics.StaticFonts;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.game.Civilization;

//TODO add functions and datas

public class MainPanel extends Pane {
    private Rectangle background;
    private ButtonOne setting;
    private ButtonOne researchPanel;
    private LabelTwo gold;
    private LabelTwo happiness;
    private LabelTwo research;
    private ButtonOne endTurn;

    public MainPanel (Civilization civilization) {
        background = new Rectangle(300, 110);
        background.setFill(Color.WHITE);
        this.getChildren().add(background);

        gold = new LabelTwo("GOLD : ", StaticFonts.segoeLoad(10), Pos.CENTER,
                50, 15, 100, 30, this);
        happiness = new LabelTwo("HAPP : ", StaticFonts.segoeLoad(10), Pos.CENTER,
                150, 15, 100, 30, this);
        research = new LabelTwo("RESEARCH : ", StaticFonts.segoeLoad(10), Pos.CENTER,
                250, 15, 100, 30, this);

        researchPanel = new ButtonOne("RESEARCH", StaticFonts.segoeLoad(20), Pos.CENTER,
                75, 50, 150, 40, this);
        setting = new ButtonOne("SETTING", StaticFonts.segoeLoad(20), Pos.CENTER,
                225, 50, 150, 40, this);
        endTurn = new ButtonOne("END TURN", StaticFonts.segoeLoad(24), Pos.CENTER,
                150, 90, 300, 40, this);

        this.setLayoutX(960 - background.getWidth()/2);
        this.setLayoutY(20);
    }
}
