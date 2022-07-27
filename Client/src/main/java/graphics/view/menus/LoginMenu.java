package graphics.view.menus;

import com.google.gson.Gson;
import graphics.objects.buttons.ButtonOne;
import graphics.objects.textFields.TextFieldOne;
import graphics.statics.StaticColors;
import graphics.statics.StaticFonts;
import client.ClientManager;
import graphics.view.popUp.Error;
import graphics.view.popUp.PopUp;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import client.Client;
import client.ClientAdapter;
import client.Response;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import model.User;


public class LoginMenu extends Pane{
    public LoginMenu () {
        Pane temp = this;

        int fromLeft = (int) ClientManager.getInstance().getMainStage().getWidth() / 2;
        int fromTop = (int) ClientManager.getInstance().getMainStage().getHeight() / 2 - 100;
        //OBJECTS
        TextFieldOne username = new TextFieldOne("username", StaticFonts.segoeLoad(20), Pos.CENTER,
                fromLeft, fromTop, 400, 30, this);
        TextFieldOne password = new TextFieldOne("password", StaticFonts.segoeLoad(20), Pos.CENTER,
                fromLeft, fromTop + 50, 400, 30, this);
        ButtonOne login = new ButtonOne("LOGIN", StaticFonts.segoeLoad(30), Pos.CENTER,
                fromLeft, fromTop + 150, 300, 50, this);

        ButtonOne signUp = new ButtonOne("SIGN UP", StaticFonts.segoeLoad(30), Pos.CENTER,
                fromLeft, fromTop + 250, 300, 50, this);

        ButtonOne exit = new ButtonOne("EXIT", StaticFonts.segoeLoad(15), Pos.CENTER,
                fromLeft, fromTop + 450, 100, 50, this);


        //ANIMATION
        ParallelTransition start = AnimatedPane.getStartAnimation(this);
        ParallelTransition end = AnimatedPane.getEndAnimation(this);
        start.play();

        //FUNCTIONS
        login.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String usernameText = username.getText();
                String passwordText = password.getText();

                Response loginResponse = Client.send(ClientAdapter.login(usernameText, passwordText));

                if (loginResponse.getMessage().startsWith("error")) {
                    new PopUp(temp, new Error(loginResponse.getMessage()));
                    return;
                }

                end.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Response getUserResponse = Client.send(ClientAdapter.getUser(usernameText));
                        String userJson = getUserResponse.getMessage();
                        Gson gson = new Gson();
                        User user = gson.fromJson(userJson, User.class);
                        ClientManager.getInstance().setMainUser(user);
                        ClientManager.getInstance().setPane(new MainMenu());
                    }
                });
                end.play();
            }
        });
        signUp.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                end.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        ClientManager.getInstance().setPane(new RegisterMenu());

                    }
                });
                end.play();
            }
        });
        exit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                end.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.exit(0);
                    }
                });
                end.play();
            }
        });
    }
}
