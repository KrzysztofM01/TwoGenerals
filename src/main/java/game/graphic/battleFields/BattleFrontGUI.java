package game.graphic.battleFields;

import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;
import game.variables.VGraphics;

class BattleFrontGUI extends FlowPane {

    BattleFrontGUI() {

        this.setHgap(-VGraphics.getInstance().getCardWidth() / 3);
        this.setVgap(-VGraphics.getInstance().getCardHeight() / 3);

        this.setAlignment(Pos.CENTER);

        this.setPrefSize(VGraphics.getInstance().getBattleFieldWidth(), VGraphics.getInstance().getBattleFieldHeight() / 2);
        this.setId("BattleFront");
    }

}
