package model.improvement;

import model.research.Research;
import model.tile.Feature;
import model.tile.Terrain;

import java.util.ArrayList;
import java.util.List;

import static model.tile.Feature.*;
import static model.tile.Terrain.*;

public enum Improvement{
    CAMP(0, 0, 0, 6, Research.TRAPPING,
            List.of(TUNDRA, FIELD, HILL),
            List.of(FOREST)
    ),
    FARM(1, 0, 0, 6, Research.AGRICULTURE,
            List.of(FIELD, GRASS, DESERT),
            List.of()
    ),
    LUMBER_MILL(0, 0, 1, 6, Research.CONSTRUCTION,
            List.of(),
            List.of(FOREST)
    ),
    MINE(0, 0, 1, 6, Research.MINING,
            List.of(DESERT, FIELD, GRASS, TUNDRA, SNOW, HILL),
            List.of(FOREST, JUNGLE, MARSH)
    ),
    PASTURE(0, 0, 0, 7, Research.ANIMAL_HUSBANDRY,
            List.of(FIELD, DESERT, GRASS, TUNDRA, HILL),
            List.of()
    ),
    PLANTATION(0, 0, 0, 5, Research.CALENDAR,
            List.of(FIELD, DESERT, GRASS),
            List.of(FOREST, JUNGLE, MARSH, PLAIN)
    ),
    QUARRY(0, 0, 0, 7, Research.MASONRY,
            List.of(FIELD, DESERT, GRASS, TUNDRA, HILL),
            List.of()
    ),
    TRADING_POST(0, 1, 0, 8, Research.TRAPPING,
            List.of(FIELD, DESERT, GRASS, TUNDRA),
            List.of()
    ),
    NO_IMPROVEMENT(0, 0, 0, 0, Research.AGRICULTURE,
            List.of(),
            List.of()
    );

    private final int food;
    private final int gold;
    private final int production;
    private final int constructionTime;

    private final Research neededResearch;
    private final ArrayList<Terrain> compatibleTerrains;
    private final ArrayList<Feature> compatibleFeatures;

    Improvement(int food, int gold, int production, int constructionTime, Research neededResearch,
                List<Terrain> compatibleTerrains, List<Feature> compatibleFeatures) {
        this.food = food;
        this.gold = gold;
        this.production = production;
        this.constructionTime = constructionTime;

        this.neededResearch = neededResearch;

        this.compatibleTerrains = new ArrayList<>(compatibleTerrains);
        this.compatibleFeatures = new ArrayList<>(compatibleFeatures);
    }

    public boolean matchesTerrain(Terrain terrain) {
        return this.compatibleTerrains.contains(terrain);
    }

    public boolean matchesFeature(Feature feature) {
        return this.compatibleFeatures.contains(feature);
    }

    //GETTERS
    public int getFood() {
        return this.food;
    }

    public int getGold() {
        return this.gold;
    }

    public int getProduction() {
        return this.production;
    }

    public int getConstructionTime() {
        return this.constructionTime;
    }

    public Research getNeededResearch() {
        return this.neededResearch;
    }

    public ArrayList<Terrain> getCompatibleTerrains() {
        return this.compatibleTerrains;
    }

    public ArrayList<Feature> getCompatibleFeatures() {
        return this.compatibleFeatures;
    }
}
