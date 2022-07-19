package graphics.view.popUp.research;

import graphics.objects.buttons.DisableButtonOne;
import graphics.objects.labels.LabelOne;
import graphics.statics.StaticFonts;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import model.research.Research;

public class TechnologyFX extends Pane {
    Pane pane;
    DisableButtonOne buttonOne;
    Research research;


    public TechnologyFX (Research research, int X, int Y, boolean isEnable, Pane pane) {
        this.pane = pane;
        pane.getChildren().add(this);
        this.research = research;

        this.setLayoutX(250*X - 250);
        this.setLayoutY(200*Y - 100);
        buttonOne = new DisableButtonOne(research.toString(), StaticFonts.segoeLoad(15), Pos.CENTER,
                100, 50, 200, 40, this);

    }

    public boolean isEnable () {
        return buttonOne.isEnable();
    }

    public void setEnable (boolean isEnable) {
        buttonOne.setEnable(isEnable);
    }
}
