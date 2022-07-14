package graphics.view.popUp;

import graphics.objects.buttons.ButtonOne;
import graphics.objects.labels.LabelOne;
import graphics.objects.textFields.TextFieldOne;
import graphics.statics.StaticFonts;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

//ADD FUNCTIONS

public class CheatCode extends Pane {
    public CheatCode () {
        this.getChildren().add(new Rectangle(600, 300, Color.WHITE));
        this.setPrefHeight(300);
        this.setPrefWidth(600);
        new LabelOne("CHEAT CODE PANEL", StaticFonts.segoeLoad(20), Pos.CENTER,
                300, 75, 200, 60, this);
        new TextFieldOne("TYPE CODE", StaticFonts.segoeLoad(30), Pos.CENTER,
                300, 150, 400, 50, this);
        new ButtonOne("ACCEPT", StaticFonts.segoeLoad(20), Pos.CENTER,
                300, 225, 200, 40, this);
    }
}
