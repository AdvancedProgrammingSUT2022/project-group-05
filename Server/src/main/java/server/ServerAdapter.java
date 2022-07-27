package server;

import com.google.gson.Gson;
import controller.GameMenuController;
import controller.GameObjectData;
import controller.LobbyController;
import controller.UserDatabaseController;
import model.Lobby;
import model.User;
import model.game.Civilization;
import model.map.Map;

import java.util.ArrayList;
import java.util.HashMap;

public class ServerAdapter {

    public static String login(Request request) {
        HashMap<String, Object> params = request.getParams();
        String username = (String) params.get("username");
        String password = (String) params.get("password");
        User user = UserDatabaseController.getUserByUsername(username);
        if (user == null)
            return "error: username doesn't exists";
        if (!user.getPassword().equals(password))
            return "error: username and password don't match";
        if (ServerManager.getInstance().isUserOnline(username))
            return "error: you can't log in twice";
        return "login successful";
    }

    public static String getUser(Request request) {
        String username = (String) request.getParams().get("username");
        User user = UserDatabaseController.getUserByUsername(username);
        Gson gson = new Gson();
        return gson.toJson(user);
    }

    public static String register(Request request) {
        HashMap<String, Object> params = request.getParams();
        String username = (String) params.get("username");
        String password = (String) params.get("password");
        String repeatPassword = (String) params.get("repeatPassword");
        String nickname = (String) params.get("nickname");
        String imageAddress = (String) params.get("imageAddress");
        User previousUsernameHolder = UserDatabaseController.getUserByUsername(username);
        User previousNicknameHolder = UserDatabaseController.getUserByNickname(nickname);
        if (previousUsernameHolder != null)
            return "error: username already taken";
        if (previousNicknameHolder != null)
            return "error: nickname already taken";
        if (!password.equals(repeatPassword))
            return "error: password and repeat don't match";
        if (password.length() < 8)
            return "error: password must be at least 8 characters";
        User user = new User(username, nickname, password, imageAddress, 0);
        UserDatabaseController.addUser(user);
        return "register successful";

    }

    public static String changePassword(Request request) {
        HashMap<String, Object> params = request.getParams();
        String oldPassword = (String) params.get("oldPassword");
        String newPassword = (String) params.get("newPassword");
        String repeatNewPassword = (String) params.get("repeatNewPassword");
        String username = (String) params.get("username");
        User user = UserDatabaseController.getUserByUsername(username);

        if (!user.getPassword().equals(oldPassword))
            return "error: current password is incorrect";
        if (!newPassword.equals(repeatNewPassword))
            return "error: new password and repeat don't match";
        if (newPassword.length() < 8)
            return "error: password must be at least 8 characters";
        if (oldPassword.equals(newPassword))
            return "error: new password can't be the same as old password";
        UserDatabaseController.changePassword(user, newPassword);
        return "password changed successfully";
    }

    public static String changeNickname(Request request) {
        HashMap<String, Object> params = request.getParams();
        String newNickname = (String) params.get("newNickname");
        String username = (String) params.get("username");
        User user = UserDatabaseController.getUserByUsername(username);
        User previousNicknameHolder = UserDatabaseController.getUserByNickname(newNickname);
        if (user.getNickname().equals(newNickname))
            return "error: old and new nickname can't be the same";
        if (previousNicknameHolder != null)
            return "error: nickname already taken";
        UserDatabaseController.changeNickname(user, newNickname);
        return "nickname changed successfully";
    }

    public static String changeImage(Request request) {
        HashMap<String, Object> params = request.getParams();
        String newImageAddress = (String) params.get("newImageAddress");
        String username = (String) params.get("username");
        User user = UserDatabaseController.getUserByUsername(username);
        UserDatabaseController.changeImage(user, newImageAddress);
        return "image changed successfully";
    }

    public static String getUsers(Request request) {
        ArrayList<HashMap<String, String>> users = UserDatabaseController.loadDatabase();
        Gson gson = new Gson();
        return gson.toJson(users);
    }

    public static String addFriend(Request request) {
        String friendUsername = (String) request.getParams().get("friendUsername");
        String username = (String) request.getParams().get("username");
        User friendUser = UserDatabaseController.getUserByUsername(friendUsername);
        User user = UserDatabaseController.getUserByUsername(username);
        UserDatabaseController.addFriend(friendUser, username);
        UserDatabaseController.addFriend(user, friendUsername);
        UserDatabaseController.removeInvitingFriend(user, friendUsername);
        if (ServerManager.getInstance().isUserOnline(friendUsername))
            ServerManager.getInstance().getUserListenerServerThread(friendUsername).send(new Request("updateProfileMenu").convertToJson());
        return "friend added successfully";
    }

    public static String removeFriend(Request request) {
        String friendUsername = (String) request.getParams().get("friendUsername");
        String username = (String) request.getParams().get("username");
        User friendUser = UserDatabaseController.getUserByUsername(friendUsername);
        User user = UserDatabaseController.getUserByUsername(username);
        UserDatabaseController.removeFriend(friendUser, username);
        UserDatabaseController.removeFriend(user, friendUsername);
        if (ServerManager.getInstance().isUserOnline(friendUsername))
            ServerManager.getInstance().getUserListenerServerThread(friendUsername).send(new Request("updateProfileMenu").convertToJson());
        return "friend removed successfully";
    }

    public static String rejectFriend(Request request) {
        String invitingFriendUsername = (String) request.getParams().get("invitingFriendUsername");
        String username = (String) request.getParams().get("username");
        User user = UserDatabaseController.getUserByUsername(username);
        UserDatabaseController.removeInvitingFriend(user, invitingFriendUsername);
        return "friend rejected successfully";
    }

