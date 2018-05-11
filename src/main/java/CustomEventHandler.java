import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class CustomEventHandler implements EventHandler<MouseEvent> {

    @Override
    public void handle(MouseEvent event) {
        System.out.println(event.getPickResult().getIntersectedNode().toString());
    }
}
