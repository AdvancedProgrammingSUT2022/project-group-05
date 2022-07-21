package graphics.view.menus;

//import controller.UserDatabaseController;
import graphics.objects.buttons.ButtonOne;
import graphics.objects.labels.LabelOne;
import graphics.objects.textFields.TextFieldOne;
import graphics.statics.StaticFonts;
import client.ClientManager;
import graphics.view.popUp.Error;
import graphics.view.popUp.FriendsPane;
import graphics.view.popUp.PopUp;
import graphics.view.popUp.Successful;
import graphics.view.popUp.research.InvitingFriendsPane;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import client.Client;
import client.ClientAdapter;
import client.Response;

import java.io.File;
import java.util.ArrayList;

public class ProfileMenu extends Pane{

    private ListView<FriendsPane> friendsControl;
    private ListView<InvitingFriendsPane> invitingFriendsControl;

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

        //Friends
        this.setFriendsControl();
        //invitingFriends
        this.setInvitingFriendsControl();

        //invite a friend

        TextFieldOne friendUsername = new TextFieldOne("friend username", StaticFonts.segoeLoad(20), Pos.CENTER,
                150, fromTop + 400, 200, 30, this);
        ButtonOne invite = new ButtonOne("invite", StaticFonts.segoeLoad(15), Pos.CENTER,
                150, fromTop + 450, 100, 50, this);

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

        invite.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String friendUsernameText = friendUsername.getText();
                Response response = Client.send(ClientAdapter.inviteFriend(friendUsernameText, ClientManager.getInstance().getMainUser().getUsername()));
                if (response.getMessage().startsWith("error")) {
                    new PopUp(temp, new Error(response.getMessage()));
                    return;
                }
                new PopUp(temp, new Successful(response.getMessage()));
            }
        });

        back.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ClientManager.getInstance().setPane(new MainMenu());
            }
        });
    }

    public void setFriendsControl() {
        this.getChildren().remove(this.friendsControl);

        this.friendsControl = new ListView<>();

        new LabelOne("Friends", StaticFonts.segoeLoad(50), Pos.CENTER,
                100, 30, 300, 70, this);

        this.friendsControl = new ListView<>();
        this.friendsControl.setPrefWidth(300);
        this.friendsControl.setPrefHeight(200);
        this.friendsControl.setLayoutX(50);
        this.friendsControl.setLayoutY(100);


        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                friendsControl.setItems(FXCollections.observableArrayList(FriendsPane.getFriendsPane()));
            }
        });

        this.getChildren().add(friendsControl);

    }

    public void setInvitingFriendsControl() {
        this.getChildren().remove(this.invitingFriendsControl);

        this.invitingFriendsControl = new ListView<>();

        int fromLeft = (int) ClientManager.getInstance().getMainStage().getWidth() / 2;

        new LabelOne("InvitingFriends", StaticFonts.segoeLoad(50), Pos.CENTER,
                fromLeft * 2 - 300, 30, 500, 70, this);

        this.invitingFriendsControl = new ListView<>();
        this.invitingFriendsControl.setPrefWidth(300);
        this.invitingFriendsControl.setPrefHeight(200);
        this.invitingFriendsControl.setLayoutX(fromLeft * 2 - 400);
        this.invitingFriendsControl.setLayoutY(100);

        ArrayList<InvitingFriendsPane> invitingFriendsPanes = InvitingFriendsPane.getInvitingFriendsPane();

        for (InvitingFriendsPane invitingFriendsPane : invitingFriendsPanes) {
            invitingFriendsPane.getAcceptButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    Client.send(ClientAdapter.addFriend(invitingFriendsPane.getInvitingFriendUsername(), ClientManager.getInstance().getMainUser().getUsername()));
                    ProfileMenu.this.setInvitingFriendsControl();
                    ProfileMenu.this.setFriendsControl();
                }
            });
            invitingFriendsPane.getRejectButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    ProfileMenu.this.setInvitingFriendsControl();
                }
            });
        }

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                invitingFriendsControl.setItems(FXCollections.observableArrayList(invitingFriendsPanes));
            }
        });

        this.getChildren().add(invitingFriendsControl);
    }
}
