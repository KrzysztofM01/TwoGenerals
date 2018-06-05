package eventHandlers;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import main.GameManager;
import network.GetData;
import network.NetworkManager;

public class ConnectHandler implements EventHandler<ActionEvent> {
    
    private NetworkManager networkManager;
    private GameManager gameManager;

    public ConnectHandler(NetworkManager networkManager, GameManager gameManager) {
        this.networkManager = networkManager;
        this.gameManager = gameManager;
    }

    @Override
    public void handle(ActionEvent event) {
        networkManager.getNetworkConnectPanel().setConnectionStatus("");
        Task task = new Task<Void>() {
            @Override
            public Void call() {
                // A task that will try to connect to typed IP and port, every 0,1 s
                networkManager.getNetworkConnectPanel().showCancelButton();
                networkManager.getNetworkConnectPanel().setConnectionStatus("Connecting to server...");
                networkManager.connect();
                networkManager.getNetworkConnectPanel().hideCancelButton();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        task.setOnSucceeded(e2 -> {
            // You have to check if the connection was successful, task could've been finished via 'cancel'
            if (networkManager.isConnected()) {
                // Load the game scene, start the receive thread and send your cards to opponent
                GetData getData = new GetData(networkManager.getOis(), gameManager);
                networkManager.setReceiveDataThread(new Thread(getData));
                networkManager.getReceiveDataThread().start();
                networkManager.sendCardsThroughNetwork(gameManager.getGraphicManager());
                gameManager.getGraphicManager().loadGameScene(false);
            }
        });
        // Set and start the connection thread
        networkManager.setThread (new Thread(task));
        networkManager.getThread().start();
    }
}
