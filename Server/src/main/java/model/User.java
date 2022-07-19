package model;

import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String nickname;
    private String password;
    private String imageAddress;
    private int score;

    public User(String username, String nickname, String password, String imageAddress, int score) {
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.imageAddress = imageAddress;
        this.score = score;
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
}
