package game.graphic.cards;

import javafx.scene.text.Text;

import java.io.Serializable;

class SerializableText extends Text implements Serializable {

    // This class is needed, because when sending an object through game.network, all it's parameters has to be
    // serializable, thus this class

    SerializableText(String text) {
        super(text);
    }
}
