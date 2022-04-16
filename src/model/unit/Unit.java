package model.unit;

import model.game.Civilization;

public abstract class Unit {
    private int speed;
    private int cost;
    private Civilization civilization;
    private int meleeStrength; //also defence
    private int rangeStrength;
    private int health;
    private int experience;
    private int level;
    private int maxMovement;
    private int remainingMovement;
    private int state;

}
