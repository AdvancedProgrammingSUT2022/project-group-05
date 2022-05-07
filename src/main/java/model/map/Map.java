package model.map;

import model.tile.Tile;
import model.unit.Unit;
import model.unit.civilian.Civilian;
import model.unit.soldier.Soldier;

import java.util.HashMap;

public class Map{
    //TODO... Move the function "movePointsNeededToEnterFrom" (or its equivalent) from Tile to this class.

    private final int sizeOfMap;
    private Tile[][] gameMap;

    //Map singleton pattern
    private static Map instance = null;


    private Map(int sizeOfMap) {
        this.sizeOfMap = sizeOfMap;
        gameMap = new Tile[sizeOfMap][sizeOfMap];
        for (int i = 0; i < sizeOfMap; i++) {
            for (int j = 0; j < sizeOfMap; j++) {
                gameMap[i][j] = new Tile(i, j, sizeOfMap);
            }
        }
    }

    public static void updateInstance(int sizeOfMap) {
        instance = new Map(sizeOfMap);
        MapGeneration.mapCreator();
    }

    public static Map getInstance() {
        return instance;
    }

    //SETTERS


    //GETTERS
    public int getSizeOfMap() {
        return sizeOfMap;
    }


    //null if tile doesnt exits
    public Tile getTileFromMap(int xPlace, int yPlace) {
        if (xPlace < 0 || xPlace >= this.sizeOfMap) return null;
        if (yPlace < 0 || yPlace >= this.sizeOfMap) return null;
        return gameMap[xPlace][yPlace];
    }

    //to find tile from two places , put the third negative
    public Tile getTileFromMap(int xPlace, int yPlace, int zPlace) {
        if (xPlace < 0) {
            xPlace = 2 * (this.getSizeOfMap() - 1) - (yPlace + zPlace);
        } else if (yPlace < 0) {
            yPlace = 2 * (this.getSizeOfMap() - 1) - (xPlace + zPlace);
        }

        return getTileFromMap(xPlace, yPlace);
    }


    //FIND DISTANCE OF TWO TILES : returns -1 if tile not exits
    public int findDistance(Tile start, Tile end) {
        int xDistance = Math.abs(start.getXPlace() - end.getXPlace());
        int yDistance = Math.abs(start.getYPlace() - end.getYPlace());
        int zDistance = Math.abs(start.getZPlace() - end.getZPlace());
        int totalDistance = xDistance + yDistance + zDistance;
        return totalDistance / 2;
    }

    public int findDistance(int startXPlace, int startYPlace, int endXPlace, int endYPlace) {
        Tile start = this.getTileFromMap(startXPlace, startYPlace);
        Tile end = this.getTileFromMap(endXPlace, endYPlace);
        if (start == null || end == null) return -1;
        return findDistance(start, end);
    }


    //FIND MP COST OF TWO TILE
    public int getMPNeededBetweenTiles(Tile first, Tile second) {
        //TODO... calculate mps
        return 0;
    }

    public int getMPNeededBetweenTiles(Tile temp, int neighbour) {
        return getMPNeededBetweenTiles(temp, this.findNeighbors(temp)[neighbour]);
    }


    //RETURNS ARRAY OF TILES NEIGHBORS : up as 0, up right as 1 , ... , up left as 5, null if no neighbor or wrong x,y
    private Tile[] findNeighbors(int xPlace, int yPlace) {
        if (this.getTileFromMap(xPlace, yPlace) == null) return null;
        Tile[] neighbors = new Tile[6];

        neighbors[0] = getTileFromMap(xPlace + 1, yPlace - 1);
        neighbors[1] = getTileFromMap(xPlace + 1, yPlace);
        neighbors[2] = getTileFromMap(xPlace, yPlace + 1);
        neighbors[3] = getTileFromMap(xPlace - 1, yPlace + 1);
        neighbors[4] = getTileFromMap(xPlace - 1, yPlace);
        neighbors[5] = getTileFromMap(xPlace, yPlace - 1);

        return neighbors;
    }

