package previewCardsPanel;

import database.DataBaseConnector;
import game.graphic.PlayerType;
import game.graphic.cards.Card;
import game.graphic.cards.cardPreview.CardPreview;
import game.graphic.panes.CardPreviewPane;
import game.logic.cards.CardLogic;
import game.variables.VGraphics;
import game.variables.VLogic;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import java.util.ArrayList;

public class InfrastructurePane {

    private ArrayList <CardLogic> playerCardsCopy;

    public ScrollPane infrastructurePane(CardPreviewPane cardPreviewPane, FlowPane selectedPlayerCards, ArrayList<CardLogic> playerCards) {

        final FlowPane flow = new FlowPane();
        flow.setPadding(new Insets(5, 5, 5, 5));
        flow.setVgap(5);
        flow.setHgap(5);
        flow.setAlignment(Pos.CENTER);
        flow.setOrientation(Orientation.VERTICAL);
        flow.setId("flowPane");
        final ScrollPane scroll = new ScrollPane();

        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);    // Horizontal scroll bar
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);    // Vertical scroll bar
        scroll.setContent(flow);

        scroll.setPrefViewportWidth(VGraphics.getInstance().getScreenWidth() * 0.5);
        scroll.setPrefViewportHeight(VGraphics.getInstance().getScreenHeight()*0.45);

        //flow.setPrefWrapLength(170); // preferred width allows for two columns
        flow.prefWidthProperty().bind(Bindings.add(-5, scroll.widthProperty()));
        flow.prefHeightProperty().bind(Bindings.add(-5, scroll.heightProperty()));

        playerCardsCopy = new ArrayList<>(playerCards);

        for (CardLogic cardLogic: playerCardsCopy) {
            Card card = new Card(cardLogic, PlayerType.player);
            card.renderCardFront();
            card.setOnMouseClicked(e -> {
                selectedPlayerCards.getChildren().remove(card);
                playerCardsCopy.remove(cardLogic);
            });
            card.setOnMouseEntered(e -> {
                CardPreview cardPreview = new CardPreview(cardLogic);
                cardPreviewPane.getChildren().add(cardPreview);
                cardPreviewPane.setTempCardPreview(cardPreview);
            });
            card.setOnMouseExited(e -> cardPreviewPane.getChildren().remove(cardPreviewPane.getTempCardPreview()));
            selectedPlayerCards.getChildren().add(card);
        }

        ArrayList<CardLogic> cardLogics = DataBaseConnector.getAllCards();

        for (CardLogic cardLogic: cardLogics) {
            Card card = new Card(cardLogic, PlayerType.player);
            card.renderCardFront();
            card.setOnMouseEntered(e -> {
                CardPreview cardPreview = new CardPreview(cardLogic);
                cardPreviewPane.getChildren().add(cardPreview);
                cardPreviewPane.setTempCardPreview(cardPreview);
            });
            card.setOnMouseExited(e -> cardPreviewPane.getChildren().remove(cardPreviewPane.getTempCardPreview()));
            card.setOnMouseClicked(e -> {
                Long numberOfThisCard = playerCardsCopy.stream()
                        .map(CardLogic::getCardID)
                        .filter(s -> s == cardLogic.getCardID())
                        .count();
                if (numberOfThisCard < VLogic.getInstance().getMaxOfCertainCardType() && playerCardsCopy.size() < VLogic.getInstance().getMaxOfCards()) {
                    Card cardCopy = new Card(cardLogic, PlayerType.player);
                    playerCardsCopy.add(cardLogic);
                    cardCopy.setOnMouseEntered(e2 -> {
                        CardPreview cardPreview = new CardPreview(cardLogic);
                        cardPreviewPane.getChildren().add(cardPreview);
                        cardPreviewPane.setTempCardPreview(cardPreview);
                    });
                    cardCopy.setOnMouseExited(e2 -> cardPreviewPane.getChildren().remove(cardPreviewPane.getTempCardPreview()));

                    cardCopy.renderCardFront();
                    selectedPlayerCards.getChildren().add(cardCopy);
                    cardCopy.setOnMouseClicked(e2 -> {
                        playerCardsCopy.remove(cardLogic);
                        selectedPlayerCards.getChildren().remove(cardCopy);
                    });
                }
            });
            flow.getChildren().add(card);
        }

        scroll.setTranslateX(VGraphics.getInstance().getScreenWidth() *0.1);
        scroll.setTranslateY(VGraphics.getInstance().getScreenHeight() *0.1);
        return scroll;
    }

    public ArrayList<CardLogic> getPlayerCardsCopy() {
        return playerCardsCopy;
    }
}
