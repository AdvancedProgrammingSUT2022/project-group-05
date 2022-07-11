package model.map;

import model.game.Civilization;
import model.resource.Resource;
import model.tile.Feature;
import model.tile.Terrain;
import model.tile.Tile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;

public class TestMap {
    @Mock
    Civilization civilization;

    @Mock
    FogOfWarStates[][] fogOfWarStates;

    @BeforeEach
    public void setup () {
        Map.updateInstance(5);
        //map when and returns
        //civilization when and returns
        //tile when and returns
        //fogOfWar
    }

    @Test
    public void getTileFromMapNULL () {
        Tile tempTile = Map.getInstance().getTileFromMap(0, 5);
        Assertions.assertNull(tempTile);
    }

    @Test
    public void getTileFromMapNULL2 () {
        Tile tempTile = Map.getInstance().getTileFromMap(-5, 4, 5);
        Assertions.assertNull(tempTile);
    }

    @Test
    public void findDistanceTest1 () {
        int dis = Map.getInstance().findDistance(0, 0 , 2, 2);
        Assertions.assertEquals(4, dis);
    }

    @Test
    public void findDistanceTest2 () {
        int dis = Map.getInstance().findDistance(0, 2 , 2, 0);
        Assertions.assertEquals(2, dis);
    }

    @Test
    public void WhichNeighborTest1 () {
        Map map = Map.getInstance();
        int which = map.whichNeighbor(map.getTileFromMap(0, 0), map.getTileFromMap(0, 1));
        Assertions.assertEquals(2, which);
    }

    @Test
    public void WhichNeighborTest2 () {
        Map map = Map.getInstance();
        int which = map.whichNeighbor(map.getTileFromMap(1, 1), map.getTileFromMap(0, 1));
        Assertions.assertEquals(4, which);
    }

    @Test
    public void testSave() {
        Map.getInstance().save();
    }

    @Test
    public void testLoad() {
        Map.load();
        System.out.println(Map.getInstance().getSizeOfMap());
    }
}
