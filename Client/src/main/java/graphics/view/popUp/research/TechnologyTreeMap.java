package graphics.view.popUp.research;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Arc;
import model.game.Civilization;

import static model.research.Research.*;

public class TechnologyTreeMap extends Pane {
    private final Civilization civilization;

    private final TechnologyFX agriculture;

    private final TechnologyFX pottery;
    private final TechnologyFX animalHusbandry;
    private final TechnologyFX archery;
    private final TechnologyFX mining;

    private final TechnologyFX calendar;
    private final TechnologyFX writing;
    private final TechnologyFX trapping;
    private final TechnologyFX theWheel;
    private final TechnologyFX masonry;
    private final TechnologyFX bronzeWorking;

    private final TechnologyFX philosophy;
    private final TechnologyFX horsebackRiding;
    private final TechnologyFX mathematics;
    private final TechnologyFX construction;
    private final TechnologyFX ironWorking;

    private final TechnologyFX theology;
    private final TechnologyFX civilService;
    private final TechnologyFX currency;
    private final TechnologyFX engineering;
    private final TechnologyFX metalCasting;

    private final TechnologyFX education;
    private final TechnologyFX chivalry;
    private final TechnologyFX machinery;
    private final TechnologyFX physics;
    private final TechnologyFX steel;

    private final TechnologyFX acoustics;
    private final TechnologyFX banking;
    private final TechnologyFX printingPress;
    private final TechnologyFX gunpowder;

    private final TechnologyFX economics;
    private final TechnologyFX chemistry;
    private final TechnologyFX metallurgy;

    private final TechnologyFX archaeology;
    private final TechnologyFX scientificTheory;
    private final TechnologyFX militaryScience;
    private final TechnologyFX fertilizer;
    private final TechnologyFX rifling;

    private final TechnologyFX biology;
    private final TechnologyFX steamPower;

    private final TechnologyFX electricity;
    private final TechnologyFX replaceableParts;
    private final TechnologyFX railroad;
    private final TechnologyFX dynamite;

    private final TechnologyFX telegraph;
    private final TechnologyFX radio;
    private final TechnologyFX combustion;

