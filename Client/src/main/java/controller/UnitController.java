package controller;

import model.game.City;
import model.improvement.Improvement;
import model.map.Map;
import model.map.Path;
import model.research.Research;
import model.tile.Feature;
import model.tile.Route;
import model.tile.Tile;
import model.unit.Unit;
import model.unit.UnitState;
import model.unit.addOns.*;
import model.unit.civilian.Civilian;
import model.unit.civilian.Settler;
import model.unit.civilian.Worker;
import model.unit.soldier.Soldier;
import model.unit.soldier.melee.AntiTankGun;
import model.unit.soldier.melee.Melee;
import model.unit.soldier.ranged.Ranged;
import model.unit.soldier.ranged.siege.Siege;
import org.w3c.dom.ranges.Range;
import utility.RandomGenerator;

public class UnitController{
    private Unit unit;

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

    public void applyUnitStateForNewTurn() { // this method is used for states that have effects in next turns
        switch (this.unit.getUnitState()) {
            case FORTIFY:
                this.setDefenceBonusInFortifyState(2);
                this.unitFortify();
                break;
            case ALERTED:
                this.checkEnemyInAlertedState();
                break;
            case RECOVERING:
                if (!this.recoverUnitInRecoveringState()) {
                    this.getUnit().wake();
                }
                break;
        }
    }


    public String unitMove(int xPlace, int yPlace) {
        this.unitWake();

        this.setDefenceBonusInFortifyState(0);

        Tile here = this.unit.getTile();
        Tile destination = Map.getInstance().getTileFromMap(xPlace, yPlace);

        if (!this.unit.canMoveTo(destination)) {
            return Responses.UNREACHABLE_DESTINATION.getResponse();
        }

        if ((this.unit instanceof Soldier && destination.hasSoldier()) ||
                (this.unit instanceof Civilian && destination.hasCivilian())) {
            return Responses.ALREADY_A_UNIT_IN_TILE.getResponse();
        }
        Path bestPath = Map.getInstance().bestPathFinder(here, destination, this.unit.getRemainingMovement());
        if (bestPath == null) {
            return Responses.UNABLE_TO_MOVE_UNIT_HERE.getResponse();
        }
        if (this.unit instanceof Soldier) {
            Map.getInstance().moveSoldier((Soldier) this.unit, bestPath);
        } else if (this.unit instanceof Civilian) {
            Map.getInstance().moveCivilian((Civilian) this.unit, bestPath);
        }

        if (destination.isRuin()) return Responses.UNIT_MOVED.getResponse() + "\n" +
                                         this.unit.getCivilization().getRandomRuinEffect(destination);
        else return Responses.UNIT_MOVED.getResponse();
    }

    public String unitSleep() {
        if (this.unit.getUnitState().equals(UnitState.ASLEEP))
            return Responses.ALREADY_ASLEEP.getResponse();
        this.unit.sleep();
        this.setDefenceBonusInFortifyState(0);
        return Responses.UNIT_SLEPT.getResponse();
    }

    public String unitAlert() {
        if (this.unit.getUnitState().equals(UnitState.ALERTED))
            return Responses.ALREADY_ALERTED.getResponse();
        this.unit.alert();
        return Responses.UNIT_ALERTED.getResponse();
    }

    public String unitFortify() {
        if (!this.unit.getUnitState().equals(UnitState.FORTIFY)) {
            this.setDefenceBonusInFortifyState(1);
            this.unit.fortify();
            return Responses.UNIT_FORTIFIED.getResponse();
        }
        return Responses.ALREADY_FORTIFIED.getResponse();
    }

    public String unitRecover() {
        if (this.unit.getUnitState().equals(UnitState.RECOVERING)) {
            return Responses.ALREADY_RECOVERED.getResponse();
        }
        this.setDefenceBonusInFortifyState(0);
        this.unit.recovering();
        if (!this.recoverUnitInRecoveringState()) {
            return Responses.UNIT_RECOVERING.getResponse();
        }
        return "Unit is in full health";
    }

    public String unitGarrison() {
        if (!(this.unit instanceof Soldier))
            return "error: cannot garrison civilian unit";
        if (!this.unit.getTile().hasCity())
            return "This tile does not belong to city";
        if (this.unit.getTile().getCity().getCivilization() != this.unit.getCivilization())
            return "Unit cannot garrison in enemy city";
        if (this.unit.getTile().getCity().hasGarrisonedUnit())
            return "City cannot have two garrisoned units";
        if (!this.unit.getUnitState().equals(UnitState.GARRISONED)) {
            this.setDefenceBonusInFortifyState(0);
            this.unit.garrison();

            this.unit.getTile().getCity().garrisonUnit();
            return Responses.UNIT_GARRISONED.getResponse();
        }
        return Responses.ALREADY_GARRISONED.getResponse();
    }

