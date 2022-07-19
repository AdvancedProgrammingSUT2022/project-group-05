package controller;


import model.game.Civilization;
import model.tile.Tile;
import model.unit.Unit;
import model.unit.civilian.Settler;
import model.unit.civilian.Worker;
import model.unit.soldier.melee.*;
import model.unit.soldier.ranged.Archer;
import model.unit.soldier.ranged.ChariotArcher;
import model.unit.soldier.ranged.Crossbowman;
import model.unit.soldier.ranged.siege.Artillery;
import model.unit.soldier.ranged.siege.Canon;
import model.unit.soldier.ranged.siege.Catapult;
import model.unit.soldier.ranged.siege.Trebuchet;
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

    @Test
    public void testArcher() {
        Unit result = GenerateUnit.StringToUnit(civilization, tile, "archer");
        Assertions.assertTrue((result instanceof Archer));
        String s = ((Archer)result).toString();
        Assertions.assertEquals("Archer", s);
    }

    @Test
    public void testChariotarcher() {
        Unit result = GenerateUnit.StringToUnit(civilization, tile, "chariotarcher");
        Assertions.assertTrue((result instanceof ChariotArcher));
        String s = result.toString();
        Assertions.assertEquals("ChArcher", s);
    }

    @Test
    public void testScout() {
        Unit result = GenerateUnit.StringToUnit(civilization, tile, "scout");
        Assertions.assertTrue((result instanceof Scout));
        String s = result.toString();
        Assertions.assertEquals("Scout", s);
    }

    @Test
    public void testSettler() {
        Unit result = GenerateUnit.StringToUnit(civilization, tile, "settler");
        Assertions.assertTrue((result instanceof Settler));
        String s = result.toString();
        Assertions.assertEquals("Settler", s);
    }

    @Test
    public void testSpearman() {
        Unit result = GenerateUnit.StringToUnit(civilization, tile, "spearman");
        Assertions.assertTrue((result instanceof Spearman));
        String s = result.toString();
        Assertions.assertEquals("Spearman", s);
    }

    @Test
    public void testWarrior() {
        Unit result = GenerateUnit.StringToUnit(civilization, tile, "warrior");
        Assertions.assertTrue((result instanceof Warrior));
        String s = result.toString();
        Assertions.assertEquals("Warrior", s);
    }

    @Test
    public void testWorker() {
        Unit result = GenerateUnit.StringToUnit(civilization, tile, "worker");
        Assertions.assertTrue((result instanceof Worker));
        String s = result.toString();
        Assertions.assertEquals("Worker", s);
    }

    @Test
    public void testCatapult() {
        Unit result = GenerateUnit.StringToUnit(civilization, tile, "catapult");
        Assertions.assertTrue((result instanceof Catapult));
        String s = result.toString();
        Assertions.assertEquals("Catapult", s);
    }

    @Test
    public void testHorseman() {
        Unit result = GenerateUnit.StringToUnit(civilization, tile, "horseman");
        Assertions.assertTrue((result instanceof Horseman));
        String s = result.toString();
        Assertions.assertEquals("Horseman", s);
    }

    @Test
    public void testSwordsman() {
        Unit result = GenerateUnit.StringToUnit(civilization, tile, "swordsman");
        Assertions.assertTrue((result instanceof Swordsman));
        String s = result.toString();
        Assertions.assertEquals("Sword", s);
    }

    @Test
    public void testCrossbowman() {
        Unit result = GenerateUnit.StringToUnit(civilization, tile, "crossbowman");
        Assertions.assertTrue((result instanceof Crossbowman));
        String s = result.toString();
        Assertions.assertEquals("Crossbow", s);
    }

    @Test
    public void testKnight() {
        Unit result = GenerateUnit.StringToUnit(civilization, tile, "knight");
        Assertions.assertTrue((result instanceof Knight));
        String s = result.toString();
        Assertions.assertEquals("Knight", s);
    }

    @Test
    public void testLongswordman() {
        Unit result = GenerateUnit.StringToUnit(civilization, tile, "longswordsman");
        Assertions.assertTrue((result instanceof Longswordsman));
        String s = result.toString();
        Assertions.assertEquals("LSword", s);
    }

    @Test
    public void testPikeman() {
        Unit result = GenerateUnit.StringToUnit(civilization, tile, "pikeman");
        Assertions.assertTrue((result instanceof Pikeman));
        String s = result.toString();
        Assertions.assertEquals("Pikeman", s);
    }

    @Test
    public void testTrebuchet() {
        Unit result = GenerateUnit.StringToUnit(civilization, tile, "trebuchet");
        Assertions.assertTrue((result instanceof Trebuchet));
        String s = result.toString();
        Assertions.assertEquals("Trebuch", s);
    }

    @Test
    public void testCanon() {
        Unit result = GenerateUnit.StringToUnit(civilization, tile, "canon");
        Assertions.assertTrue((result instanceof Canon));
        String s = result.toString();
        Assertions.assertEquals("Canon", s);
    }

    @Test
    public void testCavalry() {
        Unit result = GenerateUnit.StringToUnit(civilization, tile, "cavalry");
        Assertions.assertTrue((result instanceof Cavalry));
        String s = result.toString();
        Assertions.assertEquals("Cavalry", s);
    }

    @Test
    public void testLancer() {
        Unit result = GenerateUnit.StringToUnit(civilization, tile, "Lancer");
        Assertions.assertTrue((result instanceof Lancer));
        String s = result.toString();
        Assertions.assertEquals("Lancer", s);
    }

    @Test
    public void testMusketman() {
        Unit result = GenerateUnit.StringToUnit(civilization, tile, "musketman");
        Assertions.assertTrue((result instanceof Musketman));
        String s = result.toString();
        Assertions.assertEquals("Musket", s);
    }

    @Test
    public void testRifleman() {
        Unit result = GenerateUnit.StringToUnit(civilization, tile, "rifleman");
        Assertions.assertTrue((result instanceof Rifleman));
        String s = result.toString();
        Assertions.assertEquals("Rifleman", s);
    }

    @Test
    public void testAntitankgun() {
        Unit result = GenerateUnit.StringToUnit(civilization, tile, "antitankgun");
        Assertions.assertTrue((result instanceof AntiTankGun));
        String s = result.toString();
        Assertions.assertEquals("AntTank", s);
    }

    @Test
    public void testArtillery() {
        Unit result = GenerateUnit.StringToUnit(civilization, tile, "artillery");
        Assertions.assertTrue((result instanceof Artillery));
        String s = result.toString();
        Assertions.assertEquals("Artiler", s);
    }

    @Test
    public void testInfantry() {
        Unit result = GenerateUnit.StringToUnit(civilization, tile, "infantry");
        Assertions.assertTrue((result instanceof Infantry));
        String s = result.toString();
        Assertions.assertEquals("Infantry", s);
    }

    @Test
    public void testPanzer() {
        Unit result = GenerateUnit.StringToUnit(civilization, tile, "Panzer");
        Assertions.assertTrue((result instanceof Panzer));
        String s = result.toString();
        Assertions.assertEquals("Panzer", s);
    }

    @Test
    public void testTank() {
        Unit result = GenerateUnit.StringToUnit(civilization, tile, "Tank");
        Assertions.assertTrue((result instanceof Tank));
        String s = result.toString();
        Assertions.assertEquals("Tank", s);
    }
}
