package client;


import java.net.SocketException;

public class ClientAdapter {

    public static String login(String username, String password) {
        Request request = new Request("login");
        request.addParams("username", username);
        request.addParams("password", password);
        return request.convertToJson();
    }

    public static String getUser(String username) {
        Request request = new Request("getUser");
        request.addParams("username", username);
        return request.convertToJson();
    }

    public static String register(String username, String password, String repeatPassword, String nickname, String imageAddress) {
        Request request = new Request("register");
        request.addParams("username", username);
        request.addParams("password", password);
        request.addParams("repeatPassword", repeatPassword);
        request.addParams("nickname", nickname);
        request.addParams("imageAddress", imageAddress);
        return request.convertToJson();

    }

    public static String changePassword(String oldPassword, String newPassword, String repeatNewPassword, String username) {
        Request request = new Request("changePassword");
        request.addParams("oldPassword", oldPassword);
        request.addParams("newPassword", newPassword);
        request.addParams("repeatNewPassword", repeatNewPassword);
        request.addParams("username", username);
        return request.convertToJson();
    }

    public static String changeNickname(String newNickname, String username) {
        Request request = new Request("changeNickname");
        request.addParams("newNickname", newNickname);
        request.addParams("username", username);
        return request.convertToJson();
    }

    public static String changeImage(String newImageAddress, String username) {
        Request request = new Request("changeImage");
        request.addParams("newImageAddress", newImageAddress);
        request.addParams("username", username);
        return request.convertToJson();
    }

    public static String getUsers() {
        Request request = new Request("getUsers");
        return request.convertToJson();
    }

    public static String addFriend(String friendUsername, String username) {
        Request request = new Request("addFriend");
        request.addParams("friendUsername", friendUsername);
        request.addParams("username", username);
        return request.convertToJson();
    }
}
