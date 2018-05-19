package graphic.cards;

import javafx.scene.image.Image;

import java.io.InputStream;
import java.io.Serializable;

public class CardImage extends Image implements Serializable{

    public CardImage(String url) {
        super(url);
    }
}
