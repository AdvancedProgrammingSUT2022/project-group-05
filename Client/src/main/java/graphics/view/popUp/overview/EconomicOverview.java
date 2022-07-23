package graphics.view.popUp.overview;

import graphics.objects.labels.LabelOne;
import graphics.objects.labels.LabelTwo;
import graphics.statics.StaticFonts;
import graphics.view.popUp.city.TilePane;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.game.Civilization;

public class EconomicOverview extends Pane{
    private final Civilization civilization;

    private Rectangle background;

    private LabelTwo title;
    private ListView<CityPane> cityPanes;

    public EconomicOverview(Civilization civilization) {
        this.civilization = civilization;

        this.setBackground();
        this.setTitle();
        this.setCityPanes();
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

        this.title = new LabelTwo("ECONOMIC OVERVIEW", StaticFonts.segoeLoad(30), Pos.CENTER,
                600, 100, 800, 100, this);
    }

    private void setCityPanes() {
        this.getChildren().remove(this.cityPanes);

        this.cityPanes = new ListView<>();
        this.cityPanes.setLayoutX(200);
        this.cityPanes.setLayoutY(200);
        this.cityPanes.setPrefWidth(800);
        this.cityPanes.setPrefHeight(500);

        this.cityPanes.setItems(FXCollections.observableArrayList(CityPane.getCityPanes(this.civilization)));
        this.getChildren().add(this.cityPanes);
    }
}
