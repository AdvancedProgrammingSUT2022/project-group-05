package client;

import graphics.statics.StaticSounds;
import graphics.view.menus.LoginMenu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
import java.net.Socket;


public class Client extends Application {

    private static Socket socket;
    private static DataInputStream dataInputStream;
    private static DataOutputStream dataOutputStream;
    private static ObjectOutputStream objectOutputStream;
    private static ObjectInputStream objectInputStream;
    private static Socket listener;

    public static boolean connect(String HOST, int SERVER_PORT) {
        try {
            socket = new Socket(HOST, SERVER_PORT);
            listerForUpdates(HOST, SERVER_PORT);

            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void listerForUpdates(String HOST, int SERVER_PORT) throws IOException { // always listen for incoming messages from server
        listener = new Socket(HOST, SERVER_PORT);
        ClientThread clientThread = new ClientThread(listener);
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

    public static void sendObject(Object object) {
        try {
            if (objectOutputStream == null) objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
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
        StaticSounds.mainTheme();

        primaryStage.show();
    }

    public static void main(String[] args){
        String HOST = "localhost";
        int SERVER_PORT = 8000;

        if (connect(HOST, SERVER_PORT)) {
            System.out.println("connected");
            launch();
        } else {
            System.out.println("can't connect to server");
        }
    }
}