    public String unitSetupRanged() {
        if (!(this.unit instanceof Siege))
            return "This unit is not a siege unit";
        if (!this.unit.getUnitState().equals(UnitState.SET_FOR_SIEGE)) {
            this.setDefenceBonusInFortifyState(0);
            Siege siege = (Siege) this.unit;
            siege.setup();
            return Responses.UNIT_SETUP.getResponse();
        }
        return Responses.ALREADY_SETUP.getResponse();

    }

    public String unitAttack(int xPlace, int yPlace) {

        this.unitWake();

        Tile here = this.unit.getTile();
        Tile end = Map.getInstance().getTileFromMap(xPlace, yPlace);

        Soldier soldier;
        if (!(this.unit instanceof Soldier)) {
            return Responses.UNIT_NOT_SOLDIER.getResponse();
        }
        soldier = (Soldier) this.unit;
        if (end.hasCity() && end.getCity().getCenter().equals(end)) {
            return this.attackCity(end.getCity(), soldier);
        }
        if (!soldier.canAttackTile(end)) {
            return Responses.CANT_ATTACK_THIS_TILE.getResponse();
        }
        this.setDefenceBonusInFortifyState(0);


        if (end.hasSoldier()) this.attack(soldier, end.getSoldier());
        else {
            end.getCivilian().getCivilization().removeUnit(end.getCivilian());
            end.getCivilian().setCivilization(soldier.getCivilization());
            soldier.getCivilization().addUnit(end.getCivilian());
        }

        return Responses.UNIT_ATTACKED.getResponse();
    }

    private int calculateDamage(Soldier attacker, Unit defender) {
        int attackerStrength = attacker.getAttackStrength();
        int defenderStrength = defender.getTotalMeleeStrength();

        double exponent = 0.04 * (attackerStrength - defenderStrength);
        double randomRatio = (RandomGenerator.nextInt(40) + 80) / 100.0;

        return (int) Math.floor(3 * Math.pow(Math.E, exponent) * randomRatio);
    }

    private int calculateDamage(Soldier attacker, City defender) {
        return 4;
    }

    private int calculateDamage(City attacker, Soldier defender) {
        return 2;
    }

    private void attack(Soldier soldier, Soldier enemySoldier) {
        int damageToEnemy = calculateDamage(soldier, enemySoldier);
        int damageToSoldier = calculateDamage(enemySoldier, soldier);

        if ((soldier instanceof Ranged) && !(enemySoldier instanceof Ranged)) damageToSoldier = 0;

        soldier.setRemainingMovement(0);
        enemySoldier.setRemainingMovement(0);

        soldier.setHealth(soldier.getHealth() - damageToSoldier);
        enemySoldier.setHealth(enemySoldier.getHealth() - damageToEnemy);

        if (enemySoldier instanceof Mounted && soldier instanceof BonusVsMounted)
            enemySoldier.setHealth(enemySoldier.getHealth() - damageToEnemy);
        if (soldier instanceof Mounted && enemySoldier instanceof  BonusVsMounted)
            soldier.setHealth(enemySoldier.getHealth() - damageToSoldier);

        if (soldier instanceof AntiTankGun && enemySoldier instanceof Tank)
            enemySoldier.setHealth(enemySoldier.getHealth() - 10);
        if (enemySoldier instanceof AntiTankGun && soldier instanceof Tank)
            soldier.setHealth(soldier.getHealth() - 10);

        if (enemySoldier.getHealth() <= 0) {
            enemySoldier.kill();
            if (soldier.getHealth() > 0) {
                Tile targetTile = enemySoldier.getTile();
                Map.getInstance().moveSoldierWithoutMP(soldier, targetTile);
                if (targetTile.getCivilian() != null) targetTile.getCivilian().setCivilization(soldier.getCivilization());
            }
        }

        if (soldier.getHealth() <= 0) {
            soldier.kill();
        }
    }

