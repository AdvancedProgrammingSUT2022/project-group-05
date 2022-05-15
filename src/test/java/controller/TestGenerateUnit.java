package controller;


import model.game.Civilization;
import model.tile.Tile;
import model.unit.Unit;
import model.unit.civilian.Civilian;
import model.unit.soldier.melee.Tank;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TestGenerateUnit {


    @Mock
    Civilization civilization;

    @Mock
    Tile tile;

    @Test
    public void test() {
        Unit result = GenerateUnit.StringToUnit(civilization, tile, "u");
        Assertions.assertNull(result);
        Unit foundedUnit = GenerateUnit.StringToUnit(civilization, tile, "tANk");
        boolean result2 = (foundedUnit instanceof Tank);
        Assertions.assertTrue(result2);
    }
}
