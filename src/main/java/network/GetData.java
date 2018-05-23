package network;

import graphic.GraphicManager;
import javafx.application.Platform;
import main.GameManager;

import java.io.IOException;
import java.io.ObjectInputStream;

public class GetData implements Runnable {

    public ObjectInputStream ois;
    private GameManager gameManager;

    public GetData(ObjectInputStream ois, GameManager gameManager) {
        this.ois = ois;
        this.gameManager = gameManager;
    }

    @Override
    public void run() {
        while (true){
            try {
                Object receivedObject = ois.readObject();
                MethodWrapper methodWrapper = (MethodWrapper) receivedObject;
                Platform.runLater(
                        () -> methodWrapper.unwrapMethod(gameManager)
                );
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
