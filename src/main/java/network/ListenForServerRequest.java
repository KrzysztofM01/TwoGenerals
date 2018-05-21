package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ListenForServerRequest {

    ServerSocket serverSocket;
    ObjectInputStream ois;
    ObjectOutputStream oos;


    public ListenForServerRequest(ServerSocket serverSocket, ObjectOutputStream oos, ObjectInputStream ois) {
        this.serverSocket =serverSocket;
        this.ois = ois;
        this.oos = oos;

    }

    public boolean listen (){
        Socket socket = null;
        try {
            socket = serverSocket.accept();
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
            //clientAccepted = true;
            //networkConnectPanel.setConnectionStatus("Successfully accepted client request.");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
