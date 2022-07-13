package graphics.view.menus;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import graphics.objects.buttons.ButtonOne;
import graphics.objects.textFields.TextFieldOne;
import graphics.statics.StaticFonts;

public class LoginMenu extends Pane{
    public LoginMenu () {

        //OBJECTS
        TextFieldOne username = new TextFieldOne("username", StaticFonts.SeqoeLoad(20), Pos.CENTER,
                960, 450, 400, 30, this);
        TextFieldOne password = new TextFieldOne("password", StaticFonts.SeqoeLoad(20), Pos.CENTER,
                960, 500, 400, 30, this);
        ButtonOne login = new ButtonOne("LOGIN", StaticFonts.SeqoeLoad(30), Pos.CENTER,
                960, 600, 300, 50, this);

        ButtonOne signUp = new ButtonOne("SIGN UP", StaticFonts.SeqoeLoad(30), Pos.CENTER,
                960, 700, 300, 50, this);

        ButtonOne exit = new ButtonOne("EXIT", StaticFonts.SeqoeLoad(15), Pos.CENTER,
                960, 900, 100, 50, this);

        //FUNCTIONS
        login.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String usernameGot = username.getText();
                String passwordGot = password.getText();
                //TODO... if correct open main menu with logged in user
            }
        });
        signUp.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //TODO... open register menu
            }
        });
        exit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //TODO Exit
            }
        });
    }
}
