package graphics.view.menus;

//import controller.UserDatabaseController;
import client.*;
import com.google.gson.Gson;
import graphics.objects.buttons.ButtonOne;
import graphics.objects.labels.LabelOne;
import graphics.objects.textFields.TextFieldOne;
import graphics.statics.StaticFonts;
import graphics.view.popUp.*;
import graphics.view.popUp.Error;
import graphics.view.popUp.PopUp;
import graphics.view.popUp.Successful;
import javafx.animation.ParallelTransition;
import javafx.event.ActionEvent;
import graphics.view.popUp.research.InvitingFriendsPane;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import model.User;

import java.io.File;
import java.util.ArrayList;

public class ProfileMenu extends Pane{

    private ListView<FriendsPane> friendsControl;
    private ListView<InvitingFriendsPane> invitingFriendsControl;

    public ProfileMenu () {
        Pane temp = this;
        int fromLeft = (int) ClientManager.getInstance().getMainStage().getWidth() / 2;
        int fromTop = (int) ClientManager.getInstance().getMainStage().getHeight() / 2 - 540;

        //OBJECTS
        LabelOne menuName = new LabelOne("PROFILE MENU", StaticFonts.segoeLoad(40), Pos.CENTER,
                fromLeft, 100, 400, 50, this);
        TextFieldOne oldPassword = new TextFieldOne("old password", StaticFonts.segoeLoad(20), Pos.CENTER,
                fromLeft, 300, 400, 30, this);
        TextFieldOne newPassword = new TextFieldOne("new password", StaticFonts.segoeLoad(20), Pos.CENTER,
                fromLeft, 350, 400, 30, this);
        TextFieldOne repeatPassword = new TextFieldOne("repeat password", StaticFonts.segoeLoad(20), Pos.CENTER,
                fromLeft, 400, 400, 30, this);
        ButtonOne changePassword = new ButtonOne("CHANGE PASSWORD", StaticFonts.segoeLoad(30), Pos.CENTER,
                fromLeft, 500 , 400, 50, this);

        TextFieldOne newNickname = new TextFieldOne("new nickname", StaticFonts.segoeLoad(20), Pos.CENTER,
                fromLeft, 600, 400, 30, this);
        ButtonOne changeNickname = new ButtonOne("CHANGE NICKNAME", StaticFonts.segoeLoad(30), Pos.CENTER,
                fromLeft, 700, 400, 50, this);

        ButtonOne changePicture = new ButtonOne("CHANGE PICTURE", StaticFonts.segoeLoad(30), Pos.CENTER,
                fromLeft, 800, 400, 50, this);

        ButtonOne back = new ButtonOne("back", StaticFonts.segoeLoad(15), Pos.CENTER,
                fromLeft,950, 100, 50, this);

        //ANIMATION

        //Friends
        this.setFriendsControl();
        //invitingFriends
        this.setInvitingFriendsControl();

        //invite a friend

        TextFieldOne friendUsername = new TextFieldOne("friend username", StaticFonts.segoeLoad(20), Pos.CENTER,
                150, fromTop + 400, 200, 30, this);
        ButtonOne search = new ButtonOne("search", StaticFonts.segoeLoad(15), Pos.CENTER,
                150, fromTop + 450, 100, 50, this);

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

        search.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String friendUsernameText = friendUsername.getText();
                Response searchResponse = Client.send(ClientAdapter.searchFriend(friendUsernameText));
                if (searchResponse.getMessage().startsWith("error")) {
                    new PopUp(temp, new Error(searchResponse.getMessage()));
                    return;
                }
                User friendUser = new Gson().fromJson(searchResponse.getMessage(), User.class);
                new PopUp(temp, new FriendProfilePane(friendUser));
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

        ArrayList<FriendsPane> friendsPanes = FriendsPane.getFriendsPane();

        for (FriendsPane friendsPane : friendsPanes) {
            friendsPane.getRemoveButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    Client.send(ClientAdapter.removeFriend(friendsPane.getFriendUsername(), ClientManager.getInstance().getMainUser().getUsername()));
                    ClientManager.getInstance().updateMainUser();
                    ProfileMenu.this.setFriendsControl();
                }
            });
        }

        friendsControl.setItems(FXCollections.observableArrayList(friendsPanes));
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
                    ClientManager.getInstance().updateMainUser();
                    ProfileMenu.this.setInvitingFriendsControl();
                    ProfileMenu.this.setFriendsControl();
                }
            });
            invitingFriendsPane.getRejectButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    Client.send(ClientAdapter.rejectFriend(invitingFriendsPane.getInvitingFriendUsername(), ClientManager.getInstance().getMainUser().getUsername()));
                    ClientManager.getInstance().updateMainUser();
                    ProfileMenu.this.setInvitingFriendsControl();
                }
            });
        }


        invitingFriendsControl.setItems(FXCollections.observableArrayList(invitingFriendsPanes));

        this.getChildren().add(invitingFriendsControl);
    }

    public void update() {
        ClientManager.getInstance().updateMainUser();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                ProfileMenu.this.setFriendsControl();
                ProfileMenu.this.setInvitingFriendsControl();
            }
        });
    }
}
