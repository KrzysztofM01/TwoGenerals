package network;

import graphic.GraphicManager;
import graphic.cards.Card;
import javafx.concurrent.Task;
import javafx.stage.Stage;
import logic.players.Player;
import main.GameManager;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class NetworkManager {

    private Thread thread;
    private Thread receiveDataThread;
    private Socket clientSocket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private ServerSocket serverSocket;

    private boolean isHosting = false;
    private boolean yourTurn = false;
    private boolean clientAccepted = false;
    private boolean isConnected = false;

    private int playerID;
    private String playerName = "DefaultName";

    private NetworkConnectPanel networkConnectPanel;

    public NetworkManager(Stage primaryStage, GameManager gameManager) {
        // Start Connection Panel graphics
        this.networkConnectPanel = new NetworkConnectPanel(primaryStage);
        // Set controls for connection button
        networkConnectPanel.getConnectButton().setOnAction(e -> {
            networkConnectPanel.setConnectionStatus("");
            Task task = new Task<Void>() {
                @Override
                public Void call() {
                    networkConnectPanel.showCancelButton();
                    networkConnectPanel.setConnectionStatus("Connecting to server...");
                    connect();
                    networkConnectPanel.hideCancelButton();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            };
            task.setOnSucceeded(e2 -> {
                if (isConnected){
                    gameManager.getGraphicManager().loadGameScene(false);
                    GetData getData = new GetData(getOis(), gameManager);
                    receiveDataThread = new Thread(getData);
                    receiveDataThread.start();
                    sendCardsThroughNetwork(gameManager.getGraphicManager());
                }
            });
            thread = new Thread(task);
            thread.start();
        });
        //Set controls for cancel button
        networkConnectPanel.getCancelButton().setOnAction(e -> {
            networkConnectPanel.hideCancelButton();
            thread.stop();
            if (isHosting) {
                try {
                    serverSocket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            isHosting = false;
            serverSocket = null;
            networkConnectPanel.setConnectionStatus("Process stopped.");
        });
        //Set controls for host button
        networkConnectPanel.getHostButton().setOnAction(e -> {
            networkConnectPanel.setConnectionStatus("");
            initializeServer();
            Task task = new Task<Void>() {
                @Override
                public Void call() {
                    while (isHosting){
                        networkConnectPanel.setConnectionStatus("Waiting for connection...");
                        listenForServerRequest();
                        if (clientAccepted) {
                            networkConnectPanel.setConnectionStatus("Successfully accepted client request.");
                            networkConnectPanel.hideCancelButton();
                            isHosting = false;
                        }
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    return null;
                }
            };
            task.setOnSucceeded(e2 -> {
                if (clientAccepted){
                    gameManager.getGraphicManager().loadGameScene(true);
                    GetData getData = new GetData(getOis(), gameManager);
                    receiveDataThread = new Thread(getData);
                    receiveDataThread.start();
                    sendCardsThroughNetwork(gameManager.getGraphicManager());
                }
            });
            thread = new Thread(task);
            thread.start();
            networkConnectPanel.showCancelButton();
        });
    }

    public void connect() {
        try {
            clientSocket = new Socket(networkConnectPanel.getIp(), networkConnectPanel.getPort());
            oos = new ObjectOutputStream(clientSocket.getOutputStream());
            ois = new ObjectInputStream(clientSocket.getInputStream());
            networkConnectPanel.setConnectionStatus("Successfully connected to the server.");
            playerID = 1;
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
            playerID = 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean listenForServerRequest() {
        Socket socket = null;
        try {
            socket = serverSocket.accept();
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
            clientAccepted = true;
            networkConnectPanel.setConnectionStatus("Successfully accepted client request.");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void sendCardsThroughNetwork(GraphicManager graphicManager){
        for (Card card: graphicManager.getPlayerCardList()){
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

    public void nullServerSocket(){
        this.serverSocket = null;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }
    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public Player getPlayer(){
        return new Player();
    }

    public void closeReceiveDataThread(){
        receiveDataThread.stop();
    }

    public boolean isYourTurn() {
        return yourTurn;
    }

    public void setYourTurn(boolean yourTurn) {
        this.yourTurn = yourTurn;
    }
}
