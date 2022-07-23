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

    public static String joinLobby(String username, Lobby destinationLobby) {
        for (Lobby lobby : lobbies) {
            if (lobby.getId().equals(destinationLobby.getId())) {
                if (lobby.getPlayerUsernames().size() == 4)
                    return "error: lobby is full";
                lobby.addUser(username);
                return username + " joined lobby successfully";
            }
        }
        return "error: lobby with this id not exists";
    }

    public static String inviteToLobby(Lobby lobby, String invitedUsername) {
        if (UserDatabaseController.getUserByUsername(invitedUsername) == null)
            return "error: invited user not exists";
        if (!UserDatabaseController.getUserByUsername(lobby.getHostUsername()).getFriends().contains(invitedUsername))
            return "error: invited user is not your friend";
        if (!ServerManager.getInstance().isUserOnline(invitedUsername))
            return "error: invited user is not online";
        if (lobby.getPlayerUsernames().contains(invitedUsername))
            return "error: invited user is already in lobby";

        ServerThread serverThread = ServerManager.getInstance().getUserListenerServerThread(invitedUsername);
        Request request = new Request("invite");
        request.addParams("lobby", new Gson().toJson(lobby));
        serverThread.send(request.convertToJson());
        return "inviting successful";
    }

    public static String closeLobby(Lobby closingLobby) {
        lobbies.removeIf(lobby -> lobby.getId().equals(closingLobby.getId()));
        for (String playerUsername : closingLobby.getPlayerUsernames()) {
            Request request = new Request("closeLobby");
            ServerManager.getInstance().getUserListenerServerThread(playerUsername).send(request.convertToJson());
        }
        return "lobby removed successfully";
    }

    public static String closeLobbyBeforeGame(Lobby gameLobby) {
        lobbies.removeIf(lobby -> lobby.getId().equals(gameLobby.getId()));

        return "game created successfully";
    }

    public static ArrayList<Lobby> getLobbies() {
        return lobbies;
    }

}
