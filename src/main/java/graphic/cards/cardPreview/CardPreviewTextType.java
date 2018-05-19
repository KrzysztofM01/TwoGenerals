package graphic.cards.cardPreview;

import javafx.scene.text.Text;
import variables.VariablesGraphics;

public class CardPreviewTextType extends Text {
    public CardPreviewTextType(String cardType) {
        this.setId("cardPreviewTextType");
        this.setText(cardType);
        this.setTranslateY(VariablesGraphics.screenHeight*0.175);
    }
}
