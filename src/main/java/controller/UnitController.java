package controller;

import model.map.Map;
import model.map.Path;
import model.tile.Tile;
import model.unit.Unit;
import model.unit.UnitState;
import model.unit.civilian.Civilian;
import model.unit.civilian.Settler;
import model.unit.civilian.Worker;
import model.unit.soldier.Soldier;
import model.unit.soldier.ranged.siege.Siege;

import java.util.HashMap;

public class UnitController {

    private Unit unit;
    private Map map;

    public void applyUnitStateForTurn() { // this method is used for states that have effects in next turns
        switch (this.unit.getUnitState()) {
            case FORTIFY:
                this.setDefenceBonusInFortifyState(2);
                this.unitFortify();
                break;
            case ALERTED:
                this.checkEnemyInAlertedState(this.map);
                break;
            case RECOVERING:
                this.recoverUnitInRecoveringState();
                break;
            case PILLAGING:
                //TODO..
                break;
            case GARRISONED:
                //TODO..
                break;
            case MAKING_CITY:
                //TODO..
                break;
        }
    }

    //TODO.. check state if it is already (MR.B)

    public UnitController(Unit unit) {
        this.unit = unit;
    }

    public void unitMove(HashMap<String, String> command, Map map) {

        this.setDefenceBonusInFortifyState(0);

        int xPlace = Integer.parseInt(command.get("X_POSITION"));
        int yPlace = Integer.parseInt(command.get("Y_POSITION"));
        Tile here = this.unit.getTile();
        Tile destination = map.getTileFromMap(xPlace, yPlace);

        if (true) {
            //TODO.. handle fog of war
        }

        if ((this.unit instanceof Soldier && destination.getSoldier() != null) ||
                (this.unit instanceof Civilian && destination.getCivilian() != null)) {
            //TODO error same unit in one tile
        } else {
            Path bestPath = map.bestPathFinder(here, destination, this.unit.getRemainingMovement());
            if (bestPath == null) {
                //TODO.. error can't move
            } else {
                if (this.unit instanceof Soldier) {
                    map.moveSoldier((Soldier) this.unit, bestPath);
                } else if (this.unit instanceof Civilian) {
                    map.moveCivilian((Civilian) this.unit, bestPath);
                }
            }
        }
    }

    public void unitSleep() {
        this.unit.sleep();
    }

    public void unitAlert() {
        this.unit.alert();
    }

    public void unitFortify() {
        if (this.unit.getUnitState() != UnitState.FORTIFY) {
            this.setDefenceBonusInFortifyState(1);
            this.unit.fortify();
        } else {
            //TODO.. this is already fortified
        }
    }

    public void unitRecover() {
        this.setDefenceBonusInFortifyState(0);
        this.unit.recovering();
    }

    public void unitGarrison() {
        //TODO..  unit is in the city?
        this.setDefenceBonusInFortifyState(0);
        this.unit.garrison();
    }

    public void unitSetupRanged() {
        if (this.unit instanceof Siege) {
            this.setDefenceBonusInFortifyState(0);
            Siege siege = (Siege) this.unit;
            siege.setup();
        } else {
            //TODO.. error
        }
    }

    public void unitAttack(HashMap<String, String> command, Map map) {

        int xPlace = Integer.parseInt(command.get("X_POSITION"));
        int yPlace = Integer.parseInt(command.get("Y_POSITION"));

        Tile here = this.unit.getTile();
        Tile end = map.getTileFromMap(xPlace, yPlace);

        Soldier soldier;
        if (!(this.unit instanceof Soldier)) {
            //TODO.. error: can't attack without soldier
        } else {
            soldier = (Soldier) this.unit;
            if (!soldier.canAttackTile(end, map)) {
                //TODO error: can't attack
            } else {
                this.setDefenceBonusInFortifyState(0);
                this.attack(map, soldier, end.getSoldier());
            }
        }
    }

    private void attack(Map map, Soldier soldier, Soldier enemySoldier) {
        //TODO.. MP ???
        int totalStrengthOfSoldier = soldier.getTotalMeleeStrength();
        int totalStrengthOfEnemy = enemySoldier.getTotalMeleeStrength();
        soldier.setRemainingMovement(0);
        enemySoldier.setRemainingMovement(0);
        soldier.setHealth(soldier.getHealth() - totalStrengthOfEnemy);
        enemySoldier.setHealth(enemySoldier.getHealth() - totalStrengthOfSoldier);
        if (enemySoldier.getHealth() == 0) {
            enemySoldier.kill();
            if (soldier.getHealth() != 0) {
                map.moveSoldierWithoutMP(soldier, enemySoldier.getTile());
                //TODO handle hostage civilian
            }
        }

        if (soldier.getHealth() == 0) {
            soldier.kill();
        }
    }

    public void unitCancel() {
        //TODO.. ???
    }

    public void unitWake() {
        this.unit.wake();
    }

    public void unitDelete() {
        this.setDefenceBonusInFortifyState(0);
        this.unit.killWithGold();
    }

    public void unitFoundCity() {
        if (this.unit instanceof Settler) {
            Settler settler = (Settler) this.unit;
            settler.foundCity();
        } else {
            //TODO.. error
        }
    }

    public void unitBuild() {

    }

    public void unitRemoveFeature() {
        if (this.unit instanceof Worker) {
            Worker worker = (Worker) this.unit;
            //TODO.. override toString in Feature?
            String type = this.unit.getTile().getFeature().toString();
            if (type.equals("JUNGLE") || type.equals("FOREST") || type.equals("MARSH")) {
                worker.removeFeature();
            } else {
                //TODO.. error
            }
        } else {
            //TODO.. error
        }
    }

    public void unitRemoveRoute() {
        if (this.unit instanceof Worker) {
            Worker worker = (Worker) this.unit;
            if (this.unit.getTile().hasRoute()) {
                worker.removeRoute();
            } else {
                //TODO.. error
            }
        } else {
            //TODO.. error
        }
    }

    public void unitRepair() {
        if (this.unit instanceof Worker) {
            Worker worker = (Worker) this.unit;
            if (!this.unit.getTile().isRepaired()) {
                worker.repairTile();
            } else {
                //TODO error : tile is repaired
            }
        } else {
            //TODO error
        }
    }

    public void checkEnemyInAlertedState(Map map) { // check neighbor tile for enemies in alerted state
        if (this.unit.getUnitState() == UnitState.ALERTED) {
            Tile here = this.unit.getTile();
            Tile[] neighbors = map.getNeighbors(here); //TODO..
            for (int i = 0; i < 6; i++) {
                if (neighbors[i].getSoldier() != null) {
                    if (neighbors[i].getSoldier().getCivilization() != this.unit.getCivilization()) {
                        this.unit.wake();
                        break;
                    }
                }
            }
        }
    }

    public void setDefenceBonusInFortifyState(int numberOfTurnsSinceFortification) {
        if (numberOfTurnsSinceFortification == 0) {
            this.unit.setTemporaryDefenceBonusPercentage(0);
        } else if (numberOfTurnsSinceFortification == 1) {
            this.unit.setTemporaryDefenceBonusPercentage(25);
        } else {
            this.unit.setTemporaryDefenceBonusPercentage(50);
        }
    }

    public void recoverUnitInRecoveringState() {
        //TODO.. find the location of unit which could be in city or friendly ground or enemy ground
        int speed = 1;
        speed += this.unit.getHealingBouns();
        this.unit.setHealingSpeed(speed);
        this.unit.heal();
    }


}
