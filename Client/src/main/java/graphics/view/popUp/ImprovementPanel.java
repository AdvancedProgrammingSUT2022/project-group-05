package graphics.view.popUp;

import graphics.objects.buttons.ButtonOne;
import graphics.objects.labels.LabelOne;
import graphics.statics.StaticFonts;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.improvement.Improvement;

import java.util.ArrayList;

public class ImprovementPanel extends Pane {

    public ButtonOne camp;
    public ButtonOne farm;
    public ButtonOne lumberMill;
    public ButtonOne mine;
    public ButtonOne pasture;
    public ButtonOne plantation;
    public ButtonOne quarry;
    public ButtonOne tradingPost;

    public ImprovementPanel(ArrayList<Improvement> improvements) {

        this.getChildren().add(new Rectangle(600, 300, Color.WHITE));

        this.setPrefWidth(600);
        this.setPrefHeight(300);

        new LabelOne("IMPROVEMENT PANEL", StaticFonts.segoeLoad(20), Pos.CENTER,
                300, 50, 300, 60, this);
        this.camp = new ButtonOne("CAMP", StaticFonts.segoeLoad(20), Pos.CENTER,
                200, 100, 150, 40, this);
        this.farm = new ButtonOne("FARM", StaticFonts.segoeLoad(20), Pos.CENTER,
                400, 100, 150, 40, this);
        this.lumberMill = new ButtonOne("LUMBER_MILL", StaticFonts.segoeLoad(20), Pos.CENTER,
                200, 150, 150, 40, this);
        this.mine = new ButtonOne("MINE", StaticFonts.segoeLoad(20), Pos.CENTER,
                400, 150, 150, 40, this);
        this.pasture = new ButtonOne("PASTURE", StaticFonts.segoeLoad(20), Pos.CENTER,
                200, 200, 150, 40, this);
        this.plantation = new ButtonOne("PLANTATION", StaticFonts.segoeLoad(20), Pos.CENTER,
                400, 200, 150, 40, this);
        this.quarry = new ButtonOne("QUARRY", StaticFonts.segoeLoad(20), Pos.CENTER,
                200, 250, 150, 40, this);
        this.tradingPost = new ButtonOne("TRADING_POST", StaticFonts.segoeLoad(20), Pos.CENTER,
                400, 250, 150, 40, this);
//        if (!improvements.contains(Improvement.CAMP))
//            camp.setDisable(true);
//        if (!improvements.contains(Improvement.FARM))
//            farm.setDisable(true);
//        if (!improvements.contains(Improvement.LUMBER_MILL))
//            lumberMill.setDisable(true);
//        if (!improvements.contains(Improvement.MINE))
//            mine.setDisable(true);
//        if (!improvements.contains(Improvement.PASTURE))
//            pasture.setDisable(true);
//        if (!improvements.contains(Improvement.PLANTATION))
//            plantation.setDisable(true);
//        if (!improvements.contains(Improvement.QUARRY))
//            quarry.setDisable(true);
//        if (!improvements.contains(Improvement.TRADING_POST))
//            tradingPost.setDisable(true);
    }

}
