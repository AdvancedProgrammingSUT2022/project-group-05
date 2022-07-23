package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


//TODO determine which classes should work with server
public class Server {

    public static void main(String[] args) throws IOException {

        int SERVER_PORT = 8000;


        ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
        while (true) {
            Socket socket = serverSocket.accept();
            ServerThread serverThread = new ServerThread(socket);
            ServerManager.getInstance().addServerThread(serverThread);
            serverThread.start();
        }

    }

}
