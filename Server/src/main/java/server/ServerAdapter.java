package server;

import com.google.gson.Gson;
import controller.UserDatabaseController;
import model.User;

import java.util.ArrayList;
import java.util.HashMap;

public class ServerAdapter {

    public static String login(Request request) {
        HashMap<String, Object> params = request.getParams();
        String username = (String) params.get("username");
        String password = (String) params.get("password");
        User user = UserDatabaseController.getUserByUsername(username);
        if (user == null)
            return "error: username doesn't exists";
        if (!user.getPassword().equals(password))
            return "error: username and password don't match";
        return "login successful";
    }

    public static String getUser(Request request) {
        String username = (String) request.getParams().get("username");
        User user = UserDatabaseController.getUserByUsername(username);
        Gson gson = new Gson();
        return gson.toJson(user);
    }

    public static String register(Request request) {
        HashMap<String, Object> params = request.getParams();
        String username = (String) params.get("username");
        String password = (String) params.get("password");
        String repeatPassword = (String) params.get("repeatPassword");
        String nickname = (String) params.get("nickname");
        String imageAddress = (String) params.get("imageAddress");
        User previousUsernameHolder = UserDatabaseController.getUserByUsername(username);
        User previousNicknameHolder = UserDatabaseController.getUserByNickname(nickname);
        if (previousUsernameHolder != null)
            return "error: username already taken";
        if (previousNicknameHolder != null)
            return "error: nickname already taken";
        if (!password.equals(repeatPassword))
            return "error: password and repeat don't match";
        if (password.length() < 8)
            return "error: password must be at least 8 characters";
        User user = new User(username, nickname, password, imageAddress, 0);
        UserDatabaseController.addUser(user);
        return "register successful";

    }

    public static String changePassword(Request request) {
        HashMap<String, Object> params = request.getParams();
        String oldPassword = (String) params.get("oldPassword");
        String newPassword = (String) params.get("newPassword");
        String newPasswordRepeat = (String) params.get("newPasswordRepeat");
        String username = (String) params.get("username");
        User user = UserDatabaseController.getUserByUsername(username);
        if (!user.getPassword().equals(oldPassword))
            return "error: current password is incorrect";
        if (!newPassword.equals(newPasswordRepeat))
            return "error: new password and repeat don't match";
        if (newPassword.length() < 8)
            return "error: password must be at least 8 characters";
        if (oldPassword.equals(newPassword))
            return "error: new password can't be the same as old password";
        UserDatabaseController.changePassword(user, newPassword);
        return "password changed successfully";
    }

    public static String changeNickname(Request request) {
        HashMap<String, Object> params = request.getParams();
        String newNickname = (String) params.get("newNickname");
        String username = (String) params.get("username");
        User user = UserDatabaseController.getUserByUsername(username);
        User previousNicknameHolder = UserDatabaseController.getUserByNickname(newNickname);
        if (user.getUsername().equals(newNickname))
            return "error: old and new nickname can't be the same";
        if (previousNicknameHolder != null)
            return "error: nickname already taken";
        UserDatabaseController.changeNickname(user, newNickname);
        return "nickname changed successfully";
    }

    public static String changeImage(Request request) {
        HashMap<String, Object> params = request.getParams();
        String newImageAddress = (String) params.get("newImageAddress");
        String username = (String) params.get("username");
        User user = UserDatabaseController.getUserByUsername(username);
        UserDatabaseController.changeImage(user, newImageAddress);
        return "image changed successfully";
    }

    public static String getUsers(Request request) {
        ArrayList<HashMap<String, String>> users = UserDatabaseController.loadDatabase();
        Gson gson = new Gson();
        return gson.toJson(users);
    }
}