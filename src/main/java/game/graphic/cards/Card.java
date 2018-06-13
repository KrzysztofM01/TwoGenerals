package game.graphic.cards;

import game.graphic.PlayerType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import game.logic.cards.CardLogic;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import game.variables.VGraphics;

import java.io.Serializable;

public class Card extends StackPane implements Serializable {

    private CardLogic cardLogic;
    private boolean isHidden = false;
    private CardText cardTextPower;
    private CardText cardTextCost;
    private PlayerType ownerOfCard;
    private ImageView cardHiddenImageView;

    public Card() {
    }

    public Card(CardLogic card, PlayerType playerType) {
        this.cardLogic = card;
        this.ownerOfCard = playerType;
    }

    public void renderCardFront() {

        ImageView cardFront = new ImageView(new Image(cardLogic.getImageURL()));
        cardFront.setPreserveRatio(false);
        cardFront.setFitWidth(VGraphics.getInstance().getCardWidth());
        cardFront.setFitHeight(VGraphics.getInstance().getCardHeight());

        cardTextPower = new CardText(this.cardLogic.getCurrentPower(), true);
        cardTextCost = new CardText(this.cardLogic.getCost(), false);

        // Opens up a possibility for future game rules adjustment -> when card is played, it first is hidden
        // until player attacks with it or is attacked
        if (ownerOfCard == PlayerType.player) {
            cardHiddenImageView = new ImageView(new Image("images/hidingMist.png"));
        } else {
            cardHiddenImageView = new ImageView(new Image("images/CardBackG2.png"));
        }

        cardHiddenImageView.setFitWidth(VGraphics.getInstance().getCardWidth());
        cardHiddenImageView.setFitHeight(VGraphics.getInstance().getCardHeight());

        if (isHidden){
            cardHiddenImageView.setViewOrder(-5);
        }

        this.setPadding(new Insets(VGraphics.getInstance().getCardPadding(), VGraphics.getInstance().getCardPadding(), VGraphics.getInstance().getCardPadding(), VGraphics.getInstance().getCardPadding()));
        this.getChildren().addAll(cardHiddenImageView, cardFront, cardTextPower, cardTextCost);
    }

    public void turnCard(boolean isHidden) {
        this.isHidden = isHidden;
        if (isHidden) {
            this.cardHiddenImageView.setViewOrder(-5);
        } else {
            this.cardHiddenImageView.setViewOrder(5);
        }
    }

    public void setHighlighted(boolean highlighted) {
        if (highlighted) {
            this.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        } else {
            this.setBackground(Background.EMPTY);
        }
    }

    public CardLogic getCardLogic() {
        return cardLogic;
    }

    public CardText getCardTextPower() {

        return cardTextPower;
    }

    public void setOwnerOfCard(PlayerType ownerOfCard) {
        this.ownerOfCard = ownerOfCard;
    }

    public CardText getCardTextCost() {
        return cardTextCost;
    }

    public PlayerType getOwnerOfCard() {
        return ownerOfCard;
    }
}