package graphic.cards;

import graphic.PlayerType;
import javafx.geometry.Pos;
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
    private boolean isHidden = false;
    private CardText cardTextPower;
    private CardText cardTextCost;
    private PlayerType tempPlayerType;
    private ImageView cardBack;

    public Card(CardLogic card) {
        this.cardLogic = card;
        renderCardFront();
    }

    public CardLogic getCardLogic() {
        return cardLogic;
    }

    public void renderCardGraphicsOpponent() {
        renderCardFront();
        this.cardBack = new ImageView(new Image("images/CardBackG2.png"));
        cardBack.setFitWidth(VariablesGraphics.cardWidth);
        cardBack.setFitHeight(VariablesGraphics.cardHeight);
        this.isHidden = true;
        cardBack.setViewOrder(-5);
        this.getChildren().add(cardBack);
    }

    public void renderCardFront(){
        ImageView cardFront = new ImageView(new Image(cardLogic.getImageURL()));
        cardFront.setPreserveRatio(false);
        cardFront.setFitWidth(VariablesGraphics.cardWidth);
        cardFront.setFitHeight(VariablesGraphics.cardHeight);
        this.setPadding(new Insets(VariablesGraphics.cardPadding, VariablesGraphics.cardPadding, VariablesGraphics.cardPadding, VariablesGraphics.cardPadding ));
        this.cardTextPower = new CardText(this.cardLogic.getCurrentPower(), true);
        this.cardTextCost = new CardText(this.cardLogic.getCost(), false);
        this.getChildren().addAll(cardFront, cardTextPower, cardTextCost);
    }

    public void turnCard(boolean isHidden){
        this.isHidden = isHidden;
        if (isHidden) {
            this.cardBack.setViewOrder(-5);
        } else {
            this.cardBack.setViewOrder(5);
        }
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

    public void setTempPlayerType(PlayerType tempPlayerType) {
        this.tempPlayerType = tempPlayerType;
    }

    public PlayerType getTempPlayerType() {
        return tempPlayerType;
    }
}