package server;

import java.util.ArrayList;
import java.util.function.Predicate;

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
            if (serverThread.getUsername() != null && serverThread.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public ServerThread getUserServerThread(String username) {
        for (ServerThread serverThread : serverThreads) {
            if (serverThread.getUsername() != null && serverThread.getUsername().equals(username)) {
                return serverThread;
            }
        }
        return null;
    }
    
    public ServerThread getUserListenerServerThread(String username) {
        int port = this.getUserServerThread(username).getSocket().getPort();
        for (ServerThread serverThread : serverThreads) {
            if (serverThread.getSocket().getPort() - 1 == port) {
                return serverThread;
            }
        }
        return null;
    }

    public void removeServerThread(ServerThread serverThread) {
        serverThreads.remove(serverThread);
        ServerAdapter.sendUpdateForScoreBoard();
    }

//    public void removeServerThread(String username) {
//        for (int i = 0; i < serverThreads.size(); i++) {
//            if (serverThreads.get(i).getUsername() != null && serverThreads.get(i).getUsername().equals(username)) {
//                serverThreads.remove(i);
//                ServerAdapter.sendUpdateForScoreBoard();
//                break;
//            }
//        }
//    }

    public ArrayList<ServerThread> getServerThreads() {
        return serverThreads;
    }
}
