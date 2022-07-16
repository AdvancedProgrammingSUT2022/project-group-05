package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

//TODO determine which classes should work with client
public class Client {

    private static Socket socket;
    private static DataInputStream dataInputStream;
    private static DataOutputStream dataOutputStream;
    private static Socket listener;

    public static boolean connect(int SERVER_PORT) {
        try {
            socket = new Socket("localhost", SERVER_PORT);
            listerForUpdates();
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

    public static String send(String message) { // to send data to server
        try {
            dataOutputStream.writeUTF(message);
            dataOutputStream.flush();
            while (true) {
                String response = dataInputStream.readUTF();
                return response;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {

        int SERVER_PORT = 8000;

        if (connect(SERVER_PORT)) {
            // start
        } else {
            System.out.println("can't connect to server");
        }

    }

}