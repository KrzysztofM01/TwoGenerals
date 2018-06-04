package graphic.cards.cardPreview;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import variables.VariablesGraphics;

class CardPreviewTextNumbers extends StackPane {

    CardPreviewTextNumbers(int number, boolean isPowerText) {

        this.setMaxSize(VariablesGraphics.getInstance().getCardPrevieWidth() / 5, VariablesGraphics.getInstance().getCardPreviewHeigth() / 8);

        // Depending on whether it's power text (Red/Blue flag text) or action cost text (Purple Flag)
        // set the proper position
        if (isPowerText) {
            this.setTranslateX((-VariablesGraphics.getInstance().getCardWidth() / 2.7 - VariablesGraphics.getInstance().getCardPadding()) * 2.34);
            this.setTranslateY((-VariablesGraphics.getInstance().getCardHeight() / 2.72 - VariablesGraphics.getInstance().getCardPadding()) * 2.4);
        } else {
            this.setTranslateX((-VariablesGraphics.getInstance().getCardWidth() / 2.6 - VariablesGraphics.getInstance().getCardPadding()) * 2.4);
            this.setTranslateY((VariablesGraphics.getInstance().getCardHeight() / 3.1 - VariablesGraphics.getInstance().getCardPadding()) * 2.5);
        }


        Text text = new Text(Integer.toString(number));
        text.setId("cardPreviewTextNumbers");

        this.getChildren().add(text);
    }

}