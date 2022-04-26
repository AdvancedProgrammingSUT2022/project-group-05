package view.enums;

import utility.ListUtility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static view.enums.Entity.*;

public enum MainMenuCommand{
    USER_LOGOUT("\\s*user\\s+logout(?<entities>.*)", List.of()),

    //TWO OR MORE USERS START THE GAME
    PLAY_GAME("\\s*play\\s+game(?<entities>.*)", List.of()), //TODO... getting multiple players

    MENU_ENTER("\\s*menu\\s+enter(?<entities>.*)", List.of(MENU_NAME.getKey())), //MENU_NAME
    MENU_SHOW_CURRENT("\\s*menu\\s+show-current(?<entities>.*)", List.of()),
    MENU_EXIT("\\s*menu\\s+exit(?<entities>.*)", List.of()),
    EXIT("\\s*exit(?<entities>.*)", List.of());

    private final String regex;
    private final ArrayList<String> requiredKeys;
    private final static HashMap<MainMenuCommand, Pattern> patterns;

    MainMenuCommand (String regex, List<String> requiredKeys)
    {
        this.regex = regex;
        this.requiredKeys = new ArrayList<>(requiredKeys);
    }

    static {
        patterns = new HashMap<>();
        for (MainMenuCommand command : MainMenuCommand.values()) {
            patterns.put(command, Pattern.compile(command.regex));
        }
    }

    public HashMap<String, String> getHashMap(String input, MainMenuCommand command) {
        Matcher matcher = patterns.get(command).matcher(input);
        if (!matcher.matches()) return null;

        HashMap<String, String> result = extractEntities(input);
        if (result == null) return null;

        if (!ListUtility.isEqual(new ArrayList<String>(result.keySet()), requiredKeys)) return null;

        return result;
    }
}
