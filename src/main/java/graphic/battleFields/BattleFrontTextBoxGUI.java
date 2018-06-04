package graphic.battleFields;

import logic.battleFields.LineType;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import graphic.PlayerType;
import variables.VariablesGraphics;
import variables.VariablesLogic;

public class BattleFrontTextBoxGUI extends Pane {

    private Text hitPointsAmount = new Text(Integer.toString(VariablesLogic.getInstance().getBattleFieldHitPoints()));
    private Text powerAmount = new Text("0");

    private PlayerType playerType;
    private LineType lineType;

    BattleFrontTextBoxGUI(PlayerType playerType, LineType lineType) {

        this.playerType = playerType;
        this.lineType = lineType;

        // Set layout of this pane
        this.setId("BattleFrontTextBoxGUI");
        this.setPrefSize(VariablesGraphics.getInstance().getBattleFrontTextBoxWidth(), VariablesGraphics.getInstance().getBattleFrontTextBoxHeight());
        this.setLayoutX(VariablesGraphics.getInstance().getBattleFieldWidth() / 8);
        if (playerType == PlayerType.player) {
            this.setLayoutY(VariablesGraphics.getInstance().getBattleFieldHeight());
        } else {
            this.setLayoutY(-VariablesGraphics.getInstance().getBattleFrontTextBoxHeight());
        }

        Text hitPoints = new Text("Hit Points: ");
        Text power = new Text("Power: ");

        // Set IDs of texts, needed for css styling
        hitPoints.setId("hitPoints");
        hitPointsAmount.setId("hitPointsAmount");
        power.setId("power");
        powerAmount.setId("powerAmount");

        // Set layout positions
        hitPoints.setLayoutX(VariablesGraphics.getInstance().getBattleFrontTextBoxWidth() * 0.05);
        hitPoints.setLayoutY(VariablesGraphics.getInstance().getBattleFrontTextBoxHeight() * 0.4);
        hitPointsAmount.setLayoutX(VariablesGraphics.getInstance().getBattleFrontTextBoxWidth() * 0.22);
        hitPointsAmount.setLayoutY(VariablesGraphics.getInstance().getBattleFrontTextBoxHeight() * 0.7);
        power.setLayoutX(VariablesGraphics.getInstance().getBattleFrontTextBoxWidth() * 0.6);
        power.setLayoutY(VariablesGraphics.getInstance().getBattleFrontTextBoxHeight() * 0.4);
        powerAmount.setLayoutX(VariablesGraphics.getInstance().getBattleFrontTextBoxWidth() * 0.69);
        powerAmount.setLayoutY(VariablesGraphics.getInstance().getBattleFrontTextBoxHeight() * 0.7);

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