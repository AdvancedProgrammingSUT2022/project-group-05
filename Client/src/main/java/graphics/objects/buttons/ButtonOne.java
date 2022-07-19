package graphics.objects.buttons;

import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class ButtonOne extends Pane{
    private Pane pane;
    private Label label;
    private Rectangle paneRect;
    private Rectangle animationRect;

    public ButtonOne(String text, Font font, Pos pos,
                     double fromLeft, double fromTop, double width, double height, Pane pane) {
        this.pane = pane;
        pane.getChildren().add(this);
        this.setLayoutX(fromLeft - width/2);
        this.setLayoutY(fromTop - height/2);

        //OBJECTS
        animationRect = new Rectangle(width/2 - width/20, height - 5, width/10, 5);
        animationRect.setFill(Color.BLUE);
        this.getChildren().add(animationRect);

        label = new Label(text);
        label.setAlignment(pos);
        label.setLayoutX(0);
        label.setLayoutY(5);
        label.setPrefWidth(width);
        label.setPrefHeight(height - 10);
        label.setFont(font);
        this.getChildren().add(label);

        paneRect = new Rectangle(0, 0, width, height);
        paneRect.setFill(new Color(0,0,0,0));
        this.getChildren().add(paneRect);

        //TRANSITIONS
        ScaleTransition widthIncrease = new ScaleTransition(new Duration(100), animationRect);
        widthIncrease.setFromX(1);
        widthIncrease.setToX(10);

        ScaleTransition widthDecrease = new ScaleTransition(new Duration(100), animationRect);
        widthDecrease.setFromX(10);
        widthDecrease.setToX(1);

        ScaleTransition heightIncreaseScale = new ScaleTransition(new Duration(75), animationRect);
        heightIncreaseScale.setFromY(1);
        heightIncreaseScale.setToY(height/5);

        ScaleTransition heightDecreaseScale = new ScaleTransition(new Duration(75), animationRect);
        heightDecreaseScale.setFromY(height/5);
        heightDecreaseScale.setToY(1);

        TranslateTransition heightIncreaseTranslate = new TranslateTransition(new Duration(75), animationRect);
        heightIncreaseTranslate.setFromY(0);
        heightIncreaseTranslate.setToY(-height/2 + 2.5);

        TranslateTransition heightDecreaseTranslate = new TranslateTransition(new Duration(75), animationRect);
        heightDecreaseTranslate.setFromY(-height/2 + 2.5);
        heightDecreaseTranslate.setToY(0);

        ParallelTransition heightIncrease = new ParallelTransition(heightIncreaseTranslate, heightIncreaseScale);

        ParallelTransition heightDecrease = new ParallelTransition(heightDecreaseTranslate, heightDecreaseScale);

        SequentialTransition hoverTrue = new SequentialTransition(widthIncrease, heightIncrease);

        SequentialTransition hoverFalse = new SequentialTransition(heightDecrease, widthDecrease);

        SequentialTransition reverse = new SequentialTransition();


        ScaleTransition toZero = new ScaleTransition(new Duration(200), animationRect);
        toZero.setToY(0);
        toZero.setToX(0);


        //ANIMATIONS
        paneRect.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //widthIncrease.play();
                hoverFalse.stop();
                hoverTrue.play();
            }
        });
        paneRect.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //widthDecrease.play();
                hoverTrue.stop();
                hoverFalse.play();
            }
        });
        paneRect.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                toZero.play();
            }
        });
        paneRect.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            }
        });
        paneRect.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (label.isHover()) System.out.println("SAAAAAAAAAaa");
            }
        });
    }
}
