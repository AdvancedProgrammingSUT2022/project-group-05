package graphics.view.popUp.city;

import graphics.objects.labels.LabelOne;
import graphics.statics.StaticFonts;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.game.City;

public class CityPanel extends Pane{
    private final City city;

    public CityPanel(City city) {
        this.city = city;

        this.setBackground();
        this.setName();
        this.setPopulation();

        this.setCurrentProduction();
        this.setCurrentProductionInfo();
        this.setTimeRemaining();
        this.setTimeRemainingInfo();

        this.setTileControlTitle();
        this.setProductionControlTitle();

        this.setTileControl();
        this.setProductionControl();
    }

    private void setBackground() {
        Rectangle background = new Rectangle(1200, 800);
        this.setPrefWidth(background.getWidth());
        this.setPrefHeight(background.getHeight());

        background.setFill(Color.WHITE);

        this.getChildren().add(background);
    }

    private void setName() {
        LabelOne name = new LabelOne(city.getName(), StaticFonts.segoeLoad(60), Pos.CENTER_LEFT,
                900, 50, 500, 100, this);
    }

    private void setPopulation() {
        String populationInfo = "population<jobless>: " + this.city.getTotalCitizenCount() + "<" + this.city.getJoblessCitizenCount() + ">";
        LabelOne population = new LabelOne(populationInfo, StaticFonts.segoeLoad(30), Pos.CENTER_LEFT,
                900, 125, 500, 50, this);
    }

    private void setCurrentProduction() {
        LabelOne currentProduction = new LabelOne("current production: ", StaticFonts.segoeLoad(30), Pos.CENTER_LEFT,
                300, 25, 500, 50, this);
    }

    private void setCurrentProductionInfo() {
        Object production = city.getProductionInProgress();
        String info = (production != null)? production.toString() : "nothing";

        LabelOne currentProductionInfo = new LabelOne(info, StaticFonts.segoeLoad(30), Pos.CENTER_RIGHT,
                300, 75, 500, 50, this);
    }

    private void setTimeRemaining() {
        LabelOne timeRemaining = new LabelOne("time remaining: ", StaticFonts.segoeLoad(30), Pos.CENTER_LEFT,
                300, 125, 500, 50, this);
    }

    private void setTimeRemainingInfo() {
        String info = String.valueOf(city.getRemainingProductionTime());
        LabelOne timeRemainingInfo = new LabelOne(info, StaticFonts.segoeLoad(30), Pos.CENTER_RIGHT,
                300, 175, 500, 50, this);
    }

    private void setTileControlTitle() {
        LabelOne tileControlTitle = new LabelOne("Tile control", StaticFonts.segoeLoad(30), Pos.CENTER,
                900, 225, 500, 50, this);
    }

    private void setProductionControlTitle() {
        LabelOne productionControlTitle = new LabelOne("Production control", StaticFonts.segoeLoad(30), Pos.CENTER,
                300, 225, 500, 50, this);
    }

    private void setTileControl() {
        ScrollPane tileControl = new ScrollPane();

        tileControl.setPrefWidth(500);
        tileControl.setPrefHeight(500);

        tileControl.setLayoutX(650);
        tileControl.setLayoutY(250);

        this.getChildren().add(tileControl);
    }

    private void setProductionControl() {
        ScrollPane productionControl = new ScrollPane();

        productionControl.setPrefWidth(500);
        productionControl.setPrefHeight(500);

        productionControl.setLayoutX(50);
        productionControl.setLayoutY(250);

        this.getChildren().add(productionControl);
    }
}