    public String attackCity(City city, Soldier soldier) {
        if (city.getCivilization() == soldier.getCivilization())
            return Responses.CANT_ATTACK_OUR_CITY.getResponse();

        if (soldier instanceof BonusVsCity)
            city.setHealth(city.getHealth() - 3 * calculateDamage(soldier, city));
        else
            city.setHealth(city.getHealth() - calculateDamage(soldier, city));

        if (Map.getInstance().findDistance(soldier.getTile(), city.getCenter()) <= 2)
            soldier.setHealth(soldier.getHealth() - calculateDamage(city, soldier));

        if (! (soldier instanceof CanMoveAfterAttacking))
            soldier.setRemainingMovement(0);

        if (soldier instanceof Melee) {
            if (city.getHealth() <= 0) {
                return this.conquerCity(city, soldier);
            }
        } else if (soldier instanceof Range) {
            if (city.getHealth() <= 0) {
                city.setHealth(1);
            }
        }

        if (soldier.getHealth() <= 0) soldier.getCivilization().removeUnit(soldier);

        return Responses.ATTACK_COMPLETE.getResponse();
    }

    public String conquerCity(City city, Soldier soldier) {
        city.getCivilization().removeCity(city);

        city.setCivilization(soldier.getCivilization());
        city.setHealth(20);
        soldier.getCivilization().addCity(city);
        city.annex();

        Map.getInstance().moveSoldierWithoutMP(soldier, city.getCenter());
        return Responses.CITY_CONQUERED.getResponse();
    }


    public String unitCancel() {
        return "canceled";
    }

    public String unitWake() {
        if (this.unit.getUnitState() == UnitState.AWAKE) {
            return Responses.ALREADY_AWAKE.getResponse();
        }
        if (this.unit.getUnitState() == UnitState.GARRISONED)
            this.unit.getTile().getCity().removeGarrisonedUnit();
        this.unit.wake();
        this.setDefenceBonusInFortifyState(0);
        return Responses.UNIT_AWAKENED.getResponse();
    }

    public String unitDelete() {
        this.setDefenceBonusInFortifyState(0);
        this.unit.killWithGold();
        UnitController.updateInstance(null);
        return Responses.UNIT_DELETED.getResponse();
    }

    public String unitFoundCity() {
        if (!(this.unit instanceof Settler))
            return Responses.UNIT_NOT_SETTLER.getResponse();

        Settler settler = (Settler) this.unit;
        settler.foundCity(GameMenuController.getInstance().getRandomCityName());
        unit.getCivilization().removeUnit(this.unit);
        this.unit = null;

        return Responses.CITY_FOUNDED.getResponse();
    }

    //Worker stuff
    public String unitBuildImprovement(Improvement improvement) {
        this.unitWake();
        if (!(this.unit instanceof Worker))
            return Responses.UNIT_NOT_WORKER.getResponse();
        if (!this.unit.isInFriendlyTile())
            return Responses.TILE_NOT_IN_TERRITORY.getResponse();
        if (this.unit.getTile().hasRoute())
            return Responses.ALREADY_IMPROVEMENT_ON_TILE.getResponse();
        if (!improvement.matchesTerrain(this.unit.getTile().getTerrain()) &&
                !improvement.matchesFeature(this.unit.getTile().getFeature()))
            return Responses.IMPROVEMENT_DOESNT_MATCH_TERRAIN.getResponse();
        if (!this.unit.getCivilization().getResearchTree().isResearchDone(improvement.getNeededResearch()))
            return "error: Prerequisite tech" + improvement.getNeededResearch() + "has not been researched";

        Worker worker = (Worker) this.unit;
        worker.addImprovement(improvement);
        return Responses.IMPROVEMENT_DONE.getResponse();
    }

    public String unitBuildRoute(Route route) {
        this.unitWake();
        if (!(this.unit instanceof Worker))
            return Responses.UNIT_NOT_WORKER.getResponse();
        if (!this.unit.isInFriendlyTile())
            return Responses.TILE_NOT_IN_TERRITORY.getResponse();
        if (this.unit.getTile().hasRoute())
            return Responses.ALREADY_ROUTE.getResponse();
        if (!this.unit.getCivilization().getResearchTree().isResearchDone(route.getNeededResearch()))
            return "error: Prerequisite tech " + route.getNeededResearch() + " has not been researched";


        Worker worker = (Worker) this.unit;
        worker.addRoute(route);
        return Responses.ROUTE_DONE.getResponse();
    }

    public String unitRemoveForest() {
        this.unitWake();
        if (!(this.unit instanceof Worker))
            return Responses.UNIT_NOT_WORKER.getResponse();
        if (!this.unit.isInFriendlyTile())
            return Responses.TILE_NOT_IN_TERRITORY.getResponse();
        if (this.unit.getTile().getFeature() != Feature.FOREST)
            return Responses.NO_FOREST_IN_TILE.getResponse();
        if (this.unit.getTile().hasImprovement())
            return Responses.FEATURE_AND_IMPROVEMENT_ERROR.getResponse();
        if (!this.unit.getCivilization().getResearchTree().isResearchDone(Research.BRONZE_WORKING))
            return "error: You need " + Research.BRONZE_WORKING + " to remove forests";

        Worker worker = (Worker) this.unit;
        worker.removeFeature(Feature.FOREST);
        return Responses.FOREST_REM_DONE.getResponse();
    }

