package graphic.battleFields;

import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;
import variables.VariablesGraphics;

public class BattleFrontGUI extends FlowPane{

    public BattleFrontGUI() {
        Pos pos = Pos.CENTER;
        this.setHgap(-VariablesGraphics.cardWidth/3);
        this.setVgap(-VariablesGraphics.cardHeight/3);
        this.setAlignment(pos);
        this.setPrefSize(VariablesGraphics.battleFieldWidth, VariablesGraphics.battleFieldHeight/2);
        this.setMaxSize(VariablesGraphics.battleFieldWidth, VariablesGraphics.battleFieldHeight/2);
        this.setId("BattleFront");
    }

}
