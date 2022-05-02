package model.game;

public enum TileStatus {
    INTACT, // No citizen worked on it before
    WORKING, // A citizen is working on it
    DONE // There is no work left in this tile
}
