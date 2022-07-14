package graphics.view.menus;

import controller.UserDatabaseController;
import graphics.objects.buttons.ButtonOne;
import graphics.objects.textFields.TextFieldOne;
import graphics.statics.StaticFonts;
import graphics.view.ClientManager;
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
        TextFieldOne username = new TextFieldOne("username", StaticFonts.segoeLoad(20), Pos.CENTER,
                960, 400, 400, 30, this);
        TextFieldOne password = new TextFieldOne("password", StaticFonts.segoeLoad(20), Pos.CENTER,
                960, 450, 400, 30, this);
        TextFieldOne repeatPassword = new TextFieldOne("repeat password", StaticFonts.segoeLoad(20), Pos.CENTER,
                960, 500, 400, 30, this);
        TextFieldOne nickname = new TextFieldOne("nickname", StaticFonts.segoeLoad(20), Pos.CENTER,
                960, 550, 400, 30, this);

        ButtonOne signUp = new ButtonOne("SIGN UP", StaticFonts.segoeLoad(30), Pos.CENTER,
                960, 650, 300, 50, this);

        ButtonOne back = new ButtonOne("back", StaticFonts.segoeLoad(15), Pos.CENTER,
                100, 1000, 100, 50, this);

        //FUNCTIONS
        signUp.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String usernameText = username.getText();
                String passwordText = password.getText();
                String repeatPasswordText = repeatPassword.getText();
                String nicknameText = nickname.getText();

                User previousUsernameHolder = UserDatabaseController.getUserByUsername(usernameText);
                User previousNicknameHolder = UserDatabaseController.getUserByNickname(nicknameText);

                if (previousUsernameHolder != null)
                {
                    new PopUp(temp, new Error("username already taken"));
                    return;
                }

                if (previousNicknameHolder != null)
                {
                    new PopUp(temp, new Error("nickname already taken"));
                    return;
                }

                if (!passwordText.equals(repeatPasswordText))
                {
                    new PopUp(temp, new Error("password and repeat don't match"));
                    return;
                }

                if (passwordText.length() < 8)
                {
                    new PopUp(temp, new Error("password must be at least 8 characters"));
                    return;
                }

                User user = new User(usernameText, nicknameText, passwordText, ""); //TODO image address
                UserDatabaseController.addUser(user);

                ClientManager.getInstance().setMainUser(user);
                ClientManager.getInstance().setPane(new MainMenu());
            }
        });
        back.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ClientManager.getInstance().setPane(new LoginMenu());
            }
        });
    }
}
