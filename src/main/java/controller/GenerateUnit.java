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

import java.util.Locale;

public class GenerateUnit {

    public static Unit StringToUnit(Civilization civilization, Tile tile, String unitName) {
        unitName = unitName.toLowerCase();
        Unit unit;
        switch (unitName) {
            case ("archer"):
                unit = new Archer(civilization, tile);
                break;
            case ("chariotarcher"):
                unit = new ChariotArcher(civilization, tile);
                break;
            case ("scout"):
                unit = new Scout(civilization, tile);
                break;
            case ("settler"):
                unit = new Settler(civilization, tile);
                break;
            case ("spearman"):
                unit = new Spearman(civilization, tile);
                break;
            case ("warrior"):
                unit = new Warrior(civilization, tile);
                break;
            case ("worker"):
                unit = new Worker(civilization, tile);
                break;
            case ("catapult"):
                unit = new Catapult(civilization, tile);
                break;
            case ("horseman"):
                unit = new Horseman(civilization, tile);
                break;
            case ("swordsman"):
                unit = new Swordsman(civilization, tile);
                break;
            case ("crossbowman"):
                unit = new Crossbowman(civilization, tile);
                break;
            case ("knight"):
                unit = new Knight(civilization, tile);
                break;
            case ("longswordsman"):
                unit = new Longswordsman(civilization, tile);
                break;
            case ("pikeman"):
                unit = new Pikeman(civilization, tile);
                break;
            case ("trebuchet"):
                unit = new Trebuchet(civilization, tile);
                break;
            case ("canon"):
                unit = new Canon(civilization, tile);
                break;
            case ("cavalry"):
                unit = new Cavalry(civilization, tile);
                break;
            case ("lancer"):
                unit = new Lancer(civilization, tile);
                break;
            case ("musketman"):
                unit = new Musketman(civilization, tile);
                break;
            case ("rifleman"):
                unit = new Rifleman(civilization, tile);
                break;
            case ("antitankgun"):
                unit = new AntiTankGun(civilization, tile);
                break;
            case ("artillery"):
                unit = new Artillery(civilization, tile);
                break;
            case ("infantry"):
                unit = new Infantry(civilization, tile);
                break;
            case ("panzer"):
                unit = new Panzer(civilization, tile);
                break;
            case ("tank"):
                unit = new Tank(civilization, tile);
                break;
            default:
                unit = null;
        }
        return unit;
    }

}
