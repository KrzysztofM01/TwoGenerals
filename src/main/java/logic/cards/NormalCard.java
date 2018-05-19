package logic.cards;

import graphic.cards.CardImage;
import javafx.scene.image.Image;

class NormalCard extends CardLogic {
    NormalCard(String name, int power, int cost, String imageURL, CardType cardType) {
        super(name, power, cost, imageURL, cardType);
    }
}
