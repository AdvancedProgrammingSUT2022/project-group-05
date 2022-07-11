package controller;

import model.User;
import model.game.City;
import model.game.Civilization;
import model.map.Map;
import model.tile.Tile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;


public class TestCityController{
    Civilization civilization;
    Tile tile;
    City city;

    CityController cityController;

    @BeforeEach
    public void setUp() {
        Map.updateInstance(4);

        civilization = new Civilization(new User("a", "a", "a", null), 0);
        tile = new Tile(0, 0, 4);
        city = new City("a", tile, civilization);

        CityController.updateInstance(city);

        cityController = CityController.getInstance();
    }

    @Test
    public void createUnitWrongUnitName() {
        String response = cityController.cityCreateUnit("");

        Assertions.assertEquals("error: there is no unit with this name", response);
    }
    @Test
    public void createUnitSettlerWithLowPop() {
        String response = cityController.cityCreateUnit("settler");

        Assertions.assertEquals("error: not enough citizen for creating settler", response);
    }
    @Test
    public void createUnitRightUnitName() {
        String response = cityController.cityCreateUnit("worker");

        Assertions.assertEquals(Responses.CREATING_UNIT_STARTED.getResponse(), response);
    }

    @Test
    public void purchaseUnitWrongUnitName() {
        String response = cityController.purchaseUnit("");

        Assertions.assertEquals("error: there is no unit with this name", response);
    }
    @Test
    public void purchaseUnitRightUnitNameNoGold() {
        String response = cityController.purchaseUnit("worker");

        Assertions.assertEquals("error: not enough gold", response);
    }
    @Test
    public void purchaseUnitRightUnitNameWithGold() {
        civilization.setGold(200);
        String response = cityController.purchaseUnit("worker");

        Assertions.assertEquals("unit purchased successfully", response);
    }

    @Test
    public void buyTileTestOut() {
        String response = cityController.buyTile(3, 0);

        Assertions.assertEquals(Responses.CANT_ADD_TILE_TO_CITY.getResponse(), response);
    }
    @Test
    public void buyTileTestNoGold() {
        String response = cityController.buyTile(2, 0);

        Assertions.assertEquals(Responses.NOT_ENOUGH_GOLD.getResponse(), response);
    }
    @Test
    public void buyTileTestWithGold() {
        civilization.setGold(200);
        String response = cityController.buyTile(2, 0);

        Assertions.assertEquals(Responses.TILE_BOUGHT_SUCCESSFULLY.getResponse(), response);
    }

    @Test
    public void assignCitizenOutOfTerritory() {
        Tile tile = Mockito.mock(Tile.class);

        when(tile.getCity()).thenReturn(null);
        String response = cityController.assignCitizen(tile);

        Assertions.assertEquals("error: selected tile is out of city territory", response);
    }
    @Test
    public void assignCitizenHasCitizen() {
        cityController.assignCitizen(tile);
        String response = cityController.assignCitizen(tile);

        Assertions.assertEquals("error: selected tile already has a citizen", response);
    }
    @Test
    public void assignCitizenNotEnoughJobless() {
        cityController.assignCitizen(tile);

        Tile tile2 = Map.getInstance().getTileFromMap(0, 1);
        String response = cityController.assignCitizen(tile2);

        Assertions.assertEquals("error: not enough jobless citizens", response);
    }
    @Test
    public void assignCitizenSuccess() {
        String response = cityController.assignCitizen(tile);

        Assertions.assertEquals("citizen assigned to tile successfully", response);
    }

    @Test
    public void removeCitizenOutOfTerritory() {
        Tile tile = Mockito.mock(Tile.class);

        when(tile.getCity()).thenReturn(null);
        String response = cityController.removeCitizen(tile);

        Assertions.assertEquals("error: selected tile is out of city territory", response);
    }
    @Test
    public void removeCitizenNotFound() {
        String response = cityController.removeCitizen(tile);

        Assertions.assertEquals("error: selected tile has no assigned citizen", response);
    }
    @Test
    public void removeCitizenSuccess() {
        cityController.assignCitizen(tile);
        String response = cityController.removeCitizen(tile);

        Assertions.assertEquals("citizen removed from tile successfully", response);
    }
}
