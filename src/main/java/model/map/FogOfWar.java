package model.map;

import model.game.Civilization;
import model.tile.Feature;
import model.tile.Terrain;
import model.tile.Tile;
import model.unit.Unit;

public class FogOfWar{
    //UPDATES FOG OF WAR
    public static void updateFogOfWar (Civilization civilization) {
        if (civilization.getFogOfWar() == null) {
            FogOfWar.fogOfWarInit(civilization);
            return;
        }

        FogOfWarStates[][] fogOfWar = civilization.getFogOfWar();

        setVisToRev(fogOfWar);
        checkTerritory(civilization, fogOfWar);
        checkUnits(civilization, fogOfWar);
    }

    //Initializing fog of war for civilization
    private static void fogOfWarInit (Civilization civilization) {
        int mapSize = Map.getInstance().getSizeOfMap();
        FogOfWarStates[][] fogOfWar = new FogOfWarStates[mapSize][mapSize];
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                fogOfWar[i][j] = FogOfWarStates.FOG_OF_WAR;
            }
        }
        civilization.setFogOfWar(fogOfWar);
    }

    //setting back visible tiles to revealed
    private static void setVisToRev (FogOfWarStates[][] fogOfWar) {
        int mapSize = Map.getInstance().getSizeOfMap();
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                if (fogOfWar[i][j] == FogOfWarStates.VISIBLE)
                    fogOfWar[i][j] = FogOfWarStates.REVEALED;
            }
        }
    }

    //Update Tiles civ own
    private static void checkTerritory(Civilization civilization, FogOfWarStates[][] fogOfWar) {
        int mapSize = Map.getInstance().getSizeOfMap();
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                Tile tile =  Map.getInstance().getTileFromMap(i, j);
                if (tile.getCivilization() == null) continue;
                if (tile.getCivilization().equals(civilization)) {
                    fogOfWar[i][j] = FogOfWarStates.VISIBLE;
                    setNeighborsState(tile, fogOfWar, FogOfWarStates.VISIBLE);
                }
            }
        }
    }

    //UPDATE UNITS
    private static void checkUnits (Civilization civilization, FogOfWarStates[][] fogOfWar) {
        int mapSize = Map.getInstance().getSizeOfMap();
        for (Unit unit: civilization.getUnits()) {
            Tile place = unit.getTile();
            fogOfWar[place.getXPlace()][place.getYPlace()] = FogOfWarStates.VISIBLE;

            boolean isHill = place.getTerrain().equals(Terrain.HILL);

            FogOfWar.setNeighborsState(place, fogOfWar, FogOfWarStates.VISIBLE);
            Tile[] neighbors = Map.getInstance().findNeighbors(place);

            for (int i = 0; i < 6; i++) {
                if (neighbors[i] == null) continue;
                //IF MOUNTAIN
                if (neighbors[i].getTerrain().equals(Terrain.MOUNTAIN)) continue;

                //IF HILL
                if (!isHill && neighbors[i].getTerrain().equals(Terrain.HILL)) continue;


                //IF JUNGLE AND FOREST
                if (!isHill && neighbors[i].getFeature().equals(Feature.JUNGLE)) continue;
                if (!isHill && neighbors[i].getFeature().equals(Feature.FOREST)) continue;

                FogOfWar.setNeighborsState(neighbors[i], fogOfWar, FogOfWarStates.VISIBLE);
            }
        }
    }

    //set ALL neighbors to input state
    private static void setNeighborsState (Tile temp, FogOfWarStates[][] fogOfWar, FogOfWarStates state) {
        setNeighborsState(temp.getXPlace(), temp.getYPlace(), fogOfWar, state);
    }
    private static void setNeighborsState (int xPlace, int yPlace, FogOfWarStates[][] fogOfWar, FogOfWarStates state) {
        int mapSize = Map.getInstance().getSizeOfMap();

        boolean xUp = xPlace < mapSize - 1;
        boolean xDown = xPlace > 0;

        boolean yUp = yPlace < mapSize - 1;
        boolean yDown = yPlace > 0;

        if (xUp)
            fogOfWar[xPlace + 1][yPlace] = state;
        if (xDown)
            fogOfWar[xPlace - 1][yPlace] = state;
        if (yUp)
            fogOfWar[xPlace][yPlace + 1] = state;
        if (yDown)
            fogOfWar[xPlace][yPlace - 1] = state;

        if (xUp && yDown)
            fogOfWar[xPlace +1][yPlace -1] = state;
        if (xDown && yUp)
            fogOfWar[xPlace -1][yPlace +1] = state;
    }

    //CHEAT CODE
    public static void fogOfWarRevealAll(Civilization civilization) {
        int mapSize = Map.getInstance().getSizeOfMap();
        FogOfWarStates[][] fogOfWar = new FogOfWarStates[mapSize][mapSize];
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                fogOfWar[i][j] = FogOfWarStates.VISIBLE;
            }
        }
        civilization.setFogOfWar(fogOfWar);
    }
}
