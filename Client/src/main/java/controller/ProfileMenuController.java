//package controller;
//
//import javafx.scene.layout.Pane;
//import model.User;
//import view.menu.ErrorBox;
//import view.menu.MainMenu;
//
//public class ProfileMenuController {
//
//    public UserDatabaseController userDatabaseController = new UserDatabaseController();
//
//    public void changeNickname(String newNickname, Pane father) {
//        User user = UserDatabaseController.getUserByUsername(MainMenu.username);
//        if (UserDatabaseController.getUserByNickname(newNickname) != null) {
//            ErrorBox.getErrorBox("user with nickname " + newNickname + " already exists", father, true);
//        } else {
//            userDatabaseController.changeNickname(user, newNickname);
//            ErrorBox.getErrorBox("nickname changed successfully", father, false);
//        }
//    }
//
//    public void changePassword(String oldPassword, String newPassword, String repeatPassword, Pane father) {
//        User user = UserDatabaseController.getUserByUsername(MainMenu.username);
//        if (!user.getPassword().equals(oldPassword)) {
//            ErrorBox.getErrorBox(Responses.INVALID_CURRENT_PASSWORD.getResponse(), father, true);
//        } else if (oldPassword.equals(newPassword)) {
//            ErrorBox.getErrorBox(Responses.DUPLICATED_PASSWORD.getResponse(), father, true);
//        } else if (!newPassword.equals(repeatPassword)) {
//            ErrorBox.getErrorBox("repeated password incorrect", father, true);
//        } else {
//            userDatabaseController.changePassword(user, newPassword);
//            ErrorBox.getErrorBox(Responses.PASSWORD_CHANGED.getResponse(), father, false);
//        }
//    }
//
//    public void changeProfileImage(String newImageAddress, Pane father) {
//        User user = UserDatabaseController.getUserByUsername(MainMenu.username);
//        userDatabaseController.changeImage(user, newImageAddress);
//    }
//}
