package client;

import com.google.gson.Gson;
import controller.GameMenuController;
import controller.GameObjectData;
import graphics.view.gameContents.MapFX;
import graphics.view.menus.Game;
import graphics.view.menus.ProfileMenu;
import javafx.application.Platform;
import model.Lobby;
import model.map.Map;

import java.io.*;
import java.net.Socket;

public class ClientThread extends Thread { // This class is used for receiving data from server
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private Socket listener;

    public ClientThread(Socket listener) throws IOException {
        this.listener = listener;
        this.dataInputStream = new DataInputStream(listener.getInputStream());
        this.dataOutputStream = new DataOutputStream(listener.getOutputStream());
    }

    @Override
    public void run() { // receive
        while (true) {
            try {
                String input = dataInputStream.readUTF();
                if (input.equals("sending")) {
                    if (objectInputStream == null)
                        objectInputStream = new ObjectInputStream(listener.getInputStream());
                    GameObjectData gameObjectData = (GameObjectData) objectInputStream.readObject();
                    GameMenuController.updateInstance(gameObjectData.getGameMenuController());
                    Map.updateInstance(gameObjectData.getMap());
                    MapFX.updateInstance();
                    ClientManager.getInstance().update();
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            ClientManager.getInstance().setPane(new Game(GameMenuController.getInstance()
                                .getCivilizationByUsername(ClientManager.getInstance().getMainUser().getUsername())));
                        }
                    });
                } else if (input != null){
                    Request request = Request.convertFromJson(input);
                    this.handleRequest(request);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void handleRequest(Request request) throws NoSuchMethodException {
        if (request.getAction().equals("invite")) { //when server wants to add invitation to client
            Gson gson = new Gson();
            Lobby lobby = gson.fromJson((String) request.getParams().get("lobby"), Lobby.class);
            Lobby.getInvitedLobbies().add(lobby);
            ClientManager.getInstance().updateLobbyInvites();
        }
        if (request.getAction().equals("updateLobby")) { //when server send update to client to refresh lobby
            Gson gson = new Gson();
            Lobby updatedLobby = gson.fromJson((String) request.getParams().get("lobby"), Lobby.class);
            ClientManager.getInstance().updateLobby(updatedLobby);
        }
        if (request.getAction().equals("closeLobby")) {
            ClientManager.getInstance().closeLobby();
        }
        if (request.getAction().equals("updateGame")) {
            //TODO update game request
        }
        if (request.getAction().equals("updateScoreboard")) {
            ClientManager.getInstance().updateScoreboard();
        }
        if (request.getAction().equals("updateProfileMenu")) {
            ClientManager.getInstance().updateProfileMenu();
        }
    }
}
