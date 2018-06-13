package game.graphic.buttons;

import game.logic.battleFields.LineType;
import javafx.scene.control.Button;
import game.variables.VGraphics;


public class AttackButton extends Button {

    private LineType lineType;
    private boolean usedInThisTurn = false;

    public AttackButton(LineType lineType) {

        this.lineType = lineType;
        this.setId(this.lineType + "AttackButton");

        this.setPrefSize(VGraphics.getInstance().getScreenHeight() * 0.035, VGraphics.getInstance().getScreenHeight() * 0.035);
        this.setLayoutY(VGraphics.getInstance().getBattleFieldPositionY() + VGraphics.getInstance().getBattleFieldHeight() + VGraphics.getInstance().getBattleFrontTextBoxHeight() / 4);
        switch (lineType) {
            case left:
                this.setLayoutX(VGraphics.getInstance().getBattleFieldBreakWidth() * 0.5 + VGraphics.getInstance().getBattleFieldWidth() * 0.5
                        + VGraphics.getInstance().getBattleFrontTextBoxWidth() * 0.45);
                break;
            case center:
                this.setLayoutX(VGraphics.getInstance().getBattleFieldBreakWidth() * 1.5 + VGraphics.getInstance().getBattleFieldWidth() * 1.5
                        + VGraphics.getInstance().getBattleFrontTextBoxWidth() * 0.45);
                break;
            case right:
                this.setLayoutX(VGraphics.getInstance().getBattleFieldBreakWidth() * 2.5 + VGraphics.getInstance().getBattleFieldWidth() * 2.5
                        + VGraphics.getInstance().getBattleFrontTextBoxWidth() * 0.45);
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
