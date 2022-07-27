package graphics.view.menus;

import client.ClientManager;
import controller.GameMenuController;
import graphics.objects.buttons.ButtonOne;
import graphics.objects.labels.LabelOne;
import graphics.statics.StaticFonts;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class GameOver extends Pane{
    public GameOver() {
        Pane temp = this;

        int fromLeft = (int) ClientManager.getInstance().getMainStage().getWidth() / 2;
        int fromTop = (int) ClientManager.getInstance().getMainStage().getHeight() / 2 - 100;
        //OBJECTS
        LabelOne username = new LabelOne(ClientManager.getInstance().getMainUser().getUsername(), StaticFonts.segoeLoad(20), Pos.CENTER,
                fromLeft, fromTop, 400, 30, this);
        LabelOne score = new LabelOne("SCORE: " + GameMenuController.getInstance().getCivilizationByUsername(ClientManager.getInstance().getMainUser().getUsername()).calculateScore(),
                StaticFonts.segoeLoad(20), Pos.CENTER, fromLeft, fromTop + 50, 400, 30, this);
        ButtonOne mainMenu = new ButtonOne("MAIN MENU", StaticFonts.segoeLoad(30), Pos.CENTER,
                fromLeft, fromTop + 150, 300, 50, this);

        mainMenu.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                ClientManager.getInstance().setPane(new MainMenu());
            }
        });
    }

}
