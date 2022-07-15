package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerThread extends Thread {

    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    public ServerThread(Socket socket) throws IOException {
        this.socket = socket;
        this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
        this.dataInputStream = new DataInputStream(socket.getInputStream());
    }

    @Override
    public void run() { // receive message from client
        try {
            while (true) {
                String input = dataInputStream.readUTF();
                String response = handleInput(input);
                //this.send(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String handleInput(String input) {
        //TODO return response to received input
        return null;
    }

    public void send(String message) { // for sending message to client
        try {
            this.dataOutputStream.writeUTF(message);
            this.dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
