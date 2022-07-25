package graphics.view.gameContents;

import client.ClientManager;
import controller.GameMenuController;
import controller.UnitController;
import graphics.objects.buttons.ButtonTwo;
import graphics.statics.StaticFonts;
import graphics.view.popUp.*;
import graphics.view.popUp.Error;
import graphics.view.popUp.city.CityPanel;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import model.game.City;
import model.map.FogOfWarStates;
import model.tile.Tile;

//TODO add functions and closing system of this menu

public class TileMenu extends Pane {
    private static TileMenu instance = null;
    private static int bSize = 60;
    public static TileMenu getInstance() {
        if (instance == null) {
            instance = new TileMenu();
        }
        return instance;
    }

    private Rectangle background;
    private ButtonTwo civilizationInfo;
    private ButtonTwo cityInfo;
    private ButtonTwo tileInfo;
    private ButtonTwo civInfo;
    private ButtonTwo solInfo;
    private ButtonTwo selCiv;
    private ButtonTwo selSol;
    private ButtonTwo exit;


    private TileMenu () {
        int fromLeft = (int) ClientManager.getInstance().getMainStage().getWidth() / 2;
        int fromTop= (int) ClientManager.getInstance().getMainStage().getHeight();
        this.setVisible(false);
        background = new Rectangle(bSize*8, bSize);
        background.setFill(new Color(0, 0.5, 0.5, 0.4));
        this.getChildren().add(background);

        civilizationInfo = new ButtonTwo("CivInf", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2, bSize/2, bSize, bSize, this);
        cityInfo = new ButtonTwo("CitInf", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2 + bSize, bSize/2, bSize, bSize, this);
        tileInfo = new ButtonTwo("TilInf", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2 + bSize * 2, bSize/2, bSize, bSize, this);
        civInfo = new ButtonTwo("CivInf", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2 + bSize * 3, bSize/2, bSize, bSize, this);
        solInfo = new ButtonTwo("SolInf", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2 + bSize * 4, bSize/2, bSize, bSize, this);
        selCiv = new ButtonTwo("SelCiv", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2 + bSize * 5, bSize/2, bSize, bSize, this);
        selSol = new ButtonTwo("SelSol", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2 + bSize * 6, bSize/2, bSize, bSize, this);
        exit = new ButtonTwo("EXIT", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2 + bSize * 7, bSize/2, bSize, bSize, this);

        //Functions

        civilizationInfo.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                new PopUp((Pane)TileMenu.this.getParent(),
                        new CivilizationInfo(GameMenuController.getInstance().getCurrentCivilizationController().getCivilization()));
            }
        });

        exit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                TileMenu.this.close();
                MapFX.getInstance().getFirstSelectedTile().setSelectedDisable();
                MapFX.getInstance().setFirstSelectedTile(null);
                //can add animation here
            }
        });

        tileInfo.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Tile temp = MapFX.getInstance().getFirstSelectedTile().getTile();
                FogOfWarStates fogOfWarStates = GameMenuController.getInstance().getCurrentCivilizationController().
                        getCivilization().getFogOfWar()[temp.getXPlace()][temp.getYPlace()];
                if (fogOfWarStates.equals(FogOfWarStates.VISIBLE)) {
                    new PopUp((Pane) TileMenu.this.getParent(), new TileInfo(MapFX.getInstance().getFirstSelectedTile().getTile()));
                }
                else new PopUp((Pane) TileMenu.this.getParent(), new Error("THIS TILE IS NOT VISIBLE"));
            }
        });
        solInfo.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Tile temp = MapFX.getInstance().getFirstSelectedTile().getTile();
                FogOfWarStates fogOfWarStates = GameMenuController.getInstance().getCurrentCivilizationController().
                        getCivilization().getFogOfWar()[temp.getXPlace()][temp.getYPlace()];
                if (fogOfWarStates.equals(FogOfWarStates.VISIBLE)) {
                    if (temp.hasSoldier()) {
                        new PopUp((Pane) TileMenu.this.getParent(), new UnitInfo(temp.getSoldier()));
                    }
                    else new PopUp((Pane) TileMenu.this.getParent(), new Error("NO SOLDIER FOUND"));
                }
                else new PopUp((Pane) TileMenu.this.getParent(), new Error("THIS TILE IS NOT VISIBLE"));
            }
        });
        civInfo.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Tile temp = MapFX.getInstance().getFirstSelectedTile().getTile();
                FogOfWarStates fogOfWarStates = GameMenuController.getInstance().getCurrentCivilizationController().
                        getCivilization().getFogOfWar()[temp.getXPlace()][temp.getYPlace()];
                if (fogOfWarStates.equals(FogOfWarStates.VISIBLE)) {
                    if (temp.hasCivilian()) {
                        new PopUp((Pane) TileMenu.this.getParent(), new UnitInfo(temp.getCivilian()));
                    }
                    else new PopUp((Pane) TileMenu.this.getParent(), new Error("NO CIVILIAN FOUND"));
                }
                else new PopUp((Pane) TileMenu.this.getParent(), new Error("THIS TILE IS NOT VISIBLE"));
            }
        });

        selSol.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                Tile tile = MapFX.getInstance().getFirstSelectedTile().getTile();

                String response = GameMenuController.getInstance().selectUnitSoldier(tile.getXPlace(), tile.getYPlace());

                if (response.startsWith("error")) {
                    new PopUp((Pane) TileMenu.this.getParent(), new Error(response));
                    return;
                }

//                ((Pane) TileMenu.this.getParent()).getChildren().add(UnitMenu.getInstance());
                UnitMenu.getInstance().setName(UnitController.getInstance().getUnit().toString());
                UnitMenu.getInstance().open();
            }
        });
        selCiv.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                Tile tile = MapFX.getInstance().getFirstSelectedTile().getTile();

                String response = GameMenuController.getInstance().selectUnitCivilian(tile.getXPlace(), tile.getYPlace());

                if (response.startsWith("error")) {
                    new PopUp((Pane) TileMenu.this.getParent(), new Error(response));
                    return;
                }

                //TODO city panel tor
//                new PopUp((Pane) TileMenu.this.getParent(), UnitMenu.getInstance());
                UnitMenu.getInstance().setName(UnitController.getInstance().getUnit().toString());
                UnitMenu.getInstance().open();
            }
        });

        cityInfo.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                City city = MapFX.getInstance().getFirstSelectedTile().getTile().getCity();

                if (city == null) {
                    new PopUp((Pane) TileMenu.this.getParent(), new Error("error: tile doesn't belong to a city."));
                    return;
                }

                String response = GameMenuController.getInstance().selectCityPosition(city.getCenter().getXPlace(), city.getCenter().getYPlace());

                if (response.startsWith("error")) {
                    new PopUp((Pane) TileMenu.this.getParent(), new Error(response));
                    return;
                }

                new PopUp((Pane) TileMenu.this.getParent(), new CityPanel());
            }
        });

        this.setLayoutX(fromLeft - 4*bSize);
        this.setLayoutY(fromTop - 40 - bSize);
    }

    public void close () {
        FadeTransition end = new FadeTransition(new Duration(500), this);
        end.setFromValue(1);
        end.setToValue(0);
        end.play();
        end.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                instance.setVisible(false);
            }
        });
        end.play();
    }
    public void open () {
        this.setVisible(true);
        FadeTransition start = new FadeTransition(new Duration(500), this);
        start.setFromValue(0);
        start.setToValue(1);
        start.play();
    }
}
