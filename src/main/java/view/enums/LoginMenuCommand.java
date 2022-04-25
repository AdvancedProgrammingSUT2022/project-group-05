package view.enums;

public enum LoginMenuCommand{
    USER_LOGIN("user\\s+login"), //USERNAME, PASSWORD
    USER_CREATE("user\\s+create"), //USERNAME, PASSWORD, NICKNAME
    MENU_SHOW_CURRENT("menu\\s+show-current");

    private String regex;

    LoginMenuCommand (String regex) {
        this.regex = regex;
    }
}
