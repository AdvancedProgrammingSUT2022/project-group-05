package model.resource;

import model.improvement.Improvement;
import model.research.Research;
import model.tile.Feature;
import model.tile.Terrain;

import java.util.ArrayList;
import java.util.List;

import static model.resource.ResourceType.*;

public enum Resource{
    //BONUS RESOURCES
    BANANA(BONUS, 1, 0, 0,
            List.of(), List.of(Feature.JUNGLE),
            Improvement.PLANTATION, Research.NO_RESEARCH
    ),
    COW(BONUS, 1, 0, 0,
            List.of(Terrain.GRASS), List.of(),
            Improvement.PASTURE, Research.NO_RESEARCH
    ),
    DEER(BONUS, 1, 0, 0,
            List.of(Terrain.TUNDRA, Terrain.HILL), List.of(Feature.FOREST),
            Improvement.CAMP, Research.NO_RESEARCH
    ),
    SHEEP(BONUS, 1, 0, 0,
            List.of(Terrain.FIELD, Terrain.GRASS, Terrain.DESERT, Terrain.HILL), List.of(),
            Improvement.PASTURE, Research.NO_RESEARCH
    ),
    WHEAT(BONUS, 1, 0, 0,
            List.of(Terrain.FIELD), List.of(Feature.PLAIN),
            Improvement.FARM, Research.NO_RESEARCH
    ),

    //LUXURIOUS RESOURCES
    COTTON(LUXURIOUS, 0, 2, 0,
            List.of(Terrain.FIELD, Terrain.GRASS, Terrain.DESERT), List.of(),
            Improvement.PLANTATION, Research.NO_RESEARCH
    ),
    DYE(LUXURIOUS, 0, 2, 0,
            List.of(), List.of(Feature.FOREST, Feature.JUNGLE),
            Improvement.PLANTATION, Research.NO_RESEARCH
    ),
    FUR(LUXURIOUS, 0, 2, 0,
            List.of(Terrain.TUNDRA), List.of(Feature.FOREST),
            Improvement.CAMP, Research.NO_RESEARCH
    ),
    GEM(LUXURIOUS, 0, 3, 0,
            List.of(Terrain.DESERT, Terrain.FIELD, Terrain.GRASS, Terrain.TUNDRA, Terrain.HILL), List.of(Feature.JUNGLE),
            Improvement.MINE, Research.NO_RESEARCH
    ),
    GOLD(LUXURIOUS, 0, 2, 0,
            List.of(Terrain.DESERT, Terrain.FIELD, Terrain.GRASS, Terrain.HILL), List.of(),
            Improvement.MINE, Research.NO_RESEARCH
    ),
    INCENSE(LUXURIOUS, 0, 2, 0,
            List.of(Terrain.DESERT, Terrain.FIELD), List.of(),
            Improvement.PLANTATION, Research.NO_RESEARCH
    ),
    IVORY(LUXURIOUS, 0, 2, 0,
            List.of(Terrain.FIELD), List.of(),
            Improvement.CAMP, Research.NO_RESEARCH
    ),
    MARBLE(LUXURIOUS, 0, 2, 0,
            List.of(Terrain.DESERT, Terrain.FIELD, Terrain.GRASS, Terrain.TUNDRA, Terrain.HILL), List.of(),
            Improvement.QUARRY, Research.NO_RESEARCH
    ),
    SILK(LUXURIOUS, 0, 2, 0,
            List.of(), List.of(Feature.FOREST),
            Improvement.PLANTATION, Research.NO_RESEARCH
    ),
    SILVER(LUXURIOUS, 0, 2, 0,
            List.of(Terrain.DESERT, Terrain.TUNDRA, Terrain.HILL), List.of(),
            Improvement.MINE, Research.NO_RESEARCH
    ),
    SUGAR(LUXURIOUS, 0, 2, 0,
            List.of(), List.of(Feature.PLAIN, Feature.MARSH),
            Improvement.PLANTATION, Research.NO_RESEARCH
    ),

    //STRATEGIC RESOURCES
    COAL(STRATEGIC, 0, 0, 1,
            List.of(Terrain.FIELD, Terrain.GRASS, Terrain.HILL), List.of(),
            Improvement.MINE, Research.SCIENTIFIC_THEORY
    ),
    HORSE(STRATEGIC, 0, 0, 1,
            List.of(Terrain.FIELD, Terrain.GRASS, Terrain.TUNDRA), List.of(),
            Improvement.PASTURE, Research.ANIMAL_HUSBANDRY
    ),
    IRON(STRATEGIC, 0, 0, 1,
            List.of(Terrain.DESERT, Terrain.FIELD, Terrain.GRASS, Terrain.TUNDRA, Terrain.SNOW, Terrain.HILL), List.of(),
            Improvement.MINE, Research.IRON_WORKING
    ),

    //NONE
    NO_RESOURCE(NONE, 0, 0, 0,
            List.of(), List.of(),
            Improvement.NO_IMPROVEMENT, Research.NO_RESEARCH
    );


    private final ResourceType resourceType;
    //final Technology neededTech;

    private final int food;
    private final int gold;
    private final int production;

    private final ArrayList<Terrain> compatibleTerrains;
    private final ArrayList<Feature> compatibleFeatures;

    private final Improvement neededImprovement;
    private final Research neededResearch;

    Resource(ResourceType resourceType, int food, int gold, int production,
             List<Terrain> compatibleTerrains, List<Feature> compatibleFeatures,
             Improvement neededImprovement, Research neededResearch) {
        this.resourceType = resourceType;
        this.food = food;
        this.gold = gold;
        this.production = production;

        this.compatibleTerrains = new ArrayList<>(compatibleTerrains);
        this.compatibleFeatures = new ArrayList<>(compatibleFeatures);

        this.neededImprovement = neededImprovement;
        this.neededResearch = neededResearch;
    }

    //Checks if model.resource can appear in given terrain
    public boolean matchesTerrain(Terrain terrain) {
        return this.compatibleTerrains.contains(terrain);
    }

    //Checks if model.resource can appear in given feature
    public boolean matchesFeature(Feature feature) {
        return this.compatibleFeatures.contains(feature);
    }

    //GETTERS
    public ResourceType getResourceType() {
        return resourceType;
    }

    public int getFood() {
        return food;
    }

    public int getGold() {
        return gold;
    }

    public int getProduction() {
        return production;
    }

    public Improvement getNeededImprovement() {
        return this.neededImprovement;
    }

    public Research getNeededResearch() {
        return this.neededResearch;
    }
}
