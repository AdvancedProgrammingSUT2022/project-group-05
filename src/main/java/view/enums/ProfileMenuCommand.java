package view.enums;

public enum ProfileMenuCommand{
    PROFILE_CHANGE_NICKNAME("profile\\s+change"), //NICKNAME
    PROFILE_CHANGE_PASSWORD("profile\\s+change"), //OLD_PASSWORD, NEW_PASSWORD
    MENU_SHOW_CURRENT("menu\\s+show-current"),
    MENU_EXIT("menu\\s+exit");


    private String regex;

    ProfileMenuCommand (String regex) {
        this.regex = regex;
    }
}
