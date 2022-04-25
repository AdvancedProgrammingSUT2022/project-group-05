package view.enums;

public enum ProfileMenuCommand{
    PROFILE_CHANGE_NICKNAME("\\s*profile\\s+change(?<entities>.*)"), //NICKNAME
    PROFILE_CHANGE_PASSWORD("\\s*profile\\s+change(?<entities>.*)"), //OLD_PASSWORD, NEW_PASSWORD
    MENU_SHOW_CURRENT("\\s*menu\\s+show-current(?<entities>.*)"),
    MENU_EXIT("\\s*menu\\s+exit(?<entities>.*)"),
    EXIT("\\s*exit(?<entities>.*)");

    private String regex;

    ProfileMenuCommand (String regex) {
        this.regex = regex;
    }
}
