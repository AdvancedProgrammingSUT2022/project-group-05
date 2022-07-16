package graphics.view.popUp;

import graphics.objects.labels.LabelOne;
import graphics.statics.StaticFonts;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.unit.Unit;

public class UnitInfo extends Pane {
    Unit unit;

    Rectangle background;

    Rectangle texture;

    LabelOne unitName;
    LabelOne unitState;
    LabelOne health;
    LabelOne position;
    LabelOne remainingMoves;

    public UnitInfo(Unit unit) {
        this.unit = unit;

        background = new Rectangle(600, 800);
        this.setPrefWidth(background.getWidth());
        this.setPrefHeight(background.getHeight());
        background.setFill(Color.WHITE);
        this.getChildren().add(background);

        position =  new LabelOne(unit.getTile().getXPlace() + "," + unit.getTile().getYPlace(), StaticFonts.segoeLoad(20), Pos.CENTER,
                300, 50, 100, 60, this);
        unitName =  new LabelOne(unit.toString(), StaticFonts.segoeLoad(40), Pos.CENTER,
                300, 600, 300, 60, this);
        unitState =  new LabelOne("STATE : " + unit.getUnitState(), StaticFonts.segoeLoad(30), Pos.CENTER,
                300, 650, 300, 60, this);
        health =  new LabelOne("HP : " + unit.getHealth(), StaticFonts.segoeLoad(30), Pos.CENTER,
                300, 700, 300, 60, this);
        remainingMoves =  new LabelOne("Remaining Moves : " + unit.getRemainingMovement(), StaticFonts.segoeLoad(20), Pos.CENTER,
                300, 750, 300, 60, this);

        texture = new Rectangle(150, 100, 300, 400);
        texture.setFill(unit.getTexture());
        this.getChildren().add(texture);
    }
}
