package previewSuggestedCardsPanel;

import database.DataBaseConnector;
import game.graphic.PlayerType;
import game.graphic.buttons.SquareButton;
import game.graphic.cards.Card;
import game.graphic.cards.cardPreview.CardPreview;
import game.graphic.panes.CardPreviewPane;
import game.graphic.panes.CardScrollingPane;
import game.logic.cards.CardCreator;
import game.logic.cards.CardLogic;
import game.logic.cards.CardType;
import javafx.application.Platform;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import java.util.ArrayList;

class PreviewSuggestedCardsController {

    private Card tempCard;

    PreviewSuggestedCardsController(CardPreviewPane cardPreviewPane, ArrayList<CardLogic> cardSuggestionList, CardScrollingPane cardScrollingPane, SquareButton acceptCardButton, SquareButton declineCardButton) {

        // For every card in card suggestion list it first creates the image of the card Image URL - to check if it's
        // correct (If the ImageURL is wrong, the card will be immediately removed from database). Next it creates it's
        // graphic object and adds controller. When the card is clicked it's supposed to be displayed in the card
        // preview panel.
        for (CardLogic cardLogic : cardSuggestionList) {
            try {
                Image testImage = new Image(cardLogic.getImageURL());
                Card card = new Card(cardLogic, PlayerType.player);
                card.renderCardFront();
                card.setOnMouseClicked(e -> {
                    if (tempCard == null) {
                        CardPreview cardPreview = new CardPreview(cardLogic);
                        cardPreviewPane.getChildren().add(cardPreview);
                        cardPreviewPane.setTempCardPreview(cardPreview);
                        card.setHighlighted(true);
                        tempCard = card;
                    } else if (tempCard == card) {
                        card.setHighlighted(false);
                        cardPreviewPane.getChildren().remove(cardPreviewPane.getTempCardPreview());
                        tempCard = null;
                    } else {
                        tempCard.setHighlighted(false);
                        cardPreviewPane.getChildren().remove(cardPreviewPane.getTempCardPreview());
                        CardPreview cardPreview = new CardPreview(cardLogic);
                        cardPreviewPane.getChildren().add(cardPreview);
                        cardPreviewPane.setTempCardPreview(cardPreview);
                        card.setHighlighted(true);
                        tempCard = card;
                    }
                });
                cardScrollingPane.getCardsFlowPane().getChildren().add(card);
            } catch (IllegalArgumentException | IllegalStateException error) {
                DataBaseConnector.removeCardSuggest(cardLogic);
                error.printStackTrace();
            }
        }

        // When clicked the card is removed from database, from the preview and suggest pane and all of it's data is
        // stored in a text file for future use for admin.
        acceptCardButton.setOnMouseClicked(e -> {
            if (tempCard != null) {
                DataBaseConnector.removeCardSuggest(tempCard.getCardLogic());
                cardScrollingPane.getCardsFlowPane().getChildren().remove(tempCard);
                cardPreviewPane.getChildren().remove(cardPreviewPane.getTempCardPreview());
                try {
                    FileOutputStream f = new FileOutputStream(new File("CardSuggestion_" + tempCard.getCardLogic().getName() + ".txt"));
                    ObjectOutputStream o = new ObjectOutputStream(f);
                    o.writeObject(tempCard.getCardLogic().toString());
                    o.close();
                    f.close();
                } catch (FileNotFoundException s) {
                    System.out.println("File not found");
                } catch (IOException s) {
                    System.out.println("Error initializing stream");
                }
                tempCard = null;
            }
        });

        // When clicked the card is removed from database, from the preview and suggest pane.
        declineCardButton.setOnMouseClicked(e -> {
            if (tempCard != null) {
                DataBaseConnector.removeCardSuggest(tempCard.getCardLogic());
                cardScrollingPane.getCardsFlowPane().getChildren().remove(tempCard);
                cardPreviewPane.getChildren().remove(cardPreviewPane.getTempCardPreview());
                tempCard = null;
            }
        });
    }
}
