package model.map;

import model.tile.Tile;

import java.util.ArrayList;

public class Path{
    private ArrayList<Tile> pathTiles;

    private int mpCost;

    public Path() {
        this.pathTiles = new ArrayList<>();
    }

    public Path(Tile tile) {
        this.pathTiles = new ArrayList<>();
        this.pathTiles.add(tile);
        this.mpCost = 0;
    }

    public Path(Path pathTemp, Tile lastTile) {
        this.pathTiles = new ArrayList<>(pathTemp.getPathTiles());
        this.mpCost = pathTemp.getMpCost();
        this.mpCost += Map.getInstance().getMPNeededBetweenTiles(this.getLastTile(), lastTile);
        this.pathTiles.add(lastTile);
    }

    public Path(Tile firstTile, Path pathTemp) {
        this.pathTiles = new ArrayList<>(pathTemp.getPathTiles());
        this.mpCost = pathTemp.getMpCost();
        this.mpCost += Map.getInstance().getMPNeededBetweenTiles(firstTile, this.getFirstTile());
        this.pathTiles.add(0, firstTile);
    }

    public Path(Path startPath, Path endPath) {
        this.pathTiles = new ArrayList<>(startPath.getPathTiles());
        this.pathTiles.addAll(endPath.getPathTiles());
        this.mpCost = startPath.getMpCost() + endPath.getMpCost();
        this.mpCost += Map.getInstance().getMPNeededBetweenTiles(startPath.getLastTile(), endPath.getFirstTile());
    }

    //GETTERS
    public int getMpCost() {
        return mpCost;
    }

    public ArrayList<Tile> getPathTiles() {
        return pathTiles;
    }

    //GETTING START AND END OF PATH : null if no tile in map
    public Tile getFirstTile() {
        if (this.getPathLength() == 0) return null;
        return this.pathTiles.get(0);
    }

    public Tile getLastTile() {
        if (this.getPathLength() == 0) return null;
        return this.pathTiles.get(this.getPathLength() - 1);
    }


    //OTHER FUNCTIONS
    public int getPathLength() {
        return this.pathTiles.size();
    }
}
