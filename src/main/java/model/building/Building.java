package model.building;

import model.research.Research;
import model.resource.Resource;

import java.util.ArrayList;
import java.util.List;

public enum Building {

    //Ancient era buildings
    BARRACKS(80, 1, Research.BRONZE_WORKING, Resource.NO_RESOURCE, List.of()),
    GRANARY(100, 1, Research.POTTERY, Resource.NO_RESOURCE, List.of()),
    LIBRARY(80, 1, Research.WRITING, Resource.NO_RESOURCE, List.of()),
    MONUMENT(60, 1, Research.NO_RESEARCH, Resource.NO_RESOURCE, List.of()),
    WALLS(100, 1, Research.MASONRY, Resource.NO_RESOURCE, List.of()),
    WATER_MILL(120, 2, Research.THE_WHEEL, Resource.NO_RESOURCE, List.of()),

    //Classical era buildings
    ARMORY(130, 3, Research.IRON_WORKING, Resource.NO_RESOURCE, List.of(BARRACKS)),
    BURIAL_TOMB(120, 0, Research.PHILOSOPHY, Resource.NO_RESOURCE, List.of()),
    CIRCUS(150, 3, Research.HORSEBACK_RIDING, Resource.HORSE /*TODO or ivory*/, List.of()),
    COLOSSEUM(150, 3, Research.CONSTRUCTION, Resource.NO_RESOURCE, List.of()),
    COURTHOUSE(200, 5, Research.MATHEMATICS, Resource.NO_RESOURCE, List.of()),
    STABLE(100, 1, Research.HORSEBACK_RIDING, Resource.HORSE, List.of()),
    TEMPLE(120, 2, Research.PHILOSOPHY, Resource.NO_RESOURCE, List.of(MONUMENT)),

    //Medieval era buildings
    CASTLE(200, 3, Research.CHIVALRY, Resource.NO_RESOURCE, List.of(WALLS)),
    FORGE(150, 2, Research.METAL_CASTING, Resource.IRON, List.of()),
    GARDEN(120, 2, Research.THEOLOGY, Resource.NO_RESOURCE, List.of()),
    MARKET(120, 0, Research.CURRENCY, Resource.NO_RESOURCE, List.of()),
    MINT(120, 0, Research.CURRENCY, Resource.NO_RESOURCE, List.of()),
    MONASTERY(120, 2, Research.THEOLOGY, Resource.NO_RESOURCE, List.of()),
    UNIVERSITY(200, 3, Research.EDUCATION, Resource.NO_RESOURCE, List.of(LIBRARY)),
    WORKSHOP(100, 2, Research.METAL_CASTING, Resource.NO_RESOURCE, List.of()),

    //Renaissance era buildings
    BANK(220, 0, Research.BANKING, Resource.NO_RESOURCE, List.of(Building.MARKET)),
    MILITARY_ACADEMY(350, 3, Research.MILITARY_SCIENCE, Resource.NO_RESOURCE, List.of(BARRACKS)),
    OPERA_HOUSE(220, 3, Research.ACOUSTICS, Resource.NO_RESOURCE, List.of(TEMPLE, BURIAL_TOMB)),
    MUSEUM(350, 3, Research.ARCHAEOLOGY, Resource.NO_RESOURCE, List.of(OPERA_HOUSE)),
    PUBLIC_SCHOOL(350, 3, Research.SCIENTIFIC_THEORY, Resource.NO_RESOURCE, List.of(UNIVERSITY)),
    SATRAP_COURT(220, 0, Research.BANKING, Resource.NO_RESOURCE, List.of(MARKET)),
    THEATER(300, 5, Research.PRINTING_PRESS, Resource.NO_RESOURCE, List.of(COLOSSEUM)),
    WINDMILL(180, 2, Research.ECONOMICS, Resource.NO_RESOURCE, List.of()),

    //Industrial era buildings
    ARSENAL(350, 3, Research.RAILROAD, Resource.NO_RESOURCE, List.of(MILITARY_ACADEMY)),
    BROADCAST_TOWER(600, 3, Research.RADIO, Resource.NO_RESOURCE, List.of(MUSEUM)),
    FACTORY(300, 3, Research.STEAM_POWER, Resource.COAL, List.of()),
    HOSPITAL(400, 2, Research.BIOLOGY, Resource.NO_RESOURCE, List.of()),
    MILITARY_BASE(450, 4, Research.TELEGRAPH, Resource.NO_RESOURCE, List.of(CASTLE)),
    STOCK_EXCHANGE(650, 0, Research.ELECTRICITY, Resource.NO_RESOURCE, List.of(BANK/*TODO or satrap court*/)),
    ;

    // main.Main Castle ??


    private final int cost;
    private final int maintenance;
    private final Research researchRequired;
    private final Resource resourceNeeded;
    private final ArrayList<Building> buildingsNeeded;

    Building(int cost, int maintenance, Research researchRequired, Resource resourceNeeded, List<Building> buildingsNeeded) {
        this.cost = cost;
        this.maintenance = maintenance;
        this.researchRequired = researchRequired;
        this.resourceNeeded = resourceNeeded;

        this.buildingsNeeded = new ArrayList<>(buildingsNeeded);

    }

    //GETTERS


    public int getCost() {
        return cost;
    }

    public int getMaintenance() {
        return maintenance;
    }

    public Research getResearchRequired() {
        return researchRequired;
    }

    public Resource getResourceNeeded() {
        return resourceNeeded;
    }

    public ArrayList<Building> getBuildingsNeeded() {
        return buildingsNeeded;
    }
}
