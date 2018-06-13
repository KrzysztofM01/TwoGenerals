package game.graphic.cards.cardPreview;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import game.variables.VGraphics;

class CardPreviewTextDescription extends StackPane {

    CardPreviewTextDescription(String cardDescription) {

        Text text = new Text(cardDescription);
        text.setId("cardPreviewTextDescription");
        text.setWrappingWidth(VGraphics.getInstance().getBattleFieldWidth() * 0.65);
        text.setTextAlignment(TextAlignment.JUSTIFY);

        this.setAlignment(Pos.TOP_CENTER);
        this.setTranslateY(VGraphics.getInstance().getBattleFieldHeight() * 0.9);
        this.getChildren().add(text);
    }
}
