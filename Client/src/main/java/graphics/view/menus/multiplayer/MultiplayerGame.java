package graphics.view.menus.multiplayer;

import client.ClientManager;
import graphics.objects.buttons.ButtonOne;
import graphics.objects.labels.LabelOne;
import graphics.statics.StaticFonts;
import graphics.view.menus.MainMenu;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.Lobby;

import java.util.ArrayList;

public class MultiplayerGame extends Pane{
    private LabelOne invitationsTitle;
    private ListView<LobbyInvitationPane> invitations;

    private ButtonOne hostLobbyButton;
    private ButtonOne backButton;

    public MultiplayerGame() {
        this.setInvitationsTitle();
        this.setInvitations();

        this.setHostLobbyButton();
        this.setBackButton();
    }

    private void setInvitationsTitle() {
        this.invitationsTitle = new LabelOne("INVITATIONS", StaticFonts.segoeLoad(30), Pos.CENTER,
                960, 100, 1200, 100, this);
    }

    private void setInvitations() {
        //TODO after server can send all lobby invitations
        this.getChildren().remove(this.invitations);

        this.invitations = new ListView<>();

        this.invitations.setLayoutX(360);
        this.invitations.setLayoutY(180);

        this.invitations.setPrefWidth(1200);
        this.invitations.setPrefHeight(700);

        ArrayList<LobbyInvitationPane> invitationPanes = new ArrayList<>();
        invitationPanes.add(new LobbyInvitationPane(new Lobby("1", "sam")));
        invitationPanes.add(new LobbyInvitationPane(new Lobby("2", "rogers")));
        invitationPanes.add(new LobbyInvitationPane(new Lobby("3", "mohammadreza")));

        this.invitations.setItems(FXCollections.observableList(invitationPanes));
        this.getChildren().add(this.invitations);
    }

    private void setHostLobbyButton() {
        this.hostLobbyButton = new ButtonOne("HOST NEW LOBBY", StaticFonts.segoeLoad(30), Pos.CENTER,
                960, 980, 400, 100, this);
        this.hostLobbyButton.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                //TODO get from server
                ClientManager.getInstance().setPane(new LobbyHost());
            }
        });
    }

    private void setBackButton() {
        this.backButton = new ButtonOne("back", StaticFonts.segoeLoad(15), Pos.CENTER,
                410, 980, 100, 50, this);

        this.backButton.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                ClientManager.getInstance().setPane(new MainMenu());
            }
        });
    }
}
