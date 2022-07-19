package graphics.view.popUp.research;

import graphics.objects.buttons.DisableButtonOne;
import javafx.scene.layout.Pane;
import model.game.Civilization;
import model.research.Research;

public class TechnologyTreeMap extends Pane {
    TechnologyFX agriculture;

    TechnologyFX pottery;
    TechnologyFX animalHusbandry;
    TechnologyFX archery;
    TechnologyFX mining;

    TechnologyFX calendar;
    TechnologyFX writing;
    TechnologyFX trapping;
    TechnologyFX theWheel;
    TechnologyFX masonry;
    TechnologyFX bronzeWorking;

    TechnologyFX philosophy;
    TechnologyFX horsebackRiding;
    TechnologyFX mathematics;
    TechnologyFX construction;
    TechnologyFX ironWorking;

    TechnologyFX theology;
    TechnologyFX civilService;
    TechnologyFX currency;
    TechnologyFX engineering;
    TechnologyFX metalCasting;

    TechnologyFX education;
    TechnologyFX chivalry;
    TechnologyFX machinery;
    TechnologyFX physics;
    TechnologyFX steel;

    TechnologyFX acoustics;
    TechnologyFX banking;
    TechnologyFX printingPress;
    TechnologyFX gunpowder;

    TechnologyFX economics;
    TechnologyFX chemistry;
    TechnologyFX metallurgy;

    TechnologyFX archaeology;
    TechnologyFX scientificTheory;
    TechnologyFX militaryScience;
    TechnologyFX fertilizer;
    TechnologyFX rifling;

    TechnologyFX biology;
    TechnologyFX steamPower;

    TechnologyFX electricity;
    TechnologyFX replaceableParts;
    TechnologyFX railroad;
    TechnologyFX dynamite;

    TechnologyFX telegraph;
    TechnologyFX radio;
    TechnologyFX combustion;

    public TechnologyTreeMap (Civilization civilization) {
        agriculture = new TechnologyFX(Research.AGRICULTURE, 4, 1, true, this);

        pottery = new TechnologyFX(Research.POTTERY, 1, 2, false, this);
        animalHusbandry = new TechnologyFX(Research.ANIMAL_HUSBANDRY, 3, 2, true, this);
        archery = new TechnologyFX(Research.ARCHERY, 5, 2, false, this);
        mining = new TechnologyFX(Research.MINING, 7, 2, false, this);

        calendar = new TechnologyFX(Research.CALENDAR, 1, 3, false, this);
        writing = new TechnologyFX(Research.WRITING, 2, 3, false, this);
        trapping = new TechnologyFX(Research.WRITING, 3, 3, false, this);
        theWheel = new TechnologyFX(Research.TRAPPING, 4, 3, false, this);
        masonry = new TechnologyFX(Research.MASONRY, 6, 3, false, this);
        bronzeWorking = new TechnologyFX(Research.BRONZE_WORKING, 7, 3, false, this);

        philosophy = new TechnologyFX(Research.PHILOSOPHY, 2, 4, false, this);
        horsebackRiding = new TechnologyFX(Research.HORSEBACK_RIDING, 4, 4, false, this);
        mathematics = new TechnologyFX(Research.MATHEMATICS, 5, 4, false, this);
        construction = new TechnologyFX(Research.CONSTRUCTION, 6, 4, false, this);
        ironWorking = new TechnologyFX(Research.IRON_WORKING, 7, 4, false, this);

        theology = new TechnologyFX(Research.THEOLOGY, 1, 5, false, this);
        civilService = new TechnologyFX(Research.CIVIL_SERVICE, 3, 5, false, this);
        currency = new TechnologyFX(Research.CURRENCY, 5, 5, false, this);
        engineering = new TechnologyFX(Research.ENGINEERING, 6, 5, false, this);
        metalCasting = new TechnologyFX(Research.METAL_CASTING, 7, 5, false, this);

        education = new TechnologyFX(Research.EDUCATION, 1, 6, false, this);
        chivalry = new TechnologyFX(Research.CHIVALRY, 4, 6, false, this);
        machinery = new TechnologyFX(Research.MACHINERY, 5, 6, false, this);
        physics = new TechnologyFX(Research.PHYSICS, 6, 6, false, this);
        steel = new TechnologyFX(Research.STEEL, 7, 6, false, this);

        acoustics = new TechnologyFX(Research.ACOUSTICS, 1, 7, false, this);
        banking = new TechnologyFX(Research.BANKING, 4, 7, false, this);
        printingPress = new TechnologyFX(Research.PRINTING_PRESS, 5, 7, false, this);
        gunpowder = new TechnologyFX(Research.GUNPOWDER, 7, 7, false, this);

        economics = new TechnologyFX(Research.ECONOMICS, 4, 8, false, this);
        chemistry = new TechnologyFX(Research.CHEMISTRY, 6, 8, false, this);
        metallurgy = new TechnologyFX(Research.METALLURGY, 7, 8, false, this);

        archaeology = new TechnologyFX(Research.ARCHAEOLOGY, 1, 9, false, this);
        scientificTheory = new TechnologyFX(Research.SCIENTIFIC_THEORY, 3, 9, false, this);
        militaryScience = new TechnologyFX(Research.MILITARY_SCIENCE, 4, 9, false, this);
        fertilizer = new TechnologyFX(Research.FERTILIZER, 6, 9, false, this);
        rifling = new TechnologyFX(Research.RIFLING, 7, 9, false, this);

        biology = new TechnologyFX(Research.BIOLOGY, 1, 10, false, this);
        steamPower = new TechnologyFX(Research.STEAM_POWER, 3, 10, false, this);

        electricity = new TechnologyFX(Research.ELECTRICITY, 1, 11, false, this);
        replaceableParts = new TechnologyFX(Research.REPLACEABLE_PARTS, 3, 11, false, this);
        railroad = new TechnologyFX(Research.RAILROAD, 6, 11, false, this);
        dynamite = new TechnologyFX(Research.DYNAMITE, 7, 11, false, this);

        telegraph = new TechnologyFX(Research.TELEGRAPH, 1, 12, false, this);
        radio = new TechnologyFX(Research.RADIO, 2, 12, false, this);
        combustion = new TechnologyFX(Research.COMBUSTION, 6, 12, false, this);
    }
}
