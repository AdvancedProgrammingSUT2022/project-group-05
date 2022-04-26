package controller;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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

    private int getUserIndexByUsername(String username) {
        ArrayList<HashMap<String, String>> users = this.loadDatabase();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).get("username").equals(username)) {
                return i;
            }
        }
        return -1;
    }

    private int getUserIndexByNickname(String nickname) {
        ArrayList<HashMap<String, String>> users = this.loadDatabase();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).get("nickname").equals(nickname)) {
                return i;
            }
        }
        return -1;
    }

    public void addUser(HashMap<String, String> newUser) { // add new user to database
        ArrayList<HashMap<String, String>> users = this.loadDatabase();
        if (this.getUserIndexByUsername(newUser.get("username")) != -1) {
            System.out.println("user with username " + newUser.get("username") + " already exists");
        } else if (this.getUserIndexByNickname(newUser.get("nickname")) != -1) {
            System.out.println("user with nickname " + newUser.get("nickname") + " already exists");
        } else {
            users.add(newUser);
            this.updateDatabase(users);
        }
    }

    public void changeNickname(String username, HashMap<String, String> command) { // this method needs player username
        String newNickname = command.get("nickname");
        ArrayList<HashMap<String, String>> users = this.loadDatabase();
        int index;
        if ((index = getUserIndexByNickname(newNickname)) != -1) {
            System.out.println("user with nickname " + newNickname + " already exists");
        } else if ((index = getUserIndexByUsername(username)) == -1) {
            System.out.println("wrong username");
        } else {
            users.get(index).put("nickname", newNickname);
            System.out.println("nickname changed successfully!");
        }
        this.updateDatabase(users);
    }

    public void changePassword(String username, HashMap<String, String> command) { // this method needs player username
        String oldPassword = command.get("old-password");
        String newPassword = command.get("new-password");
        ArrayList<HashMap<String, String>> users = this.loadDatabase();
        int index = getUserIndexByUsername(username);
        if (index == -1) {
            System.out.println("wrong username");
        } else if (users.get(index).get("password").compareTo(oldPassword) != 0) {
            System.out.println("current password is invalid");
        } else if (oldPassword.equals(newPassword)) {
            System.out.println("please enter a new password");
        } else {
            users.get(index).put("password", newPassword);
            System.out.println("password changed successfully!");
        }
        this.updateDatabase(users);
    }
}