package graphics.view.popUp.city;

import graphics.objects.buttons.ButtonOne;
import graphics.objects.labels.LabelOne;
import graphics.statics.StaticFonts;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import model.building.Building;
import model.unit.Unit;
import model.unit.civilian.Settler;
import model.unit.civilian.Worker;
import model.unit.soldier.melee.*;
import model.unit.soldier.ranged.Archer;
import model.unit.soldier.ranged.ChariotArcher;
import model.unit.soldier.ranged.Crossbowman;
import model.unit.soldier.ranged.siege.Artillery;
import model.unit.soldier.ranged.siege.Canon;
import model.unit.soldier.ranged.siege.Catapult;
import model.unit.soldier.ranged.siege.Trebuchet;

import java.util.ArrayList;

public class ProductionPane extends Pane{
    private final Object production;

    private ButtonOne buyButton;
    private ButtonOne produceButton;

    public ProductionPane(Object production) {
        this.production = production;

        this.setPrefWidth(480);
        this.setPrefHeight(75);

        this.setName();
        this.setType();
        this.setCost();

        this.setBuyButton();
        this.setProduceButton();
    }

    private void setName() {
        new LabelOne("name: " + this.getProductionName(), StaticFonts.segoeLoad(15), Pos.CENTER_LEFT,
                120, 12, 200, 25, this);
    }

    private void setType() {
        new LabelOne("type: " + this.getProductionType(), StaticFonts.segoeLoad(15), Pos.CENTER_LEFT,
                120, 37, 200, 25, this);
    }

    private void setCost() {
        new LabelOne("cost: " + this.getProductionCost(), StaticFonts.segoeLoad(15), Pos.CENTER_LEFT,
                120, 62, 200, 25, this);
    }

    private void setBuyButton() {
        this.buyButton = new ButtonOne("buy", StaticFonts.segoeLoad(15), Pos.CENTER,
                360, 20, 200, 30, this);
    }

    private void setProduceButton() {
        this.produceButton = new ButtonOne("produce", StaticFonts.segoeLoad(15), Pos.CENTER,
                360, 55, 200, 30, this);
    }

    //GETTERS
    public Object getProduction() {
        return this.production;
    }

    public String getProductionName() {
        if (production instanceof Unit) return ((Unit) production).toString().toUpperCase();
        else return ((Building) production).name();
    }

    public String getProductionType() {
        if (production instanceof Unit) return "unit";
        else return "building";
    }

    public int getProductionCost() {
        if (production instanceof Unit) return ((Unit) production).getCost();
        else return ((Building) production).getCost();
    }

    public ButtonOne getBuyButton() {
        return this.buyButton;
    }

    public ButtonOne getProduceButton() {
        return this.produceButton;
    }

    //STATIC
    public static ArrayList<ProductionPane> getAllButtons() {
        ArrayList<ProductionPane> result = new ArrayList<>();

        for (Building building : Building.values()) {
            result.add(new ProductionPane(building));
        }

        result.add(new ProductionPane(new Archer(null, null)));
        result.add(new ProductionPane(new ChariotArcher(null, null)));
        result.add(new ProductionPane(new Scout(null, null)));
        result.add(new ProductionPane(new Settler(null, null)));
        result.add(new ProductionPane(new Spearman(null, null)));
        result.add(new ProductionPane(new Warrior(null, null)));
        result.add(new ProductionPane(new Worker(null, null)));
        result.add(new ProductionPane(new Catapult(null, null)));
        result.add(new ProductionPane(new Horseman(null, null)));
        result.add(new ProductionPane(new Swordsman(null, null)));
        result.add(new ProductionPane(new Crossbowman(null, null)));
        result.add(new ProductionPane(new Knight(null, null)));
        result.add(new ProductionPane(new Longswordsman(null, null)));
        result.add(new ProductionPane(new Pikeman(null, null)));
        result.add(new ProductionPane(new Trebuchet(null, null)));
        result.add(new ProductionPane(new Canon(null, null)));
        result.add(new ProductionPane(new Cavalry(null, null)));
        result.add(new ProductionPane(new Lancer(null, null)));
        result.add(new ProductionPane(new Musketman(null, null)));
        result.add(new ProductionPane(new Rifleman(null, null)));
        result.add(new ProductionPane(new AntiTankGun(null, null)));
        result.add(new ProductionPane(new Artillery(null, null)));
        result.add(new ProductionPane(new Infantry(null, null)));
        result.add(new ProductionPane(new Panzer(null, null)));
        result.add(new ProductionPane(new Tank(null, null)));

        return result;
    }
}
