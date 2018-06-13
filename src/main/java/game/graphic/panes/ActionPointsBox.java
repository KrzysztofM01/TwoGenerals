package game.graphic.panes;

import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import game.variables.VGraphics;
import game.variables.VLogic;

public class ActionPointsBox extends StackPane {

    private Text actionPointsAmount = new Text(Integer.toString(VLogic.getInstance().getPlayerActionPoints()));

    public ActionPointsBox() {

        this.setId("actionPointsBox");
        this.setPrefSize(VGraphics.getInstance().getScreenWidth() * 0.1, VGraphics.getInstance().getScreenHeight() * 0.14);
        this.setLayoutX(VGraphics.getInstance().getScreenWidth() * 0.755);
        this.setLayoutY(VGraphics.getInstance().getPlayerCardPositionY() + VGraphics.getInstance().getCardHeight() * 0.85);

        actionPointsAmount.setId("actionPointsAmount");
        this.getChildren().add(this.actionPointsAmount);
    }

    public void setActionPointsAmount(int actionPointsAmount) {
        this.actionPointsAmount.setText(Integer.toString(actionPointsAmount));
    }
}
