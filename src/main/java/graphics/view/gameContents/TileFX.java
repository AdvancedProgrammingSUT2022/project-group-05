package graphics.view.gameContents;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import model.tile.Tile;

public class TileFX extends Group {
    private Pane pane;
    private Polygon terrain;
    private Polygon feature;
    private Polygon front;
    private Rectangle soldure;
    private Rectangle civilian;

    public TileFX(Pane pane, Tile tile) {
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
        soldure.setFill(Color.BLUE);
        this.getChildren().add(soldure);

        civilian = new Rectangle(25, -50, 75, 100);
        civilian.setFill(Color.RED);
        this.getChildren().add(civilian);

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
    }
}
