package graphics.view.popUp.city;

import controller.CityController;
import graphics.objects.buttons.ButtonOne;
import graphics.objects.labels.LabelOne;
import graphics.statics.StaticFonts;
import graphics.view.gameContents.MapFX;
import graphics.view.popUp.Error;
import graphics.view.popUp.PopUp;
import graphics.view.popUp.Successful;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.game.City;

import java.util.ArrayList;

public class CityPanel extends Pane{
    private final City city;

    private Rectangle background;

    private LabelOne name;
    private LabelOne population;

    private LabelOne currentProduction;
    private LabelOne currentProductionInfo;
    private LabelOne timeRemaining;
    private LabelOne timeRemainingInfo;

    private LabelOne tileControlTitle;
    private LabelOne productionControlTitle;

    private ListView<TilePane> tileControl;
    private ListView<ProductionPane> productionControl;

    private ButtonOne buyTileButton;

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

        this.setBuyTileButton();
    }

    private void setBackground() {
        this.getChildren().remove(this.background);

        this.background = new Rectangle(1200, 800);
        this.setPrefWidth(this.background.getWidth());
        this.setPrefHeight(this.background.getHeight());

        this.background.setFill(Color.WHITE);

        this.getChildren().add(this.background);
    }

    private void setName() {
        this.getChildren().remove(this.name);

        this.name = new LabelOne(city.getName(), StaticFonts.segoeLoad(60), Pos.CENTER_LEFT,
                900, 50, 500, 100, this);
    }

    private void setPopulation() {
        this.getChildren().remove(this.population);

        String populationInfo = "population<jobless>: " + this.city.getTotalCitizenCount() + "<" + this.city.getJoblessCitizenCount() + ">";
        this.population = new LabelOne(populationInfo, StaticFonts.segoeLoad(30), Pos.CENTER_LEFT,
                900, 125, 500, 50, this);
    }

    private void setCurrentProduction() {
        this.getChildren().remove(this.currentProduction);

        this.currentProduction = new LabelOne("current production: ", StaticFonts.segoeLoad(30), Pos.CENTER_LEFT,
                300, 25, 500, 50, this);
    }

    private void setCurrentProductionInfo() {
        this.getChildren().remove(this.currentProductionInfo);

        Object production = city.getProductionInProgress();
        String info = (production != null) ? production.toString() : "nothing";

        this.currentProductionInfo = new LabelOne(info, StaticFonts.segoeLoad(30), Pos.CENTER_RIGHT,
                300, 75, 500, 50, this);
    }

    private void setTimeRemaining() {
        this.getChildren().remove(this.timeRemaining);

        this.timeRemaining = new LabelOne("time remaining: ", StaticFonts.segoeLoad(30), Pos.CENTER_LEFT,
                300, 125, 500, 50, this);
    }

    private void setTimeRemainingInfo() {
        this.getChildren().remove(this.timeRemainingInfo);

        String info = String.valueOf(this.city.getRemainingProductionTime());
        this.timeRemainingInfo = new LabelOne(info, StaticFonts.segoeLoad(30), Pos.CENTER_RIGHT,
                300, 175, 500, 50, this);
    }

    private void setTileControlTitle() {
        this.getChildren().remove(this.tileControlTitle);

        this.tileControlTitle = new LabelOne("Tile control", StaticFonts.segoeLoad(30), Pos.CENTER,
                900, 225, 500, 50, this);
    }

    private void setProductionControlTitle() {
        this.getChildren().remove(this.productionControlTitle);

        this.productionControlTitle = new LabelOne("Production control", StaticFonts.segoeLoad(30), Pos.CENTER,
                300, 225, 500, 50, this);
    }

    private void setTileControl() {
        this.getChildren().remove(this.tileControl);

        this.tileControl = new ListView<>();

        tileControl.setPrefWidth(500);
        tileControl.setPrefHeight(400);

        tileControl.setLayoutX(650);
        tileControl.setLayoutY(250);

        ArrayList<TilePane> tilePanes = TilePane.getAllButtons(city);
        for (TilePane tilePane : tilePanes) {
            tilePane.getAssignCitizenButton().setOnMouseClicked(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event) {
                    String response = CityController.getInstance().assignCitizen(tilePane.getTile());

                    if (response.startsWith("error")) new PopUp((Pane) CityPanel.this.getParent(), new Error(response));
                    else new PopUp((Pane) CityPanel.this.getParent(), new Successful(response));

                    CityPanel.this.setPopulation();
                    CityPanel.this.setTileControl();
                }
            });
            tilePane.getRemoveCitizenButton().setOnMouseClicked(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event) {
                    String response = CityController.getInstance().removeCitizen(tilePane.getTile());

                    if (response.startsWith("error")) new PopUp((Pane) CityPanel.this.getParent(), new Error(response));
                    else new PopUp((Pane) CityPanel.this.getParent(), new Successful(response));

                    CityPanel.this.setPopulation();
                    CityPanel.this.setTileControl();
                }
            });
        }

        tileControl.setItems(FXCollections.observableArrayList(tilePanes));
        this.getChildren().add(tileControl);
    }

    private void setProductionControl() {
        this.getChildren().remove(this.productionControl);

        this.productionControl = new ListView<>();

        productionControl.setPrefWidth(500);
        productionControl.setPrefHeight(400);

        productionControl.setLayoutX(50);
        productionControl.setLayoutY(250);

        ArrayList<ProductionPane> productionPanes = ProductionPane.getAllButtons();
        for (ProductionPane productionPane : productionPanes) {
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

    private void setBuyTileButton()
    {
        this.getChildren().remove(buyTileButton);

        this.buyTileButton = new ButtonOne("buy selected tile on map", StaticFonts.segoeLoad(30), Pos.CENTER,
                600, 725, 600, 50, this);

        this.buyTileButton.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                String response = CityController.getInstance().buyTile(
                        MapFX.getInstance().getSecondSelectedTile().getTile().getXPlace(),
                        MapFX.getInstance().getSecondSelectedTile().getTile().getYPlace()
                        );

                CityPanel.this.setPopulation();
                CityPanel.this.setTileControl();

                if (response.startsWith("error")) new PopUp((Pane) CityPanel.this.getParent(), new Error(response));
                else new PopUp((Pane) CityPanel.this.getParent(), new Successful(response));
            }
        });
    }
}
