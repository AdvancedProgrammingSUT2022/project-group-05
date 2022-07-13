package graphics.view.gameContents;

import model.unit.Unit;

public class UnitMenu {
    private static UnitMenu instance = null;
    public static UnitMenu getInstance(Unit unit) {
        if (instance == null) {
            instance = new UnitMenu();
        }
        instance.selectedUnit = unit;
        return instance;
    }

    private Unit selectedUnit = null;
}
