package graphic.buttons;

import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import variables.VariablesGraphics;

public class ExitButton extends StackPane {

    public ExitButton() {

        this.setId("exitButton");

        this.setPrefSize(VariablesGraphics.getInstance().getScreenWidth() * 0.14, VariablesGraphics.getInstance().getScreenHeight() * 0.12);
        this.setLayoutY(VariablesGraphics.getInstance().getScreenHeight() * 0.87);
        this.setLayoutX(VariablesGraphics.getInstance().getScreenWidth() * 0.85);

        Text exitGameText = new Text("Exit Game");
        exitGameText.setTextAlignment(TextAlignment.CENTER);
        exitGameText.setId("exitGameText");
        exitGameText.setWrappingWidth(VariablesGraphics.getInstance().getScreenWidth() * 0.1);
        exitGameText.setTranslateY(-VariablesGraphics.getInstance().getScreenHeight() * 0.005);

        this.getChildren().add(exitGameText);
    }
}
