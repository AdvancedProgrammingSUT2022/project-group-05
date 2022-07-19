package graphics.view.popUp;

import graphics.objects.buttons.DisableButtonOne;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import model.game.Civilization;

public class ResearchPanel extends Pane {
    ScrollPane scrollPane;
    DisableButtonOne agriculture;

    DisableButtonOne pottery;
    DisableButtonOne animalHusbandry;
    DisableButtonOne archery;
    DisableButtonOne mining;

    DisableButtonOne calendar;
    DisableButtonOne writing;
    DisableButtonOne trapping;
    DisableButtonOne theWheel;
    DisableButtonOne masonry;
    DisableButtonOne bronzeWorking;

    DisableButtonOne philosophy;
    DisableButtonOne horsebackRiding;
    DisableButtonOne mathematics;
    DisableButtonOne construction;
    DisableButtonOne ironWorking;

    DisableButtonOne theology;
    DisableButtonOne civilService;
    DisableButtonOne currency;
    DisableButtonOne engineering;
    DisableButtonOne metalCasting;

    DisableButtonOne education;
    DisableButtonOne chivalry;
    DisableButtonOne machinery;
    DisableButtonOne physics;
    DisableButtonOne steel;

    DisableButtonOne acoustics;
    DisableButtonOne banking;
    DisableButtonOne printingPress;
    DisableButtonOne gunpowder;

    DisableButtonOne economics;
    DisableButtonOne chemistry;
    DisableButtonOne metallurgy;

    DisableButtonOne archaeology;
    DisableButtonOne scientificTheory;
    DisableButtonOne militaryScience;
    DisableButtonOne fertilizer;
    DisableButtonOne rifling;

    DisableButtonOne biology;
    DisableButtonOne steamPower;

    DisableButtonOne electricity;
    DisableButtonOne replaceableParts;
    DisableButtonOne railroad;
    DisableButtonOne dynamite;

    DisableButtonOne telegraph;
    DisableButtonOne radio;
    DisableButtonOne combustion;

    public ResearchPanel(Civilization civilization) {
        scrollPane = new ScrollPane();
        getChildren().add(scrollPane);
        scrollPane.setPrefHeight(300);
        scrollPane.setPrefWidth(300);
        scrollPane.setContent(new Rectangle(0, 500, 200, 200));
    }
}
