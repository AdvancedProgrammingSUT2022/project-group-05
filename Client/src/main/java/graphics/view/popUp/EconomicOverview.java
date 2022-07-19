package graphics.view.popUp;

import graphics.objects.labels.LabelOne;
import javafx.scene.layout.Pane;
import model.game.Civilization;

public class EconomicOverview extends Pane{
    private Civilization civilization;

    private LabelOne title;

    public EconomicOverview(Civilization civilization) {
        this.civilization = civilization;


    }
}
