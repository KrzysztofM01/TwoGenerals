package graphic.buttons;

import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import variables.VariablesGraphics;

public class ExitButton extends StackPane {

    public ExitButton() {
        this.setId("exitButton");
        this.setPrefSize(VariablesGraphics.screenWidth*0.14, VariablesGraphics.screenHeight*0.12);
        this.setLayoutY(VariablesGraphics.screenHeight*0.87);
        this.setLayoutX(VariablesGraphics.screenWidth*0.85);
        Text exitText = new Text("Exit");
        Text exitGameText = new Text("Game");
        exitText.setId("exitText");
        exitGameText.setId("exitGameText");
        exitText.setTranslateY(-VariablesGraphics.screenHeight*0.021);
        exitGameText.setTranslateY(VariablesGraphics.screenHeight*0.019);
        this.getChildren().addAll(exitText,exitGameText);
    }
}
