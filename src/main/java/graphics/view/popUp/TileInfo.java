package graphics.view.popUp;

import graphics.objects.buttons.ButtonOne;
import graphics.objects.labels.LabelOne;
import graphics.statics.StaticFonts;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.tile.Tile;

public class TileInfo extends Pane {
    Tile tile;
    Rectangle background ;

    LabelOne terrain;
    LabelOne resource;
    LabelOne feature;

    LabelOne soldier;
    LabelOne civilization;
    LabelOne city;
    LabelOne civilian;

    Rectangle soldierImage;
    Rectangle civilianImage;

    LabelOne improvement;

    //TODO add gold per turn and project status info

    public TileInfo(Tile tile) {
        this.tile = tile;

        background = new Rectangle(1200, 800);
        this.setPrefWidth(background.getWidth());
        this.setPrefHeight(background.getHeight());
        background.setFill(Color.WHITE);
        this.getChildren().add(background);

        terrain = new LabelOne(tile.getTerrain().name(), StaticFonts.segoeLoad(40), Pos.CENTER,
                200, 120, 300, 60, this);
        resource = new LabelOne(tile.getResource().name(), StaticFonts.segoeLoad(40), Pos.CENTER,
                600, 120, 300, 60, this);
        feature = new LabelOne(tile.getFeature().name(), StaticFonts.segoeLoad(40), Pos.CENTER,
                1000, 120, 300, 60, this);

        String cityName = "NO_CITY";
        String civName = "NO_CIVILIZATION";
        if (tile.getCity() != null) {
            cityName = tile.getCity().getName();
            civName = tile.getCity().getCivilization().getPlayer().getNickname();
        }
        city = new LabelOne(cityName, StaticFonts.segoeLoad(40), Pos.CENTER,
                600, 350, 600, 60, this);
        civilization = new LabelOne(civName, StaticFonts.segoeLoad(30), Pos.CENTER,
                600, 450, 600, 60, this);

        improvement = new LabelOne(tile.getImprovement().name(), StaticFonts.segoeLoad(40), Pos.CENTER,
                600, 680, 600, 60, this);

        String solName = "";
        String civilName = "";
        if (tile.getSoldier() != null) solName = tile.getSoldier().toString();
        if (tile.getCivilian() != null) civilName = tile.getCivilian().toString();
        soldier = new LabelOne(solName, StaticFonts.segoeLoad(20), Pos.CENTER,
                200, 600, 200, 60, this);
        civilian = new LabelOne(civilName, StaticFonts.segoeLoad(20), Pos.CENTER,
                1000, 600, 200, 60, this);
    }
}
