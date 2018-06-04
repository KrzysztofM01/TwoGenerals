package graphic.panes;

import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import variables.VariablesGraphics;
import variables.VariablesLogic;

public class ActionPointsBox extends StackPane {

    private Text actionPointsAmount = new Text(Integer.toString(VariablesLogic.getInstance().getPlayerActionPoints()));

    public ActionPointsBox() {

        this.setId("actionPointsBox");
        this.setPrefSize(VariablesGraphics.getInstance().getScreenWidth() * 0.1, VariablesGraphics.getInstance().getScreenHeight() * 0.14);
        this.setLayoutX(VariablesGraphics.getInstance().getScreenWidth() * 0.755);
        this.setLayoutY(VariablesGraphics.getInstance().getPlayerCardPositionY() + VariablesGraphics.getInstance().getCardHeight() * 0.85);

        actionPointsAmount.setId("actionPointsAmount");
        this.getChildren().add(this.actionPointsAmount);
    }

    public void setActionPointsAmount(int actionPointsAmount) {
        this.actionPointsAmount.setText(Integer.toString(actionPointsAmount));
    }
}
