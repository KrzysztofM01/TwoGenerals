package logic.cards;

import javafx.scene.image.Image;

public class CardCreator {

    public static CardLogic newCard(String name, CardType cardType, int power, int cost, String imageURL) {
        switch (cardType) {
            case NormalCard:
                return new NormalCard(name, power, cost, imageURL, cardType);
            case DasDingo:
                return new DasDingo(name, power, cost, imageURL, cardType);
            default:
                return null;
        }
    }
}