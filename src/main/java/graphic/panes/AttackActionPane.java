package graphic.panes;

import javafx.scene.layout.StackPane;
import logic.battleFields.LineType;
import variables.VariablesGraphics;

public class AttackActionPane extends StackPane {
    public AttackActionPane() {
        this.setId("attackActionPane");
        this.setPrefSize(VariablesGraphics.battleFieldWidth*0.8, VariablesGraphics.battleFieldHeight*0.4);
        this.setViewOrder(-30);
        this.setLayoutY(VariablesGraphics.battleFieldPositionY+VariablesGraphics.battleFieldHeight/2-this.getPrefHeight()/2);
    }

    public void setPosition(LineType lineType) {
        switch (lineType){
            case left:
                this.setLayoutX(VariablesGraphics.battleFieldBreakWidth+VariablesGraphics.battleFieldWidth/2-this.getPrefWidth()/2);
                break;
            case center:
                this.setLayoutX(VariablesGraphics.battleFieldWidth + VariablesGraphics.battleFieldBreakWidth * 2 +VariablesGraphics.battleFieldWidth/2-this.getPrefWidth()/2);
                break;
            case right:
                this.setLayoutX(VariablesGraphics.battleFieldWidth * 2 + VariablesGraphics.battleFieldBreakWidth * 3 +VariablesGraphics.battleFieldWidth/2-this.getPrefWidth()/2);
                break;
        }
    }
}
