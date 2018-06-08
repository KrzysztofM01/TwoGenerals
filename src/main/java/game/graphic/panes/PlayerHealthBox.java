package game.graphic.panes;

import game.graphic.PlayerType;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import game.variables.VariablesGraphics;
import game.variables.VariablesLogic;

public class PlayerHealthBox extends StackPane {

    private Text HPAmountText = new Text(Integer.toString(VariablesLogic.getInstance().getPlayerHitPoints()));

    public PlayerHealthBox(PlayerType playerType) {

        Text playerText = new Text("Player");
        Text HPText = new Text("HP:");

        this.setId(playerType.toString() + "HealthBox");
        playerText.setId("playerText");
        HPText.setId("HPText");
        HPAmountText.setId("HPAmountText");

        this.setPrefSize(VariablesGraphics.getInstance().getScreenWidth() * 0.11, VariablesGraphics.getInstance().getScreenHeight() * 0.16);
        this.setLayoutX(VariablesGraphics.getInstance().getScreenWidth() * 0.75);
        switch (playerType) {
            case player:
                this.setLayoutY(VariablesGraphics.getInstance().getPlayerCardPositionY() - VariablesGraphics.getInstance().getCardHeight() * 0.75);
                break;
            case opponent:
                this.setLayoutY(VariablesGraphics.getInstance().getScreenHeight() * 0.01);
                break;
        }

        playerText.setTranslateY(-VariablesGraphics.getInstance().getScreenHeight() * 0.038);
        HPText.setTranslateY(-VariablesGraphics.getInstance().getScreenHeight() * 0.01);
        HPAmountText.setTranslateY(VariablesGraphics.getInstance().getScreenHeight() * 0.015);

        this.getChildren().addAll(playerText, this.HPAmountText, HPText);
    }

    public void setHPAmount(int HPamount) {
        this.HPAmountText.setText(Integer.toString(HPamount));
    }
}
