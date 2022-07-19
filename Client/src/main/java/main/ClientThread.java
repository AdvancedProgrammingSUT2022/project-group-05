package main;

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
                // do stuff with input
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
