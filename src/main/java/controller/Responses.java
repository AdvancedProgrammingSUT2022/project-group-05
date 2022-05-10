package controller;

public enum Responses{
    //MAP
    INVALID_DESTINATION("error: invalid destination"),
    //LOGIN
    USERNAME_NOT_FOUND("error: username not found"),

    //UNIT CONTROLLER
        //ERRORS
    ALREADY_A_UNIT_IN_TILE("error: there is already a unit in this tile"),
    UNABLE_TO_MOVE_UNIT_HERE("error: unable to move unit here"),
    ALREADY_ASLEEP("error: unit is already asleep"),
    ALREADY_ALERTED("error: unit is already alerted"),
    ALREADY_FORTIFIED("error: unit is already fortified"),
    ALREADY_RECOVERED("error: unit is already recovered"),
    ALREADY_GARRISONED("error: unit is already garrisoned"),
    ALREADY_SETUP("error: unit is already setup"),
    ALREADY_AWAKE("error: unit is already awake"),
        //MESSAGES
    UNIT_MOVED("unit moves here successfully"),
    UNIT_SLEPT("unit slept successfully"),
    UNIT_ALERTED("unit alerted successfully"),
    UNIT_FORTIFIED("unit fortified successfully"),
    UNIT_RECOVERING("unit recovered successfully"),
    UNIT_GARRISONED("unit garrisoned successfully"),
    UNIT_SETUP("unit setup successfully"),
    UNIT_AWAKENED("unit awakened successfully"),
    UNIT_DELETED("unit deleted successfully")

    ;

    private final String response;

    Responses(String response) {
        this.response = response;
    }

    public String getResponse() {
        return this.response;
    }
}
