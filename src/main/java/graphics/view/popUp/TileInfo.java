package graphics.view.popUp;

import javafx.scene.layout.Pane;
import model.tile.Tile;

public class TileInfo extends Pane {
    Tile tile;

    TileInfo (Tile tile) {
        this.tile = tile;
    }
}
