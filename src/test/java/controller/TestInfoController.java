package controller;

import model.User;
import model.game.City;
import model.game.Civilization;
import model.map.Map;
import model.tile.Tile;
import model.unit.Unit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestInfoController{
    Civilization civilization;
    Tile tile;
    City city;
    Unit unit;

    @BeforeEach
    public void setUp() {
        Map.updateInstance(4);

        civilization = new Civilization(new User("a", "a", "a"), 0);
        tile = new Tile(0, 0, 4);
        city = new City("a", tile, civilization);
        unit = civilization.getUnits().get(0);
    }

    @Test
    public void getLuxuryResourceCountTest() {
        InfoController.getLuxuryResourceCount(civilization);


    }

    @Test
    public void getSoldierCountTest() {
        InfoController.getSoldierCount(civilization);


    }

    @Test
    public void getCivilianCountTest() {
        InfoController.getCivilianCount(civilization);


    }

    @Test
    public void getTileCountTest() {
        InfoController.getTileCount(civilization);


    }

    @Test
    public void getCityInfoTest() {
        InfoController.getCityInfo(city);


    }

    @Test
    public void getCityInfoInDepthTest() {
        InfoController.getCityInfoInDepth(city);


    }

    @Test
    public void getCityTilesStatsTest() {
        InfoController.getCityTilesStats(city);


    }

    @Test
    public void getUnitInfoTest() {
        InfoController.getUnitInfo(unit);


    }

    @Test
    public void getUnitInfoInDepthTest() {
        InfoController.getUnitInfoInDepth(unit);


    }

    @Test
    public void getTileInfoTest() {
        InfoController.getTileInfo(tile);


    }

    @Test
    public void getTileStatsTest() {
        InfoController.getTileStats(tile);


    }

    @Test
    public void getTileProjectInfoTest() {
        InfoController.getTileProjectInfo(tile);


    }

    @Test
    public void getTileRiversTest() {
        InfoController.getTileRivers(tile);


    }
}
