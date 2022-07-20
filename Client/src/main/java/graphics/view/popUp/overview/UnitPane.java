package graphics.view.popUp.overview;

import graphics.objects.labels.LabelTwo;
import graphics.statics.StaticFonts;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import model.game.Civilization;
import model.unit.Unit;

import java.util.ArrayList;


public class UnitPane extends Pane{
    private Unit unit;

    private LabelTwo name;
    private Rectangle image;
    private LabelTwo status;

    private LabelTwo position;
    private LabelTwo health;
    private LabelTwo remainingMP;

    public UnitPane(Unit unit)
    {
        this.unit = unit;

        this.setPrefWidth(800);
        this.setPrefHeight(150);

        this.setName();
        this.setImage();
        this.setStatus();

        this.setPosition();
        this.setHealth();
        this.setRemainingMp();
    }

    private void setName() {
        this.name = new LabelTwo(unit.toString(), StaticFonts.segoeLoad(30), Pos.CENTER_LEFT,
                300, 75, 200, 100, this);
    }

    private void setImage() {
        this.image = new Rectangle(25, 25, 100, 100);

        this.image.setFill(this.unit.getTexture());
        this.getChildren().add(this.image);
    }

    private void setStatus() {
        this.status = new LabelTwo(unit.getUnitState().toString(), StaticFonts.segoeLoad(15), Pos.CENTER_LEFT,
                550, 45, 100, 40, this);
    }

    private void setPosition() {
        this.position = new LabelTwo(unit.getTile().getXPlace() + "," + unit.getTile().getYPlace(), StaticFonts.segoeLoad(15), Pos.CENTER_LEFT,
                550, 105, 100, 40, this);
    }

    private void setHealth() {
        this.health = new LabelTwo("HEALTH: " + unit.getHealth(), StaticFonts.segoeLoad(15), Pos.CENTER_LEFT,
                700, 45, 100, 40, this);
    }

    private void setRemainingMp() {
        this.remainingMP = new LabelTwo("MP: " + unit.getRemainingMovement(), StaticFonts.segoeLoad(15), Pos.CENTER_LEFT,
                700, 105, 100, 40, this);
    }

    //STATIC
    public static ArrayList<UnitPane> getUnitPanes(Civilization civilization) {
        ArrayList<UnitPane> result = new ArrayList<>();

        for (Unit unit : civilization.getUnits()) {
            result.add(new UnitPane(unit));
        }

        return result;
    }
}
