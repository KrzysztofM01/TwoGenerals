package game.graphic.panes;

import game.graphic.PlayerType;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import game.variables.VGraphics;
import game.variables.VLogic;

public class PlayerHealthBox extends StackPane {

    private Text HPAmountText = new Text(Integer.toString(VLogic.getInstance().getPlayerHitPoints()));

    public PlayerHealthBox(PlayerType playerType) {

        Text playerText = new Text("Player");
        Text HPText = new Text("HP:");

        this.setId(playerType.toString() + "HealthBox");
        playerText.setId("playerText");
        HPText.setId("HPText");
        HPAmountText.setId("HPAmountText");

        this.setPrefSize(VGraphics.getInstance().getScreenWidth() * 0.11, VGraphics.getInstance().getScreenHeight() * 0.16);
        this.setLayoutX(VGraphics.getInstance().getScreenWidth() * 0.75);
        switch (playerType) {
            case player:
                this.setLayoutY(VGraphics.getInstance().getPlayerCardPositionY() - VGraphics.getInstance().getCardHeight() * 0.75);
                break;
            case opponent:
                this.setLayoutY(VGraphics.getInstance().getScreenHeight() * 0.01);
                break;
        }

        playerText.setTranslateY(-VGraphics.getInstance().getScreenHeight() * 0.038);
        HPText.setTranslateY(-VGraphics.getInstance().getScreenHeight() * 0.01);
        HPAmountText.setTranslateY(VGraphics.getInstance().getScreenHeight() * 0.015);

        this.getChildren().addAll(playerText, this.HPAmountText, HPText);
    }

    public void setHPAmount(int HPamount) {
        this.HPAmountText.setText(Integer.toString(HPamount));
    }
}
