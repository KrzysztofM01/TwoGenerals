package previewCardsPanel;

import database.DataBaseConnector;
import game.graphic.PlayerType;
import game.graphic.cards.Card;
import game.graphic.cards.cardPreview.CardPreview;
import game.graphic.panes.CardPreviewPane;
import game.graphic.panes.CardScrollingPane;
import game.logic.cards.CardLogic;
import game.variables.VLogic;
import javafx.scene.layout.FlowPane;

import java.util.ArrayList;

public class PreviewCardsController {

    private ArrayList<CardLogic> playerCardsCopy;
    private CardScrollingPane cardScrollingPane;

    public PreviewCardsController(CardPreviewPane cardPreviewPane, FlowPane selectedPlayerCards, ArrayList<CardLogic> playerCards) {

        this.cardScrollingPane = new CardScrollingPane();
        playerCardsCopy = new ArrayList<>(playerCards);

        for (CardLogic cardLogic : playerCardsCopy) {
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

        for (CardLogic cardLogic : cardLogics) {
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
            cardScrollingPane.getCardsFlowPane().getChildren().add(card);
        }
    }

    public ArrayList<CardLogic> getPlayerCardsCopy() {
        return playerCardsCopy;
    }

    public CardScrollingPane getCardScrollingPane() {
        return cardScrollingPane;
    }
}
