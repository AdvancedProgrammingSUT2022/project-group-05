package graphics.view.menus;

import controller.GameMenuController;
import graphics.view.gameContents.MainPanel;
import graphics.view.gameContents.MapFX;
import javafx.scene.layout.Pane;
import model.game.Civilization;

public class Game extends Pane {
    public Game (Civilization civilization) {

        this.getChildren().add(MapFX.getInstance());
        this.getChildren().add(new MainPanel(civilization));

    }
}
