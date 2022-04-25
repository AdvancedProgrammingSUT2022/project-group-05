package view.enums;

public enum Entity{
    USERNAME("(--username|-u) (?<>//w+)"),
    PASSWORD(""),
    NEW_PASSWORD(""),
    OLD_PASSWORD(""),
    NICKNAME(""),
    AMOUNT(""),
    X_POSITION(""),
    Y_POSITION(""),
    POSITION(""),
    MENU_NAME(""),
    CITY_NAME(""),
    DIRECTION(""),
    BUILDING("");

    private String regex;

    Entity(String regex) {
        this.regex = regex;
    }


}
