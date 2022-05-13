package model.map;

import model.resource.Resource;
import model.tile.Feature;
import model.tile.Terrain;
import model.tile.Tile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

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


    static void mapCreator() {
        addTerrains();
        addRiver();
        addFeatures();
        addResource();
    }

    //ADDING TERRAINS TODO
    private static void addTerrains() {
        Map map = Map.getInstance();
        int sizeOfMap = map.getSizeOfMap();
    }


    //ADDING RIVERS
    private static void addRiver () {
        Map map = Map.getInstance();
        int sizeOfMap = map.getSizeOfMap();
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());

        for (int i = 0; i < sizeOfMap; i++) {
            for (int j = 0; j < sizeOfMap; j++) {
                Tile temp = map.getTileFromMap(i, j);
                if(temp.getTerrain().equals(Terrain.OCEAN)) continue;

                Tile[] tempNeighbors = map.findNeighbors(temp);

                for (int s = 0; s < 3; s++) {
                    if (tempNeighbors[s] == null) continue;
                    if (tempNeighbors[s].getTerrain().equals(Terrain.OCEAN)) continue;
                    if(Math.abs(rand.nextInt())%100 < RIVER_PROB) {
                        temp.addRiver(s);
                        tempNeighbors[s].addRiver((s+3)%6);
                    }
                }
            }
        }
    }


    //ADDING FEATURES
    private static void addFeatures () {
        addFeatures(Feature.PLAIN, FEATURE_PLAIN_PROB);
        addFeatures(Feature.FOREST, FEATURE_FOREST_PROB);
        addFeatures(Feature.ICE, FEATURE_ICE_PROB);
        addFeatures(Feature.JUNGLE, FEATURE_JUNGLE_PROB);
        addFeatures(Feature.MARSH, FEATURE_MARSH_PROB);
        addFeatures(Feature.OASIS, FEATURE_OASIS_PROB);
    }
    private static void addFeatures (Feature feature, int PROB) {
        Map map = Map.getInstance();
        int sizeOfMap = map.getSizeOfMap();

        ArrayList<Tile> tiles = new ArrayList<>();

        for (int i = 0; i < sizeOfMap; i++) {
            for (int j = 0; j < sizeOfMap; j++) {
                Tile temp = map.getTileFromMap(i, j);
                if (feature.matchesTerrain(temp.getTerrain()) && !temp.hasFeature() &&
                        !(feature.equals(Feature.PLAIN) && !temp.hasRiver()))
                    tiles.add(temp);
            }
        }
        Collections.shuffle(tiles);
        int count = (tiles.size() * PROB) / 100;
        for (int i = 0; i < count; i++) tiles.get(i).setFeature(feature);
    }


    //ADDING RESOURCE
    private static void addResource () {
        addResource(Resource.BANANA, RESOURCE_BANANA_PROB);
        addResource(Resource.COW, RESOURCE_COW_PROB);
        addResource(Resource.DEER, RESOURCE_DEER_PROB);
        addResource(Resource.SHEEP, RESOURCE_SHEEP_PROB);
        addResource(Resource.WHEAT, RESOURCE_WHEAT_PROB);
        addResource(Resource.COTTON, RESOURCE_COTTON_PROB);
        addResource(Resource.DYE, RESOURCE_DYE_PROB);
        addResource(Resource.FUR, RESOURCE_FUR_PROB);
        addResource(Resource.GEM, RESOURCE_GEM_PROB);
        addResource(Resource.GOLD, RESOURCE_GOLD_PROB);
        addResource(Resource.INCENSE, RESOURCE_INCENSE_PROB);
        addResource(Resource.IVORY, RESOURCE_IVORY_PROB);
        addResource(Resource.MARBLE, RESOURCE_MARBLE_PROB);
        addResource(Resource.SILK, RESOURCE_SILK_PROB);
        addResource(Resource.SILVER, RESOURCE_SILVER_PROB);
        addResource(Resource.SUGAR, RESOURCE_SUGAR_PROB);
        addResource(Resource.COAL, RESOURCE_COAL_PROB);
        addResource(Resource.HORSE, RESOURCE_HORSE_PROB);
        addResource(Resource.IRON, RESOURCE_IRON_PROB);
    }
    private static void addResource (Resource resource, int PROB) {
        Map map = Map.getInstance();
        int sizeOfMap = map.getSizeOfMap();

        ArrayList<Tile> tiles = new ArrayList<>();

        for (int i = 0; i < sizeOfMap; i++) {
            for (int j = 0; j < sizeOfMap; j++) {
                Tile temp = map.getTileFromMap(i, j);
                if (( resource.matchesTerrain(temp.getTerrain()) || resource.matchesFeature(temp.getFeature()) )
                        && !temp.hasResource())
                    tiles.add(temp);
            }
        }
        Collections.shuffle(tiles);
        int count = (tiles.size() * PROB) / 100;
        for (int i = 0; i < count; i++) tiles.get(i).setResource(resource);
    }
}
