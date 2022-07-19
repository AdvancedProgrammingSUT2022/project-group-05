package controller;

import model.User;


public class LoginMenuController {

    public void createUser(String username, String password, String repeatPassword, String nickname) {

    }

    public String loginUser(String username, String password) {
        User user = UserDatabaseController.getUserByUsername(username);
        if (user == null)
            return Responses.USERNAME_NOT_FOUND.getResponse();
        if (!user.getPassword().equals(password))
            return Responses.USERNAME_PASSWORD_DIDNT_MATCH.getResponse();
        return Responses.USER_LOGGED_IN.getResponse();
    }

}
