package view.enums;

import utility.ListUtility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static view.enums.Entity.*;

public enum ProfileMenuCommand{
    PROFILE_CHANGE_NICKNAME("\\s*profile\\s+change(?<entities>.*)", List.of(NICKNAME.getKey())),
    PROFILE_CHANGE_PASSWORD("\\s*profile\\s+change(?<entities>.*)", List.of(PASSWORD.getKey(), OLD_PASSWORD.getKey(), NEW_PASSWORD.getKey())),
    MENU_SHOW_CURRENT("\\s*menu\\s+show-current(?<entities>.*)", List.of()),
    MENU_EXIT("\\s*menu\\s+exit(?<entities>.*)", List.of()),
    EXIT("\\s*exit(?<entities>.*)", List.of());

    private final String regex;
    private final ArrayList<String> requiredKeys;
    private final static HashMap<ProfileMenuCommand, Pattern> patterns;

    ProfileMenuCommand (String regex, List<String> requiredKeys)
    {
        this.regex = regex;
        this.requiredKeys = new ArrayList<>(requiredKeys);
    }

    static {
        patterns = new HashMap<>();
        for (ProfileMenuCommand command : ProfileMenuCommand.values()) {
            patterns.put(command, Pattern.compile(command.regex));
        }
    }

    public static HashMap<String, String> getHashMap(String input, ProfileMenuCommand command) {
        Matcher matcher = patterns.get(command).matcher(input);
        if (!matcher.matches()) return null;

        HashMap<String, String> result = extractEntities(matcher.group("entities"));
        if (result == null) return null;

        if (!ListUtility.isEqual(new ArrayList<String>(result.keySet()), command.requiredKeys)) return null;

        return result;
    }
}
