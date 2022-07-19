package graphics.view.menus;

import graphics.view.gameContents.MainPanel;
import graphics.view.gameContents.MapFX;
import graphics.view.gameContents.TileMenu;
import javafx.scene.layout.Pane;
import model.game.Civilization;

public class Game extends Pane {
    public Game (Civilization civilization) {

        this.getChildren().add(MapFX.getInstance());
        this.getChildren().add(new MainPanel(civilization));
        this.getChildren().add(TileMenu.getInstance());
    }
}
