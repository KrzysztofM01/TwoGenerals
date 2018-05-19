package graphic.panes;

import graphic.PlayerType;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import variables.VariablesGraphics;
import variables.VariablesLogic;

public class PlayerHealthBox extends StackPane{
    private PlayerType playerType;
    private Text playerText = new Text("Player");
    private Text HPText = new Text("HP:");
    private Text HPAmountText = new Text(Integer.toString(VariablesLogic.playerHitPoints));

    public PlayerHealthBox(PlayerType playerType) {
        this.playerType = playerType;
        this.setId(playerType.toString() + "HealthBox");
        this.playerText.setId("playerText");
        this.HPText.setId("HPText");
        this.HPAmountText.setId("HPAmountText");
        this.setPrefSize(VariablesGraphics.screenWidth*0.11, VariablesGraphics.screenHeight*0.16);
        this.setLayoutX(VariablesGraphics.screenWidth*0.75);
        switch (playerType){
            case player:
                this.setLayoutY(VariablesGraphics.playerCardPositionY - VariablesGraphics.cardHeight*0.75);
                break;
            case opponent:
                this.setLayoutY(VariablesGraphics.screenHeight*0.01);
                break;
        }

        this.playerText.setTranslateY(-VariablesGraphics.screenHeight*0.038);
        this.HPText.setTranslateY(-VariablesGraphics.screenHeight*0.01);
        this.HPAmountText.setTranslateY(VariablesGraphics.screenHeight*0.015);
        this.getChildren().addAll(this.playerText, this.HPAmountText, this.HPText);
    }

    public void setHPAmount(int HPamount){
        this.HPAmountText.setText(Integer.toString(HPamount));
    }
}
