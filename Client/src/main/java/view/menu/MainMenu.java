//package view.menu;
//
//import controller.GameMenuController;
//import controller.MainMenuController;
//import controller.Responses;
//
//import javax.print.DocFlavor;
//import java.util.HashMap;
//import java.util.Scanner;
//
//import static view.enums.MainMenuCommand.*;
//
//public class MainMenu extends Menu {
//
//    private static String username;
//
//    public MainMenu(Scanner scanner) {
//        super(scanner);
//    }
//
//    @Override
//    public MenuType run() {
//        String input;
//        HashMap<String, String> command;
//
//        printMessage("__MAIN MENU__");
//
//        while (true) {
//            input = scanner.nextLine();
//
//            if ((command = getHashMap(input, EXIT)) != null)
//                return MenuType.EXIT;
//            else if ((command = getHashMap(input, MENU_SHOW_CURRENT)) != null)
//                printMessage("main menu");
//            else if ((command = getHashMap(input, SHOW_PROFILE)) != null) {
//                ProfileMenu.setUsername(MainMenu.username);
//                printMessage("entering profile menu");
//                return MenuType.PROFILE;
//            } else if ((command = getHashMap(input, USER_LOGOUT)) != null) {
//                printMessage("user logged out successfully!");
//                return MenuType.LOGIN;
//            }
//            else if ((command = getHashMap(input, PLAY_GAME)) != null) {
//                String response = MainMenuController.getInstance().startGame(command);
//                printMessage(response);
//                if (GameMenuController.getInstance() != null)
//                    return MenuType.GAME;
//            }
//            else
//                printMessage(Responses.INVALID_COMMAND.getResponse());
//        }
//    }
//
//    public static void setUsername(String username) {
//        MainMenu.username = username;
//    }
//}


//package view.menu;
//
//import controller.UserDatabaseController;
//import graphics.objects.buttons.ButtonOne;
//import graphics.objects.labels.LabelOne;
//import javafx.event.EventHandler;
//import javafx.geometry.Pos;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.Pane;
//import main.Main;
//import statics.StaticFonts;
//
//public class MainMenu {
//
//    public static String username;
//
//    public MainMenu () {
//
//    }
//
//
//    public Pane mainMenu () {
//        Pane menu = new Pane();
//
//        //OBJECTS
//        ButtonOne startGame = new ButtonOne("NEW GAME", StaticFonts.SeqoeLoad(40), Pos.CENTER,
//                960, 550, 400, 50, menu);
//        ButtonOne scoreBoard = new ButtonOne("SCOREBOARD", StaticFonts.SeqoeLoad(20), Pos.CENTER,
//                960, 650, 200, 50, menu);
//        ButtonOne profile = new ButtonOne("PROFILE", StaticFonts.SeqoeLoad(20), Pos.CENTER,
//                960, 700, 200, 50, menu);
//        ButtonOne chat = new ButtonOne("CHAT", StaticFonts.SeqoeLoad(20), Pos.CENTER,
//                960, 750, 200, 50, menu);
//        ButtonOne logout = new ButtonOne("LOGOUT", StaticFonts.SeqoeLoad(15), Pos.CENTER,
//                960, 900, 100, 50, menu);
//
//        //FUNCTIONS
//        startGame.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                //TODO... opening new game menu
//                ErrorBox.getErrorBox("under construction", menu, true);
//            }
//        });
//        scoreBoard.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                Main.showMenu("scoreBoard");
//            }
//        });
//        profile.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                Main.showMenu("profileMenu");
//            }
//        });
//        chat.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                Main.showMenu("chatMenu");
//            }
//        });
//        logout.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                Main.showMenu("loginMenuLogin");
//            }
//        });
//
//
//        return menu;
//    }
//
//
//    public Pane score () {
//        Pane menu = new Pane();
//
//
//
//        String[] nicknames = UserDatabaseController.getNicknames();
//        int[] scores = new int[nicknames.length]; //TODO... get from database
//        //OBJECTS
//        ButtonOne back = new ButtonOne("back", StaticFonts.SeqoeLoad(15), Pos.CENTER,
//                100, 1000, 100, 50, menu);
//
//        LabelOne[] ranks = new LabelOne[nicknames.length];
//        LabelOne[] nicknameLabels = new LabelOne[nicknames.length];
//        LabelOne[] scoreLabels = new LabelOne[nicknames.length];
//
//        for (int i = 0; i < nicknames.length; i++) {
//            ranks[i] = new LabelOne(i+1 + " :", StaticFonts.SeqoeLoad(15), Pos.CENTER_LEFT,
//                    400, 50 * i + 400, 200, 30, menu);
//            nicknameLabels[i] = new LabelOne(nicknames[i], StaticFonts.SeqoeLoad(15), Pos.CENTER_LEFT,
//                    450, 50 * i + 400, 200, 30, menu);
//            scoreLabels[i] = new LabelOne(scores[i] + "", StaticFonts.SeqoeLoad(15), Pos.CENTER_RIGHT,
//                    1520, 50 * i + 400, 200, 30, menu);
//        }
//
//        //FUNCTIONS
//        back.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                Main.showMenu("mainMenu");
//            }
//        });
//
//        return menu;
//    }
//}

