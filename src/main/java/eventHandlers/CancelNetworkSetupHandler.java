package eventHandlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import network.NetworkManager;

import java.io.IOException;

public class CancelNetworkSetupHandler implements EventHandler<ActionEvent> {

    private NetworkManager networkManager;

    public CancelNetworkSetupHandler(NetworkManager networkManager) {
        this.networkManager = networkManager;
    }

    @Override
    public void handle(ActionEvent event) {
        networkManager.getNetworkConnectPanel().hideCancelButton();
        networkManager.getThread().stop();
        if (networkManager.isHosting()) {
            try {
                networkManager.getServerSocket().close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        networkManager.setHosting(false);
        networkManager.setServerSocket(null);
        networkManager.getNetworkConnectPanel().setConnectionStatus("Process stopped.");
    }
}
