package graphics.view.popUp;

import javafx.scene.layout.Pane;
import model.game.Civilization;

public class CivilizationInfo extends Pane {
    Civilization civilization;

    CivilizationInfo (Civilization civilization) {
        this.civilization = civilization;
    }
}
