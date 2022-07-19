package model.tile;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public enum Terrain{
    DESERT(0, 0, 0, -33, 1
            ,new ImagePattern(new Image("file:src/main/resources/images/map/terrain/desert.png"))),
    GRASS(2, 0, 0, -33, 1
            ,new ImagePattern(new Image("file:src/main/resources/images/map/terrain/grass.png"))),
    HILL(0, 0, 2, 25, 2
            ,new ImagePattern(new Image("file:src/main/resources/images/map/terrain/hill.png"))),
    MOUNTAIN(0, 0, 0, 0, 100
            ,new ImagePattern(new Image("file:src/main/resources/images/map/terrain/mountain.png"))),
    OCEAN(0, 0, 0, 0, 100
            ,new ImagePattern(new Image("file:src/main/resources/images/map/terrain/ocean.png"))),
    FIELD(1, 0, 1, -33, 1
            ,new ImagePattern(new Image("file:src/main/resources/images/map/terrain/field.png"))),
    SNOW(0, 0, 0, -33, 1
            ,new ImagePattern(new Image("file:src/main/resources/images/map/terrain/snow.png"))),
    TUNDRA(1, 0, 0, -33, 1
            ,new ImagePattern(new Image("file:src/main/resources/images/map/terrain/tundra.png"))),
    NO_TERRAIN(0, 0, 0, 0, 0
            ,new ImagePattern(new Image("file:src/main/resources/images/map/terrain/null.png")));
    //Movement cost of 100 indicates that mountains and oceans cannot be passed.

    private final int food;
    private final int gold;
    private final int production;
    private final int combatBoost;
    private final int movementCost;
    private final ImagePattern texture;

    Terrain(int food, int gold, int production, int combatBoost, int movementCost, ImagePattern texture) {
        this.food = food;
        this.gold = gold;
        this.production = production;
        this.combatBoost = combatBoost;
        this.movementCost = movementCost;
        this.texture = texture;
    }

    //GETTERS
    public int getFood() {
        return food;
    }

    public int getGold() {
        return gold;
    }

    public int getProduction() {
        return production;
    }

    public int getCombatBoost() {
        return combatBoost;
    }

    public int getMovementCost() {
        return movementCost;
    }

    public ImagePattern getTexture() {
        return texture;
    }
}
