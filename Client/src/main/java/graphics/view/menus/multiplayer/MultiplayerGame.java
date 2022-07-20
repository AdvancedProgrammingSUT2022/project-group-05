package graphics.view.menus.multiplayer;

import client.Client;
import client.ClientManager;
import client.Request;
import client.Response;
import com.google.gson.Gson;
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
        this.getChildren().remove(this.invitations);

        this.invitations = new ListView<>();

        this.invitations.setLayoutX(360);
        this.invitations.setLayoutY(180);

        this.invitations.setPrefWidth(1200);
        this.invitations.setPrefHeight(700);

        ArrayList<LobbyInvitationPane> invitationPanes = new ArrayList<>();

        for (Lobby lobby : Lobby.getInvitedLobbies()) {
            LobbyInvitationPane lobbyInvitationPane = new LobbyInvitationPane(lobby);
            this.setInvitationButton(lobbyInvitationPane);
            invitationPanes.add(new LobbyInvitationPane(lobby));
        }

        //debugging purposes
        invitationPanes.add(new LobbyInvitationPane(new Lobby("1", "sam")));
        invitationPanes.add(new LobbyInvitationPane(new Lobby("2", "rogers")));
        invitationPanes.add(new LobbyInvitationPane(new Lobby("3", "mohammadreza")));
        for (LobbyInvitationPane pane : invitationPanes) {
            this.setInvitationButton(pane);
        }

        this.invitations.setItems(FXCollections.observableList(invitationPanes));
        this.getChildren().add(this.invitations);
    }

    private void setHostLobbyButton() {
        this.hostLobbyButton = new ButtonOne("HOST NEW LOBBY", StaticFonts.segoeLoad(30), Pos.CENTER,
                960, 980, 400, 100, this);
        this.hostLobbyButton.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                Request request = new Request("host");

                request.addParams("hostUsername", ClientManager.getInstance().getMainUser().getUsername());
                request.addParams("id", String.valueOf(ClientManager.getInstance().getMainUser().getUsername().hashCode()));

                String hostRequest = request.convertToJson();
                Response response = Client.send(hostRequest);

                Gson gson = new Gson();
                Lobby lobby = gson.fromJson(response.getMessage(), Lobby.class);

                ClientManager.getInstance().setPane(new LobbyHost(lobby));
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


    //BUTTON
    private void setInvitationButton(LobbyInvitationPane lobbyInvitationPane) {
        lobbyInvitationPane.getJoinButton().setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                ClientManager.getInstance().setPane(new LobbyGuest(lobbyInvitationPane.getLobby()));

                //TODO remove from server too?
                Lobby.getInvitedLobbies().remove(lobbyInvitationPane.getLobby());
                MultiplayerGame.this.setInvitations();
            }
        });

        lobbyInvitationPane.getRejectButton().setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                Lobby.getInvitedLobbies().remove(lobbyInvitationPane.getLobby());
                MultiplayerGame.this.setInvitations();
            }
        });
    }
}
