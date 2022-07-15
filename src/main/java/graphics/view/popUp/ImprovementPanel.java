package graphics.view.popUp;

import graphics.objects.buttons.ButtonOne;
import graphics.objects.labels.LabelOne;
import graphics.statics.StaticFonts;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.improvement.Improvement;

import java.util.ArrayList;

public class ImprovementPanel extends Pane {

    public ButtonOne camp;
    public ButtonOne farm;
    public ButtonOne lumber_mill;
    public ButtonOne mine;
    public ButtonOne pasture;
    public ButtonOne plantation;
    public ButtonOne quarry;
    public ButtonOne trading_post;

    public ImprovementPanel(ArrayList<Improvement> improvements) {
        new LabelOne("IMPROVEMENT PANEL", StaticFonts.segoeLoad(20), Pos.CENTER,
                300, 75, 200, 60, this);
        this.camp = new ButtonOne("CAMP", StaticFonts.segoeLoad(20), Pos.CENTER,
                225, 50, 150, 40, this);
        this.farm = new ButtonOne("FARM", StaticFonts.segoeLoad(20), Pos.CENTER,
                225, 50, 150, 40, this);
        this.lumber_mill = new ButtonOne("LUMBER_MILL", StaticFonts.segoeLoad(20), Pos.CENTER,
                225, 50, 150, 40, this);
        this.mine = new ButtonOne("MINE", StaticFonts.segoeLoad(20), Pos.CENTER,
                225, 50, 150, 40, this);
        this.pasture = new ButtonOne("PASTURE", StaticFonts.segoeLoad(20), Pos.CENTER,
                225, 50, 150, 40, this);
        this.plantation = new ButtonOne("PLANTATION", StaticFonts.segoeLoad(20), Pos.CENTER,
                225, 50, 150, 40, this);
        this.quarry = new ButtonOne("QUARRY", StaticFonts.segoeLoad(20), Pos.CENTER,
                225, 50, 150, 40, this);
        this.trading_post = new ButtonOne("TRADING_POST", StaticFonts.segoeLoad(20), Pos.CENTER,
                225, 50, 150, 40, this);
        if (!improvements.contains(Improvement.CAMP))
            camp.setDisable(true);
        if (!improvements.contains(Improvement.FARM))
            farm.setDisable(true);
        if (!improvements.contains(Improvement.LUMBER_MILL))
            lumber_mill.setDisable(true);
        if (!improvements.contains(Improvement.MINE))
            mine.setDisable(true);
        if (!improvements.contains(Improvement.PASTURE))
            pasture.setDisable(true);
        if (!improvements.contains(Improvement.PLANTATION))
            plantation.setDisable(true);
        if (!improvements.contains(Improvement.QUARRY))
            quarry.setDisable(true);
        if (!improvements.contains(Improvement.TRADING_POST))
            trading_post.setDisable(true);
    }

}
