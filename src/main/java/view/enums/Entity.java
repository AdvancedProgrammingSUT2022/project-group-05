package view.enums;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Entity{
    //normal entities
    USERNAME("username", "(--username|-u)\\s+(?<username>\\w+)\\s*"),
    PASSWORD("password", "(--password|-p)\\s+(?<password>\\S+)\\s*"),
    NEW_PASSWORD("newPassword", "(--new-password|-np)\\s+(?<newPassword>\\S+)\\s*"),
    OLD_PASSWORD("oldPassword", "(--old-password|-op)\\s+(?<oldPassword>\\S+)\\s*"),
    NICKNAME("nickname", "(--nickname|-n)\\s+(?<nickname>[a-zA-Z0-9 ]+)\\s*"),
    AMOUNT("amount", "(--amount|-a)\\s+(?<amount>-?\\d+)\\s*"),
    X_POSITION("xPosition", "(--x-position|-x)\\s+(?<xPosition>-?\\d+)\\s*"),
    Y_POSITION("yPosition", "(--y-position|-y)\\s+(?<yPosition>-?\\d+)\\s*"),
    MENU_NAME("menuName", "(--menu-name|-m)\\s+(?<menuName>profile|login|game|main)\\s*"),
    CITY_NAME("cityName", "(--city-name|-c)\\s+(?<cityName>[a-zA-Z ]+)\\s*"),
    UNIT_NAME("unitName", "(--unit-name|-un)\\s+(?<unitName>[a-zA-Z ]+)\\s*"),
    DIRECTION("direction", "(--direction|-d)\\s+(?<direction>U|D|R|L)\\s*"),
    BUILDING("building", "(--building|-b)\\s+(?<building>[a-zA-Z ]+)\\s*"),
    IMPROVEMENT("improvement", "(--improvement|-i)\\s+(?<improvement>\\S+)\\s*"),
    ROUTE("route", "(--route|-r)\\s+(?<route>\\S+)\\s*"),
    PLAYER("player", "(--player|-p)(?<index>[1-9]\\d*)\\s+(?<username>\\w+)\\s*");

    private final String key;
    private final String regex;
    private final static HashMap<Entity, Pattern> patterns;

    Entity(String key, String regex) {
        this.key = key;
        this.regex = regex;
    }

    static {
        patterns = new HashMap<>();
        for (Entity command : Entity.values()) {
            patterns.put(command, Pattern.compile(command.regex));
        }
    }

    public String getKey() {
        return this.key;
    }

    public static Matcher getMatcher(String input, Entity entity) {
        Matcher matcher = patterns.get(entity).matcher(input);
        return matcher.matches() ? matcher : null;
    }

    public static Entity findEntity(String input) {
        for (Entity entity : Entity.values()) {
            if (getMatcher(input, entity) != null) return entity;
        }
        return null;
    }

    public static HashMap<String, String> extractEntities(String input) {
        HashMap<String, String> entities = new HashMap<>();

        String[] words = input.split("\\s+-"); //TODO... find the best way to split into entities.

        for (String word : words) {
            if (word.equals("")) continue;

            Entity entity = findEntity("-" + word);
            if (entity == null) return null;
            if (entities.containsKey(entity.key)) return null;

            Matcher matcher = getMatcher("-" + word, entity);
            if (matcher == null) return null;

            if (entity != PLAYER) entities.put(entity.key, matcher.group(entity.key));
            else entities.put(entity.key + matcher.group("index"), matcher.group("username"));
        }

        return entities;
    }
}
