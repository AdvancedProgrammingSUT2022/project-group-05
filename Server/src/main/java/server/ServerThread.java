package server;

import controller.GameMenuController;
import controller.GameObjectData;
import controller.UserDatabaseController;
import model.map.Map;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread {

    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private String username;

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
                System.out.println(input + " is received in serverThread");
                if (input.equals("sending")) {
                    Response response = new Response();
                    response.setMessage("OK");
                    this.send(response.convertToJson());
                    if (objectInputStream == null)
                        objectInputStream = new ObjectInputStream(socket.getInputStream());
                    GameObjectData gameObjectData = (GameObjectData) objectInputStream.readObject();
                    GameMenuController.updateInstance(gameObjectData.getGameMenuController());
                    Map.updateInstance(gameObjectData.getMap());
                    for (String playerUsername : GameMenuController.getInstance().getPlayerUsernames()) {
                        ServerThread serverThread = ServerManager.getInstance().getUserListenerServerThread(playerUsername);
                        serverThread.send("sending");
                        serverThread.sendObject(GameObjectData.getInstance());
                    }
                } else {
                    Request request = Request.convertFromJson(input);
                    Response response = handleRequest(request);
                    if (request.getAction().equals("login") && response.getMessage().equals("login successful")) {
                        this.username = UserDatabaseController.getUserByUsername((String) request.getParams().get("username")).getUsername();
                        ServerAdapter.sendUpdateForScoreBoard();
                    }
                    String responseJson = response.convertToJson();
                    this.send(responseJson);
                }
            }
        } catch (IOException e) {
            System.out.println("client with username " + username + " disconnected");

            this.username = null;
            ServerManager.getInstance().removeServerThread(this);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Response handleRequest(Request request) {
        Response response = new Response();
        String message;
        if (request.getAction().equals("login")) {
            message = ServerAdapter.login(request);
            response.setMessage(message);
        }
        if (request.getAction().equals("getUser")) {
            message = ServerAdapter.getUser(request);
            response.setMessage(message);
        }
        if (request.getAction().equals("register")) {
            message = ServerAdapter.register(request);
            response.setMessage(message);
        }
        if (request.getAction().equals("changePassword")) {
            message = ServerAdapter.changePassword(request);
            response.setMessage(message);
        }
        if (request.getAction().equals("changeNickname")) {
            message = ServerAdapter.changeNickname(request);
            response.setMessage(message);
        }
        if (request.getAction().equals("changeImage")) {
            message = ServerAdapter.changeImage(request);
            response.setMessage(message);
        }
        if (request.getAction().equals("getUsers")) {
            message = ServerAdapter.getUsers(request);
            response.setMessage(message);
        }
        if (request.getAction().equals("addFriend")) {
            message = ServerAdapter.addFriend(request);
            response.setMessage(message);
        }
        if (request.getAction().equals("removeFriend")) {
            message = ServerAdapter.removeFriend(request);
            response.setMessage(message);
        }
        if (request.getAction().equals("rejectFriend")) {
            message = ServerAdapter.rejectFriend(request);
            response.setMessage(message);
        }
        if (request.getAction().equals("createLobby")) {
            message = ServerAdapter.createLobby(request);
            response.setMessage(message);
        }
        if (request.getAction().equals("searchFriend")) {
            message = ServerAdapter.searchFriend(request);
            response.setMessage(message);
        }
        if (request.getAction().equals("inviteFriend")) {
            message = ServerAdapter.inviteFriend(request);
            response.setMessage(message);
        }
        if (request.getAction().equals("update")) { // update changes in lobby from client
            message = ServerAdapter.update(request);
            response.setMessage(message);
        }
        if (request.getAction().equals("inviteToLobby")) {
            message = ServerAdapter.inviteToLobby(request);
            response.setMessage(message);
        }
        if (request.getAction().equals("joinLobby")) {
            message = ServerAdapter.joinLobby(request);
            response.setMessage(message);
        }
        if (request.getAction().equals("closeLobby")) {
            message = ServerAdapter.closeLobby(request);
            response.setMessage(message);
        }
        if (request.getAction().equals("startGame")) {
            message = ServerAdapter.startGame(request);
            response.setMessage(message);
        }
        if (request.getAction().equals("getOnlineUsers")) {
            message = ServerAdapter.getOnlineUsers(request);
            response.setMessage(message);
        }
        if (request.getAction().equals("userLoggedOut")) {
            message = ServerAdapter.userLoggedOut(request);
            response.setMessage(message);
        }

        return response;
    }

    public void send(String message) { // for sending message to client directly
        try {
            this.dataOutputStream.writeUTF(message);
            this.dataOutputStream.flush();
        } catch (IOException e) {
            this.username = null;
            e.printStackTrace();
        }
    }

    public void sendObject(Object object) {
        try {

            if (this.objectOutputStream == null) this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            this.objectOutputStream.writeObject(object);
            this.objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUsername() {
        return username;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