    public static String createLobby(Request request) {
        String hostUsername = (String) request.getParams().get("hostUsername");
        String id = (String) request.getParams().get("id");

        Lobby lobby = LobbyController.hostLobby(hostUsername, id, 10);
        LobbyController.getLobbies().add(lobby);

        Gson gson = new Gson();

        return gson.toJson(lobby);
    }

    public static String searchFriend(Request request) {
        String friendUsername = (String) request.getParams().get("friendUsername");
        User friendUser = UserDatabaseController.getUserByUsername(friendUsername);
        if (friendUser == null)
            return "error: friend with this username not found";
        return new Gson().toJson(friendUser);
    }

    public static String inviteFriend(Request request) {
        String friendUsername = (String) request.getParams().get("friendUsername");
        String username = (String) request.getParams().get("username");
        User friendUser = UserDatabaseController.getUserByUsername(friendUsername);
        if (friendUser.getFriends().contains(username))
            return "error: you are already friend with this user";
        if (username.equals(friendUsername))
            return "error: you can't invite yourself!";
        UserDatabaseController.addInvitingFriend(friendUser, username);
        Request inviteRequest = new Request("updateProfileMenu");
        if (ServerManager.getInstance().isUserOnline(friendUsername))
            ServerManager.getInstance().getUserListenerServerThread(friendUsername).send(inviteRequest.convertToJson());
        return "friend invited successfully";
    }

    public static String update(Request request) { // find lobby from request and change it and send it to all clients and host related to this lobby
        Gson gson = new Gson();
        Lobby updatedLobby = gson.fromJson((String) request.getParams().get("lobby"), Lobby.class);
        String whoSentIt = (String) request.getParams().get("whoSendIt");
        for (int i = 0; i < LobbyController.getLobbies().size(); i++) {
            if (LobbyController.getLobbies().get(i).getId().equals(updatedLobby.getId())) {
                LobbyController.getLobbies().set(i, updatedLobby);
            }
        }
        //now send updated lobby to related clients and host
        for (String playerUsername : updatedLobby.getPlayerUsernames()) {
            if (!playerUsername.equals(whoSentIt)) {
                Request updateRequest = new Request("updateLobby");
                updateRequest.addParams("lobby", new Gson().toJson(updatedLobby));
                ServerManager.getInstance().getUserListenerServerThread(playerUsername).send(updateRequest.convertToJson());
            }
        }
        if (!updatedLobby.getHostUsername().equals(whoSentIt)) {
            Request updateRequest = new Request("updateLobby");
            updateRequest.addParams("lobby", new Gson().toJson(updatedLobby));
            ServerManager.getInstance().getUserListenerServerThread(updatedLobby.getHostUsername()).send(updateRequest.convertToJson());
        }
        return "update successful";
    }

    public static String inviteToLobby(Request request) {
        Lobby lobby = new Gson().fromJson((String) request.getParams().get("lobby"), Lobby.class);
        String friendUsername = (String) request.getParams().get("friendUsername");

        return LobbyController.inviteToLobby(lobby, friendUsername);
    }

    public static String joinLobby(Request request) {
        Lobby lobby = new Gson().fromJson((String) request.getParams().get("lobby"), Lobby.class);
        String username = (String) request.getParams().get("username");
        return LobbyController.joinLobby(username, lobby);
    }

    public static String closeLobby(Request request) {
        Lobby closingLobby = new Gson().fromJson((String) request.getParams().get("lobby"), Lobby.class);
        return  LobbyController.closeLobby(closingLobby);

    }

    public static String startGame(Request request) {
        if (GameMenuController.getInstance() != null) return "error: there is another game is progress";

        Lobby gameLobby = new Gson().fromJson((String) request.getParams().get("lobby"), Lobby.class);

        Map.updateInstance(gameLobby.getSize());

        ArrayList<Civilization> civilizations = new ArrayList<>();
        for (int i = 0; i < gameLobby.getPlayerUsernames().size(); i++) {
            civilizations.add(new Civilization(UserDatabaseController.getUserByUsername(gameLobby.getPlayerUsernames().get(i)), i));
        }

        GameMenuController.updateInstance(civilizations);
        GameMenuController.getInstance().nextCivilization();

        LobbyController.closeLobbyBeforeGame(gameLobby);

        for (String playerUsername : gameLobby.getPlayerUsernames()) {
            ServerThread serverThread = ServerManager.getInstance().getUserListenerServerThread(playerUsername);
            serverThread.send("sending");
            serverThread.sendObject(GameObjectData.getInstance());
        }

        return "game created successfully";
    }

    public static String updateGame() {
        //TODO sending game info
        return null;
    }

    public static String getOnlineUsers(Request request) {
        ArrayList<String> onlineUsers = new ArrayList<>();
        for (ServerThread serverThread : ServerManager.getInstance().getServerThreads()) {
            if (serverThread.isAlive())
                onlineUsers.add(serverThread.getUsername());
        }
        return new Gson().toJson(onlineUsers);
    }

    public static String userLoggedOut(Request request) {
        String username = (String) request.getParams().get("username");
        sendUpdateForScoreBoard();
        ServerManager.getInstance().getUserServerThread(username).setUsername(null);
        return "user logged out successfully";
    }




    /////////////////////////////
    public static void sendUpdateForScoreBoard() {
        for (int i = 0; i < ServerManager.getInstance().getServerThreads().size(); i++) {
            ServerThread serverThread = ServerManager.getInstance().getServerThreads().get(i);
            if (serverThread.getUsername() == null) {
                Request request = new Request("updateScoreboard");
                serverThread.send(request.convertToJson());
            }
        }
    }

}
