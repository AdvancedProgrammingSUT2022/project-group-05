package controller;

public enum Responses{
    //MAP
        //ERRORS
    UNREACHABLE_DESTINATION("error: unreachable destination"),
    INVALID_DESTINATION("error: invalid destination"),
    TILE_OUT_OF_BOUND("error: tile is out of bounds"),
    NO_SOLDIER_ON_TILE("error: no soldier on selected tile"),
    NO_CIVILIAN_IN_TILE("error: no civilian on selected tile"),
    UNIT_NOT_FROM_CIVILIZATION("error: this unit is not from your civilization"),
    CITY_NOT_FROM_CIVILIZATION("error: this city is not from your civilization"),
    NOT_CITY_CENTER("error: not a city center"),
    NO_CITY_WITH_NAME("error: there is no city with this name"),
        //MESSAGES
    SOLDIER_SELECTED_SUCCESSFULLY("soldier selected successfully"),
    CIVILIAN_SELECTED_SUCCESSFULLY("civilian selected successfully"),
    CITY_SELECTED_SUCCESSFULLY("city selected successfully"),


    //LOGIN & MAIN
        //ERRORS
    INVALID_CURRENT_PASSWORD("current password is invalid"),
    DUPLICATED_PASSWORD("error: please enter a new password"),
    USERNAME_NOT_FOUND("error: username not found"),
    USERNAME_PASSWORD_DIDNT_MATCH("error: username and password didn't match!"),
    ILLEGAL_PLAYER_NUMBER("error: illegal player number"),
        //MESSAGES
    USER_LOGGED_IN("user logged in successfully!"),
    NICKNAME_CHANGED("nickname changed successfully!"),
    PASSWORD_CHANGED("password changed successfully!"),


    //CITY CONTROLLER
        //ERRORS
    NO_CITY_SELECTED("error: no city selected"),
    NO_UNIT_WITH_THIS_NAME("error: there is no unit with this name"),
    UNIT_IS_ALREADY_BEING_BUILT("error: this unit is already being built"),
    REQUIRED_RESEARCH_NOT_FOUND("error: required research not found"),
    NOT_ENOUGH_RESOURCE("error: not enough resource"),
    CANT_ADD_TILE_TO_CITY("error: can't add tile to city"),
    NOT_ENOUGH_GOLD("error: not enough gold"),
    CITY_NAME_CAB("error: city name cannot exceed 7 characters"),
    TILE_NOT_IN_TERRITORY("error: tile not in territory"),
        //MESSAGES
    CREATING_UNIT_STARTED("creating unit started"),
    CREATING_BUILDING_STARTED("creating building started"),
    TILE_BOUGHT_SUCCESSFULLY("tile bought successfully"),
    UNIT_PURCHASED_SUCCESSFULLY("unit purchased successfully"),
    BUILDING_PURCHASED_SUCCESSFULLY("building purchased successfully"),



    //UNIT CONTROLLER
        //ERRORS
    NO_UNIT_SELECTED("error: no unit selected"),
    CANT_ATTACK_OUR_CITY("error: can't attack our city"),
    UNIT_NOT_SOLDIER("error: this is not a soldier unit"),
    UNIT_NOT_SETTLER("error: not a settler"),
    UNIT_NOT_WORKER("error: not a worker"),
    ALREADY_A_UNIT_IN_TILE("error: there is already a unit in this tile"),
    UNABLE_TO_MOVE_UNIT_HERE("error: unable to move unit here"),
    ALREADY_ASLEEP("error: unit is already asleep"),
    ALREADY_ALERTED("error: unit is already alerted"),
    ALREADY_FORTIFIED("error: unit is already fortified"),
    ALREADY_RECOVERED("error: unit is already recovered"),
    ALREADY_GARRISONED("error: unit is already garrisoned"),
    ALREADY_SETUP("error: unit is already setup"),
    ALREADY_AWAKE("error: unit is already awake"),
    CANT_ATTACK_THIS_TILE("error: can't attack this tile"),
        //MESSAGES
    UNIT_MOVED("unit moves here successfully"),
    UNIT_SLEPT("unit slept successfully"),
    UNIT_ALERTED("unit alerted successfully"),
    UNIT_FORTIFIED("unit fortified successfully"),
    UNIT_RECOVERING("unit recovered successfully"),
    UNIT_GARRISONED("unit garrisoned successfully"),
    UNIT_SETUP("unit setup successfully"),
    UNIT_AWAKENED("unit awakened successfully"),
    UNIT_DELETED("unit deleted successfully"),
    UNIT_ATTACKED("unit attacked successfully"),
    CITY_FOUNDED("city founded successfully"),
    ATTACK_COMPLETE("attack complete"),
    CITY_CONQUERED("city conquered successfully"),

    //GAME CONTROLLER
        //ERRORS
    NO_IMPROVEMENT_WITH_NAME("error: no improvement exists with given name"),
    NO_ROUTE_WITH_NAME("error: no route exists with given name"),
    NO_RESEARCH_WITH_NAME("error: no research exists with given name"),
    LOCKED_RESEARCH("error: research still locked"),
    RESEARCH_ALREADY_DONE("error: research already done"),
    ILLEGAL_TURNS("error: Illegal amount of turns"),
    ALREADY_IMPROVEMENT_ON_TILE("error: already an improvement on tile"),
    IMPROVEMENT_DOESNT_MATCH_TERRAIN("error: this improvement doesn't match this feature and terrain"),
    ALREADY_ROUTE("error: already a route on tile"),
    NO_FOREST_IN_TILE("error: no forest on current tile"),
    NO_JUNGLE_IN_TILE("error: no jungle on current tile"),
    FEATURE_AND_IMPROVEMENT_ERROR("error: can't remove feature of a tile with improvement"),
        //MESSAGES
    IMPROVEMENT_DONE("improvement construction started"),
    ROUTE_DONE("route construction started"),
    FOREST_DONE("forest removal started"),
    JUNGLE_DONE("jungle removal started"),
        //CHEATS
    TURN_INCREASED("turn increased"),
    GOLD_INCREASED("gold increased"),
    RESEARCH_INCREASED("research point increased"),
    REVEAL_MAP("now you know where you're going"),
    UTOPIA1("so that's what living in Tehran feels like"),
    UTOPIA2("now everybody loves you :)"),
    UTOPIA3("here, you dropped this \uD83D\uDC51"), //the character is crown emoji
    MARCO("what souvenirs have you brought along Marco?"),
    TERMINATOR("hasta la vista baby!"),
    HEALING_UNIT("unit healed completely")
    ;

    private final String response;

    Responses(String response) {
        this.response = response;
    }

    public String getResponse() {
        return this.response;
    }
}
