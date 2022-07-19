//package view.menu;
//
//import graphics.objects.buttons.ButtonOne;
//import graphics.objects.labels.LabelOne;
//import javafx.event.EventHandler;
//import javafx.geometry.Pos;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.Pane;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Rectangle;
//import statics.StaticFonts;
//
//
//public class ErrorBox {
//    public ErrorBox() {
//
//    }
//
//    public static Pane getErrorBox (String errorText, Pane father, boolean isError) {
//        Pane error = new Pane();
//        Rectangle background = new Rectangle(0, 0, 600, 150);
//        error.getChildren().add(background);
//        if (isError)
//            background.setFill(Color.RED);
//        else
//            background.setFill(Color.GREEN);
//        LabelOne text = new LabelOne(errorText, StaticFonts.SeqoeLoad(25), Pos.CENTER,
//                300, 40, 400, 50, error);
//        ButtonOne ok = new ButtonOne("OK", StaticFonts.SeqoeLoad(40), Pos.CENTER,
//                300, 120, 80, 50, error);
//
//        error.setLayoutX(960 - 300);
//        error.setLayoutY(400);
//
//        father.getChildren().add(error);
//        ok.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                father.getChildren().remove(error);
//            }
//        });
//
//        return error;
//    }
//}
