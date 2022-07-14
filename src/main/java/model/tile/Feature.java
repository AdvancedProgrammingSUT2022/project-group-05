package model.tile;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.util.ArrayList;
import java.util.List;

public enum Feature{
    PLAIN(2, 0, 0, -33, 1,
            List.of(Terrain.DESERT)//near river//
            ,new ImagePattern(new Image("file:src/main/resources/images/map/features/plain.png"))
    ),
    FOREST(1, 0, 1, 25, 2,
            List.of(Terrain.FIELD, Terrain.GRASS, Terrain.HILL, Terrain.TUNDRA)
            ,new ImagePattern(new Image("file:src/main/resources/images/map/features/forest.png"))
    ),
    ICE(0, 0, 0, 0, 100,
            List.of(Terrain.OCEAN)
            ,new ImagePattern(new Image("file:src/main/resources/images/map/features/ice.png"))
    ),
    JUNGLE(1, 0, -1, 25, 2,
            List.of(Terrain.FIELD, Terrain.HILL)
            ,new ImagePattern(new Image("file:src/main/resources/images/map/features/jungle.png"))
    ),
    MARSH(-1, 0, 0, -33, 2,
            List.of(Terrain.GRASS)
            ,new ImagePattern(new Image("file:src/main/resources/images/map/features/marsh.png"))
    ),
    OASIS(3, 1, 0, -33, 1,
            List.of(Terrain.DESERT)
            ,new ImagePattern(new Image("file:src/main/resources/images/map/features/oasis.png"))
    ),
    NO_FEATURE(0, 0, 0, 0, 0,
            List.of()
            ,new ImagePattern(new Image("file:src/main/resources/images/map/features/null.png"))
    );

    private final int food;
    private final int gold;
    private final int production;
    private final int combatBoost;
    private final int movementCost;

    private final ArrayList<Terrain> compatibleTerrains;

    private final ImagePattern texture;

    Feature(int food, int gold, int production, int combatBoost, int movementCost,
            List<Terrain> compatibleTerrains, ImagePattern texture) {
        this.food = food;
        this.gold = gold;
        this.production = production;
        this.combatBoost = combatBoost;
        this.movementCost = movementCost;

        this.compatibleTerrains = new ArrayList<>(compatibleTerrains);

        this.texture = texture;
    }

    public boolean matchesTerrain(Terrain terrain) {
        return this.compatibleTerrains.contains(terrain);
    }

    //GETTERS
    public int getFood() {
        return this.food;
    }

    public int getGold() {
        return this.gold;
    }

    public int getProduction() {
        return this.production;
    }

    public int getCombatBoost() {
        return this.combatBoost;
    }

    public int getMovementCost() {
        return this.movementCost;
    }

    public ImagePattern getTexture() {
        return texture;
    }

    public ArrayList<Terrain> getCompatibleTerrains() {
        return this.compatibleTerrains;
    }
}
