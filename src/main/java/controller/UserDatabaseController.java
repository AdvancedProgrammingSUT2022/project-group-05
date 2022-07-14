package controller;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.User;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserDatabaseController {

    private static void updateDatabase(ArrayList<HashMap<String, String>> users) {
        try {
            FileWriter writer = new FileWriter("database/usersDatabase.json");
            writer.write(new Gson().toJson(users));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<HashMap<String, String>> loadDatabase() {
        try {
            String json = new String(Files.readAllBytes(Paths.get("database/usersDatabase.json")));
            ArrayList<HashMap<String, String>> users;
            users = new Gson().fromJson(json, new TypeToken<List<HashMap<String, String>>>() {
            }.getType());
            return users;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static int getUserIndexByUsername(String username) {
        ArrayList<HashMap<String, String>> users = loadDatabase();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).get("username").equals(username)) {
                return i;
            }
        }
        return -1;
    }

    public static void addUser(User newUser) { // add new user to database
        ArrayList<HashMap<String, String>> users = loadDatabase();
        HashMap<String, String> userNew = new HashMap<>();
        userNew.put("username", newUser.getUsername());
        userNew.put("password", newUser.getPassword());
        userNew.put("nickname", newUser.getNickname());
        userNew.put("score", "" + newUser.getScore());
        userNew.put("image", newUser.getImageAddress());
        users.add(userNew);
        updateDatabase(users);
    }

    public static void removeUser(User user) {
        ArrayList<HashMap<String, String>> users = loadDatabase();
        HashMap<String, String> oldUser = new HashMap<>();
        oldUser.put("username", user.getUsername());
        oldUser.put("password", user.getPassword());
        oldUser.put("nickname", user.getNickname());
        oldUser.put("score", "" + user.getScore());
        oldUser.put("image", user.getImageAddress());
        users.remove(oldUser);
        updateDatabase(users);
    }

    public static void changeNickname(User user, String newNickname) {
        ArrayList<HashMap<String, String>> users = loadDatabase();
        int userIndex = getUserIndexByUsername(user.getUsername());
        users.get(userIndex).put("nickname", newNickname);
        updateDatabase(users);
    }

    public static void changePassword(User user, String newPassword) {
        ArrayList<HashMap<String, String>> users = loadDatabase();
        int userIndex = getUserIndexByUsername(user.getUsername());
        users.get(userIndex).put("password", newPassword);
        updateDatabase(users);
    }

    public static User getUserByUsername(String username) {
        ArrayList<HashMap<String, String>> users = loadDatabase();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).get("username").equals(username)) {
                HashMap<String, String> userData = users.get(i);
                User user = new User(userData.get("username"), userData.get("nickname"), userData.get("password"), userData.get("image"));
                return user;
            }
        }
        return null;
    }

    public static User getUserByNickname(String nickname) {
        ArrayList<HashMap<String, String>> users = loadDatabase();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).get("nickname").equals(nickname)) {
                HashMap<String, String> userData = users.get(i);
                User user = new User(userData.get("username"), userData.get("nickname"), userData.get("password"), userData.get("image"));
                return user;
            }
        }

        return null;
    }

    public static String[] getNicknames() {
        ArrayList<HashMap<String, String>> users = loadDatabase();
        String[] nicknames = new String[users.size()];
        for (int i = 0; i < users.size(); i++) {
            nicknames[i] = users.get(i).get("nickname");
        }
        return nicknames;
    }

    public static void changeImage(User user, String newImageAddress) {
        ArrayList<HashMap<String, String>> users = loadDatabase();
        int userIndex = getUserIndexByUsername(user.getUsername());
        users.get(userIndex).put("image", newImageAddress);
        updateDatabase(users);
    }

}