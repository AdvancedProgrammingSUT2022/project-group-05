package model.tile;


import model.improvement.Improvement;
import model.resource.Resource;
import model.tile.project.ProjectManager;
import model.unit.civilian.Worker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class TestTile{
    Tile tile = new Tile(0, 0, 0);

    @Mock
    ProjectManager projectManager;

    @Mock
    Terrain terrain;
    @Mock
    Feature feature;
    @Mock
    Resource resource;

    @Mock
    Route route;
    @Mock
    Improvement improvement;

    @Test
    public void assignCitizenTest() {
        tile.assignCitizen();
        Assertions.assertTrue(tile.hasCitizen());
    }

    @Test
    public void removeCitizenTest() {
        tile.removeCitizen();
        Assertions.assertFalse(tile.hasCitizen());
    }

    @Test
    public void removalTest() {
        tile.removeTerrain();
        tile.removeFeature();
        tile.removeRoute();
        tile.removeImprovement();

        Assertions.assertEquals(Terrain.NO_TERRAIN, tile.getTerrain());
        Assertions.assertEquals(Feature.NO_FEATURE, tile.getFeature());
        Assertions.assertEquals(Route.NO_ROUTE, tile.getRoute());
        Assertions.assertEquals(Improvement.NO_IMPROVEMENT, tile.getImprovement());
    }

    @Test
    public void newTurnChangeTestWithoutWorker() {
        tile.setCivilian(null);
        tile.applyNewTurnChanges();
    }

    @Test
    public void newTurnChangeTestWithWorker() {
        tile.setCivilian(Mockito.mock(Worker.class));
        tile.applyNewTurnChanges();
    }

    @Test
    public void newTurChangeTestWithProject() {
        tile.startRepair();
        tile.applyNewTurnChanges();
    }

    @Test
    public void testAllProjectTypesStart() {
        tile.startRepair();
        tile.startRouteRemoval();
        tile.startImprovementRemoval();

        tile.startFeatureRemoval(feature);
        tile.startRouteConstruction(route);
    }
}
