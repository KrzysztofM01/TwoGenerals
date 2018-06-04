package graphic.buttons;

import logic.battleFields.LineType;
import javafx.scene.control.Button;
import variables.VariablesGraphics;


public class AttackButton extends Button {

    private LineType lineType;
    private boolean usedInThisTurn = false;

    public AttackButton(LineType lineType) {

        this.lineType = lineType;
        this.setId(this.lineType + "AttackButton");

        this.setPrefSize(VariablesGraphics.getInstance().getScreenHeight() * 0.035, VariablesGraphics.getInstance().getScreenHeight() * 0.035);
        this.setLayoutY(VariablesGraphics.getInstance().getBattleFieldPositionY() + VariablesGraphics.getInstance().getBattleFieldHeight() + VariablesGraphics.getInstance().getBattleFrontTextBoxHeight() / 4);
        switch (lineType) {
            case left:
                this.setLayoutX(VariablesGraphics.getInstance().getBattleFieldBreakWidth() * 0.5 + VariablesGraphics.getInstance().getBattleFieldWidth() * 0.5
                        + VariablesGraphics.getInstance().getBattleFrontTextBoxWidth() * 0.45);
                break;
            case center:
                this.setLayoutX(VariablesGraphics.getInstance().getBattleFieldBreakWidth() * 1.5 + VariablesGraphics.getInstance().getBattleFieldWidth() * 1.5
                        + VariablesGraphics.getInstance().getBattleFrontTextBoxWidth() * 0.45);
                break;
            case right:
                this.setLayoutX(VariablesGraphics.getInstance().getBattleFieldBreakWidth() * 2.5 + VariablesGraphics.getInstance().getBattleFieldWidth() * 2.5
                        + VariablesGraphics.getInstance().getBattleFrontTextBoxWidth() * 0.45);
                break;
        }
    }

    public boolean isUsedInThisTurn() {
        return usedInThisTurn;
    }

    public void setUsedInThisTurn(boolean usedInThisTurn) {
        this.usedInThisTurn = usedInThisTurn;
        // ID changes it's graphics from gray (Unused) to red (Used)
        if (usedInThisTurn) {
            this.setId(this.lineType + "UsedAttackButton");
        } else {
            this.setId(this.lineType + "AttackButton");
        }
    }

    public LineType getLineType() {
        return lineType;
    }
}
