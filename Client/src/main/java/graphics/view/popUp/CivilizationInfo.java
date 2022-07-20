package graphics.view.popUp;

import client.ClientManager;
import controller.GameMenuController;
import graphics.objects.buttons.ButtonOne;
import graphics.objects.labels.LabelOne;
import graphics.statics.StaticFonts;
import graphics.view.popUp.overview.EconomicOverview;
import graphics.view.popUp.overview.MilitaryOverview;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.game.Civilization;

public class CivilizationInfo extends Pane {
    private Civilization civilization;

    ButtonOne military;
    ButtonOne economic;
    public CivilizationInfo (Civilization civilization) {
        this.civilization = civilization;

        int fromLeft = (int) ClientManager.getInstance().getMainStage().getWidth() / 2;

        this.getChildren().add(new Rectangle(600, 800, Color.WHITE));
        this.setPrefWidth(600);
        this.setPrefHeight(800);

        new LabelOne(civilization.getPlayer().getNickname(), StaticFonts.segoeLoad(30), Pos.CENTER,
                300, 100, 200, 60, this);
        new LabelOne(GameMenuController.getInstance().infoDemographics(), StaticFonts.segoeLoad(30), Pos.TOP_CENTER,
                300, 350, 400, 400, this);

        military = new ButtonOne("Military Overview", StaticFonts.segoeLoad(30), Pos.CENTER,
                300, 550, 300, 60, this);
        economic = new ButtonOne("Economic Overview", StaticFonts.segoeLoad(30), Pos.CENTER,
                300, 650, 300, 60, this);

        military.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                new PopUp((Pane)CivilizationInfo.this.getParent(), new MilitaryOverview(civilization));
            }
        });
        economic.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                new PopUp((Pane) CivilizationInfo.this.getParent(), new EconomicOverview(civilization));
            }
        });
    }
}
