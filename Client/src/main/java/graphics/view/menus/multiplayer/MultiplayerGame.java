package graphics.view.menus.multiplayer;

import graphics.objects.labels.LabelOne;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

public class MultiplayerGame extends Pane{
    public LabelOne title;

    private LabelOne gameListTitle;
    private ListView<LobbyInvitationPane> invitations;
}
