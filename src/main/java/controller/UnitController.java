package controller;

import model.game.City;
import model.improvement.Improvement;
import model.map.Map;
import model.map.Path;
import model.tile.Feature;
import model.tile.Route;
import model.tile.Tile;
import model.unit.Unit;
import model.unit.UnitState;
import model.unit.civilian.Civilian;
import model.unit.civilian.Settler;
import model.unit.civilian.Worker;
import model.unit.soldier.Soldier;
import model.unit.soldier.ranged.siege.Siege;

import java.util.HashMap;

public class UnitController{
    private final Unit unit;

    //Singleton definition
    private static UnitController instance;

    private UnitController(Unit unit) {
        this.unit = unit;
    }

    public static UnitController getInstance() {
        return instance;
    }

    public static void updateInstance(Unit unit) {
        instance = new UnitController(unit);
    }
    //End of singleton definition

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

    public String unitMove(int xPlace, int yPlace) {

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

    //Worker stuff

    public String unitBuildImprovement(Improvement improvement) {
        if (!(this.unit instanceof Worker))
            return "error: Not a worker";
        if (!this.unit.isInFriendlyTile())
            return "error: Tile not in territory";
        if (this.unit.getTile().hasRoute())
            return "error: Already an improvement on tile";
        if (!improvement.matchesTerrain(this.unit.getTile().getTerrain()) &&
                !improvement.matchesFeature(this.unit.getTile().getFeature()))
            return "error: this improvement doesn't match this feature and terrain";

        return "Improvement construction started";
    }

    public String unitBuildRoute(Route route) {
        if (!(this.unit instanceof Worker))
            return "error: Not a worker";
        if (!this.unit.isInFriendlyTile())
            return "error: Tile not in territory";
        if (this.unit.getTile().hasRoute())
            return "error: Already a route on tile";

        return "Route construction started";
    }

    public String unitRemoveForest() {
        if (!(this.unit instanceof Worker))
            return "error: Not a worker";
        if (!this.unit.isInFriendlyTile())
            return "error: Tile not in territory";
        if (this.unit.getTile().getFeature() != Feature.FOREST)
            return "error: No forest on current tile";
        if (this.unit.getTile().hasImprovement())
            return "error: Can't remove feature of a tile with improvement";

        Worker worker = (Worker) this.unit;
        worker.removeFeature(Feature.FOREST);
        return "Forest removal started";
    }

    public String unitRemoveJungle() {
        if (!(this.unit instanceof Worker))
            return "error: Not a worker";
        if (!this.unit.isInFriendlyTile())
            return "error: Tile not in territory";
        if (this.unit.getTile().getFeature() != Feature.JUNGLE)
            return "error: No jungle on current tile";
        if (this.unit.getTile().hasImprovement())
            return "error: Can't remove feature of a tile with improvement";

        Worker worker = (Worker) this.unit;
        worker.removeFeature(Feature.JUNGLE);
        return "Jungle removal started";
    }

    public String unitRemoveMarsh() {
        if (!(this.unit instanceof Worker))
            return "error: Not a worker";
        if (!this.unit.isInFriendlyTile())
            return "error: Tile not in territory";
        if (this.unit.getTile().getFeature() != Feature.MARSH)
            return "error: No marsh on current tile";
        if (this.unit.getTile().hasImprovement())
            return "error: Can't remove feature of a tile with improvement";

        Worker worker = (Worker) this.unit;
        worker.removeFeature(Feature.MARSH);
        return "Marsh removal started";
    }

    public String unitRemoveRoute() {
        if (!(this.unit instanceof Worker))
            return "error: Not a worker";
        if (!this.unit.isInFriendlyTile())
            return "error: Tile not in territory";
        if (!this.unit.getTile().hasRoute())
            return "error: No route on current tile";

        Worker worker = (Worker) this.unit;
        worker.removeRoute();
        return "Route removal started";
    }

    public String unitRemoveImprovement() {
        if (!(this.unit instanceof Worker))
            return "error: Not a worker";
        if (!this.unit.isInFriendlyTile())
            return "error: Tile not in territory";
        if (!this.unit.getTile().hasImprovement())
            return "error: No improvement on current tile";

        Worker worker = (Worker) this.unit;
        worker.removeImprovement();
        return "Improvement removal started";
    }

    public String unitRepair() {
        if (!(this.unit instanceof Worker))
            return "error: Not a worker";
        if (!this.unit.isInFriendlyTile())
            return "error: Tile not in territory";
        if (this.unit.getTile().isRepaired())
            return "error: Tile already repaired";

        Worker worker = (Worker) this.unit;
        return "Repair started";
    }
    //End of worker stuff

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

    //GETTER (NOT NEEDED?)
    public Unit getUnit() {
        return this.unit;
    }
    public void spawnUnit(City city) {
        //TODO check special conditions...
        this.unit.setTile(city.getCenter());
        this.unit.getCivilization().addUnit(this.unit);
    }


}
