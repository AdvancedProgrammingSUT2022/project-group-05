package view.enums;

import utility.ListUtility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static view.enums.Entity.*;

public enum GameMenuCommand{
    //INFO COMMANDS
    INFO_RESEARCH("\\s*info\\s+research(?<entities>.*)", List.of()),
    INFO_UNITS("\\s*info\\s+units(?<entities>.*)", List.of()),
    INFO_CITIES("\\s*info\\s+cities(?<entities>.*)", List.of()),
    INFO_DIPLOMACY("\\s*info\\s+diplomacy(?<entities>.*)", List.of()),
    INFO_VICTORY("\\s*info\\s+victory(?<entities>.*)", List.of()),
    INFO_DEMOGRAPHICS("\\s*info\\s+demographics(?<entities>.*)", List.of()),
    INFO_NOTIFICATIONS("\\s*info\\s+notifications(?<entities>.*)", List.of()),
    INFO_MILITARY("\\s*info\\s+military(?<entities>.*)", List.of()),
    INFO_ECONOMIC("\\s*info\\s+economic(?<entities>.*)", List.of()),
    INFO_DIPLOMATIC("\\s*info\\s+diplomatic(?<entities>.*)", List.of()),
    INFO_DEALS("\\s*info\\s+deals(?<entities>.*)", List.of()),

    //SELECT COMMANDS
    SELECT_UNIT_MILITARY("\\s*select\\s+unit\\s+military(?<entities>.*)", List.of(X_POSITION.getKey(), Y_POSITION.getKey())),
    SELECT_UNIT_CIVILIAN("\\s*select\\s+unit\\s+civilian(?<entities>.*)", List.of(X_POSITION.getKey(), Y_POSITION.getKey())),
    SELECT_CITY_POSITION("\\s*select\\s+city(?<entities>.*)", List.of(X_POSITION.getKey(), Y_POSITION.getKey())),
    SELECT_CITY_NAME("\\s*select\\s+city(?<entities>.*)", List.of(CITY_NAME.getKey())),

    //UNIT COMMANDS
    UNIT_MOVE("\\s*unit\\s+move(?<entities>.*)", List.of(X_POSITION.getKey(), Y_POSITION.getKey())),
    UNIT_SLEEP("\\s*unit\\s+sleep(?<entities>.*)", List.of()),
    UNIT_ALERT("\\s*unit\\s+alert(?<entities>.*)", List.of()),
    UNIT_FORTIFY("\\s*unit\\s+fortify(?<entities>.*)", List.of()),
    UNIT_HEAL("\\s*unit\\s+heal(?<entities>.*)", List.of()),
    UNIT_GARRISON("\\s*unit\\s+garrison(?<entities>.*)", List.of()),
    UNIT_SETUP_RANGED("\\s*unit\\s+setup\\s+ranged(?<entities>.*)", List.of()),
    UNIT_ATTACK("\\s*unit\\s+attack(?<entities>.*)", List.of(X_POSITION.getKey(), Y_POSITION.getKey())),
    UNIT_CANCEL("\\s*unit\\s+cancel(?<entities>.*)", List.of()),
    UNIT_WAKE("\\s*unit\\s+wake(?<entities>.*)", List.of()),
    UNIT_DELETE("\\s*unit\\s+delete(?<entities>.*)", List.of()),

    UNIT_FOUND_CITY("\\s*unit\\s+found\\s+city(?<entities>.*)", List.of()),
    UNIT_BUILD("\\s*unit\\s+build(?<entities>.*)", List.of(BUILDING.getKey())),
    UNIT_REMOVE_JUNGLE("\\s*unit\\s+remove\\s+jungle(?<entities>.*)", List.of()),
    UNIT_REMOVE_ROUTE("\\s*unit\\s+remove\\s+route(?<entities>.*)", List.of()),
    UNIT_REPAIR("\\s*unit\\s+repair(?<entities>.*)", List.of()),

    //CITY COMMANDS


    //MAP COMMANDS
    MAP_SHOW_CITY("\\s*map\\s+show(?<entities>.*)", List.of(CITY_NAME.getKey())),
    MAP_SHOW_POSITION("\\s*map\\s+show(?<entities>.*)", List.of(X_POSITION.getKey(), Y_POSITION.getKey())),
    MAP_MOVE("\\s*map\\s+move(?<entities>.*)", List.of(DIRECTION.getKey())),

    //CHEAT CODES
    INCREASE_TURN("\\s*increase-turn(?<entities>.*)", List.of(AMOUNT.getKey())),
    INCREASE_GOLD("\\s*increase-gold(?<entities>.*)", List.of(AMOUNT.getKey())),


    //END OF TURN
    END("\\s*end\\s+turn(?<entities>.*)", List.of()),
    //SHOW CURRENT MENU
    MENU_SHOW_CURRENT("\\s*menu\\s+show-current(?<entities>.*)", List.of()),
    //EXIT GAME MENU
    MENU_EXIT("\\s*menu\\s+exit(?<entities>.*)", List.of()),
    //EXIT
    EXIT("\\s*exit(?<entities>.*)", List.of());

    //END OF ENUMS


    private final String regex;
    private final ArrayList<String> requiredKeys;
    private final static HashMap<GameMenuCommand, Pattern> patterns;

    GameMenuCommand (String regex, List<String> requiredKeys)
    {
        this.regex = regex;
        this.requiredKeys = new ArrayList<>(requiredKeys);
    }

    static {
        patterns = new HashMap<>();
        for (GameMenuCommand command : GameMenuCommand.values()) {
            patterns.put(command, Pattern.compile(command.regex));
        }
    }

    public HashMap<String, String> getHashMap(String input, GameMenuCommand command) {
        Matcher matcher = patterns.get(command).matcher(input);
        if (!matcher.matches()) return null;

        HashMap<String, String> result = extractEntities(input);
        if (result == null) return null;

        if (!ListUtility.isEqual(new ArrayList<String>(result.keySet()), requiredKeys)) return null;

        return result;
    }
}
