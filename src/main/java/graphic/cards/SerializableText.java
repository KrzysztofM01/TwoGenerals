package graphic.cards;

import javafx.scene.text.Text;

import java.io.Serializable;

public class SerializableText extends Text implements Serializable {
    public SerializableText(String text) {
        super(text);
    }
}
