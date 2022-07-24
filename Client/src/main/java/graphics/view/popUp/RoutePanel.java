package graphics.view.popUp;

import graphics.objects.buttons.ButtonOne;
import graphics.objects.labels.LabelOne;
import graphics.statics.StaticFonts;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RoutePanel extends Pane {

    public ButtonOne road;
    public ButtonOne rail;

    public RoutePanel() {

        this.setPrefWidth(600);
        this.setPrefHeight(300);
        this.getChildren().add(new Rectangle(600, 300, Color.WHITE));

        new LabelOne("ROUTE PANEL", StaticFonts.segoeLoad(20), Pos.CENTER,
                300, 100, 200, 60, this);
        this.road = new ButtonOne("ROAD", StaticFonts.segoeLoad(20), Pos.CENTER,
                225, 200, 150, 40, this);
        this.rail = new ButtonOne("RAIL", StaticFonts.segoeLoad(20), Pos.CENTER,
                375, 200, 150, 40, this);
    }
}
