package model.game;

import controller.UnitController;
import model.User;
import model.map.FogOfWar;
import model.map.FogOfWarStates;
import model.map.Map;
import model.research.Research;
import model.research.ResearchTree;
import model.resource.Resource;
import model.resource.ResourceList;
import model.resource.ResourceType;
import model.tile.Tile;
import model.unit.Unit;
import model.unit.civilian.Civilian;
import model.unit.civilian.Settler;
import model.unit.civilian.Worker;
import model.unit.soldier.Soldier;
import utility.RandomGenerator;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Civilization implements Serializable {
    private City capital;
    private ArrayList<City> cities;
    private ArrayList<Unit> units;


    private final User player;
    private final int color;

    private int turn;
    private int gold;
    private int production;
    private int baseProduction;
    private int researchPoint;
    private int baseResearchPoint;
    private int happiness;
    private int baseHappiness;

    private final ResourceList resourceList;
    private final ResearchTree researchTree;

    private FogOfWarStates[][] fogOfWar;

    public Civilization(User player, int color) {
        this.cities = new ArrayList<>();

        this.units = new ArrayList<>();
        this.initializeUnits();

        this.player = player;
        this.color = color;

        this.turn = 0;
        this.gold = 0;

        //TODO are these the best base values?
        this.baseProduction = 10;
        this.production = this.baseProduction;
        this.baseHappiness = 10;
        this.happiness = this.baseHappiness;
        this.baseResearchPoint = 0;
        this.researchPoint = this.baseResearchPoint;

        this.resourceList = new ResourceList();
        this.researchTree = new ResearchTree();

        FogOfWar.updateFogOfWar(this);
    }

    private void initializeUnits() {
        Tile tile = Map.getInstance().randomEmptyTile();
        this.addUnit(new Settler(this, tile));
    }

    public Boolean hasCity(City city) {
        return this.cities.contains(city);
    }

    public void addUnit(Unit unit) {
        this.units.add(unit);
        if (unit instanceof Civilian) unit.getTile().setCivilian((Civilian) unit);

        if (unit instanceof Soldier) unit.getTile().setSoldier((Soldier) unit);
    }

    public void removeUnit(Unit unit) {
        units.removeAll(Collections.singleton(unit));
        if (unit instanceof Civilian) unit.getTile().removeCivilian();
        if (unit instanceof Soldier) unit.getTile().removeSoldier();
    }

    public void startResearch(Research research) {
        this.researchTree.startResearch(research);
    }


    //SETTERS
    public void setGold(int gold) {
        this.gold = gold;
    }

    public void setProduction(int production) {
        this.production = production;
    }

    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }

    public void setResearchPoint(int researchPoint) {
        this.researchPoint = researchPoint;
    }

    public void setFogOfWar(FogOfWarStates[][] fogOfWar) {
        this.fogOfWar = fogOfWar;
    }

    public void addCity(City city) {
        this.cities.add(city);
    }

    public void removeCity(City city) {
        this.cities.remove(city);
    }


    //GETTERS
    public User getPlayer() {
        return player;
    }

    public int getColor() {
        return color;
    }

    public int getTurn() {
        return turn;
    }

    public int getGold() {
        return gold;
    }

    public int getProduction() {
        return production;
    }

    public int getBaseProduction() {
        return baseProduction;
    }

    public int getHappiness() {
        return happiness;
    }

    public  int getBaseHappiness() {
        return baseHappiness;
    }

    public int getResearchPoint() {
        return researchPoint;
    }

    public int getBaseResearchPoint() {
        return baseResearchPoint;
    }

    public FogOfWarStates[][] getFogOfWar() {
        return fogOfWar;
    }

    public ResourceList getResourceList() {
        return resourceList;
    }

    public ResearchTree getResearchTree() {
        return researchTree;
    }

    public ArrayList<Unit> getUnits() {
        return this.units;
    }

    public boolean equals(Civilization civilization) {
        return civilization.getPlayer().getNickname().equals(this.getPlayer().getNickname());
    }

    public ArrayList<City> getCities() {
        return cities;
    }

    public ArrayList<Tile> getTiles() {
        ArrayList<Tile> result = new ArrayList<>();
        for (City city : this.cities) {
            result.addAll(city.getTiles());
        }

        return result;
    }

    //CALCULATIONS
    public void applyNewTurnChanges(int currentYear) {
        this.turn = currentYear;

        //update unit state for new turn
        for (Unit unit : this.units) {
            unit.initializeRemainingMovement();

            UnitController.updateInstance(unit);
            UnitController.getInstance().applyUnitStateForNewTurn();
        }

        //update research (science is declined if negative gold)
        this.setResearchPoint(this.calculateResearchPoint());
        this.getResearchTree().continueResearch(this.gold >= 0 ? this.researchPoint : this.gold);

        //update production after changes
        this.setProduction(this.calculateProduction());

        //update gold and tiles after changes
        this.setGold(this.calculateGold());

        //update city status
        for (City city : this.getCities()) {
            city.applyNewTurnChanges();
        }

        //update happiness after changes
        this.setHappiness(this.calculateHappiness());
    }

    public int calculateGold() {
        int result = this.getGold();
        for (Tile tile : this.getTiles()) {
            result += tile.getGold() - tile.getRouteMaintenanceCost();

            tile.applyNewTurnChanges();
        }
        result -= this.calculateUnitMaintenance();

        return result;
    }

    public int calculateUnitMaintenance() {
        int unitCount = this.units.size();
        int currentTurn = this.turn;

        return ((unitCount + 1) / 2) * ((currentTurn) / 30 + 1);
    }

    public int calculateProduction() {
        int result = this.baseProduction;
        for (Tile tile : this.getTiles()) {
            this.setProduction(this.getProduction() + tile.getProduction());
        }

        return result + this.getProduction();
    }

    public int calculateHappiness() {
        int result = this.getBaseHappiness();
        for (Resource resource : Resource.values()) {
            if (resource.getResourceType().equals(ResourceType.LUXURIOUS) &&
                    this.getResourceList().hasEnough(resource, 1))
                result += 4;
        }
        for (City city : this.getCities()) {
            result -= 3 + city.getTotalCitizenCount();
            if (city.isAnnexed()) result -= 3;
        }

        return result;
    }

    public int calculateResearchPoint() {
        int result = this.baseResearchPoint + 3; //3 for capital

        for (City city : this.getCities()) {
            result += city.getTotalCitizenCount();
        }

        return result;
    }

    //CHEAT
    public void setBaseProduction(int baseProduction) {
        this.baseProduction = baseProduction;
    }

    public void setBaseHappiness(int baseHappiness) {
        this.baseHappiness = baseHappiness;
    }

    public void setBaseResearchPoint(int baseResearchPoint) {
        this.baseResearchPoint = baseResearchPoint;
    }

    public void save() {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("saves/Civilization.txt"));
            objectOutputStream.writeObject(this);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Civilization load() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("saves/Civilization.txt"));
            return (Civilization) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    //RUIN
    public String getRandomRuinEffect(Tile ruin) {//returned string shows description of the ruin effect
        ruin.setIsRuin(false);

        if (ruin.hasCivilian()) {
            switch (RandomGenerator.nextInt(2) % 2) {
                case 0:
                    return getRuinEffectOne(ruin);
                case 1:
                    return getRuinEffectThree(ruin);
                default:
                    break;
            }
        } else {
            switch (RandomGenerator.nextInt(4) % 4) {
                case 0:
                    return getRuinEffectOne(ruin);
                case 1:
                    return getRuinEffectTwo(ruin);
                case 2:
                    return getRuinEffectThree(ruin);
                case 3:
                    return getRuinEffectFour(ruin);
                default:
                    break;
            }
        }

        return "Booyah!";
    }

    private String getRuinEffectOne(Tile ruin)
    {
        this.setGold(this.getGold() + 150);
        return "150 gold found!";
    }

    private String getRuinEffectTwo(Tile ruin)
    {
        this.addUnit(new Settler(this, ruin));
        return "A settler appears!";
    }

    private String getRuinEffectThree(Tile ruin)
    {
        this.setResearchPoint(this.getResearchPoint() + 500);
        return "500 research point found!";
    }

    private String getRuinEffectFour(Tile ruin)
    {
        this.addUnit(new Worker(this, ruin));
        return "A worker appears";
    }
}
