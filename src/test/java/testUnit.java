import model.game.Civilization;
import model.tile.Tile;
import model.unit.Unit;
import model.unit.UnitState;
import model.unit.civilian.Worker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class testUnit {

    Unit unit;

    @Mock
    Civilization civilization;

    @Mock
    Tile tile;

    @Mock
    Unit mockedUnit;

    @BeforeEach
    public void initialize(){
        unit = new Worker(civilization, tile);
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
}
