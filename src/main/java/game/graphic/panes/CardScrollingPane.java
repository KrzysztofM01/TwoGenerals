package game.graphic.panes;

import game.variables.VGraphics;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;

public class CardScrollingPane extends ScrollPane {

    private FlowPane cardsFlowPane;

    public CardScrollingPane () {

        this.cardsFlowPane = new FlowPane();
        cardsFlowPane.setPadding(new Insets(5, 5, 5, 5));
        cardsFlowPane.setVgap(5);
        cardsFlowPane.setHgap(5);
        cardsFlowPane.setAlignment(Pos.CENTER);
        cardsFlowPane.setOrientation(Orientation.VERTICAL);
        cardsFlowPane.setId("flowPane");

        this.setHbarPolicy(ScrollBarPolicy.ALWAYS);
        this.setVbarPolicy(ScrollBarPolicy.NEVER);
        this.setContent(cardsFlowPane);

        this.setPrefViewportWidth(VGraphics.getInstance().getScreenWidth() * 0.5);
        this.setPrefViewportHeight(VGraphics.getInstance().getScreenHeight()*0.45);

        cardsFlowPane.prefWidthProperty().bind(Bindings.add(-5, this.widthProperty()));
        cardsFlowPane.prefHeightProperty().bind(Bindings.add(-5, this.heightProperty()));

        this.setTranslateX(VGraphics.getInstance().getScreenWidth() *0.1);
        this.setTranslateY(VGraphics.getInstance().getScreenHeight() *0.1);
    }

    public FlowPane getCardsFlowPane() {
        return cardsFlowPane;
    }
}
