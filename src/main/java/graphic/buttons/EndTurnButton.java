package graphic.buttons;

import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import variables.VariablesGraphics;

public class EndTurnButton extends StackPane{

    private Text endText = new Text("End");

    public EndTurnButton () {

        this.setId("endTurnButton");

        this.setPrefSize(VariablesGraphics.getInstance().getScreenWidth()*0.11, VariablesGraphics.getInstance().getScreenHeight()*0.11);
        this.setLayoutY(VariablesGraphics.getInstance().getScreenHeight()*0.73);
        this.setLayoutX(VariablesGraphics.getInstance().getScreenWidth()*0.87);

        Text endTurnText = new Text("Turn");
        endTurnText.setId("endTurnText");
        endTurnText.setTranslateY(VariablesGraphics.getInstance().getScreenHeight()*0.019);

        endText.setId("endText");
        endText.setTranslateY(-VariablesGraphics.getInstance().getScreenHeight()*0.021);

        this.getChildren().addAll(endText,endTurnText);
    }

    public void setYourTurnText(boolean yourTurn){
        if (yourTurn){
            this.endText.setText("End");
        } else {
            this.endText.setText("Opponent");
        }
    }
}