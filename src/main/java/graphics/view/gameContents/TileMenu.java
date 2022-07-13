package graphics.view.gameContents;

import graphics.objects.buttons.ButtonTwo;
import graphics.statics.StaticFonts;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

//TODO add functions and closing system of this menu

public class TileMenu extends Pane {
    private static TileMenu instance = null;
    private static int bSize = 60;
    public static TileMenu getInstance(TileFX tileFX) {
        if (instance == null) {
            instance = new TileMenu();
        }
        instance.selectedTile = tileFX;
        return instance;
    }

    private TileFX selectedTile = null;
    private Rectangle background;
    private ButtonTwo civilizationInfo;
    private ButtonTwo cityInfo;
    private ButtonTwo tileInfo;
    private ButtonTwo civInfo;
    private ButtonTwo solInfo;
    private ButtonTwo selCiv;
    private ButtonTwo selSol;
    private ButtonTwo exit;

    private TileMenu () {
        background = new Rectangle(bSize*8, bSize);
        background.setFill(new Color(0, 0.5, 0.5, 0.4));
        this.getChildren().add(background);

        civilizationInfo = new ButtonTwo("CivInf", StaticFonts.SeqoeLoad(10), Pos.CENTER,
                bSize/2, bSize/2, bSize, bSize, this);
        cityInfo = new ButtonTwo("CitInf", StaticFonts.SeqoeLoad(10), Pos.CENTER,
                bSize/2 + bSize, bSize/2, bSize, bSize, this);
        tileInfo = new ButtonTwo("TilInf", StaticFonts.SeqoeLoad(10), Pos.CENTER,
                bSize/2 + bSize * 2, bSize/2, bSize, bSize, this);
        civInfo = new ButtonTwo("CivInf", StaticFonts.SeqoeLoad(10), Pos.CENTER,
                bSize/2 + bSize * 3, bSize/2, bSize, bSize, this);
        solInfo = new ButtonTwo("SolInf", StaticFonts.SeqoeLoad(10), Pos.CENTER,
                bSize/2 + bSize * 4, bSize/2, bSize, bSize, this);
        selCiv = new ButtonTwo("SelCiv", StaticFonts.SeqoeLoad(10), Pos.CENTER,
                bSize/2 + bSize * 5, bSize/2, bSize, bSize, this);
        selSol = new ButtonTwo("SelSol", StaticFonts.SeqoeLoad(10), Pos.CENTER,
                bSize/2 + bSize * 6, bSize/2, bSize, bSize, this);
        exit = new ButtonTwo("EXIT", StaticFonts.SeqoeLoad(10), Pos.CENTER,
                bSize/2 + bSize * 7, bSize/2, bSize, bSize, this);

        exit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setVisible(false);
                //can add animation here
            }
        });

        //TODO other functions

        this.setLayoutX(960 - 4*bSize);
        this.setLayoutY(1040 - bSize);
    }
}
