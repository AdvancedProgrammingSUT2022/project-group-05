package model;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private String username;
    private String nickname;
    private String password;
    private String imageAddress;
    private int score;
    private ArrayList<String> friends;
    private ArrayList<String> invitingFriends; //players who have invited this user

    public User(String username, String nickname, String password, String imageAddress, int score) {
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.imageAddress = imageAddress;
        this.score = score;
        this.friends = new ArrayList<>();
        this.invitingFriends = new ArrayList<>();
    }

    public void addFriend(String friendUsername) {
        friends.add(friendUsername);
    }

    public void addInvitingFriend(String invitingFriendUsername) {
        invitingFriends.add(invitingFriendUsername);
    }

    //SETTERS
    public void setUsername(String username) {
        this.username = username;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }

    public void setFriends(ArrayList<String> friends) {
        this.friends = friends;
    }

    //GETTERS
    public String getUsername() {
        return this.username;
    }

    public String getNickname() {
        return this.nickname;
    }

    public String getPassword() {
        return this.password;
    }

    public int getScore() {
        return this.score;
    }

    public String getImageAddress() {
        return imageAddress;
    }

    public ArrayList<String> getFriends() {
        return friends;
    }

    public ArrayList<String> getInvitingFriends() {
        return invitingFriends;
    }
}
