package graphic.panes;

import javafx.scene.layout.StackPane;
import logic.battleFields.LineType;
import variables.VariablesGraphics;

public class AttackActionPane extends StackPane {

    public AttackActionPane() {

        this.setId("attackActionPane");
        this.setPrefSize(VariablesGraphics.getInstance().getBattleFieldWidth()*0.8, VariablesGraphics.getInstance().getBattleFieldHeight()*0.4);
        this.setLayoutY(VariablesGraphics.getInstance().getBattleFieldPositionY()+VariablesGraphics.getInstance().getBattleFieldHeight()/2-this.getPrefHeight()/2);

        // Ensures that this image is on top on all cards
        this.setViewOrder(-10);
        }

    public void setPosition(LineType lineType) {
        switch (lineType){
            case left:
                this.setLayoutX(VariablesGraphics.getInstance().getBattleFieldBreakWidth()+VariablesGraphics.getInstance().getBattleFieldWidth()/2-this.getPrefWidth()/2);
                break;
            case center:
                this.setLayoutX(VariablesGraphics.getInstance().getBattleFieldWidth() + VariablesGraphics.getInstance().getBattleFieldBreakWidth() * 2 +VariablesGraphics.getInstance().getBattleFieldWidth()/2-this.getPrefWidth()/2);
                break;
            case right:
                this.setLayoutX(VariablesGraphics.getInstance().getBattleFieldWidth() * 2 + VariablesGraphics.getInstance().getBattleFieldBreakWidth() * 3 +VariablesGraphics.getInstance().getBattleFieldWidth()/2-this.getPrefWidth()/2);
                break;
        }
    }
}
