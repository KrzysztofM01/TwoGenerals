package graphic.cards;

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

public class Card extends StackPane implements Serializable{
    private CardLogic cardLogic;
    private boolean highlighted;
    private CardText cardTextPower;
    private CardText cardTextCost;
    private int tempCardPlayerID;

    public Card(CardLogic card) {
        this.cardLogic = card;
        ImageView cardFront = new ImageView(new Image(card.getImageURL()));
        cardFront.setPreserveRatio(false);
        cardFront.setFitWidth(VariablesGraphics.cardWidth);
        cardFront.setFitHeight(VariablesGraphics.cardHeight);
        /*
        this.cardBack.setPreserveRatio(true);
        this.cardBack.setFitHeight(VariablesGraphics.cardHeight);
        */
        this.setPadding(new Insets(VariablesGraphics.cardPadding, VariablesGraphics.cardPadding, VariablesGraphics.cardPadding, VariablesGraphics.cardPadding ));
        //this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        this.cardTextPower = new CardText(this.cardLogic.getCurrentPower(), true);
        this.cardTextCost = new CardText(this.cardLogic.getCost(), false);
        this.getChildren().addAll(cardFront, cardTextPower, cardTextCost);
    }

    public CardLogic getCardLogic() {
        return cardLogic;
    }

    public boolean isHighlighted() {
        return highlighted;
    }

    public void setHighlighted(boolean highlighted) {
        this.highlighted = highlighted;
        if (highlighted){
            this.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        }
        else{
            this.setBackground(Background.EMPTY);
        }
    }

    public void setCardTextPower(CardText cardTextPower) {
        this.cardTextPower = cardTextPower;
    }

    public CardText getCardTextPower() {

        return cardTextPower;
    }

    public CardText getCardTextCost() {
        return cardTextCost;
    }

    public int getTempCardPlayerID() {
        return tempCardPlayerID;
    }

    public void setTempCardPlayerID(int tempCardPlayerID) {
        this.tempCardPlayerID = tempCardPlayerID;
    }

}