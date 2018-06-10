package previewCardsPanel;

import database.DataBaseConnector;
import game.graphic.PlayerType;
import game.graphic.cards.Card;
import game.logic.cards.CardLogic;
import game.variables.VariablesGraphics;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class InfrastructurePane {

    public ScrollPane infrastructurePane() {

        final FlowPane flow = new FlowPane();
        flow.setPadding(new Insets(5, 5, 5, 5));
        flow.setVgap(5);
        flow.setHgap(5);
        flow.setAlignment(Pos.TOP_LEFT);
        flow.setOrientation(Orientation.VERTICAL);
        flow.setId("flowPane");
        final ScrollPane scroll = new ScrollPane();

        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);    // Horizontal scroll bar
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);    // Vertical scroll bar
        scroll.setContent(flow);

        scroll.setPrefViewportWidth(VariablesGraphics.getInstance().getCardWidth() * 10);
        scroll.setPrefViewportHeight(VariablesGraphics.getInstance().getCardHeight() * 6);

        //flow.setPrefWrapLength(170); // preferred width allows for two columns
        flow.prefWidthProperty().bind(Bindings.add(-5, scroll.widthProperty()));
        flow.prefHeightProperty().bind(Bindings.add(-5, scroll.heightProperty()));

        ArrayList<CardLogic> cardLogics = DataBaseConnector.getAllCards();

        for (CardLogic cardLogic: cardLogics) {
            Card card = new Card(cardLogic, PlayerType.player);
            card.renderCardFront();
            flow.getChildren().add(card);
        }
//        for (int i = 0; i < 180; i++) {
//            flow.getChildren().add(generateRectangle());
//        }

        return scroll;
    }

//    public Rectangle generateRectangle() {
//
//        Rectangle rect2 = new Rectangle(10, 10, 10, 10);
//        rect2.setId("app");
//        rect2.setArcHeight(8);
//        rect2.setArcWidth(8);
//        //rect2.setX(10);
//        //rect2.setY(160);
//        rect2.setStrokeWidth(1);
//        rect2.setStroke(Color.WHITE);
//        rect2.setWidth(150);
//        rect2.setHeight(150);
//
//        return rect2;
//    }
}
