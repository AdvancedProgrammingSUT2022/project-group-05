package model.map;

import model.tile.Tile;

public class Map {
    private final int sizeOfMap;
    private Tile[][] gameMap;

    //Map constructor
    public Map (int sizeOfMap) {
        this.sizeOfMap = sizeOfMap;
        gameMap = new Tile[sizeOfMap][sizeOfMap];
        mapCreater(sizeOfMap);
    }

    private void mapCreater (int sizeOfMap) {
        //TODO... creating random tile creater
    }
}
