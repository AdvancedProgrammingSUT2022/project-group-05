package graphics.view.mapFX;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import model.map.Map;

public class MapFX extends Pane {
    private static MapFX instance = null;


    public static MapFX getInstance () {
        if (instance == null) {
            instance = new MapFX();
            int mapSize = Map.getInstance().getSizeOfMap();
            for (int i = 0; i < mapSize; i++) {
                for (int j = 0; j < mapSize; j++) {
                    new TileFX(instance, Map.getInstance().getTileFromMap(i, j));
                }
            }
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
}
