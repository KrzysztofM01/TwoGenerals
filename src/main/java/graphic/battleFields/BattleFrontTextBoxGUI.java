package graphic.battleFields;

import logic.battleFields.LineType;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import graphic.PlayerType;
import variables.VariablesGraphics;
import variables.VariablesLogic;

public class BattleFrontTextBoxGUI extends Pane {
    public static final String STYLESHEET = "battleFrontStyles.css";
    private Text hitPoints = new Text("Hit Points: ");
    private Text hitPointsAmount = new Text(Integer.toString(VariablesLogic.battleFieldHitPoints));
    private Text power = new Text("Power: ");
    private Text powerAmount = new Text("0");
    private PlayerType playerType;
    private LineType lineType;

    public BattleFrontTextBoxGUI(PlayerType playerType, LineType lineType){
        this.getStylesheets().addAll(STYLESHEET);
        this.playerType = playerType;
        this.lineType = lineType;
        this.setId("BattleFrontTextBoxGUI");
        this.setLayoutX(VariablesGraphics.battleFieldWidth/8);
        if (playerType == PlayerType.player){
            this.setLayoutY(VariablesGraphics.battleFieldHeight);
        } else {
            this.setLayoutY(-VariablesGraphics.battleFrontTextBoxHeight);
        }
        // Set layout positions
        this.setPrefSize(VariablesGraphics.battleFrontTextBoxWidth, VariablesGraphics.battleFrontTextBoxHeight);
        this.getHitPoints().setLayoutX(VariablesGraphics.battleFrontTextBoxWidth * 0.1);
        this.getHitPointsAmount().setLayoutX(VariablesGraphics.battleFrontTextBoxWidth*0.2);
        this.getPower().setLayoutX(VariablesGraphics.battleFrontTextBoxWidth * 0.65);
        this.getPowerAmount().setLayoutX(VariablesGraphics.battleFrontTextBoxWidth*0.74);
        this.getHitPoints().setLayoutY(VariablesGraphics.battleFrontTextBoxHeight * 0.4);
        this.getHitPointsAmount().setLayoutY(VariablesGraphics.battleFrontTextBoxHeight * 0.7);
        this.getPower().setLayoutY(VariablesGraphics.battleFrontTextBoxHeight * 0.4);
        this.getPowerAmount().setLayoutY(VariablesGraphics.battleFrontTextBoxHeight * 0.7);
        //
        this.getChildren().addAll(this.hitPoints, this.hitPointsAmount, this.power, this.powerAmount);
    }

    public void setHitPointsAmount(int hitPointsAmount) {
        this.hitPointsAmount.setText(Integer.toString(hitPointsAmount));
    }

    public void setPowerAmount(int powerAmount) {
        this.powerAmount.setText(Integer.toString(powerAmount));
    }

    public Text getHitPoints() {
        return hitPoints;
    }

    public Text getHitPointsAmount() {
        return hitPointsAmount;
    }

    public Text getPower() {
        return power;
    }

    public Text getPowerAmount() {
        return powerAmount;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public LineType getLineType() {
        return lineType;
    }

    public int getIntHitPointsAmount() {
        return Integer.valueOf(this.hitPointsAmount.getText());
    }

    public int getIntPowerAmount() {
        return Integer.valueOf(this.powerAmount.getText());
    }
}
