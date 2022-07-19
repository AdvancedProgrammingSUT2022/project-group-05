package model.map;

import model.game.Civilization;
import model.resource.Resource;
import model.tile.Feature;
import model.tile.Terrain;
import model.tile.Tile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TestColorChar {
    ColorChar colorChar = new ColorChar();
    ColorChar[][] colorChars = new ColorChar[2][5];

    @Mock
    Map map;

    @Mock
    Civilization civilization;

    @Mock
    FogOfWarStates[][] fogOfWarStates;

    @Mock
    Tile tile;

    @BeforeEach
    public void setup () {
        Map.updateInstance(map);
        //map when and returns
        Mockito.when(map.getSizeOfMap()).thenReturn(1);
        Mockito.when(map.getTileFromMap(0, 0)).thenReturn(tile);
        //civilization when and returns
        Mockito.when(civilization.getFogOfWar()).thenReturn(fogOfWarStates);
        //tile when and returns
        Mockito.when(tile.getTerrain()).thenReturn(Terrain.FIELD);
        Mockito.when(tile.getFeature()).thenReturn(Feature.FOREST);
        Mockito.when(tile.getResource()).thenReturn(Resource.WHEAT);
        Mockito.when(tile.hasRiver(0)).thenReturn(true);
        Mockito.when(tile.hasRiver(1)).thenReturn(true);
        Mockito.when(tile.hasRiver(2)).thenReturn(true);
        Mockito.when(tile.hasRiver(3)).thenReturn(true);
        Mockito.when(tile.hasRiver(4)).thenReturn(true);
        Mockito.when(tile.hasRiver(5)).thenReturn(true);
        Mockito.when(tile.getXPlace()).thenReturn(0);
        Mockito.when(tile.getYPlace()).thenReturn(0);
        //fogOfWar
        Mockito.when(fogOfWarStates[0][0]).thenReturn(FogOfWarStates.VISIBLE);
    }
    @Test
    public void testOne () {
        ColorChar[][] output = ColorChar.mapConsoleOutputCreator(civilization);
        Assertions.assertEquals(output[0][8].getBackgroundColor(), "\u001B[43m");
    }
}
