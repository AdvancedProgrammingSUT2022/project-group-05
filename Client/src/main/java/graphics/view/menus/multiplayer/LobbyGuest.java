package graphics.view.menus.multiplayer;

import client.ClientManager;
import graphics.objects.buttons.ButtonOne;
import graphics.objects.labels.LabelOne;
import graphics.statics.StaticFonts;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.Lobby;

import java.util.ArrayList;

public class LobbyGuest extends Pane{
    private Lobby lobby;

    private LabelOne title;

    private LabelOne mapSizeLabel;

    private LabelOne gameId;

    private LabelOne firstPlayer;
    private LabelOne secondPlayer;
    private LabelOne thirdPlayer;
    private LabelOne fourthPlayer;

    private ButtonOne leaveButton;

    public LobbyGuest(Lobby lobby) {
        this.lobby = lobby;

        this.setGameId();
        this.setMapSizeLabel();
        this.setTitle();

        this.setPlayerLabels();

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

    private void setPlayerLabels() {
        this.firstPlayer = new LabelOne(ClientManager.getInstance().getMainUser().getUsername(), StaticFonts.segoeLoad(40), Pos.CENTER,
                960, 400, 400, 60, this);
        this.secondPlayer = new LabelOne("NO PLAYER", StaticFonts.segoeLoad(40), Pos.CENTER,
                960, 500, 400, 60, this);
        this.thirdPlayer = new LabelOne("NO PLAYER", StaticFonts.segoeLoad(40), Pos.CENTER,
                960, 600, 400, 60, this);
        this.fourthPlayer = new LabelOne("NO PLAYER", StaticFonts.segoeLoad(40), Pos.CENTER,
                960, 700, 400, 60, this);
    }


    private void setLeaveButton() {
        this.leaveButton = new ButtonOne("leave", StaticFonts.segoeLoad(15), Pos.CENTER,
                960, 1000, 100, 50, this);

        this.leaveButton.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                LobbyGuest.this.getLobby().removeUser(ClientManager.getInstance().getMainUser().getUsername());
                ClientManager.getInstance().sendUpdatedLobbyToServer(LobbyGuest.this.getLobby());

                ClientManager.getInstance().setPane(new MultiplayerGame());
            }
        });
    }

    //SETTERS
    public synchronized void updateLobby(Lobby lobby) {
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

        ClientManager.getInstance().sendUpdatedLobbyToServer(this.lobby);
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