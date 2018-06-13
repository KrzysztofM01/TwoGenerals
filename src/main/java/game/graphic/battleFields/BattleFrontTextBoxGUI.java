package game.graphic.battleFields;

import game.logic.battleFields.LineType;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import game.graphic.PlayerType;
import game.variables.VGraphics;
import game.variables.VLogic;

public class BattleFrontTextBoxGUI extends Pane {

    private Text hitPointsAmount = new Text(Integer.toString(VLogic.getInstance().getBattleFieldHitPoints()));
    private Text powerAmount = new Text("0");

    private PlayerType playerType;
    private LineType lineType;

    BattleFrontTextBoxGUI(PlayerType playerType, LineType lineType) {

        this.playerType = playerType;
        this.lineType = lineType;

        // Set layout of this pane
        this.setId("BattleFrontTextBoxGUI");
        this.setPrefSize(VGraphics.getInstance().getBattleFrontTextBoxWidth(), VGraphics.getInstance().getBattleFrontTextBoxHeight());
        this.setLayoutX(VGraphics.getInstance().getBattleFieldWidth() / 8);
        if (playerType == PlayerType.player) {
            this.setLayoutY(VGraphics.getInstance().getBattleFieldHeight());
        } else {
            this.setLayoutY(-VGraphics.getInstance().getBattleFrontTextBoxHeight());
        }

        Text hitPoints = new Text("Hit Points: ");
        Text power = new Text("Power: ");

        // Set IDs of texts, needed for css styling
        hitPoints.setId("hitPoints");
        hitPointsAmount.setId("hitPointsAmount");
        power.setId("power");
        powerAmount.setId("powerAmount");

        // Set layout positions
        hitPoints.setLayoutX(VGraphics.getInstance().getBattleFrontTextBoxWidth() * 0.05);
        hitPoints.setLayoutY(VGraphics.getInstance().getBattleFrontTextBoxHeight() * 0.4);
        hitPointsAmount.setLayoutX(VGraphics.getInstance().getBattleFrontTextBoxWidth() * 0.22);
        hitPointsAmount.setLayoutY(VGraphics.getInstance().getBattleFrontTextBoxHeight() * 0.7);
        power.setLayoutX(VGraphics.getInstance().getBattleFrontTextBoxWidth() * 0.6);
        power.setLayoutY(VGraphics.getInstance().getBattleFrontTextBoxHeight() * 0.4);
        powerAmount.setLayoutX(VGraphics.getInstance().getBattleFrontTextBoxWidth() * 0.69);
        powerAmount.setLayoutY(VGraphics.getInstance().getBattleFrontTextBoxHeight() * 0.7);

        this.getChildren().addAll(hitPoints, hitPointsAmount, power, powerAmount);
    }

    public void setHitPointsAmount(int hitPointsAmount) {
        this.hitPointsAmount.setText(Integer.toString(hitPointsAmount));
    }

    public void setPowerAmount(int powerAmount) {
        this.powerAmount.setText(Integer.toString(powerAmount));
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public LineType getLineType() {
        return lineType;
    }
}