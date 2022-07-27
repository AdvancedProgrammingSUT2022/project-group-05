package graphics.view.popUp;

import client.ClientManager;
import graphics.objects.buttons.ButtonOne;
import graphics.statics.StaticFonts;
import graphics.statics.StaticSounds;
import graphics.view.menus.MainMenu;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Setting extends Pane {
    private Rectangle background;
    private ButtonOne ExitGame;
    private ButtonOne Sound;

    public Setting () {
        background = new Rectangle(400, 300);
        this.setPrefHeight(background.getHeight());
        this.setPrefWidth(background.getWidth());

        background.setFill(Color.WHITE);
        this.getChildren().add(background);

        ExitGame = new ButtonOne("EXIT FROM GAME", StaticFonts.segoeLoad(30), Pos.CENTER,
                background.getWidth()/2, 200, 300, 50, this);
        ExitGame.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ClientManager.getInstance().setPane(new MainMenu());
            }
        });

        Sound = new ButtonOne("SET SOUND", StaticFonts.segoeLoad(30), Pos.CENTER,
                background.getWidth()/2, 100, 300, 50, this);
        Sound.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                StaticSounds.mainTheme();
            }
        });
    }
}
