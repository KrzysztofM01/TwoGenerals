package graphic.cards.cardPreview;

import javafx.scene.text.Text;
import variables.VariablesGraphics;

public class CardPreviewTextName extends Text{

    public CardPreviewTextName(String cardName) {
        this.setId("cardPreviewTextName");
        this.setText(cardName);
        this.setTranslateY(VariablesGraphics.screenHeight/6.8);
    }
}
