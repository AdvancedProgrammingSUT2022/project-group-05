package server;

import controller.UserDatabaseController;
import model.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerThread extends Thread {

    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
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
                Request request = Request.convertFromJson(input);
                Response response = handleRequest(request);
                if (request.getAction().equals("login") && response.getMessage().equals("login successful")) {
                    this.username = UserDatabaseController.getUserByUsername((String) request.getParams().get("username")).getUsername();
                    ServerAdapter.sendUpdateForScoreBoard();
                }
                String responseJson = response.convertToJson();
                this.send(responseJson);
            }
        } catch (IOException e) {
            System.out.println("client with username " + username + " disconnected");

            this.username = null;
            ServerManager.getInstance().removeServerThread(this);
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
