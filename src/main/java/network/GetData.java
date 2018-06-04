package network;

import javafx.application.Platform;
import main.GameManager;

import java.io.IOException;
import java.io.ObjectInputStream;

public class GetData implements Runnable {

    private ObjectInputStream ois;
    private GameManager gameManager;

    public GetData(ObjectInputStream ois, GameManager gameManager) {
        this.ois = ois;
        this.gameManager = gameManager;
    }

    @SuppressWarnings("InfiniteLoopStatement")
    // It's supposed to be infinite, will be broken when someone stops the thread
    @Override
    public void run() {
        while (true){
            try {
                Object receivedObject = ois.readObject();
                MethodWrapper methodWrapper = (MethodWrapper) receivedObject;
                Platform.runLater( () -> methodWrapper.unwrapMethod(gameManager));
                Thread.sleep(25);
            } catch (IOException | ClassNotFoundException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
