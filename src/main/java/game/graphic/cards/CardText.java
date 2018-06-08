package game.graphic.cards;


import javafx.scene.layout.StackPane;
import game.variables.VariablesGraphics;

import java.io.Serializable;

public class CardText extends StackPane implements Serializable{
    private SerializableText text;

    CardText(int number, boolean isPowerText) {

        this.setMaxSize(VariablesGraphics.getInstance().getCardWidth()/5, VariablesGraphics.getInstance().getCardHeight()/8);

        // Depending on whether it's power text (Red/Blue flag text) or action cost text (Purple Flag)
        // set the proper position
        if (isPowerText){
            this.setTranslateX(-VariablesGraphics.getInstance().getCardWidth()/2.78- VariablesGraphics.getInstance().getCardPadding());
            this.setTranslateY(-VariablesGraphics.getInstance().getCardHeight()/2.72- VariablesGraphics.getInstance().getCardPadding());
        } else {
            this.setTranslateX(-VariablesGraphics.getInstance().getCardWidth()/2.6- VariablesGraphics.getInstance().getCardPadding());
            this.setTranslateY(VariablesGraphics.getInstance().getCardHeight()/3.1- VariablesGraphics.getInstance().getCardPadding());
        }

        text = new SerializableText(Integer.toString(number));
        text.setId("cardNumbers");

        this.getChildren().add(this.text);
    }

    public void setText(int power) {
        this.text.setText(Integer.toString(power));
    }
}
