package graphics.view;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import graphics.objects.buttons.ButtonOne;
import graphics.objects.labels.LabelOne;
import graphics.statics.StaticFonts;


public class ErrorBox {
    public ErrorBox () {

    }

    public static Pane getErrorBox (String errorText, Pane father) {
        Pane error = new Pane();
        Rectangle background = new Rectangle(0, 0, 600, 150);
        error.getChildren().add(background);
        background.setFill(Color.RED);
        LabelOne text = new LabelOne(errorText, StaticFonts.segoeLoad(25), Pos.CENTER,
                300, 40, 400, 50, error);
        ButtonOne ok = new ButtonOne("OK", StaticFonts.segoeLoad(40), Pos.CENTER,
                300, 120, 80, 50, error);

        error.setLayoutX(960 - 300);
        error.setLayoutY(400);

        father.getChildren().add(error);
        ok.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                father.getChildren().remove(error);
            }
        });

        return error;
    }
}
