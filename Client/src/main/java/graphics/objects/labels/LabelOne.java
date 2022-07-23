package graphics.objects.labels;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class LabelOne extends Pane {
    private Pane pane;
    private Label label;
    private Rectangle rectangle;

    public LabelOne(String text, Font font, Pos pos, double fromLeft, double fromTop, double width, double height, Pane pane) {
        this.pane = pane;
        pane.getChildren().add(this);
        this.setLayoutX(fromLeft - width/2);
        this.setLayoutY(fromTop - height/2);

        rectangle = new Rectangle(0, 0, width, height);
        rectangle.setFill(new Color(0,0,0,0));
        this.getChildren().add(rectangle);

        label = new Label(text);
        label.setAlignment(pos);
        label.setLayoutX(0);
        label.setLayoutY(0);
        label.setPrefWidth(width);
        label.setPrefHeight(height);
        label.setFont(font);
        this.getChildren().add(label);
    }

    public LabelOne(String text, Font font, Pos pos, double fromLeft, double fromTop, double width, double height, Group group) {
        group.getChildren().add(this);
        this.setLayoutX(fromLeft - width/2);
        this.setLayoutY(fromTop - height/2);

        rectangle = new Rectangle(0, 0, width, height);
        rectangle.setFill(new Color(0,0,0,0));
        this.getChildren().add(rectangle);

        label = new Label(text);
        label.setAlignment(pos);
        label.setLayoutX(0);
        label.setLayoutY(0);
        label.setPrefWidth(width);
        label.setPrefHeight(height);
        label.setFont(font);
        this.getChildren().add(label);
    }

    public void changeText(String text) {
        Label label = (Label) this.getChildren().get(1);
        label.setText(text);

    public void setText(String name) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                LabelOne.this.label.setText(name);
            }
        });
    }
}
