package graphic.cards.cardPreview;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import variables.VariablesGraphics;

public class CardPreviewTextNumbers extends StackPane {

    public CardPreviewTextNumbers(int number, boolean isPowerText) {

        this.setMaxSize(VariablesGraphics.cardPrevieWidth/5, VariablesGraphics.cardPreviewHeigth/8);

        if (isPowerText){
            this.setTranslateX((-VariablesGraphics.cardWidth/2.7- VariablesGraphics.cardPadding)*2.34);
            this.setTranslateY((-VariablesGraphics.cardHeight/2.72- VariablesGraphics.cardPadding)*2.4);
        } else {
            this.setTranslateX((-VariablesGraphics.cardWidth/2.6- VariablesGraphics.cardPadding) *2.4);
            this.setTranslateY((VariablesGraphics.cardHeight/3.1- VariablesGraphics.cardPadding)* 2.5);
        }


        Text text = new Text(Integer.toString(number));
        text.setId("cardPreviewTextNumbers");

        this.getChildren().add(text);
    }

}