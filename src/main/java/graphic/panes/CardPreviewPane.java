package graphic.panes;

import javafx.scene.layout.StackPane;
import variables.VariablesGraphics;

public class CardPreviewPane extends StackPane{

    public CardPreviewPane() {

        this.setId("cardPreview");
        this.setLayoutY(VariablesGraphics.battleFieldPositionY);
        this.setLayoutX(VariablesGraphics.battleFieldWidth*3+ VariablesGraphics.battleFieldBreakWidth*4);
        this.setPrefSize(VariablesGraphics.battleFieldWidth, VariablesGraphics.battleFieldHeight);
    }
}
