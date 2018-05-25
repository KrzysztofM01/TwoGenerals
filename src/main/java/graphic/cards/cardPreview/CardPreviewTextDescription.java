package graphic.cards.cardPreview;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import variables.VariablesGraphics;

public class CardPreviewTextDescription extends StackPane {

    public CardPreviewTextDescription(String cardDescription) {
        Text text = new Text(cardDescription);
        text.setId("cardPreviewTextDescription");
        text.setWrappingWidth(VariablesGraphics.battleFieldWidth*0.65);
        text.setTextAlignment(TextAlignment.JUSTIFY);
        this.setAlignment(Pos.TOP_CENTER);
        this.setTranslateY(VariablesGraphics.battleFieldHeight*0.9);
        this.getChildren().add(text);
    }
}
