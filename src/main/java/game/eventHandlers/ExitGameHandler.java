package game.eventHandlers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import game.network.MethodWrapper;
import game.network.NetworkManager;

import java.io.IOException;

public class ExitGameHandler implements EventHandler<MouseEvent> {

    private NetworkManager networkManager;
    private Stage primaryStage;

    public ExitGameHandler(NetworkManager networkManager, Stage primaryStage) {
        this.networkManager = networkManager;
        this.primaryStage = primaryStage;
    }

    @Override
    public void handle(MouseEvent event) {
        try {
            networkManager.getOos().writeObject(MethodWrapper.stopGettingData());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        primaryStage.close();
        networkManager.closeReceiveDataThread();
    }
}
