package view.enums;

public enum MainMenuCommand{
    USER_LOGOUT("user\\s+logout"),

    //TWO OR MORE USERS START THE GAME
    PLAY_GAME("play\\s+game"), //USERNAME

    MENU_ENTER("menu\\s+enter"), //MENU_NAME
    MENU_SHOW_CURRENT("menu\\s+show-current"),
    MENU_EXIT("menu\\s+exit");



    private String regex;

    MainMenuCommand (String regex) {
        this.regex = regex;
    }
}
