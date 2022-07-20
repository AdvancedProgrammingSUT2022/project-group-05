package model;

import java.util.ArrayList;

public class Lobby{
    private int size;
    private String id;

    private String hostUsername;
    private ArrayList<String> playerUsernames;

    public Lobby(String id, String hostUsername) {
        this.setId(id);
        this.setHostUsername(hostUsername);
    }

    //SETTERS
    public void setSize(int size) {
        this.size = size;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setHostUsername(String hostUsername) {
        this.hostUsername = hostUsername;
    }

    public void setPlayerUsernames(ArrayList<String> playerUsernames) {
        this.playerUsernames = playerUsernames;
    }

    //GETTER
    public int getSize() {
        return this.size;
    }

    public String getId() {
        return this.id;
    }

    public String getHostUsername() {
        return this.hostUsername;
    }

    public ArrayList<String> getPlayerUsernames() {
        return this.playerUsernames;
    }

    //METHOD
    public void removeUser(String username) {
        this.playerUsernames.remove(username);
    }

    public void addUser(String username) {this.playerUsernames.add(username);}
}
