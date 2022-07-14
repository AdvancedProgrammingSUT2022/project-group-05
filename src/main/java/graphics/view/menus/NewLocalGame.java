package graphics.view.menus;

import graphics.objects.buttons.ButtonOne;
import graphics.objects.buttons.ButtonTwo;
import graphics.objects.labels.LabelOne;
import graphics.objects.labels.LabelTwo;
import graphics.statics.StaticFonts;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;

//TODO add functions

public class NewLocalGame extends Pane {
    int size = 2;
    int players = 2;

    LabelOne title;

    ButtonTwo small;
    ButtonTwo medium;
    ButtonTwo big;

    ButtonTwo two;
    ButtonTwo three;
    ButtonTwo four;

    ButtonOne createGame;
    ButtonOne back;

    public NewLocalGame () {
        title = new LabelOne("NEW LOCAL GAME", StaticFonts.SeqoeLoad(50), Pos.CENTER,
                960, 200, 600, 70, this);

        small = new ButtonTwo("SMALL", StaticFonts.SeqoeLoad(40), Pos.CENTER,
                680, 400, 400, 60, this);
        medium = new ButtonTwo("MEDIUM", StaticFonts.SeqoeLoad(40), Pos.CENTER,
                680, 500, 400, 60, this);
        big = new ButtonTwo("BIG", StaticFonts.SeqoeLoad(40), Pos.CENTER,
                680, 600, 400, 60, this);

        two = new ButtonTwo("2 PLAYERS", StaticFonts.SeqoeLoad(40), Pos.CENTER,
                1240, 400, 400, 60, this);
        three = new ButtonTwo("3 PLAYERS", StaticFonts.SeqoeLoad(40), Pos.CENTER,
                1240, 500, 400, 60, this);
        four = new ButtonTwo("4 PLAYERS", StaticFonts.SeqoeLoad(40), Pos.CENTER,
                1240, 600, 400, 60, this);

        createGame = new ButtonOne("CREATE GAME", StaticFonts.SeqoeLoad(50), Pos.CENTER,
                960, 800, 600, 70, this);
        back = new ButtonOne("back", StaticFonts.SeqoeLoad(15), Pos.CENTER,
                960, 1000, 100, 50, this);
    }
}
