//package view.menu;
//
//import controller.ProfileMenuController;
//import controller.Responses;
//
//import java.util.HashMap;
//import java.util.Scanner;
//
//import static view.enums.ProfileMenuCommand.*;
//
//public class ProfileMenu extends Menu {
//
//    private ProfileMenuController profileMenuController = new ProfileMenuController();
//    private static String  username;
//
//
//    public ProfileMenu(Scanner scanner) {
//        super(scanner);
//    }
//
//    @Override
//    public MenuType run() {
//
//
//
//        String input;
//        HashMap<String, String> command;
//
//        printMessage("__PROFILE MENU__");
//
//        while (true) {
//            input = scanner.nextLine();
//
//            if ((command = getHashMap(input, EXIT)) != null)
//                return MenuType.EXIT;
//            else if ((command = getHashMap(input, MENU_EXIT)) != null) {
//                printMessage("exiting profile menu");
//                return MenuType.MAIN;
//            }
//            else if ((command = getHashMap(input, MENU_SHOW_CURRENT)) != null)
//                printMessage("profile menu");
//            else if ((command = getHashMap(input, PROFILE_CHANGE_PASSWORD)) != null)
//                printMessage(profileMenuController.changePassword(command, username));
//            else if ((command = getHashMap(input, PROFILE_CHANGE_NICKNAME)) != null)
//                printMessage(profileMenuController.changeNickname(command, username));
//            else
//                printMessage(Responses.INVALID_COMMAND.getResponse());
//        }
//    }
//
//    public static void setUsername(String username) {
//        ProfileMenu.username = username;
//    }
//}


package view.menu;

import controller.ProfileMenuController;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import main.Main;
import model.graphicObjects.ButtonOne;
import model.graphicObjects.TextFieldOne;
import statics.StaticFonts;

import java.io.File;

public class ProfileMenu {

    private ProfileMenuController profileMenuController = new ProfileMenuController();
    private static String  username;

    public ProfileMenu () {

    }


    public Pane profileMenu () {
        Pane menu = new Pane();

        //OBJECTS
        TextFieldOne oldPassword = new TextFieldOne("old password", StaticFonts.SeqoeLoad(20), Pos.CENTER,
                960, 300, 400, 30, menu);
        TextFieldOne newPassword = new TextFieldOne("new password", StaticFonts.SeqoeLoad(20), Pos.CENTER,
                960, 350, 400, 30, menu);
        TextFieldOne repeatPassword = new TextFieldOne("repeat password", StaticFonts.SeqoeLoad(20), Pos.CENTER,
                960, 400, 400, 30, menu);
        ButtonOne changePassword = new ButtonOne("CHANGE PASSWORD", StaticFonts.SeqoeLoad(30), Pos.CENTER,
                960, 450, 400, 50, menu);

        TextFieldOne newNickname = new TextFieldOne("new nickname", StaticFonts.SeqoeLoad(20), Pos.CENTER,
                960, 550, 400, 30, menu);
        ButtonOne changeNickname = new ButtonOne("CHANGE NICKNAME", StaticFonts.SeqoeLoad(30), Pos.CENTER,
                960, 600, 400, 50, menu);

        ButtonOne changePicture = new ButtonOne("CHANGE PICTURE", StaticFonts.SeqoeLoad(30), Pos.CENTER,
                960, 700, 400, 50, menu);

        ButtonOne back = new ButtonOne("back", StaticFonts.SeqoeLoad(15), Pos.CENTER,
                100, 1000, 100, 50, menu);

        //FUNCTIONS
        changePassword.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String oldPass = oldPassword.getText();
                String newPass = newPassword.getText();
                String repeatPass = repeatPassword.getText();
                profileMenuController.changePassword(oldPass, newPass, repeatPass, menu);
            }
        });
        changeNickname.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String newNick = newNickname.getText();
                profileMenuController.changeNickname(newNick, menu);
            }
        });
        FileChooser fileChooser = new FileChooser();
        changePicture.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                File selectedFile = fileChooser.showOpenDialog(Main.mainStage);
                String newImageAddress = selectedFile.getPath();
                profileMenuController.changeProfileImage(newImageAddress, menu);
            }
        });
        back.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Main.showMenu("mainMenu");
            }
        });


        return menu;
    }
}
