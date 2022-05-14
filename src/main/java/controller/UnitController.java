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
import model.unit.soldier.ranged.siege.Siege;
import org.w3c.dom.ranges.Range;

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
            return "can't move to this tile";
        }
        if ((this.unit instanceof Soldier && destination.getSoldier() != null) ||
                (this.unit instanceof Civilian && destination.getCivilian() != null)) {
            return Responses.ALREADY_A_UNIT_IN_TILE.toString();
        }
        Path bestPath = Map.getInstance().bestPathFinder(here, destination, this.unit.getRemainingMovement());
        if (bestPath == null) {
            return Responses.UNABLE_TO_MOVE_UNIT_HERE.toString();
        }
        if (this.unit instanceof Soldier) {
            Map.getInstance().moveSoldier((Soldier) this.unit, bestPath);
        } else if (this.unit instanceof Civilian) {
            Map.getInstance().moveCivilian((Civilian) this.unit, bestPath);
        }
        return Responses.UNIT_MOVED.toString();
    }

    public String unitSleep() {
        if (this.unit.getUnitState().equals(UnitState.ASLEEP))
            return Responses.ALREADY_ASLEEP.toString();
        this.unit.sleep();
        this.setDefenceBonusInFortifyState(0);
        return Responses.UNIT_SLEPT.toString();
    }

    public String unitAlert() {
        if (this.unit.getUnitState().equals(UnitState.ALERTED))
            return Responses.ALREADY_ALERTED.toString();
        this.unit.alert();
        return Responses.UNIT_ALERTED.toString();
    }

    public String unitFortify() {
        if (!this.unit.getUnitState().equals(UnitState.FORTIFY)) {
            this.setDefenceBonusInFortifyState(1);
            this.unit.fortify();
            return Responses.UNIT_FORTIFIED.toString();
        }
        return Responses.ALREADY_FORTIFIED.toString();
    }

    public String unitRecover() {
        if (!this.unit.getUnitState().equals(UnitState.RECOVERING)) {
            return Responses.ALREADY_RECOVERED.toString();
        }
        this.setDefenceBonusInFortifyState(0);
        this.unit.recovering();
        if (!this.recoverUnitInRecoveringState()) {
            return Responses.UNIT_RECOVERING.toString();
        }
        return "Unit is in full health";
    }

    public String unitGarrison() {
        if (!this.unit.getTile().hasCity())
            return "This tile does not belong to city";
        if (this.unit.getTile().getCity().getCivilization() != this.unit.getCivilization())
            return "Unit cannot garrison in enemy city";
        if (this.unit.getTile().getCity().hasGarrisonedUnit())
            return "City cannot have two garrisoned units";
        if (!this.unit.getUnitState().equals(UnitState.GARRISONED)) {
            this.setDefenceBonusInFortifyState(0);
            this.unit.garrison();
            return Responses.UNIT_GARRISONED.toString();
        }
        return Responses.ALREADY_GARRISONED.toString();
    }

    public String unitSetupRanged() {
        if (!(this.unit instanceof Siege))
            return "This unit is not a siege unit";
        if (!this.unit.getUnitState().equals(UnitState.SET_FOR_SIEGE)) {
            this.setDefenceBonusInFortifyState(0);
            Siege siege = (Siege) this.unit;
            siege.setup();
            return Responses.UNIT_SETUP.toString();
        }
        return Responses.ALREADY_SETUP.toString();

    }

    public String unitAttack(int xPlace, int yPlace) {

        this.unitWake();

        Tile here = this.unit.getTile();
        Tile end = Map.getInstance().getTileFromMap(xPlace, yPlace);

        Soldier soldier;
        if (!(this.unit instanceof Soldier)) {
            return "This is not a soldier unit";
        }
        soldier = (Soldier) this.unit;
        if (end.hasCity() && end.getCity().getCenter().equals(end)) {
            return this.attackCity(end.getCity(), soldier);
        }
        if (!soldier.canAttackTile(end)) {
            return "Can't attack tile";
        }
        this.setDefenceBonusInFortifyState(0);


        if (soldier instanceof Melee)
            this.attack(soldier, end.getSoldier());
        else {
            //TODO rangedAttack
        }
        return "unit attacked successfully";
    }

    private void attack(Soldier soldier, Soldier enemySoldier) {
        //TODO.. MP ???
        int totalStrengthOfSoldier = soldier.getTotalMeleeStrength();
        int totalStrengthOfEnemy = enemySoldier.getTotalMeleeStrength();
        soldier.setRemainingMovement(0);
        enemySoldier.setRemainingMovement(0);
        soldier.setHealth(soldier.getHealth() - totalStrengthOfEnemy);
        enemySoldier.setHealth(enemySoldier.getHealth() - totalStrengthOfSoldier);

        if (enemySoldier instanceof Mounted && soldier instanceof BonusVsMounted)
            enemySoldier.setHealth(enemySoldier.getHealth() - totalStrengthOfSoldier);
        if (soldier instanceof Mounted && enemySoldier instanceof  BonusVsMounted)
            soldier.setHealth(enemySoldier.getHealth() - totalStrengthOfEnemy);

        if (soldier instanceof AntiTankGun && enemySoldier instanceof Tank)
            enemySoldier.setHealth(enemySoldier.getHealth() - 10);
        if (enemySoldier instanceof AntiTankGun && soldier instanceof Tank)
            soldier.setHealth(soldier.getHealth() - 10);

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
        }
        this.unit.wake();
        return Responses.UNIT_AWAKENED.toString();

    }

    public String unitDelete() {
        this.setDefenceBonusInFortifyState(0);
        this.unit.killWithGold();
        return Responses.UNIT_DELETED.toString();
    }

    public String unitFoundCity() {
        if (!(this.unit instanceof Settler))
            return "error: Not a settler";
        Settler settler = (Settler) this.unit;
        String cityName = "New city";
        settler.foundCity(cityName);
        return "City found successfully";
    }

    public String attackCity(City city, Soldier soldier) {
        if (city.getCivilization() == soldier.getCivilization())
            return "can't attack our city";


        //TODO calculate health after attack...??????
        if (soldier instanceof BonusVsCity)
            city.setHealth(city.getHealth() - soldier.getAttackStrength() - 10);
        else
            city.setHealth(city.getHealth() - soldier.getAttackStrength());


        if (Map.getInstance().findDistance(soldier.getTile(), city.getCenter()) <= 2)
            soldier.setHealth(soldier.getHealth() - city.getDefenceStrength());

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
        //TODO increase xp and ...
        return "attack complete";
    }

    public String conquerCity(City city, Soldier soldier) {
        city.getCivilization().removeCity(city);
        city.setCivilization(soldier.getCivilization());
        city.setHealth(20);
        soldier.getCivilization().addCity(city);
        Map.getInstance().moveSoldierWithoutMP(soldier, city.getCenter());
        return "city conquered successfully";
    }


    //Worker stuff

    public String unitBuildImprovement(Improvement improvement) {
        this.unitWake();
        if (!(this.unit instanceof Worker))
            return "error: Not a worker";
        if (!this.unit.isInFriendlyTile())
            return "error: Tile not in territory";
        if (this.unit.getTile().hasRoute())
            return "error: Already an improvement on tile";
        if (!improvement.matchesTerrain(this.unit.getTile().getTerrain()) &&
                !improvement.matchesFeature(this.unit.getTile().getFeature()))
            return "error: This improvement doesn't match this feature and terrain";
        if (!this.unit.getCivilization().getResearchTree().isResearchDone(improvement.getNeededResearch()))
            return "error: Prerequisite tech" + improvement.getNeededResearch() + "has not been researched";

        Worker worker = (Worker) this.unit;
        worker.addImprovement(improvement);
        return "Improvement construction started";
    }

    public String unitBuildRoute(Route route) {
        this.unitWake();
        if (!(this.unit instanceof Worker))
            return "error: Not a worker";
        if (!this.unit.isInFriendlyTile())
            return "error: Tile not in territory";
        if (this.unit.getTile().hasRoute())
            return "error: Already a route on tile";
        if (!this.unit.getCivilization().getResearchTree().isResearchDone(route.getNeededResearch()))
            return "error: Prerequisite tech " + route.getNeededResearch() + " has not been researched";


        Worker worker = (Worker) this.unit;
        worker.addRoute(route);
        return "Route construction started";
    }

    public String unitRemoveForest() {
        this.unitWake();
        if (!(this.unit instanceof Worker))
            return "error: Not a worker";
        if (!this.unit.isInFriendlyTile())
            return "error: Tile not in territory";
        if (this.unit.getTile().getFeature() != Feature.FOREST)
            return "error: No forest on current tile";
        if (this.unit.getTile().hasImprovement())
            return "error: Can't remove feature of a tile with improvement";
        if (!this.unit.getCivilization().getResearchTree().isResearchDone(Research.BRONZE_WORKING))
            return "error: You need " + Research.BRONZE_WORKING + " to remove forests";

        Worker worker = (Worker) this.unit;
        worker.removeFeature(Feature.FOREST);
        return "Forest removal started";
    }

    public String unitRemoveJungle() {
        this.unitWake();
        if (!(this.unit instanceof Worker))
            return "error: Not a worker";
        if (!this.unit.isInFriendlyTile())
            return "error: Tile not in territory";
        if (this.unit.getTile().getFeature() != Feature.JUNGLE)
            return "error: No jungle on current tile";
        if (this.unit.getTile().hasImprovement())
            return "error: Can't remove feature of a tile with improvement";
        if (this.unit.getCivilization().getResearchTree().isResearchDone(Research.BRONZE_WORKING))
            return "error: You need " + Research.BRONZE_WORKING + " to remove jungles";

        Worker worker = (Worker) this.unit;
        worker.removeFeature(Feature.JUNGLE);
        return "Jungle removal started";
    }

    public String unitRemoveMarsh() {
        this.unitWake();
        if (!(this.unit instanceof Worker))
            return "error: Not a worker";
        if (!this.unit.isInFriendlyTile())
            return "error: Tile not in territory";
        if (this.unit.getTile().getFeature() != Feature.MARSH)
            return "error: No marsh on current tile";
        if (this.unit.getTile().hasImprovement())
            return "error: Can't remove feature of a tile with improvement";
        if (!this.unit.getCivilization().getResearchTree().isResearchDone(Research.MASONRY))
            return "error: You need " + Research.MASONRY + " to removes marshes";

        Worker worker = (Worker) this.unit;
        worker.removeFeature(Feature.MARSH);
        return "Marsh removal started";
    }

    public String unitRemoveRoute() {
        this.unitWake();
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
        this.unitWake();
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
        this.unitWake();
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

    public boolean recoverUnitInRecoveringState() {
        //TODO.. find the location of unit which could be in city or friendly ground or enemy ground
        if (this.unit.getHealth() == 10) {
            return false;
        } else {
            int speed = 1;
            speed += this.unit.getHealingBonus();
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
