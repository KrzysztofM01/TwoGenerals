package graphic.panes;

import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import variables.VariablesGraphics;
import variables.VariablesLogic;

public class ActionPointsBox extends StackPane{

    private Text actionPointsAmount = new Text(Integer.toString(VariablesLogic.playerActionPoints));

    public ActionPointsBox() {
        this.setId("actionPointsBox");
        this.setPrefSize(VariablesGraphics.screenWidth*0.1, VariablesGraphics.screenHeight*0.14);
        this.setLayoutX(VariablesGraphics.screenWidth*0.755);
        this.setLayoutY(VariablesGraphics.playerCardPositionY + VariablesGraphics.cardHeight*0.85);

        this.actionPointsAmount.setId("actionPointsAmount");
        this.getChildren().add(this.actionPointsAmount);
    }

    public void setActionPointsAmount(int actionPointsAmount) {
        this.actionPointsAmount.setText(Integer.toString(actionPointsAmount));
    }
}
