package view.enums;

public enum MainMenuCommand{
    USER_LOGOUT("\\s*user\\s+logout(?<entities>.*)"),

    //TWO OR MORE USERS START THE GAME
    PLAY_GAME("\\s*play\\s+game(?<entities>.*)"), //USERNAME

    MENU_ENTER("\\s*menu\\s+enter(?<entities>.*)"), //MENU_NAME
    MENU_SHOW_CURRENT("\\s*menu\\s+show-current(?<entities>.*)"),
    MENU_EXIT("\\s*menu\\s+exit(?<entities>.*)"),
    EXIT("\\s*exit(?<entities>.*)");



    private String regex;

    MainMenuCommand (String regex) {
        this.regex = regex;
    }
}
