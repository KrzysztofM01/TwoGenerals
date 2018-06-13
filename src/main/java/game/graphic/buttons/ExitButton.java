package game.graphic.buttons;

import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import game.variables.VGraphics;

public class ExitButton extends StackPane {

    public ExitButton(boolean isExitButton) {

        this.setId("exitButton");

        this.setPrefSize(VGraphics.getInstance().getScreenWidth() * 0.14, VGraphics.getInstance().getScreenHeight() * 0.12);
        this.setLayoutY(VGraphics.getInstance().getScreenHeight() * 0.87);
        this.setLayoutX(VGraphics.getInstance().getScreenWidth() * 0.85);

        Text exitGameText = new Text("Exit Game");
        exitGameText.setTextAlignment(TextAlignment.CENTER);

        // Checks if this is button for game or for card preview in menu
        if (!isExitButton) {
            this.setLayoutX(VGraphics.getInstance().getScreenWidth() * 0.8);
            this.setLayoutY(VGraphics.getInstance().getScreenHeight() * 0.79);
            exitGameText.setText("Back to Menu");
        }

        exitGameText.setId("exitGameText");
        exitGameText.setWrappingWidth(VGraphics.getInstance().getScreenWidth() * 0.1);
        exitGameText.setTranslateY(-VGraphics.getInstance().getScreenHeight() * 0.005);

        this.getChildren().add(exitGameText);
    }
}
