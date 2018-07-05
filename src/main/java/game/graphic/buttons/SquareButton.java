package game.graphic.buttons;

import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import game.variables.VGraphics;
import javafx.scene.text.TextAlignment;

public class SquareButton extends StackPane {

    private Text buttonText;

    public SquareButton(String text) {

        this.setId("squareButton");
        this.setPrefSize(VGraphics.getInstance().getScreenWidth() * 0.11, VGraphics.getInstance().getScreenHeight() * 0.11);
        this.setLayoutY(VGraphics.getInstance().getScreenHeight() * 0.73);
        this.setLayoutX(VGraphics.getInstance().getScreenWidth() * 0.87);

        buttonText = new Text(text);
        buttonText.setTextAlignment(TextAlignment.CENTER);
        buttonText.setId("squareButtonText");
        buttonText.setTranslateY(-VGraphics.getInstance().getScreenHeight() * 0.005);
        buttonText.setWrappingWidth(VGraphics.getInstance().getScreenWidth() * 0.1);

        this.getChildren().add(buttonText);

    }

    public void setYourTurnText(boolean yourTurn) {
        if (yourTurn) {
            this.buttonText.setText("End Turn");
        } else {
            this.buttonText.setText("Opponent Turn");
        }
    }
}