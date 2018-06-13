package game.graphic.panes;

import javafx.scene.layout.StackPane;
import game.logic.battleFields.LineType;
import game.variables.VGraphics;

public class AttackActionPane extends StackPane {

    public AttackActionPane() {

        this.setId("attackActionPane");
        this.setPrefSize(VGraphics.getInstance().getBattleFieldWidth()*0.8, VGraphics.getInstance().getBattleFieldHeight()*0.4);
        this.setLayoutY(VGraphics.getInstance().getBattleFieldPositionY()+ VGraphics.getInstance().getBattleFieldHeight()/2-this.getPrefHeight()/2);

        // Ensures that this image is on top on all cards
        this.setViewOrder(-10);
        }

    public void setPosition(LineType lineType) {
        switch (lineType){
            case left:
                this.setLayoutX(VGraphics.getInstance().getBattleFieldBreakWidth()+ VGraphics.getInstance().getBattleFieldWidth()/2-this.getPrefWidth()/2);
                break;
            case center:
                this.setLayoutX(VGraphics.getInstance().getBattleFieldWidth() + VGraphics.getInstance().getBattleFieldBreakWidth() * 2 + VGraphics.getInstance().getBattleFieldWidth()/2-this.getPrefWidth()/2);
                break;
            case right:
                this.setLayoutX(VGraphics.getInstance().getBattleFieldWidth() * 2 + VGraphics.getInstance().getBattleFieldBreakWidth() * 3 + VGraphics.getInstance().getBattleFieldWidth()/2-this.getPrefWidth()/2);
                break;
        }
    }
}
