package graphics.view.gameContents;

import client.ClientManager;
import controller.GameMenuController;
import graphics.objects.buttons.ButtonOne;
import graphics.objects.labels.LabelTwo;
import graphics.statics.StaticFonts;
import graphics.view.menus.Game;
import graphics.view.popUp.CheatCode;
import graphics.view.popUp.Error;
import graphics.view.popUp.PopUp;
import graphics.view.popUp.Setting;
import graphics.view.popUp.research.ResearchPanel;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.game.Civilization;
import model.map.Map;

//TODO add functions and data's

public class MainPanel extends Pane {
    Civilization civilization;

    private Rectangle background;
    private ButtonOne setting;
    private ButtonOne researchPanel;
    private ButtonOne cheatCode;
    private LabelTwo gold;
    private LabelTwo happiness;
    private LabelTwo research;
    private LabelTwo year;
    private ButtonOne endTurn;

    private boolean isLocal;

    public MainPanel (Civilization civilization, boolean isLocal) {
        this.isLocal = isLocal;
        this.civilization = civilization;
        int fromLeft = (int) ClientManager.getInstance().getMainStage().getWidth() / 2;

        background = new Rectangle(450, 110);
        background.setFill(Color.WHITE);
        this.getChildren().add(background);


        year = new LabelTwo("YEAR : " + GameMenuController.getInstance().getCurrentYear(), StaticFonts.segoeLoad(10), Pos.CENTER,
                35, 15, 70, 30, this);
        gold = new LabelTwo("GOLD : " + civilization.getGold(), StaticFonts.segoeLoad(10), Pos.CENTER,
                115, 15, 90, 30, this);
        happiness = new LabelTwo("HAPPINESS : " + civilization.getHappiness(), StaticFonts.segoeLoad(10), Pos.CENTER,
                205, 15, 90, 30, this);
        research = new LabelTwo("RESEARCH : " + civilization.getResearchTree().getCurrentResearch() + " "
                + civilization.getResearchTree().getResearchProgressPercentage() + "%", StaticFonts.segoeLoad(10), Pos.CENTER,
                350, 15, 200, 30, this);

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
                String response = GameMenuController.getInstance().nextCivilization();

                if (response.startsWith("error")) {
                    new PopUp((Pane) MainPanel.this.getParent(), new Error(response));
                    return;
                }

                if (!isLocal) {
                    ClientManager.getInstance().updateAll();
                } else {
                    ClientManager.getInstance().update(true);
                    MapFX.updateInstance();
                    ClientManager.getInstance().setPane(new Game(GameMenuController.getInstance().getCurrentCivilizationController().getCivilization(), true));
                }
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

    public void updatePanel () {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                year.changeText("YEAR : " + GameMenuController.getInstance().getCurrentYear());
                gold.changeText("GOLD : " + civilization.getGold());
                happiness.changeText("HAPPINESS : " + civilization.getHappiness());
                research.changeText("RESEARCH : " + civilization.getResearchTree().getCurrentResearch() + " "
                        + civilization.getResearchTree().getResearchProgressPercentage() + "%");
            }
        });

    }
}
