package graphics.view.popUp.city;

import controller.CityController;
import controller.GameMenuController;
import graphics.objects.labels.LabelOne;
import graphics.statics.StaticFonts;
import graphics.view.ClientManager;
import graphics.view.popUp.Error;
import graphics.view.popUp.PopUp;
import graphics.view.popUp.Successful;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.game.City;
import model.game.Civilization;
import model.tile.Tile;
import model.unit.civilian.Settler;
import model.unit.civilian.Worker;
import model.unit.soldier.ranged.Archer;

import java.util.ArrayList;

public class CityPanel extends Pane{
    private final City city;

    public CityPanel(City city) {
        this.city = city;
        CityController.updateInstance(city);

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
        new LabelOne(city.getName(), StaticFonts.segoeLoad(60), Pos.CENTER_LEFT,
                900, 50, 500, 100, this);
    }

    private void setPopulation() {
        String populationInfo = "population<jobless>: " + this.city.getTotalCitizenCount() + "<" + this.city.getJoblessCitizenCount() + ">";
        new LabelOne(populationInfo, StaticFonts.segoeLoad(30), Pos.CENTER_LEFT,
                900, 125, 500, 50, this);
    }

    private void setCurrentProduction() {
        new LabelOne("current production: ", StaticFonts.segoeLoad(30), Pos.CENTER_LEFT,
                300, 25, 500, 50, this);
    }

    private void setCurrentProductionInfo() {
        Object production = city.getProductionInProgress();
        String info = (production != null) ? production.toString() : "nothing";

        new LabelOne(info, StaticFonts.segoeLoad(30), Pos.CENTER_RIGHT,
                300, 75, 500, 50, this);
    }

    private void setTimeRemaining() {
        new LabelOne("time remaining: ", StaticFonts.segoeLoad(30), Pos.CENTER_LEFT,
                300, 125, 500, 50, this);
    }

    private void setTimeRemainingInfo() {
        String info = String.valueOf(city.getRemainingProductionTime());
        new LabelOne(info, StaticFonts.segoeLoad(30), Pos.CENTER_RIGHT,
                300, 175, 500, 50, this);
    }

    private void setTileControlTitle() {
        new LabelOne("Tile control", StaticFonts.segoeLoad(30), Pos.CENTER,
                900, 225, 500, 50, this);
    }

    private void setProductionControlTitle() {
        new LabelOne("Production control", StaticFonts.segoeLoad(30), Pos.CENTER,
                300, 225, 500, 50, this);
    }

    private void setTileControl() {
        ListView<TilePane> tileControl = new ListView<>();

        tileControl.setPrefWidth(500);
        tileControl.setPrefHeight(500);

        tileControl.setLayoutX(650);
        tileControl.setLayoutY(250);

        this.getChildren().add(tileControl);
    }

    private void setProductionControl() {
        ListView<ProductionPane> productionControl = new ListView<>();

        productionControl.setPrefWidth(500);
        productionControl.setPrefHeight(500);

        productionControl.setLayoutX(50);
        productionControl.setLayoutY(250);

        ArrayList<ProductionPane> productionPanes = ProductionPane.getAllButtons();
        for (ProductionPane productionPane : productionPanes)
        {
            productionPane.getBuyButton().setOnMouseClicked(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event) {
                    String response;
                    if (productionPane.getProductionType().equals("unit"))
                        response = CityController.getInstance().purchaseUnit(productionPane.getProductionName());
                    else
                        response = CityController.getInstance().purchaseBuilding(productionPane.getProductionName());

                    if (response.startsWith("error")) new PopUp((Pane) CityPanel.this.getParent(), new Error(response));
                    else new PopUp((Pane) CityPanel.this.getParent(), new Successful(response));
                }
            });
            productionPane.getProduceButton().setOnMouseClicked(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event) {
                    String response;
                    if (productionPane.getProductionType().equals("unit"))
                        response = CityController.getInstance().cityCreateUnit(productionPane.getProductionName());
                    else
                        response = CityController.getInstance().cityCreateBuilding(productionPane.getProductionName());

                    CityPanel.this.setCurrentProductionInfo();
                    CityPanel.this.setTimeRemainingInfo();

                    if (response.startsWith("error")) new PopUp((Pane) CityPanel.this.getParent(), new Error(response));
                    else new PopUp((Pane) CityPanel.this.getParent(), new Successful(response));
                }
            });
        }

        productionControl.setItems(FXCollections.observableArrayList(productionPanes));
        this.getChildren().add(productionControl);
    }
}
