package server;

import java.util.ArrayList;

public class ServerManager {

    //Singleton
    private static ServerManager instance;

    private ServerManager() {

    }

    public static ServerManager getInstance() {
        if (instance == null)
            instance = new ServerManager();
        return instance;
    }

    public static void updateInstance() {
        instance = new ServerManager();
    }
    // end of singleton

    private static ArrayList<ServerThread> serverThreads = new ArrayList<>();

    public void addServerThread(ServerThread serverThread) {
        serverThreads.add(serverThread);
    }

    public boolean isUserOnline(String username) {
        for (ServerThread serverThread : serverThreads) {
            if (serverThread.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public ServerThread getUserServerThread(String username) {
        for (ServerThread serverThread : serverThreads) {
            if (serverThread.getUsername().equals(username)) {
                return serverThread;
            }
        }
        return null;
    }

}
