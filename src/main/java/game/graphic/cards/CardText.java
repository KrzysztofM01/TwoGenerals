package game.graphic.cards;


import javafx.scene.layout.StackPane;
import game.variables.VGraphics;

import java.io.Serializable;

public class CardText extends StackPane implements Serializable{
    private SerializableText text;

    CardText(int number, boolean isPowerText) {

        this.setMaxSize(VGraphics.getInstance().getCardWidth()/5, VGraphics.getInstance().getCardHeight()/8);

        // Depending on whether it's power text (Red/Blue flag text) or action cost text (Purple Flag)
        // set the proper position
        if (isPowerText){
            this.setTranslateX(-VGraphics.getInstance().getCardWidth()/2.78- VGraphics.getInstance().getCardPadding());
            this.setTranslateY(-VGraphics.getInstance().getCardHeight()/2.72- VGraphics.getInstance().getCardPadding());
        } else {
            this.setTranslateX(-VGraphics.getInstance().getCardWidth()/2.6- VGraphics.getInstance().getCardPadding());
            this.setTranslateY(VGraphics.getInstance().getCardHeight()/3.1- VGraphics.getInstance().getCardPadding());
        }

        text = new SerializableText(Integer.toString(number));
        text.setId("cardNumbers");

        this.getChildren().add(this.text);
    }

    public void setText(int power) {
        this.text.setText(Integer.toString(power));
    }
}