    public String unitRemoveJungle() {
        this.unitWake();
        if (!(this.unit instanceof Worker))
            return Responses.UNIT_NOT_WORKER.getResponse();
        if (!this.unit.isInFriendlyTile())
            return Responses.TILE_NOT_IN_TERRITORY.getResponse();
        if (this.unit.getTile().getFeature() != Feature.JUNGLE)
            return Responses.NO_JUNGLE_IN_TILE.getResponse();
        if (this.unit.getTile().hasImprovement())
            return Responses.FEATURE_AND_IMPROVEMENT_ERROR.getResponse();
        if (!this.unit.getCivilization().getResearchTree().isResearchDone(Research.BRONZE_WORKING))
            return "error: You need " + Research.BRONZE_WORKING + " to remove jungles";

        Worker worker = (Worker) this.unit;
        worker.removeFeature(Feature.JUNGLE);
        return Responses.JUNGLE_REM_DONE.getResponse();
    }

    public String unitRemoveMarsh() {
        this.unitWake();
        if (!(this.unit instanceof Worker))
            return Responses.UNIT_NOT_WORKER.getResponse();
        if (!this.unit.isInFriendlyTile())
            return Responses.TILE_NOT_IN_TERRITORY.getResponse();
        if (this.unit.getTile().getFeature() != Feature.MARSH)
            return Responses.NO_MARSH_IN_TILE.getResponse();
        if (this.unit.getTile().hasImprovement())
            return Responses.FEATURE_AND_IMPROVEMENT_ERROR.getResponse();
        if (!this.unit.getCivilization().getResearchTree().isResearchDone(Research.MASONRY))
            return "error: You need " + Research.MASONRY + " to removes marshes";

        Worker worker = (Worker) this.unit;
        worker.removeFeature(Feature.MARSH);
        return Responses.MARSH_REM_DONE.getResponse();
    }

    public String unitRemoveRoute() {
        this.unitWake();
        if (!(this.unit instanceof Worker))
            return Responses.UNIT_NOT_WORKER.getResponse();
        if (!this.unit.isInFriendlyTile())
            return Responses.TILE_NOT_IN_TERRITORY.getResponse();
        if (!this.unit.getTile().hasRoute())
            return Responses.NO_ROUTE_IN_TILE.getResponse();

        Worker worker = (Worker) this.unit;
        worker.removeRoute();
        return Responses.ROUTE_REM_DONE.getResponse();
    }

    public String unitRemoveImprovement() {
        this.unitWake();
        if (!(this.unit instanceof Worker))
            return Responses.UNIT_NOT_WORKER.getResponse();
        if (!this.unit.isInFriendlyTile())
            return Responses.TILE_NOT_IN_TERRITORY.getResponse();
        if (!this.unit.getTile().hasImprovement())
            return Responses.NO_IMPROVEMENT_IN_TILE.getResponse();

        Worker worker = (Worker) this.unit;
        worker.removeImprovement();
        return Responses.IMPROVEMENT_REM_DONE.getResponse();
    }

    public String unitRepair() {
        this.unitWake();
        if (!(this.unit instanceof Worker))
            return Responses.UNIT_NOT_WORKER.getResponse();
        if (!this.unit.isInFriendlyTile())
            return Responses.TILE_NOT_IN_TERRITORY.getResponse();
        if (this.unit.getTile().isRepaired())
            return Responses.ALREADY_REPAIRED.getResponse();

        Worker worker = (Worker) this.unit;
        worker.repair();
        return Responses.REPAIR_STARTED.getResponse();
    }
    //End of worker stuff

    private void checkEnemyInAlertedState() { // check neighbor tile for enemies in alerted state
        if (this.unit.getUnitState() == UnitState.ALERTED) {
            Tile here = this.unit.getTile();
            Tile[] neighbors = Map.getInstance().findNeighbors(here);
            for (int i = 0; i < 6; i++) {
                if (neighbors[i].getSoldier() != null) {
                    if (neighbors[i].getSoldier().getCivilization() != this.unit.getCivilization()) {
                        this.unitWake();
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

    private boolean recoverUnitInRecoveringState() {
        if (this.unit.getHealth() == 10) {
            return false;
        } else {
            int speed = this.unit.getHealingBonus();
            this.unit.setHealingSpeed(speed);
            this.unit.heal();
            return true;
        }
    }

    //GETTER
    public Unit getUnit() {
        return this.unit;
    }

}
