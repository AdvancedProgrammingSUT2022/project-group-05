package graphics.view.menus;

import graphics.view.gameContents.MainPanel;
import graphics.view.gameContents.MapFX;
import graphics.view.gameContents.TileMenu;
import javafx.animation.ParallelTransition;
import javafx.scene.layout.Pane;
import model.game.Civilization;

public class Game extends Pane {
    public Game (Civilization civilization) {
        this.getChildren().add(MapFX.getInstance());
        this.getChildren().add(new MainPanel(civilization));
        this.getChildren().add(TileMenu.getInstance());

        //ANIMATION
        ParallelTransition start = AnimatedPane.getStartAnimation(this);
        ParallelTransition end = AnimatedPane.getEndAnimation(this);
        start.play();
    }
}
