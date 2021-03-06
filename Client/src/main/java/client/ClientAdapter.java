package client;


import com.google.gson.Gson;
import model.Lobby;

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

    public static String changeScore(int score, String username) {
        Request request = new Request("changeScore");
        request.addParams("score", score);
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

    public static String removeFriend(String friendUsername, String username) {
        Request request = new Request("removeFriend");
        request.addParams("friendUsername", friendUsername);
        request.addParams("username", username);
        return request.convertToJson();
    }

    public static String rejectFriend(String invitingFriendUsername, String username) {
        Request request = new Request("rejectFriend");
        request.addParams("invitingFriendUsername", invitingFriendUsername);
        request.addParams("username", username);
        return request.convertToJson();
    }

    public static String inviteFriend(String friendUsername, String username) {
        Request request = new Request("inviteFriend");
        request.addParams("friendUsername", friendUsername);
        request.addParams("username", username);
        return request.convertToJson();
    }

    public static String inviteToLobby(Lobby lobby, String friendUsername) {
        Request request = new Request("inviteToLobby");
        request.addParams("friendUsername", friendUsername);
        request.addParams("lobby", new Gson().toJson(lobby));
        return request.convertToJson();
    }

    public static String joinLobby(Lobby lobby, String username) {
        Request request = new Request("joinLobby");
        request.addParams("lobby", new Gson().toJson(lobby));
        request.addParams("username", username);
        return request.convertToJson();
    }

    public static String createLobby(String hostUsername, String id) {
        Request request = new Request("createLobby");
        request.addParams("hostUsername", hostUsername);
        request.addParams("id", id);

        return request.convertToJson();
    }

    public static String closeLobby(Lobby lobby) {
        Request request = new Request("closeLobby");
        request.addParams("lobby", new Gson().toJson(lobby));
        return request.convertToJson();
    }

    public static String startGame(Lobby lobby) {
        Request request = new Request("startGame");
        request.addParams("lobby", new Gson().toJson(lobby));
        return request.convertToJson();
    }

    public static String getOnlineUsers() {
        Request request = new Request("getOnlineUsers");
        return request.convertToJson();
    }

    public static String userLoggedOut(String username) {
        Request request = new Request("userLoggedOut");
        request.addParams("username", username);
        return request.convertToJson();
    }

    public static String searchFriend(String friendUsername) {
        Request request = new Request("searchFriend");
        request.addParams("friendUsername", friendUsername);
        return request.convertToJson();
    }

}
