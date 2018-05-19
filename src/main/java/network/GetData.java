package network;

import java.io.IOException;
import java.io.ObjectInputStream;

public class GetData implements Runnable {

    public ObjectInputStream ois;

    public GetData(ObjectInputStream ois) {
        this.ois = ois;
    }

    @Override
    public void run() {
        while (true){
            try {
                Object receivedObject = ois.readObject();
                MethodWrapper methodWrapper = (MethodWrapper) receivedObject;
                methodWrapper.unwrapMethod();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
