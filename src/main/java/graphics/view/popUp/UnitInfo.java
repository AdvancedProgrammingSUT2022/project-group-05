package graphics.view.popUp;

import javafx.scene.layout.Pane;
import model.unit.Unit;

public class UnitInfo extends Pane {
    Unit unit;

    UnitInfo (Unit unit) {
        this.unit = unit;
    }
}
