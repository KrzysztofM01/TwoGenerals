package graphic.cards;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import variables.VariablesGraphics;

import java.io.Serializable;

public class CardImageView extends ImageView implements Serializable {
    public CardImageView(Image image) {
        super(image);
        this.setFitWidth(VariablesGraphics.cardWidth);
        this.setFitHeight(VariablesGraphics.cardHeight);
    }
}
