import model.game.Civilization;
import model.map.Map;
import model.tile.Tile;
import model.unit.Unit;
import model.unit.UnitState;
import model.unit.civilian.Worker;
import model.unit.soldier.melee.Warrior;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class testUnit {

    Unit unit;

    @Mock
    Civilization civilization;

    @Mock
    Tile tile;

    @Mock
    Map map;

    @BeforeEach
    public void initialize(){
        unit = new Warrior(civilization, tile);
    }

    @Test
    public void testSleep() {
        unit.sleep();
        UnitState result = unit.getUnitState();
        Assertions.assertEquals(UnitState.ASLEEP, result);
    }

    @Test
    public void testWake() {
        unit.wake();
        UnitState result = unit.getUnitState();
        Assertions.assertEquals(UnitState.AWAKE, result);
    }

    @Test
    public void testAlert() {
        unit.alert();
        UnitState result = unit.getUnitState();
        Assertions.assertEquals(UnitState.ALERTED, result);
    }

    @Test
    public void testFortify() {
        unit.fortify();
        UnitState result = unit.getUnitState();
        Assertions.assertEquals(UnitState.FORTIFY, result);
    }

    @Test
    public void testRecovering() {
        unit.recovering();
        UnitState result = unit.getUnitState();
        Assertions.assertEquals(UnitState.RECOVERING, result);
    }

    @Test
    public void testGarrison() {
        unit.garrison();
        UnitState result = unit.getUnitState();
        Assertions.assertEquals(UnitState.GARRISONED, result);
    }

//    @Test
//    public void testKillWithGold() {
//        unit.killWithGold();
//        //TODO how to override method of a mocked class to show the desired result
//        Mockito.when(civilization.getGold()).thenReturn(10);
//        Mockito.verify(civilization).setGold(17);
//        Mockito.verify(unit).kill();
//    }
    @Test
    public void testGetAttackStrength() {
        int result = unit.getAttackStrength();
        Assertions.assertEquals(6, result);
    }

    @Test
    public void testInitializeRemainingMovement() {
        unit.initializeRemainingMovement();
        int result = unit.getRemainingMovement();
        Assertions.assertEquals(2, result);
    }

    @Test
    public void testIsInFriendlyTile() {
        boolean result = unit.isInFriendlyTile();
        Mockito.when(tile.getCivilization()).thenReturn(civilization);
        Assertions.assertTrue(result);
    }

    @Test
    public void testHeal() {
        int result = unit.getHealth();
        unit.setHealth(5);
        unit.setHealingSpeed(1);
        unit.heal();
        Assertions.assertEquals(6, result);
    }

    @Test
    public void testIsTileInRange() {
        Mockito.when(map.findDistance(unit.getTile(), tile)).thenReturn(2);
        boolean result = unit.isTileInRange(tile);
        Assertions.assertFalse(result);
    }

    @Test
    public void testSetTile() {
        unit.setTile(tile);
        Tile result = unit.getTile();
        Assertions.assertEquals(tile, result);
    }

    @Test
    public void testSetRemainingMovement() {
        unit.setRemainingMovement(10);
        int result = unit.getRemainingMovement();
        Assertions.assertEquals(10, result);
    }

    @Test
    public void testSetHealth() {
        unit.setHealth(7);
        int result = unit.getHealth();
        Assertions.assertEquals(7, result);
    }


}
