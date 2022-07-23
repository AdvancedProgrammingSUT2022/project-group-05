package graphics.view.menus;

import graphics.statics.StaticColors;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class AnimatedPane {
    public static ParallelTransition getStartAnimation (Pane pane) {
        Rectangle animationRectTop = new Rectangle(1920, 540);
        pane.getChildren().add(animationRectTop);
        Rectangle animationRectDown = new Rectangle(0, 540, 1920, 540);
        pane.getChildren().add(animationRectDown);
        animationRectTop.setFill(StaticColors.PaneAnimation);
        animationRectDown.setFill(StaticColors.PaneAnimation);
        TranslateTransition topRect = new TranslateTransition(new Duration(500), animationRectTop);
        TranslateTransition downRect = new TranslateTransition(new Duration(500), animationRectDown);
        topRect.setByY(-540);
        downRect.setByY(540);

        return new ParallelTransition(topRect, downRect);
    }

    public static ParallelTransition getEndAnimation (Pane pane) {
        Rectangle animationRectLeft = new Rectangle(-960, 0 ,960, 1080);
        pane.getChildren().add(animationRectLeft);
        Rectangle animationRectRight = new Rectangle(1920, 0, 960, 1080);
        pane.getChildren().add(animationRectRight);
        animationRectLeft.setFill(StaticColors.PaneAnimation);
        animationRectRight.setFill(StaticColors.PaneAnimation);
        TranslateTransition leftRect = new TranslateTransition(new Duration(500), animationRectLeft);
        TranslateTransition rightRect = new TranslateTransition(new Duration(500), animationRectRight);
        leftRect.setByX(960);
        rightRect.setByX(-960);

        return new ParallelTransition(leftRect, rightRect);
    }
}
