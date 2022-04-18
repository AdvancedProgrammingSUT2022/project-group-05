package model.unit;

import model.game.Civilization;

public abstract class Unit {
    private Civilization civilization;
    private int health;
    private int cost;

    private int maxMovement;
    private int remainingMovement;

    private int meleeStrength; //also defence
    private int rangeStrength;
    private int experience;
    private int level;

    private int state;
}
