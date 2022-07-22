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

    private LabelOne playerOne;
    private LabelOne playerTwo;
    private LabelOne playerThree;
    private LabelOne playerFour;

    private ButtonOne join;
    private ButtonOne reject;

    public LobbyInvitationPane(Lobby lobby) {
        this.lobby = lobby;

        this.setPrefWidth(1200);
        this.setPrefHeight(700);

        this.setHostUsername();
        this.setGameId();

        this.setPlayerNicknamesText();
        this.setPlayersNicknames();

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

    private void setPlayerNicknamesText() {
        this.playerOne = new LabelOne("NO PLAYER", StaticFonts.segoeLoad(60), Pos.BOTTOM_LEFT,
                450, 300, 700, 100, this);
        this.playerTwo = new LabelOne("NO PLAYER", StaticFonts.segoeLoad(60), Pos.BOTTOM_LEFT,
                450, 400, 700, 100, this);
        this.playerThree = new LabelOne("NO PLAYER", StaticFonts.segoeLoad(60), Pos.BOTTOM_LEFT,
                450, 500, 700, 100, this);
        this.playerFour = new LabelOne("NO PLAYER", StaticFonts.segoeLoad(60), Pos.BOTTOM_LEFT,
                450, 600, 700, 100, this);
    }

    private void setPlayersNicknames() {
        switch (this.lobby.getPlayerUsernames().size()) {
            case (4) :
                this.playerFour.setText("PLAYER ONE : " + this.lobby.getPlayerUsernames().get(3));
                this.playerThree.setText("PLAYER ONE : " + this.lobby.getPlayerUsernames().get(2));
                this.playerTwo.setText("PLAYER ONE : " + this.lobby.getPlayerUsernames().get(1));
                this.playerOne.setText("PLAYER ONE : " + this.lobby.getPlayerUsernames().get(0));
                break;
            case (3) :
                this.playerFour.setText("NO PLAYER");
                this.playerThree.setText("PLAYER ONE : " + this.lobby.getPlayerUsernames().get(2));
                this.playerTwo.setText("PLAYER ONE : " + this.lobby.getPlayerUsernames().get(1));
                this.playerOne.setText("PLAYER ONE : " + this.lobby.getPlayerUsernames().get(0));
                break;
            case (2) :
                this.playerFour.setText("NO PLAYER");
                this.playerThree.setText("NO PLAYER");
                this.playerTwo.setText("PLAYER ONE : " + this.lobby.getPlayerUsernames().get(1));
                this.playerOne.setText("PLAYER ONE : " + this.lobby.getPlayerUsernames().get(0));
                break;
            case (1) :
                this.playerFour.setText("NO PLAYER");
                this.playerThree.setText("NO PLAYER");
                this.playerTwo.setText("NO PLAYER");
                this.playerOne.setText("PLAYER ONE : " + this.lobby.getPlayerUsernames().get(0));
                break;
            default:
                break;
        }
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
