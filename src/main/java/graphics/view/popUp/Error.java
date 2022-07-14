package graphics.view.popUp;

import graphics.objects.labels.LabelOne;
import graphics.objects.labels.LabelTwo;
import graphics.statics.StaticFonts;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Error extends Pane {
    public Error (String message) {
        this.getChildren().add(new Rectangle(600, 300, Color.RED));
        this.setPrefHeight(300);
        this.setPrefWidth(600);
        new LabelOne("ERROR", StaticFonts.SeqoeLoad(30), Pos.CENTER,
                300, 75, 200, 60, this);
        new LabelOne(message, StaticFonts.SeqoeLoad(20), Pos.CENTER,
                300, 200, 400, 50, this);
    }
}
