package graphics.view.popUp.research;

import graphics.objects.buttons.DisableButtonOne;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import model.game.Civilization;

public class ResearchPanel extends Pane {
    ScrollPane scrollPane;

    public ResearchPanel(Civilization civilization) {
        this.setPrefWidth(1800);
        this.setPrefHeight(800);
        scrollPane = new ScrollPane(new TechnologyTreeMap(civilization));
        this.getChildren().add(scrollPane);
        scrollPane.setPrefHeight(800);
        scrollPane.setPrefWidth(1800);
    }
}
