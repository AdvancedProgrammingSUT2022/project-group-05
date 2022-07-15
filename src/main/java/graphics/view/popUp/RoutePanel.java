package graphics.view.popUp;

import graphics.objects.buttons.ButtonOne;
import graphics.objects.labels.LabelOne;
import graphics.statics.StaticFonts;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;

public class RoutePanel extends Pane {

    public ButtonOne road;
    public ButtonOne rail;

    public RoutePanel(boolean canRoad, boolean canRail) {
        new LabelOne("ROUTE PANEL", StaticFonts.segoeLoad(20), Pos.CENTER,
                300, 75, 200, 60, this);
        this.road = new ButtonOne("ROAD", StaticFonts.segoeLoad(20), Pos.CENTER,
                225, 50, 150, 40, this);
        this.rail = new ButtonOne("RAIL", StaticFonts.segoeLoad(20), Pos.CENTER,
                225, 50, 150, 40, this);
        if (!canRoad)
            road.setDisable(true);
        if (!canRail)
            rail.setDisable(true);
    }
}
