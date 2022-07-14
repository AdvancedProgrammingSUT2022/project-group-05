package graphics.view.gameContents;

import controller.UnitController;
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
        UnitController.updateInstance(unit);
        return instance;
    }

    private UnitController unitController = UnitController.getInstance();

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
    private ButtonTwo buildRoute;
    private ButtonTwo removeFeature;
    private ButtonTwo remove;
    private ButtonTwo repair;

    private ButtonTwo exit;

    public UnitMenu () {
        background = new javafx.scene.shape.Rectangle(bSize*10, bSize*2);
        background.setFill(new Color(0, 0.5, 0.5, 0.4));
        this.getChildren().add(background);

        move = new ButtonTwo("Move", StaticFonts.SeqoeLoad(10), Pos.CENTER,
                bSize/2, bSize/2, bSize, bSize, this);
        sleep = new ButtonTwo("Sleep", StaticFonts.SeqoeLoad(10), Pos.CENTER,
                bSize/2 + bSize, bSize/2, bSize, bSize, this);
        alert = new ButtonTwo("Alert", StaticFonts.SeqoeLoad(10), Pos.CENTER,
                bSize/2 + bSize*2, bSize/2, bSize, bSize, this);
        fortify = new ButtonTwo("Fortify", StaticFonts.SeqoeLoad(10), Pos.CENTER,
                bSize/2 + bSize*3, bSize/2, bSize, bSize, this);
        recover = new ButtonTwo("Recover", StaticFonts.SeqoeLoad(10), Pos.CENTER,
                bSize/2 + bSize*4, bSize/2, bSize, bSize, this);
        garrison = new ButtonTwo("Garrison", StaticFonts.SeqoeLoad(10), Pos.CENTER,
                bSize/2 + bSize*5, bSize/2, bSize, bSize, this);
        wake = new ButtonTwo("Wake", StaticFonts.SeqoeLoad(10), Pos.CENTER,
                bSize/2 + bSize*6, bSize/2, bSize, bSize, this);
        cancel = new ButtonTwo("Cancel", StaticFonts.SeqoeLoad(10), Pos.CENTER,
                bSize/2 + bSize*7, bSize/2, bSize, bSize, this);
        delete = new ButtonTwo("Delete", StaticFonts.SeqoeLoad(10), Pos.CENTER,
                bSize/2 + bSize*8, bSize/2, bSize, bSize, this);
        foundCity = new ButtonTwo("FoundCity", StaticFonts.SeqoeLoad(10), Pos.CENTER,
                bSize/2 + bSize*9, bSize/2, bSize, bSize, this);

        setupRanged = new ButtonTwo("SetRanged", StaticFonts.SeqoeLoad(10), Pos.CENTER,
                bSize/2, bSize/2 + bSize, bSize, bSize, this);
        attack = new ButtonTwo("Attack", StaticFonts.SeqoeLoad(10), Pos.CENTER,
                bSize/2 + bSize, bSize/2 + bSize, bSize, bSize, this);
        attackCity = new ButtonTwo("AttCity", StaticFonts.SeqoeLoad(10), Pos.CENTER,
                bSize/2 + bSize*2, bSize/2 + bSize, bSize, bSize, this);
        conquerCity = new ButtonTwo("Conquer", StaticFonts.SeqoeLoad(10), Pos.CENTER,
                bSize/2 + bSize*3, bSize/2 + bSize, bSize, bSize, this);
        buildImprovement = new ButtonTwo("BuildImp", StaticFonts.SeqoeLoad(10), Pos.CENTER,
                bSize/2 + bSize*4, bSize/2 + bSize, bSize, bSize, this);
        buildRoute = new ButtonTwo("BuildRoute", StaticFonts.SeqoeLoad(10), Pos.CENTER,
                bSize/2 + bSize*5, bSize/2 + bSize, bSize, bSize, this);
        removeFeature = new ButtonTwo("RemFeature", StaticFonts.SeqoeLoad(10), Pos.CENTER,
                bSize/2 + bSize*6, bSize/2 + bSize, bSize, bSize, this);
        remove = new ButtonTwo("Remove", StaticFonts.SeqoeLoad(10), Pos.CENTER,
                bSize/2 + bSize*7, bSize/2 + bSize, bSize, bSize, this);
        repair = new ButtonTwo("Repair", StaticFonts.SeqoeLoad(10), Pos.CENTER,
                bSize/2 + bSize*8, bSize/2 + bSize, bSize, bSize, this);

        exit = new ButtonTwo("EXIT", StaticFonts.SeqoeLoad(10), Pos.CENTER,
                bSize/2 + bSize*9, bSize/2 + bSize, bSize, bSize, this);

        //Functions

        move.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            }
        });

        sleep.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                unitController.unitSleep();
            }
        });

        alert.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                unitController.unitAlert();
            }
        });

        fortify.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                unitController.unitFortify();
            }
        });

        recover.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                unitController.unitRecover();
            }
        });

        garrison.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                unitController.unitGarrison();
            }
        });

        wake.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                unitController.unitWake();
            }
        });



        cancel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                unitController.unitCancel();
            }
        });

        foundCity.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                unitController.unitFoundCity();
            }
        });

        setupRanged.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                unitController.unitSetupRanged();
            }
        });

//        attack.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                unitController.unitAttack();
//            }
//        });

//        conquerCity.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                unitController.conquerCity();
//            }
//        });

//        buildImprovement.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                unitController.unitBuildImprovement();
//            }
//        });

//        buildRoute.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                unitController.unitBuildRoute();
//            }
//        });

//

//        remove.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//
//            }
//        });

        repair.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                unitController.unitRepair();
            }
        });

        delete.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                unitController.unitDelete();
            }
        });




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
