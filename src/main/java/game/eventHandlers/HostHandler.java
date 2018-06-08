package game.eventHandlers;

import game.graphic.PlayerType;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import game.GameManager;
import game.network.GetData;
import game.network.NetworkManager;
import game.variables.VariablesLogic;

public class HostHandler implements EventHandler<ActionEvent> {

    private NetworkManager networkManager;
    private GameManager gameManager;

    public HostHandler(NetworkManager networkManager, GameManager gameManager) {
        this.networkManager = networkManager;
        this.gameManager = gameManager;
    }

    @Override
    public void handle(ActionEvent event) {
        networkManager.getNetworkConnectPanel().setConnectionStatus("");
        networkManager.getNetworkConnectPanel().showCancelButton();
        networkManager.initializeServer();
        Task task = new Task<Void>() {
            @Override
            public Void call() {
                // Task will keep running and waiting for proper connection, if connection was denied due to
                // some reasons, the server will be still waiting for another connection until cancelled or
                // succeeded.
                while (networkManager.isHosting()) {
                    networkManager.getNetworkConnectPanel().setConnectionStatus("Waiting for connection...");
                    networkManager.listenForServerRequest();
                    if (networkManager.isClientAccepted()) {
                        networkManager.getNetworkConnectPanel().setConnectionStatus("Successfully accepted client request.");
                        networkManager.getNetworkConnectPanel().hideCancelButton();
                        networkManager.setHosting(false);
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
            // You have to check if the connection was successful, task could've been finished via 'cancel'
            if (networkManager.isClientAccepted()) {
                // Load the game scene, start the receive thread and send your cards to opponent
                // also set your AP to half of it for balance purposes
                gameManager.getLogicManager().getPlayer(PlayerType.player).setActionPoints(VariablesLogic.getInstance().getPlayerActionPoints() / 2);
                gameManager.getGraphicManager().setActionPointsText(VariablesLogic.getInstance().getPlayerActionPoints() / 2);
                GetData getData = new GetData(networkManager.getOis(), gameManager);
                networkManager.setReceiveDataThread(new Thread(getData));
                networkManager.getReceiveDataThread().start();
                networkManager.sendCardsThroughNetwork(gameManager.getGraphicManager());
                gameManager.getGraphicManager().loadGameScene(true);
            }
        });
        // Set and start the host thread
        networkManager.setThread(new Thread(task));
        networkManager.getThread().start();
    }
}