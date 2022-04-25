package model.map;

import model.tile.Tile;
import model.unit.Unit;

public class Map {
    private final int sizeOfMap;
    private Tile[][] gameMap;

    //Map constructor
    public Map (int sizeOfMap) {
        this.sizeOfMap = sizeOfMap;
        gameMap = new Tile[sizeOfMap][sizeOfMap];
        mapCreator(sizeOfMap);
    }

    //SETTERS


    //GETTERS
    public int getSizeOfMap() {
        return sizeOfMap;
    }


    //null if tile doesnt exits
    public Tile getTileFromMap (int xPlace, int yPlace) {
        if (xPlace < 0 || xPlace >= this.sizeOfMap) return null;
        if (yPlace < 0 || yPlace >= this.sizeOfMap) return null;
        return gameMap[xPlace][yPlace];
    }

    //to find tile from two places , put the third negative
    public Tile getTileFromMap (int xPlace, int yPlace, int zPlace) {
        if (xPlace < 0) {
            xPlace = 2 * (this.getSizeOfMap() - 1) - (yPlace + zPlace);
        }
        else if (yPlace < 0) {
            yPlace = 2 * (this.getSizeOfMap() - 1) - (xPlace + zPlace);
        }

        return getTileFromMap(xPlace, yPlace);
    }



    //ETC

    private void mapCreator (int sizeOfMap) {
        //TODO... creating random tile creator
    }


    //FIND DISTANCE OF TWO TILES : returns -1 if tile not exits
    public int findDistance (Tile start, Tile end) {
        int xDistance = Math.abs(start.getxPlace() - end.getxPlace());
        int yDistance = Math.abs(start.getyPlace() - end.getyPlace());
        int zDistance = Math.abs(start.getzPlace() - end.getzPlace());
        int totalDistance = xDistance + yDistance + zDistance;
        return totalDistance/2;
    }
    public int findDistance (int startXPlace, int startYPlace, int endXPlace, int endYPlace) {
        Tile start = this.getTileFromMap(startXPlace, startYPlace);
        Tile end = this.getTileFromMap(endXPlace, endYPlace);
        if (start == null || end == null) return -1;
        return findDistance(start, end);
    }

    //RETURNS ARRAY OF TILES NEIGHBORS : up as 0, up right as 1 , ... , up left as 5, null if no neighbor or wrong x,y
    private Tile[] findNeighbors (int xPlace, int yPlace) {
        if(this.getTileFromMap(xPlace, yPlace) == null) return null;
        Tile[] neighbors = new Tile[6];

        neighbors[0] = getTileFromMap(xPlace + 1, yPlace - 1);
        neighbors[1] = getTileFromMap(xPlace + 1, yPlace);
        neighbors[2] = getTileFromMap(xPlace, yPlace + 1);
        neighbors[3] = getTileFromMap(xPlace - 1, yPlace + 1);
        neighbors[4] = getTileFromMap(xPlace - 1, yPlace);
        neighbors[5] = getTileFromMap(xPlace, yPlace - 1);

        return neighbors;
    }
    private Tile[] findNeighbors (Tile center) {
        int xTemp = center.getxPlace();
        int yTemp = center.getyPlace();
        return findNeighbors(xTemp, yTemp);
    }

    //FINDING PATH WITH MINIMUM MP NEEDED : return null if no path exists
    public Path bestPathFinder (Tile start, Tile end, int pathLengthCab) {
        if (findDistance(start, end) < pathLengthCab) return null;
        Tile[] neighbors = findNeighbors(start);
        //TODO... back track in neighbor tiles
        return new Path();
    }
    // can't handle wrong input
    public Path bestPathFinder (int startXPlace, int startYPlace, int endXPlace, int endYPlace, int pathSizeCab) {
        return bestPathFinder(gameMap[startXPlace][startYPlace], gameMap[endXPlace][endYPlace], pathSizeCab);
    }


    //
    public void tilesVisibleRefresh (Tile tile, Unit unitType) {

    }

    //MAKING READY FOR PRINT
    public void printMap () {

    }
}
