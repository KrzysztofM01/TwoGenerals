package game.network;

import game.eventHandlers.CancelNetworkSetupHandler;
import game.eventHandlers.ConnectHandler;
import game.eventHandlers.HostHandler;
import game.graphic.GraphicManager;
import game.graphic.cards.Card;
import javafx.stage.Stage;
import game.logic.Player;
import game.GameManager;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class NetworkManager {

    private Thread thread;
    private Thread receiveDataThread;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private ServerSocket serverSocket;

    private boolean isHosting = false;
    private boolean yourTurn = false;
    private boolean clientAccepted = false;
    private boolean isConnected = false;

    private NetworkConnectPanel networkConnectPanel;

    public NetworkManager(Stage primaryStage, GameManager gameManager) {

        // Start Connection Panel graphics
        this.networkConnectPanel = new NetworkConnectPanel(primaryStage);

        // Set controls for buttons
        networkConnectPanel.getConnectButton().setOnAction(new ConnectHandler(this, gameManager));
        networkConnectPanel.getCancelButton().setOnAction(new CancelNetworkSetupHandler(this));
        networkConnectPanel.getHostButton().setOnAction(new HostHandler(this, gameManager));

    }

    public void connect() {
        try {
            Socket clientSocket = new Socket(networkConnectPanel.getIp(), networkConnectPanel.getPort());
            oos = new ObjectOutputStream(clientSocket.getOutputStream());
            ois = new ObjectInputStream(clientSocket.getInputStream());
            networkConnectPanel.setConnectionStatus("Successfully connected to the server.");
            isConnected = true;
        } catch (IOException e) {
            networkConnectPanel.setConnectionStatus("Unable to connect to the address: " + networkConnectPanel.getIp() + ":" + networkConnectPanel.getPort());
        }
    }

    public void initializeServer() {
        try {
            serverSocket = new ServerSocket(networkConnectPanel.getPort(), 8, InetAddress.getByName(networkConnectPanel.getIp()));
            isHosting = true;
            yourTurn = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listenForServerRequest() {
        Socket socket;
        try {
            socket = serverSocket.accept();
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
            clientAccepted = true;
            networkConnectPanel.setConnectionStatus("Successfully accepted client request.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendCardsThroughNetwork(GraphicManager graphicManager) {
        for (Card card : graphicManager.getCardList()) {
            try {
                MethodWrapper cardToSend = MethodWrapper.addCardToPlayer(card);
                this.oos.writeObject(cardToSend);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ObjectOutputStream getOos() {
        return oos;
    }

    public ObjectInputStream getOis() {
        return ois;
    }

    public boolean isClientAccepted() {
        return clientAccepted;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public Player getPlayer() {
        return new Player();
    }

    public void closeReceiveDataThread() {
        receiveDataThread.stop();
    }

    public boolean isYourTurn() {
        return yourTurn;
    }

    public void setYourTurn(boolean yourTurn) {
        this.yourTurn = yourTurn;
    }

    public Thread getThread() {
        return thread;
    }

    public Thread getReceiveDataThread() {
        return receiveDataThread;
    }

    public boolean isHosting() {
        return isHosting;
    }

    public NetworkConnectPanel getNetworkConnectPanel() {
        return networkConnectPanel;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public void setReceiveDataThread(Thread receiveDataThread) {
        this.receiveDataThread = receiveDataThread;
    }

    public void setHosting(boolean hosting) {
        isHosting = hosting;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }
}
