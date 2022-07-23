package graphics.view.gameContents;

import client.ClientManager;
import controller.GameMenuController;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import model.map.FogOfWar;
import model.map.Map;

public class MapFX extends Pane {
    private static MapFX instance = null;
    private static double xDragged;
    private static double yDragged;

    public static TileFX[][] tileFXES = new TileFX[100][100];

    private static TileFX firstSelectedTile = null;
    private static TileFX secondSelectedTile = null;

    public static MapFX getInstance () {
        if (instance == null) {
            instance = new MapFX();
            int mapSize = Map.getInstance().getSizeOfMap();
            for (int i = mapSize - 1; i >= 0; i--) {
                for (int j = 0; j < mapSize; j++) {
                   tileFXES[i][j] =  new TileFX(instance, Map.getInstance().getTileFromMap(i, j));
                }
            }
            instance.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    xDragged = instance.getLayoutX() - event.getSceneX();
                    yDragged = instance.getLayoutY() - event.getSceneY();
                }
            });
            instance.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    instance.setLayoutX(xDragged + event.getSceneX());
                    instance.setLayoutY(yDragged + event.getSceneY());
                }
            });
            instance.setOnScroll(new EventHandler<ScrollEvent>() {
                @Override
                public void handle(ScrollEvent event) {
                    double s = event.getDeltaY();
                    if (s > 0) {
                        if (instance.getScaleX() < 1.25) {
                            instance.setScaleX(instance.getScaleX() * 1.03);
                            instance.setScaleY(instance.getScaleY() * 1.03);
                        }
                    }
                    else {
                        if (instance.getScaleX() > 0.3) {
                            instance.setScaleX(instance.getScaleX() / 1.03);
                            instance.setScaleY(instance.getScaleY() / 1.03);
                        }
                    }
                }
            });
        }
        return instance;
    }

    public void setFirstSelectedTile(TileFX firstSelectedTile) {
        MapFX.firstSelectedTile = firstSelectedTile;
    }

    public void setSecondSelectedTile(TileFX secondSelectedTile) {
        MapFX.secondSelectedTile = secondSelectedTile;
    }

    public TileFX getFirstSelectedTile() {
        return firstSelectedTile;
    }

    public TileFX getSecondSelectedTile() {
        return secondSelectedTile;
    }

    public void updateMapTextures () {
        FogOfWar.updateFogOfWar(GameMenuController.getInstance().getCivilizationByUsername(ClientManager
                .getInstance().getMainUser().getUsername()));
        int mapSize = Map.getInstance().getSizeOfMap();
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                tileFXES[i][j].updateTexture();
            }
        }
    }
}
