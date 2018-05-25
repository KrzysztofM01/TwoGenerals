package graphic.cards;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import variables.VariablesGraphics;

import java.io.Serializable;

public class CardText extends StackPane implements Serializable{
    private SerializableText text;

    public CardText(int number, boolean isPowerText) {
        this.setMaxSize(VariablesGraphics.cardWidth/5, VariablesGraphics.cardHeight/8);

        if (isPowerText){
            this.setTranslateX(-VariablesGraphics.cardWidth/2.78- VariablesGraphics.cardPadding);
            this.setTranslateY(-VariablesGraphics.cardHeight/2.72- VariablesGraphics.cardPadding);
        } else {
            this.setTranslateX(-VariablesGraphics.cardWidth/2.6- VariablesGraphics.cardPadding);
            this.setTranslateY(VariablesGraphics.cardHeight/3.1- VariablesGraphics.cardPadding);
        }

        this.text = new SerializableText(Integer.toString(number));
        this.text.setId("cardNumbers");
        this.getChildren().add(this.text);
    }

    public void setText(int power) {
        this.text.setText(Integer.toString(power));
    }

    public SerializableText getText() {
        return text;
    }
}
