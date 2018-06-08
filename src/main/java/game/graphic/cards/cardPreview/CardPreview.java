package game.graphic.cards.cardPreview;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import game.logic.cards.CardLogic;
import game.variables.VariablesGraphics;

public class CardPreview extends StackPane {


    public CardPreview(CardLogic cardLogic) {

        ImageView imageView = new ImageView(cardLogic.getImageURL());
        imageView.setFitHeight(VariablesGraphics.getInstance().getCardPreviewHeigth());
        imageView.setFitWidth(VariablesGraphics.getInstance().getCardPrevieWidth());

        this.setTranslateY(-VariablesGraphics.getInstance().getScreenHeight() * 0.09);

        this.getChildren().addAll(imageView, new CardPreviewTextNumbers(cardLogic.getCost(), false),
                new CardPreviewTextNumbers(cardLogic.getCurrentPower(), true),
                new CardPreviewTextName(cardLogic.getName()),
                new CardPreviewTextDescription(cardLogic.getDescription()),
                new CardPreviewTextType(cardLogic.getCardType().toStringSeparated()));
    }
}
