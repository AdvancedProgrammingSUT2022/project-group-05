package graphics.view.popUp.overview;

import graphics.objects.labels.LabelTwo;
import graphics.statics.StaticFonts;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.game.Civilization;

public class MilitaryOverview extends Pane {
    private final Civilization civilization;

    private Rectangle background;

    private LabelTwo title;
    private ListView<UnitPane> unitPanes;

    public MilitaryOverview(Civilization civilization) {
        this.civilization = civilization;

        this.setBackground();
        this.setTitle();
        this.setUnitPanes();
    }

    private void setBackground() {
        this.getChildren().remove(this.background);

        this.background = new Rectangle(1200, 800);
        this.setPrefWidth(this.background.getWidth());
        this.setPrefHeight(this.background.getHeight());

        this.background.setFill(Color.WHITE);

        this.getChildren().add(this.background);
    }

    private void setTitle() {
        this.getChildren().remove(this.title);

        this.title = new LabelTwo("MILITARY OVERVIEW", StaticFonts.segoeLoad(30), Pos.CENTER,
                600, 100, 800, 100, this);
    }

    private void setUnitPanes() {
        this.getChildren().remove(this.unitPanes);

        this.unitPanes = new ListView<>();
        this.unitPanes.setLayoutX(200);
        this.unitPanes.setLayoutY(200);
        this.unitPanes.setPrefWidth(800);
        this.unitPanes.setPrefHeight(500);

        this.unitPanes.setItems(FXCollections.observableArrayList(UnitPane.getUnitPanes(this.civilization)));
        this.getChildren().add(this.unitPanes);
    }
}
