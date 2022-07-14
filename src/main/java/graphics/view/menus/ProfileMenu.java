package graphics.view.menus;

import graphics.view.ErrorBox;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import graphics.objects.buttons.ButtonOne;
import graphics.objects.textFields.TextFieldOne;
import graphics.statics.StaticFonts;

public class ProfileMenu extends Pane{
    public ProfileMenu () {
        Pane menu = new Pane();

        //OBJECTS
        TextFieldOne oldPassword = new TextFieldOne("old password", StaticFonts.SeqoeLoad(20), Pos.CENTER,
                960, 300, 400, 30, this);
        TextFieldOne newPassword = new TextFieldOne("new password", StaticFonts.SeqoeLoad(20), Pos.CENTER,
                960, 350, 400, 30, this);
        TextFieldOne repeatPassword = new TextFieldOne("repeat password", StaticFonts.SeqoeLoad(20), Pos.CENTER,
                960, 400, 400, 30, this);
        ButtonOne changePassword = new ButtonOne("CHANGE PASSWORD", StaticFonts.SeqoeLoad(30), Pos.CENTER,
                960, 450, 400, 50, this);

        TextFieldOne newNickname = new TextFieldOne("new nickname", StaticFonts.SeqoeLoad(20), Pos.CENTER,
                960, 550, 400, 30, this);
        ButtonOne changeNickname = new ButtonOne("CHANGE NICKNAME", StaticFonts.SeqoeLoad(30), Pos.CENTER,
                960, 600, 400, 50, this);

        ButtonOne changePicture = new ButtonOne("CHANGE PICTURE", StaticFonts.SeqoeLoad(30), Pos.CENTER,
                960, 700, 400, 50, this);

        ButtonOne back = new ButtonOne("back", StaticFonts.SeqoeLoad(15), Pos.CENTER,
                100, 1000, 100, 50, this);

        //FUNCTIONS
        changePassword.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String oldPass = oldPassword.getText();
                String newPass = newPassword.getText();
                String repeatPass = repeatPassword.getText();
                //TODO... changing password
            }
        });
        changeNickname.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String newNick = newNickname.getText();
                //TODO... changing nickname
            }
        });
        changePicture.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //TODO... nothing yet
            }
        });
        back.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //nemoone error box (TExt erroe va oon menu i ke gharae toosh bashe ro voroodi migitre)
                ErrorBox.getErrorBox("ERROR: aghab nadarim", menu);
                //TODO... opening main menu
            }
        });
    }
}
