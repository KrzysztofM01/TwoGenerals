package graphic.cards;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import variables.VariablesGraphics;

public class CardText extends StackPane {
    private int power;
    private Text text;

    public CardText(int number, CardTextType cardTextType) {
        this.power = number;
        this.setMaxSize(VariablesGraphics.cardWidth/5, VariablesGraphics.cardHeight/8);
        this.setTranslateY(-VariablesGraphics.cardHeight/2.65- VariablesGraphics.cardPadding);
        switch (cardTextType){
            case power:
                this.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
                this.setTranslateX(-VariablesGraphics.cardWidth/2.65- VariablesGraphics.cardPadding);
                break;
            case cost:
                this.setBackground(new Background(new BackgroundFill(Color.LIGHTSEAGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
                this.setTranslateX(-VariablesGraphics.cardWidth/6.5- VariablesGraphics.cardPadding);
                break;
        }


        this.text = new Text(Integer.toString(number));
        this.getChildren().add(this.text);
    }

    public void setText(int power) {
        this.text.setText(Integer.toString(power));
    }

    public Text getText() {
        return text;
    }
}