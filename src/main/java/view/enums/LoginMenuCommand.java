package view.enums;

import utility.ListUtility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static view.enums.Entity.*;

public enum LoginMenuCommand{
    USER_LOGIN("\\s*user\\s+login(?<entities>.*)", List.of(USERNAME.getKey(), PASSWORD.getKey())),
    USER_CREATE("\\s*user\\s+create(?<entities>.*)", List.of(USERNAME.getKey(), PASSWORD.getKey(), NICKNAME.getKey())),
    MENU_SHOW_CURRENT("\\s*menu\\s+show-current(?<entities>.*)", List.of()),
    EXIT("\\s*exit(?<entities>.*)", List.of());

    private final String regex;
    private final ArrayList<String> requiredKeys;
    private final static HashMap<LoginMenuCommand, Pattern> patterns;

    LoginMenuCommand (String regex, List<String> requiredKeys)
    {
        this.regex = regex;
        this.requiredKeys = new ArrayList<>(requiredKeys);
    }

    static {
        patterns = new HashMap<>();
        for (LoginMenuCommand command : LoginMenuCommand.values()) {
            patterns.put(command, Pattern.compile(command.regex));
        }
    }

    public static HashMap<String, String> getHashMap(String input, LoginMenuCommand command) {
        Matcher matcher = patterns.get(command).matcher(input);
        if (!matcher.matches()) return null;

        HashMap<String, String> result = extractEntities(input);
        if (result == null) return null;

        if (!ListUtility.isEqual(new ArrayList<String>(result.keySet()), command.requiredKeys)) return null;

        return result;
    }
}
