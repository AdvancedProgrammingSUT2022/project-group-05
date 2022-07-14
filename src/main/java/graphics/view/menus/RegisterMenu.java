package graphics.view.menus;

import graphics.objects.buttons.ButtonOne;
import graphics.objects.textFields.TextFieldOne;
import graphics.statics.StaticFonts;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class RegisterMenu extends Pane{
    public RegisterMenu () {
        //OBJECTs
        TextFieldOne username = new TextFieldOne("username", StaticFonts.SeqoeLoad(20), Pos.CENTER,
                960, 400, 400, 30, this);
        TextFieldOne password = new TextFieldOne("password", StaticFonts.SeqoeLoad(20), Pos.CENTER,
                960, 450, 400, 30, this);
        TextFieldOne repeatPassword = new TextFieldOne("repeat password", StaticFonts.SeqoeLoad(20), Pos.CENTER,
                960, 500, 400, 30, this);
        TextFieldOne nickname = new TextFieldOne("nickname", StaticFonts.SeqoeLoad(20), Pos.CENTER,
                960, 550, 400, 30, this);

        ButtonOne signUp = new ButtonOne("SIGN UP", StaticFonts.SeqoeLoad(30), Pos.CENTER,
                960, 650, 300, 50, this);

        ButtonOne back = new ButtonOne("back", StaticFonts.SeqoeLoad(15), Pos.CENTER,
                100, 1000, 100, 50, this);

        //FUNCTIONS
        signUp.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String usernameGot = username.getText();
                String passwordGot = password.getText();
                String repeatPasswordGot = repeatPassword.getText();
                String nicknameGot = nickname.getText();
                //TODO... handling register and if true getting back to login menu
            }
        });
        back.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //TODO... opening login menu
            }
        });
    }
}
