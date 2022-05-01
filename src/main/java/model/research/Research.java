package model.research;

import java.util.ArrayList;
import java.util.List;

public enum Research{
    AGRICULTURE(20, List.of()),
    ANIMAL_HUSBANDRY(35, List.of(AGRICULTURE)),
    ARCHERY(35, List.of(AGRICULTURE)),
    POTTERY(35, List.of(AGRICULTURE)),
    MINING(35, List.of(AGRICULTURE)),
    BRONZE_WORKING(55, List.of(MINING)),
    MASONRY(55, List.of(MINING)),
    THE_WHEEL(55, List.of(ANIMAL_HUSBANDRY)),
    TRAPPING(55, List.of(ANIMAL_HUSBANDRY)),
    WRITING(55, List.of(POTTERY)),
    CALENDAR(70, List.of(POTTERY)),

    CONSTRUCTION(100, List.of(MASONRY)),
    HORSEBACK_RIDING(100, List.of(THE_WHEEL)),
    IRON_WORKING(150, List.of(BRONZE_WORKING)),
    MATHEMATICS(100, List.of(THE_WHEEL, ARCHERY)),
    PHILOSOPHY(100, List.of(WRITING)),

    CIVIL_SERVICE(400, List.of(TRAPPING, PHILOSOPHY)),
    CURRENCY(250, List.of(MATHEMATICS)),
    ENGINEERING(250, List.of(MATHEMATICS, CONSTRUCTION)),
    METAL_CASTING(240, List.of(IRON_WORKING)),
    THEOLOGY(250, List.of(CALENDAR, PHILOSOPHY)),
    MACHINERY(440, List.of(ENGINEERING)),
    CHIVALRY(440, List.of(HORSEBACK_RIDING, CIVIL_SERVICE, CURRENCY)),
    PHYSICS(440, List.of(ENGINEERING, METAL_CASTING)),
    STEEL(440, List.of(METAL_CASTING)),
    EDUCATION(440, List.of(THEOLOGY)),

    ACOUSTICS(650, List.of(EDUCATION)),
    BANKING(650, List.of(EDUCATION, CHIVALRY)),
    GUNPOWDER(680, List.of(PHYSICS, STEEL)),
    CHEMISTRY(900, List.of(GUNPOWDER)),
    FERTILIZER(1300, List.of(CHEMISTRY)),
    METALLURGY(900, List.of(GUNPOWDER)),
    PRINTING_PRESS(650, List.of(MACHINERY, PHYSICS)),
    ECONOMICS(900, List.of(BANKING, PRINTING_PRESS)),
    MILITARY_SCIENCE(1300, List.of(ECONOMICS, CHEMISTRY)),
    RIFLING(1425, List.of(METALLURGY)),
    SCIENTIFIC_THEORY(1300, List.of(ACOUSTICS)),
    ARCHAEOLOGY(1300, List.of(ACOUSTICS)),

    BIOLOGY(1680, List.of(ARCHAEOLOGY, SCIENTIFIC_THEORY)),
    STEAM_POWER(1680, List.of(SCIENTIFIC_THEORY, MILITARY_SCIENCE)),
    DYNAMITE(1900, List.of(FERTILIZER, RIFLING)),
    ELECTRICITY(1900, List.of(BIOLOGY, STEAM_POWER)),
    RADIO(2200, List.of(ELECTRICITY)),
    RAILROAD(1900, List.of(STEAM_POWER)),
    REPLACEABLE_PARTS(1900, List.of(STEAM_POWER)),
    COMBUSTION(2200, List.of(REPLACEABLE_PARTS, RAILROAD, DYNAMITE)),
    TELEGRAPH(2200, List.of(ELECTRICITY));

    private final int cost;
    private final ArrayList<Research> ancestors;

    Research(int cost, List<Research> ancestors) {
        this.cost = cost;
        this.ancestors = new ArrayList<>(ancestors);
    }

    //GETTER
    public int getCost() {
        return this.cost;
    }

    public ArrayList<Research> getAncestors() {
        return this.ancestors;
    }
}
