package graphics;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import variables.VariablesGraphics;

public class PowerText extends StackPane {
    private int power;
    private Text text;

    public PowerText(int power) {
        this.power = power;
        this.setMaxSize(VariablesGraphics.cardWidth/5, VariablesGraphics.cardHeight/8);
        //this.setPrefSize(VariablesGraphics.cardWidth/5, VariablesGraphics.cardHeight/8);
        this.setTranslateX(-VariablesGraphics.cardWidth/2.65- VariablesGraphics.cardPadding);
        this.setTranslateY(-VariablesGraphics.cardHeight/2.65- VariablesGraphics.cardPadding);
        this.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        this.text = new Text(Integer.toString(power));
        this.getChildren().add(this.text);
    }

    public void setText(int power) {
        this.text.setText(Integer.toString(power));
    }

    public Text getText() {
        return text;
    }
}
