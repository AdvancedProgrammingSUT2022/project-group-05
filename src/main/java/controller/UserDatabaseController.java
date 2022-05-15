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

    private void updateDatabase(ArrayList<HashMap<String, String>> users) {
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

    private int getUserIndexByUsername(String username) {
        ArrayList<HashMap<String, String>> users = loadDatabase();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).get("username").equals(username)) {
                return i;
            }
        }
        return -1;
    }

    public void addUser(User newUser) { // add new user to database
        ArrayList<HashMap<String, String>> users = loadDatabase();
        HashMap<String, String> userNew = new HashMap<>();
        userNew.put("username", newUser.getUsername());
        userNew.put("password", newUser.getPassword());
        userNew.put("nickname", newUser.getNickname());
        users.add(userNew);
        this.updateDatabase(users);
    }

    public void removeUser(User user) {
        ArrayList<HashMap<String, String>> users = loadDatabase();
        HashMap<String, String> oldUser = new HashMap<>();
        oldUser.put("username", user.getUsername());
        oldUser.put("password", user.getPassword());
        oldUser.put("nickname", user.getNickname());
        users.remove(oldUser);
        this.updateDatabase(users);
    }

    public void changeNickname(User user, String newNickname) {
        ArrayList<HashMap<String, String>> users = loadDatabase();
        int userIndex = this.getUserIndexByUsername(user.getUsername());
        users.get(userIndex).put("nickname", newNickname);
        this.updateDatabase(users);
    }

    public void changePassword(User user, String newPassword) {
        ArrayList<HashMap<String, String>> users = loadDatabase();
        int userIndex = this.getUserIndexByUsername(user.getUsername());
        users.get(userIndex).put("password", newPassword);
        this.updateDatabase(users);
    }

    public static User getUserByUsername(String username) {
        ArrayList<HashMap<String, String>> users = loadDatabase();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).get("username").equals(username)) {
                HashMap<String, String> userData = users.get(i);
                User user = new User(userData.get("username"), userData.get("nickname"), userData.get("password"));
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
                User user = new User(userData.get("username"), userData.get("nickname"), userData.get("password"));
                return user;
            }
        }

        return null;
    }
}