package graphics.view.gameContents;

import graphics.objects.buttons.ButtonTwo;
import graphics.statics.StaticFonts;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.unit.Unit;

//TODO add functions and closing system of this menu

public class UnitMenu extends Pane{
    private static UnitMenu instance = null;
    private static int bSize = 60;
    public static UnitMenu getInstance(Unit unit) {
        if (instance == null) {
            instance = new UnitMenu();
        }
        instance.selectedUnit = unit;
        return instance;
    }

    private Unit selectedUnit = null;

    private Rectangle background;

    private ButtonTwo move;
    private ButtonTwo sleep;
    private ButtonTwo alert;
    private ButtonTwo fortify;
    private ButtonTwo recover;
    private ButtonTwo garrison;
    private ButtonTwo wake;
    private ButtonTwo cancel;
    private ButtonTwo delete;

    private ButtonTwo foundCity;

    private ButtonTwo setupRanged;
    private ButtonTwo attack;
    private ButtonTwo attackCity;
    private ButtonTwo conquerCity;

    private ButtonTwo buildImprovement;
    private ButtonTwo buildRout;
    private ButtonTwo removeFeature;
    private ButtonTwo remove;
    private ButtonTwo repair;

    private ButtonTwo exit;

    public UnitMenu () {
        background = new javafx.scene.shape.Rectangle(bSize*10, bSize*2);
        background.setFill(new Color(0, 0.5, 0.5, 0.4));
        this.getChildren().add(background);

        move = new ButtonTwo("Move", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2, bSize/2, bSize, bSize, this);
        sleep = new ButtonTwo("Sleep", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2 + bSize, bSize/2, bSize, bSize, this);
        alert = new ButtonTwo("Alert", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2 + bSize*2, bSize/2, bSize, bSize, this);
        fortify = new ButtonTwo("Fortify", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2 + bSize*3, bSize/2, bSize, bSize, this);
        recover = new ButtonTwo("Recover", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2 + bSize*4, bSize/2, bSize, bSize, this);
        garrison = new ButtonTwo("Garrison", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2 + bSize*5, bSize/2, bSize, bSize, this);
        wake = new ButtonTwo("Wake", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2 + bSize*6, bSize/2, bSize, bSize, this);
        cancel = new ButtonTwo("Cancel", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2 + bSize*7, bSize/2, bSize, bSize, this);
        delete = new ButtonTwo("Delete", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2 + bSize*8, bSize/2, bSize, bSize, this);
        foundCity = new ButtonTwo("FoundCity", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2 + bSize*9, bSize/2, bSize, bSize, this);

        setupRanged = new ButtonTwo("SetRanged", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2, bSize/2 + bSize, bSize, bSize, this);
        attack = new ButtonTwo("Attack", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2 + bSize, bSize/2 + bSize, bSize, bSize, this);
        attackCity = new ButtonTwo("AttCity", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2 + bSize*2, bSize/2 + bSize, bSize, bSize, this);
        conquerCity = new ButtonTwo("Conquer", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2 + bSize*3, bSize/2 + bSize, bSize, bSize, this);
        buildImprovement = new ButtonTwo("BuildImp", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2 + bSize*4, bSize/2 + bSize, bSize, bSize, this);
        buildRout = new ButtonTwo("BuildRoute", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2 + bSize*5, bSize/2 + bSize, bSize, bSize, this);
        removeFeature = new ButtonTwo("RemFeature", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2 + bSize*6, bSize/2 + bSize, bSize, bSize, this);
        remove = new ButtonTwo("Remove", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2 + bSize*7, bSize/2 + bSize, bSize, bSize, this);
        repair = new ButtonTwo("Repair", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2 + bSize*8, bSize/2 + bSize, bSize, bSize, this);

        exit = new ButtonTwo("EXIT", StaticFonts.segoeLoad(10), Pos.CENTER,
                bSize/2 + bSize*9, bSize/2 + bSize, bSize, bSize, this);

        exit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setVisible(false);
                //can add animation here
            }
        });

        //TODO other functions

        this.setLayoutX(960 - 5*bSize);
        this.setLayoutY(1060 - 2*bSize);
    }
}
