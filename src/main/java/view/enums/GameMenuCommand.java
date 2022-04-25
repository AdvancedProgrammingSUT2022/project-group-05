package view.enums;

public enum GameMenuCommand{
    //INFO COMMANDS
    INFO_RESEARCH("info\\s+research"),
    INFO_UNITS("info\\s+units"),
    INFO_CITIES("info\\s+cities"),
    INFO_DIPLOMACY("info\\s+diplomacy"),
    INFO_VICTORY("info\\s+victory"),
    INFO_DEMOGRAPHICS("info\\s+demographics"),
    INFO_NOTIFICATIONS("info\\s+notifications"),
    INFO_MILITARY("info\\s+military"),
    INFO_ECONOMIC("info\\s+economic"),
    INFO_DIPLOMATIC("info\\s+diplomatic"),
    INFO_DEALS("info\\s+deals"),

    //SELECT COMMANDS
    SELECT_UNIT_MILITARY("select\\s+unit\\s+military"),//POSITION
    SELECT_UNIT_CIVILIAN("select\\s+unit\\s+civilian"),//POSITION
    SELECT_CITY("select\\s+city"),//CITY_NAME
    //SELECT_CITY also by POSITION

    //UNIT COMMANDS
    UNIT_MOVE("unit\\s+move"),//POSITION
    UNIT_SLEEP("unit\\s+sleep"),
    UNIT_ALERT("unit\\s+alert"),
    UNIT_FORTIFY("unit\\s+fortify"),
    UNIT_HEAL("unit\\s+heal"),
    UNIT_GARRISON("unit\\s+garrison"),
    UNIT_SETUP_RANGED("unit\\s+setup\\s+ranged"),
    UNIT_ATTACK("unit\\s+attack"),//POSITION
    UNIT_CANCEL("unit\\s+cancel"),
    UNIT_WAKE("unit\\s+wake"),
    UNIT_DELETE("unit\\s+delete"),

    UNIT_FOUND_CITY("unit\\s+found\\s+city"),
    UNIT_BUILD("unit\\s+build"),//BUILDING
    UNIT_REMOVE_JUNGLE("unit\\s+remove\\s+jungle"),
    UNIT_REMOVE_ROUTE("unit\\s+remove\\s+route"),
    UNIT_REPAIR("unit\\s+repair"),

    //CITY COMMANDS


    //MAP COMMANDS
    MAP_SHOW("map\\s+show"),//POSITION
    //MAP_SHOW also by CITY_NAME
    MAP_MOVE("map\\s+move"),//DIRECTION

    //CHEAT CODES
    INCREASE_TURN("increase\\s+-turn"), //AMOUNT
    INCREASE_GOLD("increase\\s+-gold"), //AMOUNT


    //END OF TURN
    END("end\\s+turn"),
    //SHOW CURRENT MENU
    MENU_SHOW_CURRENT("menu\\s+show-current"),
    //EXIT GAME MENU
    MENU_EXIT("menu\\s+exit");
    //END OF ENUMS


    private String regex;

    GameMenuCommand (String regex) {
        this.regex = regex;
    }
}
