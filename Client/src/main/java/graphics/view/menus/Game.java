package graphics.view.menus;

import client.ClientManager;
import graphics.view.gameContents.MainPanel;
import graphics.view.gameContents.MapFX;
import graphics.view.gameContents.TileMenu;
import javafx.animation.ParallelTransition;
import graphics.view.gameContents.UnitMenu;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.game.Civilization;

public class Game extends Pane {
    public Game (Civilization civilization) {

        this.setScaleX(ClientManager.getInstance().getMainStage().getWidth() / 1920);
        this.setScaleY(ClientManager.getInstance().getMainStage().getHeight() / 1080);


        this.getChildren().add(MapFX.getInstance());
        this.getChildren().add(new MainPanel(civilization));
        this.getChildren().add(TileMenu.getInstance());

        //ANIMATION
        this.getChildren().add(UnitMenu.getInstance());

    }
}
