package graphics.view.menus;

import controller.UserDatabaseController;
import graphics.view.ClientManager;
import graphics.view.popUp.Error;
import graphics.view.popUp.PopUp;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import graphics.objects.buttons.ButtonOne;
import graphics.objects.textFields.TextFieldOne;
import graphics.statics.StaticFonts;
import model.User;

public class LoginMenu extends Pane{
    public LoginMenu () {
        Pane temp = this;

        //OBJECTS
        TextFieldOne username = new TextFieldOne("username", StaticFonts.segoeLoad(20), Pos.CENTER,
                960, 450, 400, 30, this);
        TextFieldOne password = new TextFieldOne("password", StaticFonts.segoeLoad(20), Pos.CENTER,
                960, 500, 400, 30, this);
        ButtonOne login = new ButtonOne("LOGIN", StaticFonts.segoeLoad(30), Pos.CENTER,
                960, 600, 300, 50, this);

        ButtonOne signUp = new ButtonOne("SIGN UP", StaticFonts.segoeLoad(30), Pos.CENTER,
                960, 700, 300, 50, this);

        ButtonOne exit = new ButtonOne("EXIT", StaticFonts.segoeLoad(15), Pos.CENTER,
                960, 900, 100, 50, this);

        //FUNCTIONS
        login.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String usernameText = username.getText();
                String passwordText = password.getText();

                User user = UserDatabaseController.getUserByUsername(usernameText);
                if (user == null)
                {
                    new PopUp(temp, new Error("username doesn't exist"));
                    return;
                }

                if (!user.getPassword().equals(passwordText))
                {
                    new PopUp(temp, new Error("username and password don't match"));
                    return;
                }
                ClientManager.getInstance().setMainUser(user);
                ClientManager.getInstance().setPane(new MainMenu());
            }
        });
        signUp.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ClientManager.getInstance().setPane(new RegisterMenu());
            }
        });
        exit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.exit(0);
            }
        });
    }
}
