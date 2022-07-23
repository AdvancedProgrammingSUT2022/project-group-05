package graphics.view.gameContents;
import controller.GameMenuController;
import graphics.objects.buttons.DisableButtonOne;
import graphics.objects.labels.LabelOne;
import graphics.statics.StaticFonts;
import graphics.statics.StaticImages;
import javafx.animation.FillTransition;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import model.map.FogOfWarStates;
import model.tile.Tile;

public class TileFX extends Group {
    private Pane pane;
    private Tile tile;
    private Polygon terrain;
    private Polygon feature;
    private Polygon[] rivers;
    private Polygon upper;

    private LabelOne place;
    private LabelOne cityName;
    private LabelOne resource;

    private Polygon fogOfWar;
    private Polygon selected;
    private Polygon front;
    private Rectangle ruin;
    private Rectangle soldure;
    private Rectangle civilian;
    private Polygon city;

    private FillTransition upperToNormal;
    private FillTransition upperToFirst;
    private FillTransition upperToSecond;

    private FillTransition selectedToNormal;
    private FillTransition selectedToFirst;
    private FillTransition selectedToSecond;

    TranslateTransition start;
    TranslateTransition end;

    public TileFX(Pane pane, Tile tile) {
        this.tile = tile;
        int xPlace = tile.getXPlace();
        int yPlace = tile.getYPlace();
        pane.getChildren().add(this);
        this.pane = pane;

        this.setLayoutX((xPlace + yPlace) * 300);
        this.setLayoutY((yPlace - xPlace) * 100);

        upper = new Polygon(100, 0, 300, 0, 400, 100, 400, 110, 300, 210, 100, 210, 0, 110, 0, 100);
        upper.setLayoutX(-200);
        upper.setLayoutY(-100);
        upper.setFill(Color.BLUE);
        this.getChildren().add(upper);
        upperToNormal = new FillTransition(new Duration(500), upper);
        upperToNormal.setToValue(Color.BLUE);
        upperToFirst = new FillTransition(new Duration(500), upper);
        upperToFirst.setToValue(Color.GREEN);
        upperToSecond = new FillTransition(new Duration(500), upper);
        upperToSecond.setToValue(Color.RED);

        terrain = new Polygon(100, 0, 300, 0, 400, 100, 300, 200, 100, 200, 0, 100);
        terrain.setLayoutX(-200);
        terrain.setLayoutY(-100);
        terrain.setFill(tile.getTerrain().getTexture());
        this.getChildren().add(terrain);

        feature = new Polygon(100, 0, 300, 0, 400, 100, 300, 200, 100, 200, 0, 100);
        feature.setLayoutX(-200);
        feature.setLayoutY(-100);
        feature.setFill(tile.getFeature().getTexture());
        this.getChildren().add(feature);

        city = new Polygon(100, 0, 300, 0, 400, 100, 300, 200, 100, 200, 0, 100);
        city.setLayoutX(-200);
        city.setLayoutY(-100);
        city.setFill(StaticImages.City);
        city.setVisible(false);
        this.getChildren().add(city);

        rivers = new Polygon[6];
        rivers[0] = new Polygon(100, 0, 300, 0, 297, 3, 103, 3);
        rivers[1] = new Polygon(300, 0, 400, 100, 394, 100, 297, 3);
        rivers[2] = new Polygon(400, 100, 300, 200, 297, 197, 394, 100);
        rivers[3] = new Polygon(100, 200, 300, 200, 297, 197, 103, 197);
        rivers[4] = new Polygon(0, 100, 100, 200, 103, 197, 6, 100);
        rivers[5] = new Polygon(100, 0, 0, 100, 6, 100, 103, 3);
        for (int i = 0; i < 6; i++) {
            rivers[i].setLayoutX(-200);
            rivers[i].setLayoutY(-100);
            rivers[i].setFill(Color.BLUE);
            this.getChildren().add(rivers[i]);
        }

        ruin = new Rectangle(30, -30, 60, 60);
        this.getChildren().add(ruin);
        ruin.setFill(StaticImages.Ruin);
        ruin.setVisible(tile.isRuin());

        soldure = new Rectangle(-100, -50, 75, 100);
        this.getChildren().add(soldure);
        soldure.setFill(new Color(0, 0, 0, 0));
        civilian = new Rectangle(25, -50, 75, 100);
        this.getChildren().add(civilian);
        civilian.setFill(new Color(0, 0, 0 , 0));

        place = new LabelOne(tile.getXPlace() + "," + tile.getYPlace(), StaticFonts.segoeLoad(10), Pos.CENTER,
                0, -94, 20, 10, this);
        String cityString = "";
        if (tile.hasCity()) cityString = tile.getCity().getName();
        cityName = new LabelOne(cityString, StaticFonts.segoeLoad(12), Pos.CENTER,
                0, -82, 200, 12, this);
        resource = new LabelOne(tile.getResource().toString(), StaticFonts.segoeLoad(12), Pos.CENTER,
                0, 90, 200, 12, this);

        fogOfWar = new Polygon(100, 0, 300, 0, 400, 100, 300, 200, 100, 200, 0, 100);
        fogOfWar.setLayoutX(-200);
        fogOfWar.setLayoutY(-100);
        this.getChildren().add(fogOfWar);
        fogOfWar.setFill(new Color(0, 0, 0, 0));

        selected = new Polygon(100, 0, 300, 0, 400, 100, 300, 200, 100, 200, 0, 100);
        selected.setLayoutX(-200);
        selected.setLayoutY(-100);
        this.getChildren().add(selected);
        selected.setFill(new Color(0, 0, 0, 0));
        selectedToNormal = new FillTransition(new Duration(500), selected);
        selectedToNormal.setToValue(Color.TRANSPARENT);
        selectedToFirst = new FillTransition(new Duration(500), selected);
        selectedToFirst.setToValue(new Color(0, 1, 0, 0.5));
        selectedToSecond = new FillTransition(new Duration(500), selected);
        selectedToSecond.setToValue(new Color(1, 0, 0, 0.5));

        front = new Polygon(100, 0, 300, 0, 400, 100, 300, 200, 100, 200, 0, 100);
        front.setLayoutX(-200);
        front.setLayoutY(-100);
        this.getChildren().add(front);
        front.setFill(new Color(0, 0, 0, 0));

        start = new TranslateTransition(new Duration(200), this);
        start.setToY(-10);

        end  = new TranslateTransition(new Duration(200), this);
        end.setToY(0);
        //TILES EFFECTS
        front.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //front.setFill(new Color(0.2, 0, 0, 0.2));
                end.stop();
                start.play();
            }
        });
        front.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //front.setFill(new Color(0, 0, 0, 0));
                if (TileFX.this.equals(MapFX.getInstance().getFirstSelectedTile()) || TileFX.this.equals(MapFX.getInstance().getSecondSelectedTile())) return;
                start.stop();
                end.play();
            }
        });
        front.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY)) {
                    if (MapFX.getInstance().getSecondSelectedTile() == TileFX.this) {
                        MapFX.getInstance().getSecondSelectedTile().setSelectedDisable();
                        MapFX.getInstance().setSecondSelectedTile(null);
                    }
                    if (MapFX.getInstance().getFirstSelectedTile() != null) {
                        MapFX.getInstance().getFirstSelectedTile().setSelectedDisable();
                    }
                    if (MapFX.getInstance().getFirstSelectedTile() != TileFX.this) {
                        MapFX.getInstance().setFirstSelectedTile(TileFX.this);
                        MapFX.getInstance().getFirstSelectedTile().setSelectedFirst();
                    }
                    else {
                        MapFX.getInstance().setFirstSelectedTile(null);
                    }

                }
                else if (event.getButton().equals(MouseButton.SECONDARY)) {
                    if (MapFX.getInstance().getFirstSelectedTile() == TileFX.this) {
                        MapFX.getInstance().setFirstSelectedTile(null);
                    }
                    if (MapFX.getInstance().getSecondSelectedTile() != null) {
                        MapFX.getInstance().getSecondSelectedTile().setSelectedDisable();
                    }
                    if (MapFX.getInstance().getSecondSelectedTile() != TileFX.this) {
                        MapFX.getInstance().setSecondSelectedTile(TileFX.this);
                        MapFX.getInstance().getSecondSelectedTile().setSelectedSecond();
                    }
                    else {
                        MapFX.getInstance().setSecondSelectedTile(null);
                    }
                }

                if (MapFX.getInstance().getFirstSelectedTile() == null) TileMenu.getInstance().setVisible(false);
                else TileMenu.getInstance().setVisible(true);
            }
        });
    }

    public void updateTexture () {
        FogOfWarStates fogOfWarStates = GameMenuController.getInstance().getCurrentCivilizationController().
                getCivilization().getFogOfWar()[tile.getXPlace()][tile.getYPlace()];

        String cityString = "";
        if (tile.hasCity()) {
            cityString = tile.getCity().getName();
            city.setVisible(tile.isCityCenter());
        }
        cityName.changeText(cityString);

        if (fogOfWarStates.equals(FogOfWarStates.VISIBLE)) {
            if (tile.hasCivilian())
                civilian.setFill(tile.getCivilian().getTexture());
            else civilian.setFill(new Color(0, 0, 0, 0));

            if (tile.hasSoldier())
                soldure.setFill(tile.getSoldier().getTexture());
            else soldure.setFill(new Color(0, 0, 0, 0));

            ruin.setVisible(this.getTile().isRuin());
        }
        if(fogOfWarStates.equals(FogOfWarStates.FOG_OF_WAR)) fogOfWar.setFill(StaticImages.FogOfWar);
        else {
            fogOfWar.setFill(new Color(0, 0, 0, 0));
            for (int i = 0; i < 6; i++) {
                rivers[i].setVisible(tile.hasRiver(i));
            }
        }
        if (fogOfWarStates.equals(FogOfWarStates.REVEALED)) {
            civilian.setFill(Color.TRANSPARENT);
            soldure.setFill(Color.TRANSPARENT);
            fogOfWar.setFill(StaticImages.FogOfWar2);
        }
    }

    public void setSelectedDisable () {
        selectedToNormal.play();
        upperToNormal.play();
        start.stop();
        end.play();
    }
    public void setSelectedFirst() {
        selectedToFirst.play();
        upperToFirst.play();
    }
    public void setSelectedSecond() {
        selectedToSecond.play();
        upperToSecond.play();
    }
    public Tile getTile () {
        return this.tile;
    }
}
