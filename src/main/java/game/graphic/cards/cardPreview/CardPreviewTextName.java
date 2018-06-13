package game.graphic.cards.cardPreview;

import javafx.scene.text.Text;
import game.variables.VGraphics;

class CardPreviewTextName extends Text {

    CardPreviewTextName(String cardName) {

        this.setId("cardPreviewTextName");
        this.setText(cardName);
        this.setTranslateY(VGraphics.getInstance().getScreenHeight() / 6.8);
    }
}
