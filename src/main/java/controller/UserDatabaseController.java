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


    public void initialize() { // for first use
        ArrayList<HashMap<String, String>> users = new ArrayList<>();
        this.updateDatabase(users);
    }

    private void updateDatabase(ArrayList<HashMap<String, String>> users) {
        try {
            FileWriter writer = new FileWriter("src/main/java/models/usersDatabase.json");
            writer.write(new Gson().toJson(users));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<HashMap<String, String>> loadDatabase() {
        try {
            String json = new String(Files.readAllBytes(Paths.get("src/main/java/models/usersDatabase.json")));
            ArrayList<HashMap<String, String>> users;
            users = new Gson().fromJson(json, new TypeToken<List<HashMap<String, String>>>() {
            }.getType());
            return users;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getUserIndexByUsername(String username) {
        ArrayList<HashMap<String, String>> users = this.loadDatabase();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).get("username").equals(username)) {
                return i;
            }
        }
        return -1;
    }

    public int getUserIndexByNickname(String nickname) {
        ArrayList<HashMap<String, String>> users = this.loadDatabase();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).get("nickname").equals(nickname)) {
                return i;
            }
        }
        return -1;
    }

    public boolean isPasswordCorrect(int userIndex, String password) {
        ArrayList<HashMap<String, String>> users = this.loadDatabase();
        if (users.get(userIndex).get("password").equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    public void addUser(User newUser) { // add new user to database
        ArrayList<HashMap<String, String>> users = this.loadDatabase();
        HashMap<String, String> userNew = new HashMap<>();
        userNew.put("username", newUser.getUsername());
        userNew.put("password", newUser.getPassword());
        userNew.put("nickname", newUser.getNickname());
        users.add(userNew);
        this.updateDatabase(users);
    }

    public void changeNickname(int userIndex, String newNickname) {
        ArrayList<HashMap<String, String>> users = this.loadDatabase();
        users.get(userIndex).put("nickname", newNickname);
        this.updateDatabase(users);
    }

    public void changePassword(int userIndex, String newPassword) {
        ArrayList<HashMap<String, String>> users = this.loadDatabase();
        users.get(userIndex).put("password", newPassword);
        this.updateDatabase(users);
    }

    public String getPasswordByIndex(int userIndex) {
        ArrayList<HashMap<String, String>> users = this.loadDatabase();
        return users.get(userIndex).get("password");
    }
}