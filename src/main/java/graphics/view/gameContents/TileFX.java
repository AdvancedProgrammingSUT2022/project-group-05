package graphics.view.gameContents;
import controller.GameMenuController;
import graphics.statics.StaticImages;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import model.map.FogOfWarStates;
import model.tile.Tile;

public class TileFX extends Group {
    private Pane pane;
    private Tile tile;
    private Polygon terrain;
    private Polygon feature;
    private Polygon fogOfWar;
    private Polygon selected;
    private Polygon front;
    private Rectangle soldure;
    private Rectangle civilian;

    public TileFX(Pane pane, Tile tile) {
        this.tile = tile;
        int xPlace = tile.getXPlace();
        int yPlace = tile.getYPlace();
        pane.getChildren().add(this);
        this.pane = pane;

        this.setLayoutX((xPlace + yPlace) * 300);
        this.setLayoutY((yPlace - xPlace) * 100);

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

        soldure = new Rectangle(-100, -50, 75, 100);
        this.getChildren().add(soldure);
        soldure.setFill(new Color(0, 0, 0, 0));
        civilian = new Rectangle(25, -50, 75, 100);
        this.getChildren().add(civilian);
        civilian.setFill(new Color(0, 0, 0 , 0));

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

        front = new Polygon(100, 0, 300, 0, 400, 100, 300, 200, 100, 200, 0, 100);
        front.setLayoutX(-200);
        front.setLayoutY(-100);
        this.getChildren().add(front);
        front.setFill(new Color(0, 0, 0, 0));


        //TILES EFFECTS
        front.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                front.setFill(new Color(0.2, 0, 0, 0.2));
            }
        });
        front.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                front.setFill(new Color(0, 0, 0, 0));
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

        if (fogOfWarStates.equals(FogOfWarStates.VISIBLE)) {
            if (tile.hasCivilian())
                civilian.setFill(tile.getCivilian().getTexture());
            else civilian.setFill(new Color(0, 0, 0, 0));

            if (tile.hasSoldier())
                soldure.setFill(tile.getSoldier().getTexture());
            else soldure.setFill(new Color(0, 0, 0, 0));
        }
        if(fogOfWarStates.equals(FogOfWarStates.FOG_OF_WAR)) fogOfWar.setFill(StaticImages.FogOfWar);
        else fogOfWar.setFill(new Color(0, 0, 0, 0));
    }

    public void setSelectedDisable () {
        selected.setFill(new Color(0, 0, 0, 0));
    }
    public void setSelectedFirst() {
        selected.setFill(new Color(0 ,0.5, 0, 0.3));

    }
    public void setSelectedSecond() {
        selected.setFill(new Color(0.5 ,0, 0, 0.3));
    }
    public Tile getTile () {
        return this.tile;
    }
}
