package graphics.view.gameContents;

import controller.GameMenuController;
import graphics.objects.buttons.ButtonTwo;
import graphics.statics.StaticFonts;
import graphics.view.ClientManager;
import graphics.view.popUp.*;
import graphics.view.popUp.Error;
import graphics.view.popUp.city.CityPanel;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.game.City;
import model.game.Civilization;
import model.map.FogOfWarStates;
import model.map.Map;
import model.tile.Tile;
import model.unit.civilian.Civilian;

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
                setVisible(false);
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

                ((Pane) TileMenu.this.getParent()).getChildren().add(UnitMenu.getInstance());
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
                new PopUp((Pane) TileMenu.this.getParent(), UnitMenu.getInstance());
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

        this.setLayoutX(960 - 4*bSize);
        this.setLayoutY(1040 - bSize);
    }
}