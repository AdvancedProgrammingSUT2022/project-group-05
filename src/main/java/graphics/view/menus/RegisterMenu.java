package graphics.view.menus;

import controller.UserDatabaseController;
import graphics.objects.buttons.ButtonOne;
import graphics.objects.textFields.TextFieldOne;
import graphics.statics.StaticFonts;
import graphics.view.PaneChanger;
import graphics.view.popUp.Error;
import graphics.view.popUp.PopUp;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.User;

public class RegisterMenu extends Pane{
    public RegisterMenu () {
        Pane temp = this;

        //OBJECTS
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
                String usernameText = username.getText();
                String passwordText = password.getText();
                String repeatPasswordText = repeatPassword.getText();
                String nicknameText = nickname.getText();

                User previousUser = UserDatabaseController.getUserByUsername(usernameText);

                if (previousUser != null)
                {
                    new PopUp(temp, new Error("username already taken"));
                    return;
                }

                if (!passwordText.equals(repeatPasswordText))
                {
                    new PopUp(temp, new Error("password and repeat don't match"));
                    return;
                }

                if (passwordText.length() <= 8)
                {
                    new PopUp(temp, new Error("password must be at least 8 characters"));
                    return;
                }

                User user = new User(usernameText, nicknameText, passwordText, ""); //TODO image address
                UserDatabaseController.addUser(user);
            }
        });
        back.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                PaneChanger.getInstance().setPane(new LoginMenu());
            }
        });
    }
}
