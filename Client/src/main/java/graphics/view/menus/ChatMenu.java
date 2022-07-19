package graphics.view.menus;

import graphics.objects.buttons.ButtonOne;
import graphics.objects.textFields.TextFieldOne;
import graphics.statics.StaticFonts;
import graphics.view.ClientManager;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class ChatMenu extends Pane {
    public ChatMenu () {
        //OBJECTS
        TextFieldOne privateText = new TextFieldOne("private", StaticFonts.segoeLoad(20), Pos.CENTER,
                300, 900, 400, 40, this);
        ButtonOne privateSend = new ButtonOne("Send", StaticFonts.segoeLoad(20), Pos.CENTER,
                600, 900, 100, 40, this);
        TextFieldOne privateTexts = new TextFieldOne("messages", StaticFonts.segoeLoad(20), Pos.TOP_LEFT,
                375, 450, 550, 800, this);

        TextFieldOne publicText = new TextFieldOne("public", StaticFonts.segoeLoad(20), Pos.CENTER,
                900, 900, 400, 40, this);
        ButtonOne publicSend = new ButtonOne("Send", StaticFonts.segoeLoad(20), Pos.CENTER,
                1200, 900, 100, 40, this);
        TextFieldOne publicTexts = new TextFieldOne("messages", StaticFonts.segoeLoad(20), Pos.TOP_LEFT,
                975, 450, 550, 800, this);

        TextFieldOne partyText = new TextFieldOne("party", StaticFonts.segoeLoad(20), Pos.CENTER,
                1500, 900, 400, 40, this);
        ButtonOne partySend = new ButtonOne("Send", StaticFonts.segoeLoad(20), Pos.CENTER,
                1800, 900, 100, 40, this);
        TextFieldOne partyTexts = new TextFieldOne("messages", StaticFonts.segoeLoad(20), Pos.TOP_LEFT,
                1575, 450, 550, 800, this);

        ButtonOne back = new ButtonOne("back", StaticFonts.segoeLoad(15), Pos.CENTER,
                100, 1000, 100, 50, this);


        //FUNCTIONS
        privateSend.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                privateTexts.getTextField().setText(privateText.getText());
            }
        });
        publicSend.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                publicTexts.getTextField().setText(publicText.getText());
            }
        });
        partySend.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                partyTexts.getTextField().setText(partyText.getText());
            }
        });
        back.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ClientManager.getInstance().setPane(new MainMenu());
            }
        });
    }
}
