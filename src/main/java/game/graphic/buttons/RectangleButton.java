package game.graphic.buttons;

import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import game.variables.VGraphics;

public class RectangleButton extends StackPane {

    public RectangleButton(String text) {

        this.setId("rectangleButton");
        this.setPrefSize(VGraphics.getInstance().getScreenWidth() * 0.14, VGraphics.getInstance().getScreenHeight() * 0.12);
        this.setLayoutY(VGraphics.getInstance().getScreenHeight() * 0.87);
        this.setLayoutX(VGraphics.getInstance().getScreenWidth() * 0.85);

        Text exitGameText = new Text(text);
        exitGameText.setTextAlignment(TextAlignment.CENTER);
        exitGameText.setId("rectangleButtonText");
        exitGameText.setWrappingWidth(VGraphics.getInstance().getScreenWidth() * 0.1);
        exitGameText.setTranslateY(-VGraphics.getInstance().getScreenHeight() * 0.005);

        this.getChildren().add(exitGameText);
    }
}
