package graphics.view.popUp;

import controller.GameMenuController;
import controller.Responses;
import graphics.objects.buttons.ButtonOne;
import graphics.objects.labels.LabelOne;
import graphics.objects.textFields.TextFieldOne;
import graphics.statics.StaticFonts;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.HashMap;

import static view.enums.GameMenuCommand.*;

//ADD FUNCTIONS

public class CheatCode extends Pane {

    private TextFieldOne cheatCodeField;
    private ButtonOne accept;

    public CheatCode () {
        this.getChildren().add(new Rectangle(600, 300, Color.WHITE));
        this.setPrefHeight(300);
        this.setPrefWidth(600);
        new LabelOne("CHEAT CODE PANEL", StaticFonts.segoeLoad(20), Pos.CENTER,
                300, 75, 200, 60, this);
        this.cheatCodeField = new TextFieldOne("TYPE CODE", StaticFonts.segoeLoad(30), Pos.CENTER,
                300, 150, 400, 50, this);
        this.accept = new ButtonOne("ACCEPT", StaticFonts.segoeLoad(20), Pos.CENTER,
                300, 225, 200, 40, this);

        this.setAccept();
    }

    public void setAccept() {
        this.accept.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                String cheatCode = cheatCodeField.getText();
                String response = "";
                HashMap<String, String> command;
                
                if ((command = getHashMap(cheatCode, INCREASE_TURN)) != null)
                    response = GameMenuController.getInstance().increaseTurn(command);
                else if ((command = getHashMap(cheatCode, INCREASE_GOLD)) != null)
                    response = GameMenuController.getInstance().increaseGold(command);
                else if ((command = getHashMap(cheatCode, INCREASE_TURN)) != null)
                    response = GameMenuController.getInstance().increaseTurn(command);
                else if ((command = getHashMap(cheatCode, INCREASE_GOLD)) != null)
                    response = GameMenuController.getInstance().increaseGold(command);

                else if ((command = getHashMap(cheatCode, KILL_SOLDIER)) != null)
                    response = GameMenuController.getInstance().killSoldier(command);
                else if ((command = getHashMap(cheatCode, KILL_CIVILIAN)) != null)
                    response = GameMenuController.getInstance().killCivilian(command);
                else if ((command = getHashMap(cheatCode, SPAWN_UNIT)) != null)
                    response = GameMenuController.getInstance().spawnUnit(command);

                else if ((command = getHashMap(cheatCode, REVEAL_ALL)) != null)
                    response = GameMenuController.getInstance().revealAll(command);
                else if ((command = getHashMap(cheatCode, INDUSTRIAL_REVOLUTION)) != null)
                    response = GameMenuController.getInstance().industrialRevolution(command);
                else if ((command = getHashMap(cheatCode, WELCOME_TO_UTOPIA)) != null)
                    response = GameMenuController.getInstance().welcomeToUtopia(command);
                else if ((command = getHashMap(cheatCode, BIG_BRAIN)) != null)
                    response = GameMenuController.getInstance().bigBrain(command);


                    //UNIT CHEAT CODES
                else if ((command = getHashMap(cheatCode, MARCOPOLO)) != null)
                    response = GameMenuController.getInstance().marcopolo(command);
                else if ((command = getHashMap(cheatCode, TERMINATOR)) != null)
                    response = GameMenuController.getInstance().terminator(command);
                else if ((command = getHashMap(cheatCode, INSTANT_HEAL)) != null)
                    response = GameMenuController.getInstance().instantHeal(command);

                    //INVALID
                else
                    response = Responses.INVALID_COMMAND.getResponse();

                assert response != null;
                if (response.startsWith("error")) new PopUp((Pane) CheatCode.this.getParent(), new Error(response));
                else new PopUp((Pane) CheatCode.this.getParent(), new Successful(response));
            }
        });
    }
}
