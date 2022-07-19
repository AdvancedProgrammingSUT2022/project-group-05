package model.game;


import model.User;
import model.map.Map;
import model.research.Research;
import model.tile.Tile;
import model.unit.soldier.Soldier;
import model.unit.soldier.melee.Warrior;
import model.unit.soldier.ranged.siege.Catapult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class TestCivilization {

    Civilization civilization;

    @Mock
    User player;

    @Mock
    City city;

    @Mock
    Soldier soldier;

    @Mock
    Tile tile;

    @Mock
    Research research;

    @Mock
    Civilization civilization2;

    @Mock
    User user2;


    @BeforeEach
    public void setup() {
        Map.updateInstance(10);
        civilization = new Civilization(player, 10);
    }

    @Test
    public void testHasCity() {
        civilization.addCity(city);
        boolean result = civilization.hasCity(city);
        Assertions.assertTrue(result);
    }

    @Test
    public void testAddUnit() {
        Mockito.when(soldier.getTile()).thenReturn(tile);
        civilization.addUnit(soldier);
        Mockito.verify(tile).setSoldier(soldier);
    }

    @Test
    public void testRemoveUnit() {
        Mockito.when(soldier.getTile()).thenReturn(tile);
        civilization.addUnit(soldier);
        civilization.removeUnit(soldier);
        boolean result = civilization.getUnits().contains(soldier);
        Assertions.assertFalse(result);
        Mockito.verify(tile).removeSoldier();
    }

    @Test
    public void testStartResearch() {
        civilization.startResearch(research);
        Research result = civilization.getResearchTree().getCurrentResearch();
        Assertions.assertEquals(research, result);
    }

    @Test
    public void testSetGold() {
        civilization.setGold(100);
        int result = civilization.getGold();
        Assertions.assertEquals(100, result);
    }

    @Test
    public void testSetProduction() {
        civilization.setProduction(123);
        int result = civilization.getProduction();
        Assertions.assertEquals(123, result);
    }

    @Test
    public void testSetHappiness() {
        civilization.setHappiness(123);
        int result = civilization.getHappiness();
        Assertions.assertEquals(123, result);
    }

    @Test
    public void testSetResearchPoint() {
        civilization.setResearchPoint(123);
        int result = civilization.getResearchPoint();
        Assertions.assertEquals(123, result);
    }

    @Test
    public void testRemoveCity() {
        civilization.addCity(city);
        civilization.removeCity(city);
        boolean result = civilization.getCities().contains(city);
        Assertions.assertFalse(result);
    }

    @Test
    public void testGetPlayer() {
        User result = civilization.getPlayer();
        Assertions.assertEquals(player, result);
    }

    @Test
    public void testGetColor() {
        int result = civilization.getColor();
        Assertions.assertEquals(10, result);
    }

    @Test
    public void testGetTurn() {
        int result = civilization.getTurn();
        Assertions.assertEquals(0, result);
    }

    @Test
    public void testGetBaseProduction() {
        civilization.setBaseProduction(123);
        int result = civilization.getBaseProduction();
        Assertions.assertEquals(123, result);
    }

    @Test
    public void testGetBaseResearchPoint() {
        civilization.setBaseResearchPoint(123);
        int result = civilization.getBaseResearchPoint();
        Assertions.assertEquals(123, result);
    }

//    @Test
//    public void testGetResourceList() {
//
//    }

    @Test
    public void testEquals() {
        Mockito.when(civilization2.getPlayer()).thenReturn(user2);
        Mockito.when(user2.getNickname()).thenReturn("one");
        Mockito.when(user2.getNickname()).thenReturn("two");
        boolean result = civilization.equals(civilization2);
        Assertions.assertFalse(result);
    }

    @Test
    public void testGetTile() {
        civilization.addCity(city);
        ArrayList<Tile> tiles = new ArrayList<>();
        tiles.add(tile);
        Mockito.when(city.getTiles()).thenReturn(tiles);
        ArrayList<Tile> result = civilization.getTiles();
        Assertions.assertEquals(tiles ,result);
    }

    @Test
    public void testApplyNewTurnChanges() {
        civilization.applyNewTurnChanges(123);
        Assertions.assertEquals(123, civilization.getTurn());
        // because it calls other methods
        // there is no need to check this method further
    }

    @Test
    public void testCalculateGold() {
        civilization.setGold(123);
        civilization.addUnit(new Warrior(civilization, tile));
        int result = civilization.calculateGold();
        Assertions.assertEquals(122, result);
    }

    @Test
    public void testCalculateMaintenance() {
        civilization.addUnit(new Warrior(civilization, tile));
        civilization.addUnit(new Catapult(civilization, tile));
        int result = civilization.calculateUnitMaintenance();
        Assertions.assertEquals(2, result);
    }

    @Test
    public void testCalculateProduction() {
        civilization.setBaseProduction(1);
        civilization.addCity(city);
        ArrayList<Tile> tiles = new ArrayList<>();
        tiles.add(tile);
        Mockito.when(city.getTiles()).thenReturn(tiles);
        Mockito.when(tile.getProduction()).thenReturn(1);
        int result = civilization.calculateProduction();
        Assertions.assertEquals(12, result);
    }

    @Test
    public void testCalculateHappiness() {
        civilization.setBaseHappiness(1);
        civilization.addCity(city);
        ArrayList<Tile> tiles = new ArrayList<>();
        tiles.add(tile);
        //Mockito.when(city.getTiles()).thenReturn(tiles);
        Mockito.when(city.getTotalCitizenCount()).thenReturn(1);
        int result = civilization.calculateHappiness();
        Assertions.assertEquals(-3, result);
    }

    @Test
    public void testCalculateResearchPoint() {
        civilization.setBaseResearchPoint(1);
        civilization.addCity(city);
        ArrayList<Tile> tiles = new ArrayList<>();
        tiles.add(tile);
//        Mockito.when(city.getTiles()).thenReturn(tiles);
        Mockito.when(city.getTotalCitizenCount()).thenReturn(1);
        int result = civilization.calculateResearchPoint();
        Assertions.assertEquals(5, result);
    }

    @Test
    public void testSave() {
        civilization.save();
    }
}
