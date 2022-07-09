package graphics.view.menus;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import graphics.objects.buttons.ButtonOne;
import graphics.objects.textFields.TextFieldOne;
import graphics.statics.StaticFonts;

public class LoginMenu {
    public LoginMenu () {

    }

    public Pane login () {
        Pane menu = new Pane();

        //OBJECTS
        TextFieldOne username = new TextFieldOne("username", StaticFonts.SeqoeLoad(20), Pos.CENTER,
                960, 450, 400, 30, menu);
        TextFieldOne password = new TextFieldOne("password", StaticFonts.SeqoeLoad(20), Pos.CENTER,
                960, 500, 400, 30, menu);
        ButtonOne login = new ButtonOne("LOGIN", StaticFonts.SeqoeLoad(30), Pos.CENTER,
                960, 600, 300, 50, menu);

        ButtonOne signUp = new ButtonOne("SIGN UP", StaticFonts.SeqoeLoad(30), Pos.CENTER,
                960, 700, 300, 50, menu);

        ButtonOne exit = new ButtonOne("EXIT", StaticFonts.SeqoeLoad(15), Pos.CENTER,
                960, 900, 100, 50, menu);

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

        return menu;
    }

    public Pane register () {
        Pane menu = new Pane();

        //OBJECTS
        TextFieldOne username = new TextFieldOne("username", StaticFonts.SeqoeLoad(20), Pos.CENTER,
                960, 400, 400, 30, menu);
        TextFieldOne password = new TextFieldOne("password", StaticFonts.SeqoeLoad(20), Pos.CENTER,
                960, 450, 400, 30, menu);
        TextFieldOne repeatPassword = new TextFieldOne("repeat password", StaticFonts.SeqoeLoad(20), Pos.CENTER,
                960, 500, 400, 30, menu);
        TextFieldOne nickname = new TextFieldOne("nickname", StaticFonts.SeqoeLoad(20), Pos.CENTER,
                960, 550, 400, 30, menu);

        ButtonOne signUp = new ButtonOne("SIGN UP", StaticFonts.SeqoeLoad(30), Pos.CENTER,
                960, 650, 300, 50, menu);

        ButtonOne back = new ButtonOne("back", StaticFonts.SeqoeLoad(15), Pos.CENTER,
                100, 1000, 100, 50, menu);

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

        return menu;
    }
}
