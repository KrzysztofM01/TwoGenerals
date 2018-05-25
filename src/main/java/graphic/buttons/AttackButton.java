package graphic.buttons;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import logic.battleFields.LineType;
import javafx.scene.control.Button;
import variables.VariablesGraphics;


public class AttackButton extends Button {
    private LineType lineType;
    private boolean usedInThisTurn = false;

    public AttackButton(LineType lineType){
        this.lineType = lineType;
        this.setPrefSize(VariablesGraphics.screenHeight*0.035, VariablesGraphics.screenHeight*0.035);
        this.setId(this.lineType+"AttackButton");
        this.setLayoutY(VariablesGraphics.battleFieldPositionY + VariablesGraphics.battleFieldHeight + VariablesGraphics.battleFrontTextBoxHeight/4);
        switch (lineType){
            case left:
                this.setLayoutX(VariablesGraphics.battleFieldBreakWidth*0.5 + VariablesGraphics.battleFieldWidth*0.5
                        + VariablesGraphics.battleFrontTextBoxWidth*0.45);
                break;
            case center:
                this.setLayoutX(VariablesGraphics.battleFieldBreakWidth*1.5 + VariablesGraphics.battleFieldWidth*1.5
                        + VariablesGraphics.battleFrontTextBoxWidth*0.45);
                break;
            case right:
                this.setLayoutX(VariablesGraphics.battleFieldBreakWidth*2.5 + VariablesGraphics.battleFieldWidth*2.5
                        + VariablesGraphics.battleFrontTextBoxWidth*0.45);
                break;
        }
    }

    public boolean isUsedInThisTurn() {
        return usedInThisTurn;
    }

    public void setUsedInThisTurn(boolean usedInThisTurn) {
        this.usedInThisTurn = usedInThisTurn;
        if (usedInThisTurn){
            this.setId(this.lineType + "UsedAttackButton");
        } else {
            this.setId(this.lineType+"AttackButton");
        }
    }

    public LineType getLineType() {
        return lineType;
    }
}
