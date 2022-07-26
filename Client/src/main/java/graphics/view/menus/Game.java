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

import java.util.ArrayList;

public class Game extends Pane {

    public boolean isLocal;

    public Game (Civilization civilization, boolean isLocal) {

        this.isLocal = isLocal;

        this.setScaleX(ClientManager.getInstance().getMainStage().getWidth() / 1920);
        this.setScaleY(ClientManager.getInstance().getMainStage().getHeight() / 1080);

        MapFX.getInstance().updateMapTextures(isLocal);
        this.getChildren().add(MapFX.getInstance());
        this.getChildren().add(new MainPanel(civilization, isLocal));
        this.getChildren().add(TileMenu.getInstance());

        //ANIMATION
        this.getChildren().add(UnitMenu.getInstance());

    }
}
