package model.map;

import model.game.Civilization;
import model.unit.Unit;

public class FogOfWar{

    public FogOfWar () {

    }

    //UPDATES FOG OF WAR
    public static void updateFogOfWar (Civilization civilization) {
        if (civilization.getFogOfWar() == null) {
            FogOfWar.fogOfWarInit(civilization);
            return;
        }

        FogOfWarStates[][] fogOfWar = civilization.getFogOfWar();

        setVisToRev(fogOfWar);
        checkCities(civilization, fogOfWar);

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
    private static void checkCities (Civilization civilization, FogOfWarStates[][] fogOfWar) {
        int mapSize = Map.getInstance().getSizeOfMap();
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                if (Map.getInstance().getTileFromMap(i, j).getCivilization().equals(civilization))
                    fogOfWar[i][j] = FogOfWarStates.VISIBLE;
            }
        }
    }

    private static void checkUnits (Civilization civilization, FogOfWarStates[][] fogOfWar) {
        int mapSize = Map.getInstance().getSizeOfMap();
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                if (Map.getInstance().getTileFromMap(i, j).getCivilian().equals(civilization))
                    fogOfWar[i][j] = FogOfWarStates.VISIBLE;
                Unit unit = new Unit();
                unit.getT
                }
            }
        }
    }
}
