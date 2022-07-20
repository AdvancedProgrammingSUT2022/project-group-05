package client;

import com.google.gson.Gson;
import model.Lobby;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class ClientThread extends Thread { // This class is used for receiving data from server
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    public ClientThread(DataInputStream dataInputStream, DataOutputStream dataOutputStream) {
        this.dataInputStream = dataInputStream;
        this.dataOutputStream = dataOutputStream;
    }

    @Override
    public void run() { // receive
        while (true) {
            try {
                String input = dataInputStream.readUTF();
                Request request = Request.convertFromJson(input);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void handleRequest(Request request) {
        if (request.getAction().equals("invite")) {
            Gson gson = new Gson();
            Lobby lobby = gson.fromJson((String) request.getParams().get("lobby"), Lobby.class);
            Lobby.getInvitedLobbies().add(lobby);
        }
    }
}
