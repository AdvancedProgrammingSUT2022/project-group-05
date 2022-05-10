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


    public void applyUnitStateForTurn() { // this method is used for states that have effects in next turns
        switch (this.unit.getUnitState()) {
            case FORTIFY:
                this.setDefenceBonusInFortifyState(2);
                this.unitFortify();
                break;
            case ALERTED:
                this.checkEnemyInAlertedState();
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
    //TODO.. handle deselect or select a unit after a command which is better

    public UnitController(Unit unit) {
        this.unit = unit;
    }

    public String unitMove(int xPlace, int yPlace) {
        //TODO.. handle multiple-turn-moves
        this.setDefenceBonusInFortifyState(0);
        Tile here = this.unit.getTile();
        Tile destination = Map.getInstance().getTileFromMap(xPlace, yPlace);

        if (true) {
            //TODO.. handle fog of war
        }

        if ((this.unit instanceof Soldier && destination.getSoldier() != null) ||
                (this.unit instanceof Civilian && destination.getCivilian() != null)) {
            return Responses.ALREADY_A_UNIT_IN_TILE.toString();
        } else {
            Path bestPath = Map.getInstance().bestPathFinder(here, destination, this.unit.getRemainingMovement());
            if (bestPath == null) {
                return Responses.UNABLE_TO_MOVE_UNIT_HERE.toString();
            } else {
                if (this.unit instanceof Soldier) {
                    Map.getInstance().moveSoldier((Soldier) this.unit, bestPath);
                } else if (this.unit instanceof Civilian) {
                    Map.getInstance().moveCivilian((Civilian) this.unit, bestPath);
                }
                return Responses.UNIT_MOVED.toString();
            }
        }
    }

    public String unitSleep() {
        if (this.unit.getUnitState().equals(UnitState.ASLEEP)) return Responses.ALREADY_ASLEEP.toString();
        this.unit.sleep();
        this.setDefenceBonusInFortifyState(0);
        return Responses.UNIT_SLEPT.toString();
    }

    public String unitAlert() {
        if (this.unit.getUnitState().equals(UnitState.ALERTED)) return Responses.ALREADY_ALERTED.toString();
        this.unit.alert();
        return Responses.UNIT_ALERTED.toString();
    }

    public String unitFortify() {
        if (!this.unit.getUnitState().equals(UnitState.FORTIFY)) {
            this.setDefenceBonusInFortifyState(1);
            this.unit.fortify();
            return Responses.UNIT_FORTIFIED.toString();
        } else {
            return Responses.ALREADY_FORTIFIED.toString();
        }
    }

    public String unitRecover() {
        if (!this.unit.getUnitState().equals(UnitState.RECOVERING)) {
            this.setDefenceBonusInFortifyState(0);
            this.unit.recovering();
            return Responses.UNIT_RECOVERING.toString();
        } else {
            return Responses.ALREADY_RECOVERED.toString();
        }
    }

    public String unitGarrison() {
        if (!this.unit.getTile().hasCity()) {
            return "This tile does not belong to city";
        } else if (this.unit.getTile().getCity().getCivilization() != this.unit.getCivilization()) {
            return "Unit cannot garrison in enemy city";
        } else if (this.unit.getTile().getCity().hasGarrisonedUnit()) {
            return "City cannot have two garrisoned units";
        } if (!this.unit.getUnitState().equals(UnitState.GARRISONED)) {
            this.setDefenceBonusInFortifyState(0);
            this.unit.garrison();
            return Responses.UNIT_GARRISONED.toString();
        } else {
            return Responses.ALREADY_GARRISONED.toString();
        }
    }

    public String unitSetupRanged() {
        if (!(this.unit instanceof Siege)) {
            return "This unit is not a siege unit";
        } else if (!this.unit.getUnitState().equals(UnitState.SET_FOR_SIEGE)) {
            this.setDefenceBonusInFortifyState(0);
            Siege siege = (Siege) this.unit;
            siege.setup();
            return Responses.UNIT_SETUP.toString();
        } else {
            return Responses.ALREADY_SETUP.toString();
        }
    }

    public String unitAttack(int xPlace, int yPlace) {

        Tile here = this.unit.getTile();
        Tile end = Map.getInstance().getTileFromMap(xPlace, yPlace);

        Soldier soldier;
        if (!(this.unit instanceof Soldier)) {
            return "This is not a soldier unit";
        } else {
            soldier = (Soldier) this.unit;
            if (!soldier.canAttackTile(end)) {
                //TODO error: can't attack
                return "Can't attack tile";
            } else {
                this.setDefenceBonusInFortifyState(0);
                this.attack(soldier, end.getSoldier());
                return "unit attacked successfully";
            }
        }
    }

    private void attack(Soldier soldier, Soldier enemySoldier) {
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
                Map.getInstance().moveSoldierWithoutMP(soldier, enemySoldier.getTile());
                //TODO handle hostage civilian
            }
        }

        if (soldier.getHealth() == 0) {
            soldier.kill();
        }
    }

    public String unitCancel() {
        //TODO.. cancel multiple-turn-moves and ...
        return "";
    }

    public String unitWake() {
        if (this.unit.getUnitState() != UnitState.ASLEEP) {
            return Responses.ALREADY_AWAKE.toString();
        } else {
            this.unit.wake();
            return Responses.UNIT_AWAKENED.toString();
        }
    }

    public String unitDelete() {
        this.setDefenceBonusInFortifyState(0);
        this.unit.killWithGold();
        return Responses.UNIT_DELETED.toString();
    }

    public String unitFoundCity() {
        if (this.unit instanceof Settler) {
            Settler settler = (Settler) this.unit;
            String cityName = "New city";
            settler.foundCity(cityName);
            return "City found successfully";
        } else {
            return "This is not settler unit";
        }
    }

    public void unitBuild() {

    }

    public void unitRemoveForest() {
        if (this.unit instanceof Worker) {
            Worker worker = (Worker) this.unit;
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

    public void unitRemoveJungle() {

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

    public void checkEnemyInAlertedState() { // check neighbor tile for enemies in alerted state
        if (this.unit.getUnitState() == UnitState.ALERTED) {
            Tile here = this.unit.getTile();
            Tile[] neighbors = Map.getInstance().findNeighbors(here);
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
        speed += this.unit.getHealingBonus();
        this.unit.setHealingSpeed(speed);
        this.unit.heal();
    }


}
