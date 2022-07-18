package view.menu;

import graphics.objects.buttons.ButtonOne;
import graphics.objects.textFields.TextFieldOne;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import main.Main;
import statics.StaticFonts;

public class ChatMenu {
    public ChatMenu () {

    }

    public Pane chatMenu () {
        Pane menu = new Pane();

        //OBJECTS
        TextFieldOne privateText = new TextFieldOne("private", StaticFonts.SeqoeLoad(20), Pos.CENTER,
                300, 900, 400, 40, menu);
        ButtonOne privateSend = new ButtonOne("Send", StaticFonts.SeqoeLoad(20), Pos.CENTER,
                600, 900, 100, 40, menu);
        TextFieldOne privateTexts = new TextFieldOne("messages", StaticFonts.SeqoeLoad(20), Pos.TOP_LEFT,
                375, 450, 550, 800, menu);

        TextFieldOne publicText = new TextFieldOne("public", StaticFonts.SeqoeLoad(20), Pos.CENTER,
                900, 900, 400, 40, menu);
        ButtonOne publicSend = new ButtonOne("Send", StaticFonts.SeqoeLoad(20), Pos.CENTER,
                1200, 900, 100, 40, menu);
        TextFieldOne publicTexts = new TextFieldOne("messages", StaticFonts.SeqoeLoad(20), Pos.TOP_LEFT,
                975, 450, 550, 800, menu);

        TextFieldOne partyText = new TextFieldOne("party", StaticFonts.SeqoeLoad(20), Pos.CENTER,
                1500, 900, 400, 40, menu);
        ButtonOne partySend = new ButtonOne("Send", StaticFonts.SeqoeLoad(20), Pos.CENTER,
                1800, 900, 100, 40, menu);
        TextFieldOne partyTexts = new TextFieldOne("messages", StaticFonts.SeqoeLoad(20), Pos.TOP_LEFT,
                1575, 450, 550, 800, menu);

        ButtonOne back = new ButtonOne("back", StaticFonts.SeqoeLoad(15), Pos.CENTER,
                100, 1000, 100, 50, menu);


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
                Main.showMenu("mainMenu");
            }
        });

        return menu;
    }
}
