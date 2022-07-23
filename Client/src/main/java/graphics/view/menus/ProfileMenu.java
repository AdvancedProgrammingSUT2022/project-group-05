package graphics.view.menus;

//import controller.UserDatabaseController;
import graphics.objects.buttons.ButtonOne;
import graphics.objects.textFields.TextFieldOne;
import graphics.statics.StaticFonts;
import client.ClientManager;
import graphics.view.popUp.Error;
import graphics.view.popUp.PopUp;
import graphics.view.popUp.Successful;
import javafx.animation.ParallelTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import client.Client;
import client.ClientAdapter;
import client.Response;

        import java.io.File;

public class ProfileMenu extends Pane{
    public ProfileMenu () {
        Pane temp = this;
        int fromLeft = (int) ClientManager.getInstance().getMainStage().getWidth() / 2;
        int fromTop = (int) ClientManager.getInstance().getMainStage().getHeight() / 2 - 200;

        //OBJECTS
        TextFieldOne oldPassword = new TextFieldOne("old password", StaticFonts.segoeLoad(20), Pos.CENTER,
                fromLeft, fromTop, 400, 30, this);
        TextFieldOne newPassword = new TextFieldOne("new password", StaticFonts.segoeLoad(20), Pos.CENTER,
                fromLeft, fromTop + 50, 400, 30, this);
        TextFieldOne repeatPassword = new TextFieldOne("repeat password", StaticFonts.segoeLoad(20), Pos.CENTER,
                fromLeft, fromTop + 100, 400, 30, this);
        ButtonOne changePassword = new ButtonOne("CHANGE PASSWORD", StaticFonts.segoeLoad(30), Pos.CENTER,
                fromLeft, fromTop + 150, 400, 50, this);

        TextFieldOne newNickname = new TextFieldOne("new nickname", StaticFonts.segoeLoad(20), Pos.CENTER,
                fromLeft, fromTop + 250, 400, 30, this);
        ButtonOne changeNickname = new ButtonOne("CHANGE NICKNAME", StaticFonts.segoeLoad(30), Pos.CENTER,
                fromLeft, fromTop + 300, 400, 50, this);

        ButtonOne changePicture = new ButtonOne("CHANGE PICTURE", StaticFonts.segoeLoad(30), Pos.CENTER,
                fromLeft, fromTop + 400, 400, 50, this);

        ButtonOne back = new ButtonOne("back", StaticFonts.segoeLoad(15), Pos.CENTER,
                fromLeft, fromTop + 450, 100, 50, this);

        //ANIMATION
        ParallelTransition start = AnimatedPane.getStartAnimation(this);
        ParallelTransition end = AnimatedPane.getEndAnimation(this);
        start.play();

        //FUNCTIONS
        changePassword.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String oldPasswordText = oldPassword.getText();
                String newPasswordText = newPassword.getText();
                String repeatNewPasswordText = repeatPassword.getText();

                Response changePasswordResponse = Client.send(ClientAdapter.changePassword(oldPasswordText, newPasswordText, repeatNewPasswordText, ClientManager.getInstance().getMainUser().getUsername()));
                if (changePasswordResponse.getMessage().startsWith("error")) {
                    new PopUp(temp, new Error(changePasswordResponse.getMessage()));
                    return;
                }

                ClientManager.getInstance().updateMainUser();

                new PopUp(temp, new Successful(changePasswordResponse.getMessage()));
            }
        });
        changeNickname.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String newNicknameText = newNickname.getText();

                Response changeNicknameResponse = Client.send(ClientAdapter.changeNickname(newNicknameText, ClientManager.getInstance().getMainUser().getUsername()));
                if (changeNicknameResponse.getMessage().startsWith("error")) {
                    new PopUp(temp, new Error(changeNicknameResponse.getMessage()));
                    return;
                }
                ClientManager.getInstance().updateMainUser();

                new PopUp(temp, new Successful(changeNicknameResponse.getMessage()));
            }
        });
        changePicture.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FileChooser fileChooser = new FileChooser();
                File selectedFile = fileChooser.showOpenDialog(ClientManager.getInstance().getMainStage());
                String newImageAddress = selectedFile.getPath();
                Response changeImageResponse = Client.send(ClientAdapter.changeImage(newImageAddress, ClientManager.getInstance().getMainUser().getUsername()));
                new PopUp(temp, new Successful(changeImageResponse.getMessage()));
            }
        });
        back.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                end.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        ClientManager.getInstance().setPane(new MainMenu());
                    }
                });
                end.play();
            }
        });
    }
}
