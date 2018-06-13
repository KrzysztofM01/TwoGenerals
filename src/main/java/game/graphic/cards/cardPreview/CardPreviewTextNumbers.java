package game.graphic.cards.cardPreview;

import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import game.variables.VGraphics;

public class CardPreviewTextNumbers extends StackPane {

    public CardPreviewTextNumbers(int number, boolean isPowerText) {

        this.setMaxSize(VGraphics.getInstance().getCardPrevieWidth() / 5, VGraphics.getInstance().getCardPreviewHeigth() / 8);

        // Depending on whether it's power text (Red/Blue flag text) or action cost text (Purple Flag)
        // set the proper position
        if (isPowerText) {
            this.setTranslateX((-VGraphics.getInstance().getCardWidth() / 2.7 - VGraphics.getInstance().getCardPadding()) * 2.34);
            this.setTranslateY((-VGraphics.getInstance().getCardHeight() / 2.72 - VGraphics.getInstance().getCardPadding()) * 2.4);
        } else {
            this.setTranslateX((-VGraphics.getInstance().getCardWidth() / 2.6 - VGraphics.getInstance().getCardPadding()) * 2.4);
            this.setTranslateY((VGraphics.getInstance().getCardHeight() / 3.1 - VGraphics.getInstance().getCardPadding()) * 2.5);
        }

        Text text = new Text(Integer.toString(number));
        text.setId("cardPreviewTextNumbers");

        this.getChildren().add(text);
    }

}