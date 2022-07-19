package graphics.view.gameContents;

import client.ClientManager;
import controller.GameMenuController;
import graphics.objects.buttons.ButtonOne;
import graphics.objects.labels.LabelTwo;
import graphics.statics.StaticFonts;
import graphics.view.popUp.CheatCode;
import graphics.view.popUp.PopUp;
import graphics.view.popUp.Setting;
import graphics.view.popUp.research.ResearchPanel;
import graphics.view.popUp.research.TechnologyTreeMap;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.game.Civilization;

//TODO add functions and datas

public class MainPanel extends Pane {
    private Rectangle background;
    private ButtonOne setting;
    private ButtonOne researchPanel;
    private ButtonOne cheatCode;
    private LabelTwo gold;
    private LabelTwo happiness;
    private LabelTwo research;
    private ButtonOne endTurn;

    public MainPanel (Civilization civilization) {

        int fromLeft = (int) ClientManager.getInstance().getMainStage().getWidth() / 2;

        background = new Rectangle(450, 110);
        background.setFill(Color.WHITE);
        this.getChildren().add(background);

        gold = new LabelTwo("GOLD : " + civilization.getGold(), StaticFonts.segoeLoad(10), Pos.CENTER,
                50, 15, 100, 30, this);
        happiness = new LabelTwo("HAPPINESS : " + civilization.getHappiness(), StaticFonts.segoeLoad(10), Pos.CENTER,
                150, 15, 100, 30, this);
        research = new LabelTwo("RESEARCH : " + civilization.getResearchTree().getCurrentResearch() + " "
                + civilization.getResearchTree().getResearchProgressPercentage() + "%", StaticFonts.segoeLoad(10), Pos.CENTER,
                325, 15, 250, 30, this);

        researchPanel = new ButtonOne("RESEARCH", StaticFonts.segoeLoad(20), Pos.CENTER,
                75, 50, 150, 40, this);
        cheatCode = new ButtonOne("CHEAT CODE", StaticFonts.segoeLoad(20), Pos.CENTER,
                225, 50, 150, 40, this);
        setting = new ButtonOne("SETTING", StaticFonts.segoeLoad(20), Pos.CENTER,
                375, 50, 150, 40, this);
        endTurn = new ButtonOne("END TURN", StaticFonts.segoeLoad(24), Pos.CENTER,
                225, 90, 450, 40, this);

        endTurn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                GameMenuController.getInstance().nextCivilization();
            }
        });
        cheatCode.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                new PopUp((Pane)MainPanel.this.getParent(), new CheatCode());
            }
        });
        researchPanel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                new PopUp((Pane)MainPanel.this.getParent(),
                        new ResearchPanel(GameMenuController.getInstance().getCurrentCivilizationController().getCivilization()));
            }
        });
        setting.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                new PopUp((Pane)MainPanel.this.getParent(), new Setting());
            }
        });

        this.setLayoutX(fromLeft - background.getWidth()/2);
        this.setLayoutY(20);
    }
}
