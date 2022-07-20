package graphics.view.menus.multiplayer;

import graphics.objects.buttons.ButtonOne;
import graphics.objects.labels.LabelOne;
import graphics.statics.StaticFonts;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import model.Lobby;

public class LobbyInvitationPane extends Pane{
    private Lobby lobby;

    private LabelOne gameId;
    private LabelOne hostUsername;

    private ButtonOne join;
    private ButtonOne reject;

    public LobbyInvitationPane(Lobby lobby) {
        this.lobby = lobby;

        this.setPrefWidth(1200);
        this.setPrefHeight(200);

        this.setHostUsername();
        this.setGameId();

        this.setJoin();
        this.setReject();
    }

    private void setGameId() {
        this.gameId = new LabelOne("lobby ID: " + this.lobby.getId(), StaticFonts.segoeLoad(30), Pos.BOTTOM_LEFT,
                350, 150, 500, 50, this);
    }

    private void setHostUsername() {
        this.hostUsername = new LabelOne(this.lobby.getHostUsername() + "'s game", StaticFonts.segoeLoad(60), Pos.BOTTOM_LEFT,
                350, 75, 500, 100, this);
    }

    private void setJoin() {
        this.join = new ButtonOne("JOIN", StaticFonts.segoeLoad(40), Pos.CENTER,
                750, 100, 200, 150, this);
    }

    private void setReject() {
        this.reject = new ButtonOne("REJECT", StaticFonts.segoeLoad(40), Pos.CENTER,
                1050, 100, 200, 150, this);
    }

    //GETTER
    public ButtonOne getJoinButton() {
        return this.join;
    }

    public ButtonOne getRejectButton() {
        return this.reject;
    }

    public Lobby getLobby() {
        return this.lobby;
    }
}
