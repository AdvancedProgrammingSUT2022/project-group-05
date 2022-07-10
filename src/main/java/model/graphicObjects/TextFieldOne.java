package model.graphicObjects;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class TextFieldOne extends Pane {
    private Pane pane;
    private TextField textField;
    private Rectangle rectangle;

    public TextFieldOne(String text, Font font, Pos pos, double fromLeft, double fromTop, double width, double height, Pane pane) {
        this.pane = pane;
        pane.getChildren().add(this);
        this.setLayoutX(fromLeft - width/2);
        this.setLayoutY(fromTop - height/2);

        rectangle = new Rectangle(0, 0, width, height);
        rectangle.setFill(new Color(0,0,0,0));
        this.getChildren().add(rectangle);

        textField = new TextField(text);
        textField.setAlignment(pos);
        textField.setLayoutX(0);
        textField.setLayoutY(0);
        textField.setPrefWidth(width);
        textField.setPrefHeight(height);
        textField.setFont(font);
        this.getChildren().add(textField);
    }

    public String getText () {
        return textField.getText();
    }

    public TextField getTextField () {
        return textField;
    }
}
