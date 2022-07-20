package controller;

import com.google.gson.Gson;
import model.Lobby;
import model.User;
import server.Request;
import server.ServerManager;
import server.ServerThread;

import java.util.ArrayList;

public class LobbyController {
    private static ArrayList<Lobby> lobbies = new ArrayList<>();

    public static Lobby hostLobby(String username, String id, int size) {
        Lobby lobby = new Lobby(id, username);
        lobby.setSize(size);
        lobbies.add(lobby);
        return lobby;
    }

    public static String joinLobby(String username, String id) {
        for (Lobby lobby : lobbies) {
            if (lobby.getId().equals(id)) {
                if (lobby.getPlayerUsernames().size() == 4)
                    return "error: lobby is full";
                lobby.addUser(username);
                return username + " joined lobby successfully";
            }
        }
        return "error: lobby with this id not exists";
    }

    public static String inviteToLobby(String id, String invitedUsername) {
        for (Lobby lobby : lobbies) {
            if (lobby.getId().equals(id)) {
                if (!ServerManager.getInstance().isUserOnline(invitedUsername))
                    return "error: invited user is not online";
                ServerThread serverThread = ServerManager.getInstance().getUserServerThread(invitedUsername);
                Request request = new Request("invite");
                Gson gson = new Gson();
                String lobbyJson = gson.toJson(lobby);
                request.addParams("lobby", lobbyJson);
                serverThread.send(request.convertToJson());
                return "inviting successful";
            }
        }
        return "error: no lobby with this id exists";
    }

    public static ArrayList<Lobby> getLobbies() {
        return lobbies;
    }
}
