package graphics.view.popUp;

import graphics.statics.StaticColors;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class PopUp extends Pane{
    private Pane father;
    private Rectangle background;

    public PopUp (Pane father, Pane child) {
        this.father = father;
        father.getChildren().add(this);

        background = new Rectangle(0, 0, 1920, 1080);
        background.setFill(StaticColors.PopUpBackground);
        this.getChildren().add(background);
        background.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                exit();
            }
        });
        //add transition here for background

        double height = child.getPrefHeight();
        double width = child.getPrefWidth();

        this.getChildren().add(child);
        child.setLayoutX(960 - width/2);
        child.setLayoutY(540 - height/2);
    }

    private void exit() {
        ScaleTransition toZero = new ScaleTransition(new Duration(500), this);
        toZero.setToX(0);
        toZero.setToY(0);
        Pane temp = this;
        toZero.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                father.getChildren().remove(temp);
            }
        });
        toZero.play();
    }
}
