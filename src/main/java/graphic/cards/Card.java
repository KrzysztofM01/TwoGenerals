package graphic.cards;

import graphic.PlayerType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logic.cards.CardLogic;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import variables.VariablesGraphics;

import java.io.Serializable;

public class Card extends StackPane implements Serializable {

    private CardLogic cardLogic;
    private boolean isHidden = false;
    private CardText cardTextPower;
    private CardText cardTextCost;
    private PlayerType ownerOfCard;
    private ImageView cardBack;

    public Card() {

    }

    public Card(CardLogic card) {

        this.cardLogic = card;
        renderCardFront();
    }

    public void renderCardGraphicsOpponent() {
        renderCardFront();

        cardBack = new ImageView(new Image("images/CardBackG2.png"));
        cardBack.setFitWidth(VariablesGraphics.getInstance().getCardWidth());
        cardBack.setFitHeight(VariablesGraphics.getInstance().getCardHeight());
        cardBack.setViewOrder(-5);

        isHidden = true;

        this.getChildren().add(cardBack);
    }

    private void renderCardFront() {

        ImageView cardFront = new ImageView(new Image(cardLogic.getImageURL()));
        cardFront.setPreserveRatio(false);
        cardFront.setFitWidth(VariablesGraphics.getInstance().getCardWidth());
        cardFront.setFitHeight(VariablesGraphics.getInstance().getCardHeight());

        cardTextPower = new CardText(this.cardLogic.getCurrentPower(), true);
        cardTextCost = new CardText(this.cardLogic.getCost(), false);

        this.setPadding(new Insets(VariablesGraphics.getInstance().getCardPadding(), VariablesGraphics.getInstance().getCardPadding(), VariablesGraphics.getInstance().getCardPadding(), VariablesGraphics.getInstance().getCardPadding()));
        this.getChildren().addAll(cardFront, cardTextPower, cardTextCost);
    }

    public void turnCard(boolean isHidden) {
        this.isHidden = isHidden;
        if (isHidden) {
            this.cardBack.setViewOrder(-5);
        } else {
            this.cardBack.setViewOrder(5);
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

    public CardText getCardTextCost() {
        return cardTextCost;
    }

    public void setOwnerOfCard(PlayerType ownerOfCard) {
        this.ownerOfCard = ownerOfCard;
    }

    public PlayerType getOwnerOfCard() {
        return ownerOfCard;
    }
}