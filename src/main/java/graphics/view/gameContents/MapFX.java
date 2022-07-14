package graphics.view.gameContents;

import controller.GameMenuController;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.DragEvent;
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

    public static MapFX getInstance () {
        if (instance == null) {
            instance = new MapFX();
            int mapSize = Map.getInstance().getSizeOfMap();
            for (int i = 0; i < mapSize; i++) {
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
                        if (instance.getScaleX() > 0.75) {
                            instance.setScaleX(instance.getScaleX() / 1.03);
                            instance.setScaleY(instance.getScaleY() / 1.03);
                        }
                    }
                }
            });
        }
        return instance;
    }

    public void updateMapTextures () {
        FogOfWar.updateFogOfWar(GameMenuController.getInstance().getCurrentCivilizationController().getCivilization());
        int mapSize = Map.getInstance().getSizeOfMap();
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                tileFXES[i][j].updateTexture();
            }
        }
    }
}
