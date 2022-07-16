package graphics.view.popUp.city;

import graphics.objects.buttons.ButtonOne;
import graphics.objects.labels.LabelOne;
import graphics.statics.StaticFonts;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import model.game.City;
import model.tile.Tile;

import java.util.ArrayList;

public class TilePane extends Pane{
    private final Tile tile;

    private ButtonOne assignCitizenButton;
    private ButtonOne removeCitizenButton;

    public TilePane(Tile tile) {
        this.tile = tile;

        this.setPrefWidth(480);
        this.setPrefHeight(75);

        this.setGold();
        this.setFood();
        this.setHasCitizen();

        this.setAssignCitizenButton();
        this.setRemoveCitizenButton();
    }

    private void setGold() {
        new LabelOne("gold<potential>: " + tile.getGold() + "<" + tile.getGoldPotential() +  ">", StaticFonts.segoeLoad(15), Pos.CENTER_LEFT,
                120, 12, 200, 25, this);
    }

    private void setFood() {
        new LabelOne("food<potential>: " + tile.getFood() + "<" + tile.getFoodPotential() +  ">", StaticFonts.segoeLoad(15), Pos.CENTER_LEFT,
                120, 37, 200, 25, this);
    }

    private void setHasCitizen() {
        new LabelOne("has citizen: " + tile.hasCitizen(), StaticFonts.segoeLoad(15), Pos.CENTER_LEFT,
                120, 62, 200, 25, this);
    }

    private void setAssignCitizenButton() {
        this.assignCitizenButton = new ButtonOne("assign citizen", StaticFonts.segoeLoad(15), Pos.CENTER,
                360, 20, 200, 30, this);
    }

    private void setRemoveCitizenButton() {
        this.removeCitizenButton = new ButtonOne("remove citizen", StaticFonts.segoeLoad(15), Pos.CENTER,
                360, 55, 200, 30, this);
    }

    //GETTER
    public Tile getTile() {
        return this.tile;
    }

    public ButtonOne getAssignCitizenButton() {
        return this.assignCitizenButton;
    }

    public ButtonOne getRemoveCitizenButton() {
        return this.removeCitizenButton;
    }

    //STATIC
    public static ArrayList<TilePane> getAllButtons(City city) {
        ArrayList<TilePane> result = new ArrayList<>();

        for (Tile tile : city.getTiles()) {
            result.add(new TilePane(tile));
        }

        return result;
    }
}
