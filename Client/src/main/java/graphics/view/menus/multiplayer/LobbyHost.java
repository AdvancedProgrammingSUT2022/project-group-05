package graphics.view.menus.multiplayer;

//import controller.UserDatabaseController;
import client.Client;
import client.ClientAdapter;
import client.Response;
import graphics.objects.buttons.ButtonOne;
import graphics.objects.buttons.ButtonTwo;
import graphics.objects.labels.LabelOne;
import graphics.objects.textFields.TextFieldOne;
import graphics.statics.StaticFonts;
import client.ClientManager;
import graphics.view.popUp.Error;
import graphics.view.popUp.PopUp;
import graphics.view.popUp.Successful;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.Lobby;

import java.util.ArrayList;

public class LobbyHost extends Pane {
    private Lobby lobby;

    private LabelOne title;

    private LabelOne mapSizeLabel;

    private ButtonTwo small;
    private ButtonTwo medium;
    private ButtonTwo big;
    private ButtonTwo huge;

    private TextFieldOne inviteText;
    private ButtonOne inviteButton;

    private LabelOne gameId;

    private LabelOne firstPlayer;
    private LabelOne secondPlayer;
    private LabelOne thirdPlayer;
    private LabelOne fourthPlayer;

    private ButtonOne startGameButton;
    private ButtonOne leaveButton;

    public LobbyHost(Lobby lobby) {

        this.setScaleX(0.5);
        this.setScaleY(0.5);

        this.lobby = lobby;

        this.setGameId();
        this.setMapSizeLabel();
        this.setTitle();
        this.setSizeButtons();

        this.setInviteText();
        this.setInviteButton();

        this.setPlayerLabels();

        this.setStartGameButton();
        this.setLeaveButton();

        this.updateLobby(lobby);
    }

    //GRAPHIC INITIALIZERS
    private void setTitle() {
        this.title = new LabelOne("LOBBY", StaticFonts.segoeLoad(50), Pos.CENTER,
                960, 200, 1000, 70, this);
    }

    private void setGameId() {
        this.gameId = new LabelOne("Game Id: " + this.lobby.getId(), StaticFonts.segoeLoad(15), Pos.CENTER_LEFT,
                600, 50, 1000, 60, this);
    }

    private void setMapSizeLabel() {
        this.mapSizeLabel = new LabelOne("Small map 10x10", StaticFonts.segoeLoad(30), Pos.CENTER,
                960, 270, 1000, 60, this);
    }

    private void setSizeButtons() {
        this.small = new ButtonTwo("SMALL", StaticFonts.segoeLoad(40), Pos.CENTER,
                500, 400, 400, 60, this);
        this.medium = new ButtonTwo("MEDIUM", StaticFonts.segoeLoad(40), Pos.CENTER,
                500, 500, 400, 60, this);
        this.big = new ButtonTwo("BIG", StaticFonts.segoeLoad(40), Pos.CENTER,
                500, 600, 400, 60, this);
        this.huge = new ButtonTwo("HUGE", StaticFonts.segoeLoad(40), Pos.CENTER,
                500, 700, 400, 60, this);

        this.small.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                LobbyHost.this.setMapSize(10);
            }
        });

        this.medium.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                LobbyHost.this.setMapSize(15);
            }
        });

        this.big.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                LobbyHost.this.setMapSize(20);
            }
        });

        this.huge.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                LobbyHost.this.setMapSize(25);
            }
        });
    }

    private void setInviteText() {
        this.inviteText = new TextFieldOne("TYPE USERNAME", StaticFonts.segoeLoad(20), Pos.CENTER,
                960, 500, 400, 40, this);
    }

    private void setInviteButton() {
        this.inviteButton = new ButtonOne("INVITE", StaticFonts.segoeLoad(30), Pos.CENTER,
                960, 600, 200, 60, this);

        this.inviteButton.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                String friendUsername = inviteText.getText();

                Response response = Client.send(ClientAdapter.inviteToLobby(LobbyHost.this.lobby, friendUsername));

                if(response.getMessage().startsWith("error")) {
                    new PopUp(LobbyHost.this, new Error(response.getMessage()));
                    return;
                }

                new PopUp(LobbyHost.this, new Successful(response.getMessage()));
            }
        });
    }

    private void setPlayerLabels() {
        this.firstPlayer = new LabelOne(ClientManager.getInstance().getMainUser().getUsername() , StaticFonts.segoeLoad(40), Pos.CENTER,
                1420, 400, 400, 60, this);
        this.secondPlayer = new LabelOne("NO PLAYER", StaticFonts.segoeLoad(40), Pos.CENTER,
                1420, 500, 400, 60, this);
        this.thirdPlayer = new LabelOne("NO PLAYER", StaticFonts.segoeLoad(40), Pos.CENTER,
                1420, 600, 400, 60, this);
        this.fourthPlayer = new LabelOne("NO PLAYER", StaticFonts.segoeLoad(40), Pos.CENTER,
                1420, 700, 400, 60, this);
    }

    private void setStartGameButton() {
        this.startGameButton = new ButtonOne("START GAME", StaticFonts.segoeLoad(50), Pos.CENTER,
                960, 900, 600, 70, this);

        //TODO add function
    }

    private void setLeaveButton() {
        this.leaveButton = new ButtonOne("leave", StaticFonts.segoeLoad(15), Pos.CENTER,
                960, 1000, 100, 50, this);

        this.leaveButton.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                Client.send(ClientAdapter.closeLobby(LobbyHost.this.getLobby()));
                ClientManager.getInstance().setPane(new MultiplayerGame());
            }
        });
    }

    //SETTERS
    public void updateLobby(Lobby lobby) {
        this.lobby = lobby;

        this.setMapSize(lobby.getSize());
        this.setPlayers(lobby.getPlayerUsernames());
    }

    private void setMapSize(int mapSize) {
        switch (mapSize) {
            case 15:
                this.setMapMedium();
                break;
            case 20:
                this.setMapBig();
                break;
            case 25:
                this.setMapHuge();
                break;
            default:
                this.setMapSmall();
                break;
        }
        ClientManager.getInstance().sendUpdatedLobbyToServer(this.getLobby(), this.getLobby().getHostUsername());
    }

    private void setPlayers(ArrayList<String> usernames) {
        switch (usernames.size()) {
            case 4:
                fourthPlayer.setText(usernames.get(3));
            case 3:
                thirdPlayer.setText(usernames.get(2));
            case 2:
                secondPlayer.setText(usernames.get(1));
            case 1:
                firstPlayer.setText(usernames.get(0));
            default:
                break;
        }
    }

    private void setMapSmall() {
        this.lobby.setSize(10);

        this.mapSizeLabel.setText("Small map 10x10");
    }

    private void setMapMedium() {
        this.lobby.setSize(15);

        this.mapSizeLabel.setText("Medium map 15x15");
    }

    private void setMapBig() {
        this.lobby.setSize(20);

        this.mapSizeLabel.setText("Big map 20x20");
    }

    private void setMapHuge() {
        this.lobby.setSize(25);

        this.mapSizeLabel.setText("Huge map 25x25");
    }

    //GETTERS
    private Lobby getLobby() {
        return this.lobby;
    }
}
