package graphic.cards.cardPreview;

import javafx.scene.text.Text;
import variables.VariablesGraphics;

class CardPreviewTextName extends Text {

    CardPreviewTextName(String cardName) {

        this.setId("cardPreviewTextName");
        this.setText(cardName);
        this.setTranslateY(VariablesGraphics.getInstance().getScreenHeight() / 6.8);
    }
}
