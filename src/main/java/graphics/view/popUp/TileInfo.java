package graphics.view.popUp;

import graphics.objects.labels.LabelOne;
import graphics.statics.StaticFonts;
import javafx.geometry.Pos;
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

    LabelOne location;
    LabelOne gold;
    LabelOne food;
    LabelOne production;
    LabelOne combatBoost;
    LabelOne MPCost;

    LabelOne soldier;
    LabelOne civilization;
    LabelOne city;
    LabelOne civilian;

    Rectangle soldierImage;
    Rectangle civilianImage;

    LabelOne improvement;

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


        location = new LabelOne(tile.getXPlace() + "," + tile.getYPlace(), StaticFonts.segoeLoad(20), Pos.CENTER,
                600, 50, 600, 60, this);
        gold = new LabelOne("GOLD : " + tile.getGold(), StaticFonts.segoeLoad(25), Pos.CENTER,
                600, 200, 600, 60, this);
        food = new LabelOne("FOOD : " + tile.getFood(), StaticFonts.segoeLoad(25), Pos.CENTER,
                600, 240, 600, 60, this);
        production = new LabelOne("PRODUCTION : " + tile.getProduction(), StaticFonts.segoeLoad(25), Pos.CENTER,
                600, 280, 600, 60, this);

        combatBoost = new LabelOne("COMBAT BOOST : " + tile.getCombatBoost(), StaticFonts.segoeLoad(25), Pos.CENTER,
                600, 360, 600, 60, this);
        MPCost = new LabelOne("MP COST : " + tile.getMovementCost(), StaticFonts.segoeLoad(25), Pos.CENTER,
                600, 400, 600, 60, this);


        city = new LabelOne(cityName, StaticFonts.segoeLoad(40), Pos.CENTER,
                600, 500, 600, 60, this);
        civilization = new LabelOne(civName, StaticFonts.segoeLoad(30), Pos.CENTER,
                600, 550, 600, 60, this);

        improvement = new LabelOne(tile.getImprovement().name(), StaticFonts.segoeLoad(40), Pos.CENTER,
                600, 680, 600, 60, this);

        String solName = "";
        String civilName = "";
        if (tile.getSoldier() != null) solName = tile.getSoldier().toString();
        if (tile.getCivilian() != null) civilName = tile.getCivilian().toString();
        soldier = new LabelOne(solName, StaticFonts.segoeLoad(20), Pos.CENTER,
                200, 650, 200, 60, this);
        civilian = new LabelOne(civilName, StaticFonts.segoeLoad(20), Pos.CENTER,
                1000, 650, 200, 60, this);

        civilianImage = new Rectangle(50, 200,300, 400);
        soldierImage = new Rectangle(850, 200,300, 400);
        if (tile.hasCivilian())
            civilianImage.setFill(tile.getCivilian().getTexture());
        else civilianImage.setFill(new Color(0, 0, 0, 0));
        if (tile.hasSoldier())
            soldierImage.setFill(tile.getSoldier().getTexture());
        else soldierImage.setFill(new Color(0, 0, 0, 0));
        this.getChildren().add(civilianImage);
        this.getChildren().add(soldierImage);
    }
}
