package graphic.cards.cardPreview;

import javafx.scene.text.Text;
import variables.VariablesGraphics;

class CardPreviewTextType extends Text {
    CardPreviewTextType(String cardType) {

        this.setId("cardPreviewTextType");
        this.setText(cardType);
        this.setTranslateY(VariablesGraphics.getInstance().getScreenHeight()*0.175);
    }
}
