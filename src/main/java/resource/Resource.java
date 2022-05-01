package resource;

import model.tile.Feature;
import model.tile.Terrain;

import java.util.ArrayList;
import java.util.List;

import static resource.ResourceType.*;

public enum Resource{
    //BONUS RESOURCES
    BANANA(BONUS, 1, 0, 0,
            List.of(), List.of(Feature.JUNGLE)
    ),
    COW(BONUS, 1, 0, 0,
            List.of(Terrain.GRASS), List.of()
    ),
    DEER(BONUS, 1, 0, 0,
            List.of(Terrain.TUNDRA, Terrain.HILL), List.of(Feature.FOREST)
    ),
    SHEEP(BONUS, 1, 0, 0,
            List.of(Terrain.FIELD, Terrain.GRASS, Terrain.DESERT, Terrain.HILL), List.of()
    ),
    WHEAT(BONUS, 1, 0, 0,
            List.of(Terrain.FIELD), List.of(Feature.PLAIN)
    ),

    //LUXURIOUS RESOURCES
    COTTON(LUXURIOUS, 0, 2, 0,
            List.of(Terrain.FIELD, Terrain.GRASS, Terrain.DESERT), List.of()
    ),
    DYE(LUXURIOUS, 0, 2, 0,
            List.of(), List.of(Feature.FOREST, Feature.JUNGLE)
    ),
    FUR(LUXURIOUS, 0, 2, 0,
            List.of(Terrain.TUNDRA), List.of(Feature.FOREST)
    ),
    GEM(LUXURIOUS, 0, 3, 0,
            List.of(Terrain.DESERT, Terrain.FIELD, Terrain.GRASS, Terrain.TUNDRA, Terrain.HILL), List.of(Feature.JUNGLE)
    ),
    GOLD(LUXURIOUS, 0, 2, 0,
            List.of(Terrain.DESERT, Terrain.FIELD, Terrain.GRASS, Terrain.HILL), List.of()
    ),
    INCENSE(LUXURIOUS, 0, 2, 0,
            List.of(Terrain.DESERT, Terrain.FIELD), List.of()
    ),
    IVORY(LUXURIOUS, 0, 2, 0,
            List.of(Terrain.FIELD), List.of()
    ),
    MARBLE(LUXURIOUS, 0, 2, 0,
            List.of(Terrain.DESERT, Terrain.FIELD, Terrain.GRASS, Terrain.TUNDRA, Terrain.HILL), List.of()
    ),
    SILK(LUXURIOUS, 0, 2, 0,
            List.of(), List.of(Feature.FOREST)
    ),
    SILVER(LUXURIOUS, 0, 2, 0,
            List.of(Terrain.DESERT, Terrain.TUNDRA, Terrain.HILL), List.of()
    ),
    SUGAR(LUXURIOUS, 0, 2, 0,
            List.of(), List.of(Feature.PLAIN, Feature.MARSH)
    ),

    //STRATEGIC RESOURCES
    COAL(STRATEGIC, 0, 0, 1,
            List.of(Terrain.FIELD, Terrain.GRASS, Terrain.HILL), List.of()
    ),
    HORSE(STRATEGIC, 0, 0, 1,
            List.of(Terrain.FIELD, Terrain.GRASS, Terrain.TUNDRA), List.of()
    ),
    IRON(STRATEGIC, 0, 0, 1,
            List.of(Terrain.DESERT, Terrain.FIELD, Terrain.GRASS, Terrain.TUNDRA, Terrain.SNOW, Terrain.HILL), List.of()
    ),

    //NONE
    NO_RESOURCE(NONE, 0, 0, 0,
            List.of(), List.of()
    );


    private final ResourceType resourceType;
    //final Technology neededTech;

    private final int food;
    private final int gold;
    private final int production;

    private final ArrayList<Terrain> compatibleTerrains;
    private final ArrayList<Feature> compatibleFeatures;

    Resource(ResourceType resourceType, int food, int gold, int production,
             List<Terrain> compatibleTerrains, List<Feature> compatibleFeatures) {
        this.resourceType = resourceType;
        this.food = food;
        this.gold = gold;
        this.production = production;

        this.compatibleTerrains = new ArrayList<>(compatibleTerrains);
        this.compatibleFeatures = new ArrayList<>(compatibleFeatures);
    }

    //Checks if resource can appear in given terrain
    public boolean matchesTerrain(Terrain terrain) {
        return this.compatibleTerrains.contains(terrain);
    }

    //Checks if resource can appear in given feature
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
}
