package model.map;

import model.tile.Feature;
import model.tile.Tile;

import java.util.ArrayList;

public class MapGeneration {
    //MAP CREATOR FUNCTIONS
    private static final int TERRAIN_DESERT_PROB = 5;
    private static final int TERRAIN_GRASS_PROB = 5;
    private static final int TERRAIN_HILL_PROB = 5;
    private static final int TERRAIN_MOUNTAIN_PROB = 5;
    private static final int TERRAIN_OCEAN_PROB = 5;
    private static final int TERRAIN_FIELD_PROB = 5;
    private static final int TERRAIN_SNOW_PROB = 5;
    private static final int RESOURCE_TUNDRA_PROB = 5;

    private static final int RIVER_PROB = 5;

    private static final int FEATURE_PLAIN_PROB = 5;
    private static final int FEATURE_FOREST_PROB = 5;
    private static final int FEATURE_ICE_PROB = 5;
    private static final int FEATURE_JUNGLE_PROB = 5;
    private static final int FEATURE_MARSH_PROB = 5;
    private static final int FEATURE_OASIS_PROB = 5;
    private static final int FEATURE_NO_PROB = 5;

    private static final int RESOURCE_BANANA_PROB = 5;
    private static final int RESOURCE_COW_PROB = 5;
    private static final int RESOURCE_DEER_PROB = 5;
    private static final int RESOURCE_SHEEP_PROB = 5;
    private static final int RESOURCE_WHEAT_PROB = 5;
    private static final int RESOURCE_COTTON_PROB = 5;
    private static final int RESOURCE_DYE_PROB = 5;
    private static final int RESOURCE_FUR_PROB = 5;
    private static final int RESOURCE_GEM_PROB = 5;
    private static final int RESOURCE_GOLD_PROB = 5;
    private static final int RESOURCE_INCENSE_PROB = 5;
    private static final int RESOURCE_IVORY_PROB = 5;
    private static final int RESOURCE_MARBLE_PROB = 5;
    private static final int RESOURCE_SILK_PROB = 5;
    private static final int RESOURCE_SILVER_PROB = 5;
    private static final int RESOURCE_SUGAR_PROB = 5;
    private static final int RESOURCE_COAL_PROB = 5;
    private static final int RESOURCE_HORSE_PROB = 5;
    private static final int RESOURCE_IRON_PROB = 5;
    private static final int RESOURCE_NONE_PROB = 5;

    static void mapCreator() {
        addTerrain();
        addRiver();
        addFeatures();
        addResource();
    }

    private static void addTerrain () {
        Map map = Map.getInstance();
        int sizeOfMap = map.getSizeOfMap();
    }

    private static void addRiver () {
        Map map = Map.getInstance();
        int sizeOfMap = map.getSizeOfMap();


    }

    private static void addFeatures () {
        Map map = Map.getInstance();
        int sizeOfMap = map.getSizeOfMap();
    }
    private static void addFeatures (Feature feature) {
        Map map = Map.getInstance();
        int sizeOfMap = map.getSizeOfMap();

        ArrayList<Tile> tiles = new ArrayList<>();

        for (int i = 0; i < sizeOfMap; i++) {
            for (int j = 0; j < sizeOfMap; j++) {
                Tile temp = map.getTileFromMap(i, j);
                if (feature.matchesTerrain(temp.getTerrain() && temp.has)
            }
        }
    }

    private static void addResource () {
        Map map = Map.getInstance();
        int sizeOfMap = map.getSizeOfMap();
    }
}