    public Tile[] findNeighbors(Tile center) {
        int xTemp = center.getXPlace();
        int yTemp = center.getYPlace();
        return findNeighbors(xTemp, yTemp);
    }


    //FINDING PATH WITH MINIMUM MP NEEDED : return null if no path exists
    public Path bestPathFinder(Tile start, Tile end, int remainingMP) {
        HashMap<Integer[], Path> pathsMap = new HashMap<>();

        Integer[] endKey = {end.getID(), end.getID()};
        pathsMap.put(endKey, new Path(end));

        return bestPathFindersBacktrack(start, end, remainingMP, pathsMap);
    }

    // can't handle wrong input
    public Path bestPathFinder(int startXPlace, int startYPlace, int endXPlace, int endYPlace, int pathSizeCab) {
        return bestPathFinder(gameMap[startXPlace][startYPlace], gameMap[endXPlace][endYPlace], pathSizeCab);
    }

    private Path bestPathFindersBacktrack(Tile start, Tile end, int remainingMP, HashMap<Integer[], Path> pathsMap) {
        if (remainingMP <= 0) return null;
        Tile[] neighbors = findNeighbors(start);
        Path[] pathsFinded = new Path[6];
        //Finding paths from neighbors to end
        for (int i = 0; i < 6; i++) {
            if (neighbors[i] == null) {
                pathsFinded[i] = null;
                continue;
            }

            Integer[] keySet = {neighbors[i].getID(), end.getID()};
            if (pathsMap.containsKey(keySet)) {
                pathsFinded[i] = pathsMap.get(keySet);
            } else {
                int newRemainingMP = remainingMP - neighbors[i].movePointsNeededToEnterFrom(start);
                pathsFinded[i] = bestPathFindersBacktrack(neighbors[i], end, newRemainingMP, pathsMap);
                pathsMap.put(keySet, pathsFinded[i]);
            }
        }
        //creating paths from start to neighbor and then to end
        for (int i = 0; i < 6; i++) {
            if (pathsFinded[i] == null) continue;
            pathsFinded[i] = new Path(start, pathsFinded[i]);
        }
        //finding the path with minimum mp
        int minMP = 100;
        int minMPIndex = 0;
        for (int i = 0; i < 6; i++) {
            if (pathsFinded[i] == null) continue;
            if (minMP > pathsFinded[i].getMpCost()) {
                minMP = pathsFinded[i].getMpCost();
                minMPIndex = i;
            }
        }
        //returning minimum value
        return pathsFinded[minMPIndex];
    }

    public void moveCivilian(Civilian civilian, Path path) {
        int remainingMPs = path.getFirstTile().getCivilian().getRemainingMovement();
        int currentMPs = remainingMPs - path.getMpCost();
        if (currentMPs < 0) currentMPs = 0;
        path.getFirstTile().getCivilian().setRemainingMovement(currentMPs);

        path.getLastTile().setCivilian(path.getFirstTile().getCivilian());
        path.getFirstTile().removeCivilian();
    }

    public void moveSoldier(Soldier soldier, Path path) {
        int remainingMPs = path.getFirstTile().getSoldier().getRemainingMovement();
        int currentMPs = remainingMPs - path.getMpCost();
        if (currentMPs < 0) currentMPs = 0;
        path.getFirstTile().getSoldier().setRemainingMovement(currentMPs);

        path.getLastTile().setSoldier(path.getFirstTile().getSoldier());
        path.getFirstTile().removeSoldier();
    }

    public void moveSoldierWithoutMP(Soldier soldier, Tile tile) { // this method is used at attack
        tile.setSoldier(soldier.getTile().getSoldier());
        soldier.getTile().removeSoldier();
    }


    //
    public void tilesVisibleRefresh(Tile tile, Unit unitType) {

    }

    //MAKING READY FOR PRINT
    public void printMap() {

    }
}
