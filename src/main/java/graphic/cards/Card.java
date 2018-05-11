package graphic.cards;

import logic.cards.CardLogic;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import variables.VariablesGraphics;

public class Card extends StackPane {
    private CardLogic cardLogic;
    private boolean highlighted;
    private PowerText powerText;
    private ImageView cardBack  = new ImageView(new Image("cardImages/mahjong.png"));

    public Card(CardLogic card) {
        this.cardLogic = card;
        ImageView imageView = new ImageView(card.getImage());
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(VariablesGraphics.cardWidth);
        this.cardBack.setPreserveRatio(true);
        this.cardBack.setFitHeight(VariablesGraphics.cardHeight);
        this.setPadding(new Insets(VariablesGraphics.cardPadding, VariablesGraphics.cardPadding, VariablesGraphics.cardPadding, VariablesGraphics.cardPadding ));
        this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        this.powerText = new PowerText(this.cardLogic.getCurrentPower());
        this.cardBack.setViewOrder(1);
        this.getChildren().addAll(imageView, powerText, cardBack);
    }

    public void turnCardBack(boolean isCardBackUp){
        if (isCardBackUp){
            this.cardBack.setViewOrder(-1);
        } else {
            this.cardBack.setViewOrder(1);
        }
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
            this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }

    public void setPowerText(PowerText powerText) {
        this.powerText = powerText;
    }

    public PowerText getPowerText() {

        return powerText;
    }
}