    public TechnologyTreeMap (Civilization civilization) {
        this.civilization = civilization;
        
        agriculture = new TechnologyFX(civilization.getResearchTree().getResearch(AGRICULTURE), 4, 1, this);

        pottery = new TechnologyFX(civilization.getResearchTree().getResearch(POTTERY), 1, 2, this);
        animalHusbandry = new TechnologyFX(civilization.getResearchTree().getResearch(ANIMAL_HUSBANDRY), 3, 2, this);
        archery = new TechnologyFX(civilization.getResearchTree().getResearch(ARCHERY), 5, 2, this);
        mining = new TechnologyFX(civilization.getResearchTree().getResearch(MINING), 7, 2, this);

        calendar = new TechnologyFX(civilization.getResearchTree().getResearch(CALENDAR), 1, 3, this);
        writing = new TechnologyFX(civilization.getResearchTree().getResearch(WRITING), 2, 3, this);
        trapping = new TechnologyFX(civilization.getResearchTree().getResearch(TRAPPING), 3, 3, this);
        theWheel = new TechnologyFX(civilization.getResearchTree().getResearch(THE_WHEEL), 4, 3, this);
        masonry = new TechnologyFX(civilization.getResearchTree().getResearch(MASONRY), 6, 3, this);
        bronzeWorking = new TechnologyFX(civilization.getResearchTree().getResearch(BRONZE_WORKING), 7, 3, this);

        philosophy = new TechnologyFX(civilization.getResearchTree().getResearch(PHILOSOPHY), 2, 4, this);
        horsebackRiding = new TechnologyFX(civilization.getResearchTree().getResearch(HORSEBACK_RIDING), 4, 4, this);
        mathematics = new TechnologyFX(civilization.getResearchTree().getResearch(MATHEMATICS), 5, 4, this);
        construction = new TechnologyFX(civilization.getResearchTree().getResearch(CONSTRUCTION), 6, 4, this);
        ironWorking = new TechnologyFX(civilization.getResearchTree().getResearch(IRON_WORKING), 7, 4, this);

        theology = new TechnologyFX(civilization.getResearchTree().getResearch(THEOLOGY), 1, 5, this);
        civilService = new TechnologyFX(civilization.getResearchTree().getResearch(CIVIL_SERVICE), 3, 5, this);
        currency = new TechnologyFX(civilization.getResearchTree().getResearch(CURRENCY), 5, 5, this);
        engineering = new TechnologyFX(civilization.getResearchTree().getResearch(ENGINEERING), 6, 5, this);
        metalCasting = new TechnologyFX(civilization.getResearchTree().getResearch(METAL_CASTING), 7, 5, this);

        education = new TechnologyFX(civilization.getResearchTree().getResearch(EDUCATION), 1, 6, this);
        chivalry = new TechnologyFX(civilization.getResearchTree().getResearch(CHIVALRY), 4, 6, this);
        machinery = new TechnologyFX(civilization.getResearchTree().getResearch(MACHINERY), 5, 6, this);
        physics = new TechnologyFX(civilization.getResearchTree().getResearch(PHYSICS), 6, 6, this);
        steel = new TechnologyFX(civilization.getResearchTree().getResearch(STEEL), 7, 6, this);

        acoustics = new TechnologyFX(civilization.getResearchTree().getResearch(ACOUSTICS), 1, 7, this);
        banking = new TechnologyFX(civilization.getResearchTree().getResearch(BANKING), 4, 7, this);
        printingPress = new TechnologyFX(civilization.getResearchTree().getResearch(PRINTING_PRESS), 5, 7, this);
        gunpowder = new TechnologyFX(civilization.getResearchTree().getResearch(GUNPOWDER), 7, 7, this);

        economics = new TechnologyFX(civilization.getResearchTree().getResearch(ECONOMICS), 4, 8, this);
        chemistry = new TechnologyFX(civilization.getResearchTree().getResearch(CHEMISTRY), 6, 8, this);
        metallurgy = new TechnologyFX(civilization.getResearchTree().getResearch(METALLURGY), 7, 8, this);

        archaeology = new TechnologyFX(civilization.getResearchTree().getResearch(ARCHAEOLOGY), 1, 9, this);
        scientificTheory = new TechnologyFX(civilization.getResearchTree().getResearch(SCIENTIFIC_THEORY), 3, 9, this);
        militaryScience = new TechnologyFX(civilization.getResearchTree().getResearch(MILITARY_SCIENCE), 4, 9, this);
        fertilizer = new TechnologyFX(civilization.getResearchTree().getResearch(FERTILIZER), 6, 9, this);
        rifling = new TechnologyFX(civilization.getResearchTree().getResearch(RIFLING), 7, 9, this);

        biology = new TechnologyFX(civilization.getResearchTree().getResearch(BIOLOGY), 1, 10, this);
        steamPower = new TechnologyFX(civilization.getResearchTree().getResearch(STEAM_POWER), 3, 10, this);

        electricity = new TechnologyFX(civilization.getResearchTree().getResearch(ELECTRICITY), 1, 11, this);
        replaceableParts = new TechnologyFX(civilization.getResearchTree().getResearch(REPLACEABLE_PARTS), 3, 11, this);
        railroad = new TechnologyFX(civilization.getResearchTree().getResearch(RAILROAD), 6, 11, this);
        dynamite = new TechnologyFX(civilization.getResearchTree().getResearch(DYNAMITE), 7, 11, this);

        telegraph = new TechnologyFX(civilization.getResearchTree().getResearch(TELEGRAPH), 1, 12, this);
        radio = new TechnologyFX(civilization.getResearchTree().getResearch(RADIO), 2, 12, this);
        combustion = new TechnologyFX(civilization.getResearchTree().getResearch(COMBUSTION), 6, 12, this);
    }

    public Civilization getCivilization() {
        return this.civilization;
    }
}
