package graphic.buttons;

import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import variables.VariablesGraphics;

public class EndTurnButton extends StackPane{

    private Text endText = new Text("End");

    public EndTurnButton () {
        this.setId("endTurnButton");
        this.setPrefSize(VariablesGraphics.screenWidth*0.11, VariablesGraphics.screenHeight*0.11);
        this.setLayoutY(VariablesGraphics.screenHeight*0.73);
        this.setLayoutX(VariablesGraphics.screenWidth*0.87);
        Text endTurnText = new Text("Turn");
        endText.setId("endText");
        endTurnText.setId("endTurnText");
        endText.setTranslateY(-VariablesGraphics.screenHeight*0.021);
        endTurnText.setTranslateY(VariablesGraphics.screenHeight*0.019);
        this.getChildren().addAll(endText,endTurnText);
    }

    public void isYourTurnText (boolean isYourTurn){
        if (isYourTurn){
            this.endText.setText("End");
        } else {
            this.endText.setText("Opponent");
        }
    }
}