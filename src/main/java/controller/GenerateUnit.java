package controller;

import model.User;
import model.game.Civilization;
import model.tile.Terrain;
import model.tile.Tile;
import model.unit.Unit;
import model.unit.civilian.Civilian;
import model.unit.civilian.Settler;
import model.unit.civilian.Worker;
import model.unit.soldier.Soldier;
import model.unit.soldier.melee.*;
import model.unit.soldier.ranged.Archer;
import model.unit.soldier.ranged.ChariotArcher;
import model.unit.soldier.ranged.Crossbowman;
import model.unit.soldier.ranged.siege.Artillery;
import model.unit.soldier.ranged.siege.Canon;
import model.unit.soldier.ranged.siege.Catapult;
import model.unit.soldier.ranged.siege.Trebuchet;

public class GenerateUnit {

    public static Unit StringToUnit(Civilization civilization, Tile tile, String unitName) {
        Unit unit;
        switch (unitName) {
            case ("Archer"):
                unit = new Archer(civilization, tile);
                break;
            case ("ChariotArcher"):
                unit = new ChariotArcher(civilization, tile);
                break;
            case ("Scout"):
                unit = new Scout(civilization, tile);
                break;
            case ("Settler"):
                unit = new Settler(civilization, tile);
                break;
            case ("Spearman"):
                unit = new Spearman(civilization, tile);
                break;
            case ("Warrior"):
                unit = new Warrior(civilization, tile);
                break;
            case ("Worker"):
                unit = new Worker(civilization, tile);
                break;
            case ("Catapult"):
                unit = new Catapult(civilization, tile);
                break;
            case ("Horseman"):
                unit = new Horseman(civilization, tile);
                break;
            case ("Swordsman"):
                unit = new Swordsman(civilization, tile);
                break;
            case ("Crossbowman"):
                unit = new Crossbowman(civilization, tile);
                break;
            case ("Knight"):
                unit = new Knight(civilization, tile);
                break;
            case ("Longswordsman"):
                unit = new Longswordsman(civilization, tile);
                break;
            case ("Pikeman"):
                unit = new Pikeman(civilization, tile);
                break;
            case ("Trebuchet"):
                unit = new Trebuchet(civilization, tile);
                break;
            case ("Canon"):
                unit = new Canon(civilization, tile);
                break;
            case ("Cavalry"):
                unit = new Cavalry(civilization, tile);
                break;
            case ("Lancer"):
                unit = new Lancer(civilization, tile);
                break;
            case ("Musketman"):
                unit = new Musketman(civilization, tile);
                break;
            case ("Rifleman"):
                unit = new Rifleman(civilization, tile);
                break;
            case ("AntiTankGun"):
                unit = new AntiTankGun(civilization, tile);
                break;
            case ("Artillery"):
                unit = new Artillery(civilization, tile);
                break;
            case ("Infantry"):
                unit = new Infantry(civilization, tile);
                break;
            case ("Panzer"):
                unit = new Panzer(civilization, tile);
                break;
            case ("Tank"):
                unit = new Tank(civilization, tile);
                break;
            default:
                unit = null;
        }
        return unit;
    }

}
