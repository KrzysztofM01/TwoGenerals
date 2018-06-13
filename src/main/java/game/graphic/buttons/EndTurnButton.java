package game.graphic.buttons;

import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import game.variables.VGraphics;

public class EndTurnButton extends StackPane{

    private Text endText = new Text("End");

    public EndTurnButton (boolean isEndTurnButton) {

        this.setId("endTurnButton");

        this.setPrefSize(VGraphics.getInstance().getScreenWidth()*0.11, VGraphics.getInstance().getScreenHeight()*0.11);
        this.setLayoutY(VGraphics.getInstance().getScreenHeight()*0.73);
        this.setLayoutX(VGraphics.getInstance().getScreenWidth()*0.87);

        Text endTurnText = new Text("Turn");
        endTurnText.setId("endTurnText");
        endTurnText.setTranslateY(VGraphics.getInstance().getScreenHeight()*0.019);

        endText.setId("endText");
        endText.setTranslateY(-VGraphics.getInstance().getScreenHeight()*0.021);

        // Checks if this is button for game or for card preview in menu
        if (!isEndTurnButton) {
            this.setLayoutX(VGraphics.getInstance().getScreenWidth()*0.815);
            this.setLayoutY(VGraphics.getInstance().getScreenHeight()*0.65);
            endTurnText.setText("Deck");
            endText.setText("Save");
        }

        this.getChildren().addAll(endText, endTurnText);
    }

    public void setYourTurnText(boolean yourTurn){
        if (yourTurn){
            this.endText.setText("End");
        } else {
            this.endText.setText("Opponent");
        }
    }
}