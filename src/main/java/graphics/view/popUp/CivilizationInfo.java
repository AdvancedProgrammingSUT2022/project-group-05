package graphics.view.popUp;

import controller.GameMenuController;
import graphics.objects.buttons.ButtonOne;
import graphics.objects.labels.LabelOne;
import graphics.statics.StaticFonts;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import model.game.Civilization;

public class CivilizationInfo extends Pane {
    private Civilization civilization;

    public CivilizationInfo (Civilization civilization) {
        this.civilization = civilization;

        new LabelOne(GameMenuController.getInstance().infoDemographics(), StaticFonts.SeqoeLoad(30), Pos.CENTER,
                300, 75, 200, 60, this);
    }
}
