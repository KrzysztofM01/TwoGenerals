package network;

import graphic.GraphicManager;
import javafx.concurrent.Task;
import javafx.stage.Stage;
import logic.players.Player;
import main.GameManager;

import java.awt.*;
import java.io.IOException;

public class Network {

    private NetworkConnectPanel networkConnectPanel;
    private NetworkManager networkManager;
    private Thread thread;
    private boolean isTryingToConnect = false;
    private boolean isConnected = false;

    public Network(Stage primaryStage, GraphicManager graphicManager, Player player0, Player player1) {
        this.networkConnectPanel = new NetworkConnectPanel(primaryStage);


        networkConnectPanel.getConnectButton().setOnAction(e -> {
            networkConnectPanel.setConnectionStatus("");
            Task task = new Task<Void>() {
                @Override
                public Void call() {
                    networkConnectPanel.getConnectButton().setVisible(false);
                    networkConnectPanel.getConnectButton().setManaged(false);
                    networkConnectPanel.getHostButton().setVisible(false);
                    networkConnectPanel.getHostButton().setManaged(false);
                    networkConnectPanel.getCancelButton().setVisible(true);
                    networkConnectPanel.getCancelButton().setManaged(true);
                    networkManager.connect();
                    networkConnectPanel.getConnectButton().setVisible(true);
                    networkConnectPanel.getConnectButton().setManaged(true);
                    networkConnectPanel.getHostButton().setVisible(true);
                    networkConnectPanel.getHostButton().setManaged(true);
                    networkConnectPanel.getCancelButton().setVisible(false);
                    networkConnectPanel.getCancelButton().setManaged(false);
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    return null;
                }
            };
            task.setOnSucceeded(e2 -> {
                if (networkManager.isConnected()){
                    graphicManager.loadGameScene();
                }
            });
            thread = new Thread(task);
            thread.start();
        });
        networkConnectPanel.getCancelButton().setOnAction(e -> {
            networkConnectPanel.getConnectButton().setVisible(true);
            networkConnectPanel.getConnectButton().setManaged(true);
            networkConnectPanel.getHostButton().setVisible(true);
            networkConnectPanel.getHostButton().setManaged(true);
            networkConnectPanel.getCancelButton().setVisible(false);
            networkConnectPanel.getCancelButton().setManaged(false);
            thread.stop();
            if (isTryingToConnect) {
                try {
                    networkManager.getServerSocket().close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            isTryingToConnect = false;
            networkManager.nullServerSocket();
            System.out.println("stopped connection");

        });
        networkConnectPanel.getHostButton().setOnAction(e -> {
            networkConnectPanel.setConnectionStatus("");
            networkManager.initializeServer();
            /*
            ListenForServerRequest listenForServerRequest = new ListenForServerRequest(networkManager.getServerSocket(), networkManager.getOos(), networkManager.getOis());
            Thread thread = new Thread(listenForServerRequest);
            thread.run();
            */


            Task task = new Task<Void>() {
                @Override
                public Void call() {
                    isTryingToConnect = true;
                    while (isTryingToConnect){
                        System.out.println("waiting for connection");
                        ListenForServerRequest listenForServerRequest = new ListenForServerRequest(networkManager.getServerSocket(), networkManager.getOos(), networkManager.getOis());
                        isConnected = listenForServerRequest.listen();
                        System.out.println("connection done");
                        if (isConnected) {
                            networkConnectPanel.setConnectionStatus("Successfully accepted client request.");
                            networkConnectPanel.getConnectButton().setVisible(false);
                            networkConnectPanel.getConnectButton().setManaged(false);
                            networkConnectPanel.getHostButton().setVisible(false);
                            networkConnectPanel.getHostButton().setManaged(false);
                            networkConnectPanel.getCancelButton().setVisible(true);
                            networkConnectPanel.getCancelButton().setManaged(true);
                            isTryingToConnect = false;
                        }
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("finished");
                    return null;
                }
            };
            task.setOnSucceeded(e2 -> {
                if (networkManager.isClientAccepted()){
                    graphicManager.loadGameScene();
                }
            });
            thread = new Thread(task);
            thread.start();
            networkConnectPanel.getConnectButton().setVisible(false);
            networkConnectPanel.getConnectButton().setManaged(false);
            networkConnectPanel.getHostButton().setVisible(false);
            networkConnectPanel.getHostButton().setManaged(false);
            networkConnectPanel.getCancelButton().setVisible(true);
            networkConnectPanel.getCancelButton().setManaged(true);
        });
    }

    public NetworkConnectPanel getNetworkConnectPanel() {
        return networkConnectPanel;
    }

    public NetworkManager getNetworkManager() {
        return networkManager;
    }
}
