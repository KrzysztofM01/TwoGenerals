package game.graphic.panes;

import javafx.scene.layout.StackPane;
import game.variables.VariablesGraphics;

public class CardPreviewPane extends StackPane {

    public CardPreviewPane() {

        this.setId("cardPreview");
        this.setPrefSize(VariablesGraphics.getInstance().getBattleFieldWidth(), VariablesGraphics.getInstance().getBattleFieldHeight());
        this.setLayoutX(VariablesGraphics.getInstance().getBattleFieldWidth() * 3 + VariablesGraphics.getInstance().getBattleFieldBreakWidth() * 4);
        this.setLayoutY(VariablesGraphics.getInstance().getBattleFieldPositionY());
    }
}
