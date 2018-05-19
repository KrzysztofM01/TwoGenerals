package graphic.cards.cardPreview;

import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import variables.VariablesGraphics;

public class CardPreviewTextDescription extends Text {

    public CardPreviewTextDescription(String cardDescription) {
        this.setId("cardPreviewTextDescription");
        this.setWrappingWidth(VariablesGraphics.battleFieldWidth*0.65);
        this.setTextAlignment(TextAlignment.JUSTIFY);
        this.setText(cardDescription);
        this.setTranslateY(VariablesGraphics.screenHeight*0.22);
    }
}
