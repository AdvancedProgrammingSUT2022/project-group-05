package graphics.view.menus;

import controller.Responses;
import controller.UserDatabaseController;
import graphics.view.ClientManager;
import graphics.view.popUp.Error;
import graphics.view.popUp.PopUp;
import graphics.view.popUp.Successful;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import graphics.objects.buttons.ButtonOne;
import graphics.objects.textFields.TextFieldOne;
import graphics.statics.StaticFonts;
import model.User;

public class ProfileMenu extends Pane{
    public ProfileMenu () {
        Pane temp = this;

        //OBJECTS
        TextFieldOne oldPassword = new TextFieldOne("old password", StaticFonts.segoeLoad(20), Pos.CENTER,
                960, 300, 400, 30, this);
        TextFieldOne newPassword = new TextFieldOne("new password", StaticFonts.segoeLoad(20), Pos.CENTER,
                960, 350, 400, 30, this);
        TextFieldOne repeatPassword = new TextFieldOne("repeat password", StaticFonts.segoeLoad(20), Pos.CENTER,
                960, 400, 400, 30, this);
        ButtonOne changePassword = new ButtonOne("CHANGE PASSWORD", StaticFonts.segoeLoad(30), Pos.CENTER,
                960, 450, 400, 50, this);

        TextFieldOne newNickname = new TextFieldOne("new nickname", StaticFonts.segoeLoad(20), Pos.CENTER,
                960, 550, 400, 30, this);
        ButtonOne changeNickname = new ButtonOne("CHANGE NICKNAME", StaticFonts.segoeLoad(30), Pos.CENTER,
                960, 600, 400, 50, this);

        ButtonOne changePicture = new ButtonOne("CHANGE PICTURE", StaticFonts.segoeLoad(30), Pos.CENTER,
                960, 700, 400, 50, this);

        ButtonOne back = new ButtonOne("back", StaticFonts.segoeLoad(15), Pos.CENTER,
                100, 1000, 100, 50, this);

        //FUNCTIONS
        changePassword.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String oldPasswordText = oldPassword.getText();
                String newPasswordText = newPassword.getText();
                String repeatNewPasswordText = repeatPassword.getText();

                if (!ClientManager.getInstance().getMainUser().getPassword().equals(oldPasswordText))
                {
                    new PopUp(temp, new Error("current password is incorrect"));
                    return;
                }

                if (!newPasswordText.equals(repeatNewPasswordText))
                {
                    new PopUp(temp, new Error("new password and repeat don't match"));
                    return;
                }

                if (newPasswordText.length() < 8)
                {
                    new PopUp(temp, new Error("password must be at least 8 characters"));
                    return;
                }

                if (oldPasswordText.equals(newPasswordText))
                {
                    new PopUp(temp, new Error("new password can't be the same as old password"));
                    return;
                }

                UserDatabaseController.changePassword(ClientManager.getInstance().getMainUser(), newPasswordText);
                ClientManager.getInstance().updateMainUser();

                new PopUp(temp, new Successful(Responses.PASSWORD_CHANGED.getResponse()));
            }
        });
        changeNickname.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String newNicknameText = newNickname.getText();

                User previousUsernameHolder = UserDatabaseController.getUserByNickname(newNicknameText);

                if (ClientManager.getInstance().getMainUser().getNickname().equals(newNicknameText))
                {
                    new PopUp(temp, new Error("old and new nickname can't be the same"));
                    return;
                }

                if (previousUsernameHolder != null)
                {
                    new PopUp(temp, new Error("nickname already taken"));
                    return;
                }

                UserDatabaseController.changeNickname(ClientManager.getInstance().getMainUser(), newNicknameText);
                ClientManager.getInstance().updateMainUser();

                new PopUp(temp, new Successful(Responses.NICKNAME_CHANGED.getResponse()));
            }
        });
        changePicture.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                new PopUp(temp, new Error("not implemented yet :("));
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
