package game.graphic.cards.cardPreview;

import javafx.scene.text.Text;
import game.variables.VGraphics;

class CardPreviewTextType extends Text {
    CardPreviewTextType(String cardType) {

        this.setId("cardPreviewTextType");
        this.setText(cardType);
        this.setTranslateY(VGraphics.getInstance().getScreenHeight()*0.175);
    }
}
