package graphic.battleFields;

import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;
import variables.VariablesGraphics;

class BattleFrontGUI extends FlowPane {

    BattleFrontGUI() {

        this.setHgap(-VariablesGraphics.getInstance().getCardWidth() / 3);
        this.setVgap(-VariablesGraphics.getInstance().getCardHeight() / 3);

        this.setAlignment(Pos.CENTER);

        this.setPrefSize(VariablesGraphics.getInstance().getBattleFieldWidth(), VariablesGraphics.getInstance().getBattleFieldHeight() / 2);
        this.setId("BattleFront");
    }

}
