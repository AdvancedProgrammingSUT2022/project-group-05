package model.tile;

import java.util.ArrayList;
import java.util.List;

public enum Feature{
    PLAIN(2, 0, 0, -33, 1,
            List.of(Terrain.DESERT, Terrain.FIELD, Terrain.GRASS, Terrain.SNOW, Terrain.TUNDRA)
    ),
    FOREST(1, 0, 1, 25, 2,
            List.of(Terrain.DESERT, Terrain.FIELD, Terrain.GRASS, Terrain.SNOW, Terrain.TUNDRA)
    ),
    ICE(0, 0, 0, 0, 100,
            List.of(Terrain.OCEAN)
    ),
    JUNGLE(1, 0, -1, 25, 2,
            List.of(Terrain.FIELD, Terrain.GRASS, Terrain.SNOW, Terrain.TUNDRA)
    ),
    MARSH(-1, 0, 0, -33, 2,
            List.of(Terrain.FIELD, Terrain.GRASS, Terrain.TUNDRA)
    ),
    OASIS(3, 1, 0, -33, 1,
            List.of(Terrain.DESERT)
    ),
    NO_FEATURE(0, 0, 0, 0, 0,
            List.of()
    );

    final int food;
    final int gold;
    final int production;
    final int combatBoost;
    final int movementCost;

    final ArrayList<Terrain> compatibleTerrains;

    Feature(int food, int gold, int production, int combatBoost, int movementCost,
            List<Terrain> compatibleTerrains) {
        this.food = food;
        this.gold = gold;
        this.production = production;
        this.combatBoost = combatBoost;
        this.movementCost = movementCost;

        this.compatibleTerrains = new ArrayList<>(compatibleTerrains);
    }


}
