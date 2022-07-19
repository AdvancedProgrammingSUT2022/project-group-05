package main;

import graphics.view.ClientManager;
import graphics.view.menus.LoginMenu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class Client extends Application {

    private static Socket socket;
    private static DataInputStream dataInputStream;
    private static DataOutputStream dataOutputStream;
    private static Socket listener;

    public static boolean connect(int SERVER_PORT) {
        try {
            socket = new Socket("localhost", SERVER_PORT);
//            listerForUpdates();
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void listerForUpdates() throws IOException { // always listen for incoming messages from server
        listener = new Socket("localhost", 8000);
        DataInputStream dataInputStream = new DataInputStream(listener.getInputStream());
        DataOutputStream dataOutputStream = new DataOutputStream(listener.getOutputStream());
        ClientThread clientThread = new ClientThread(dataInputStream, dataOutputStream);
        clientThread.start();
    }

    public static Response send(String message) { // to send data to server
        try {
            dataOutputStream.writeUTF(message);
            dataOutputStream.flush();
            while (true) {
                String input = dataInputStream.readUTF();
                return Response.convertFromJson(input);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        // find size of screen
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        primaryStage.setWidth(size.width);
        primaryStage.setHeight(size.height);
        primaryStage.setFullScreen(true);

        Scene primaryScene = new Scene(new Pane());

        ClientManager.updateInstance(primaryStage, primaryScene);
        ClientManager.getInstance().setPane(new LoginMenu());

        primaryStage.show();
    }

    public static void main(String[] args){
        int SERVER_PORT = 8000;

        if (connect(SERVER_PORT)) {
            launch();
        } else {
            System.out.println("can't connect to server");
        }
    }
}
