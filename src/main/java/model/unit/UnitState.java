package model.unit;

public enum UnitState {
    AWAKE,

    ASLEEP,
    ALERTED,
    FORTIFY,
    RECOVERING,

    GARRISONED,

    SET_FOR_SIEGE,
    ATTACKING_FROM_DISTANCE,

    MAKING_CITY,
    WORKING,

    PILLAGING
    //TODO mrb used equals method in this enum maybe we need to overwrite this method in this enum
}
