package view.enums;

public enum LoginMenuCommand{
    USER_LOGIN("\\s*user\\s+login(?<entities>.*)"), //USERNAME, PASSWORD
    USER_CREATE("\\s*user\\s+create(?<entities>.*)"), //USERNAME, PASSWORD, NICKNAME
    MENU_SHOW_CURRENT("\\s*menu\\s+show-current(?<entities>.*)");

    private String regex;

    LoginMenuCommand (String regex) {
        this.regex = regex;
    }
}
