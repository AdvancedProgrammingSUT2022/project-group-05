package graphics.view.popUp;

import graphics.objects.buttons.ButtonOne;
import graphics.statics.StaticFonts;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Setting extends Pane {
    private Rectangle background;
    private ButtonOne ExitGame;

    public Setting () {
        background = new Rectangle(400, 300);
        this.setPrefHeight(background.getHeight());
        this.setPrefWidth(background.getWidth());

        background.setFill(Color.WHITE);
        this.getChildren().add(background);

        ExitGame = new ButtonOne("EXIT FROM GAME", StaticFonts.segoeLoad(30), Pos.CENTER,
                background.getWidth()/2, 100, 300, 50, this);
    }
}
