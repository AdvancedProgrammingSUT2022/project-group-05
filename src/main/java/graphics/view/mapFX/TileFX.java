package graphics.view.mapFX;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class TileFX extends Group {
    private Pane pane;
    private Polygon background;
    private Polygon front;

    public TileFX(Pane pane, int xPlace, int yPlace) {
        pane.getChildren().add(this);
        this.pane = pane;

        this.setLayoutX((xPlace + yPlace) * 300);
        this.setLayoutY((yPlace - xPlace) * 100);

        background = new Polygon(100, 0, 300, 0, 400, 100, 300, 200, 100, 200, 0, 100);
        background.setLayoutX(-200);
        background.setLayoutY(-100);
        this.getChildren().add(background);

        front = new Polygon(100, 0, 300, 0, 400, 100, 300, 200, 100, 200, 0, 100);
        front.setLayoutX(-200);
        front.setLayoutY(-100);
        this.getChildren().add(front);
        front.setFill(new Color(0, 0, 0, 0));

        background.setFill(Color.RED);
    }
}
