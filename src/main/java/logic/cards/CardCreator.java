package logic.cards;

import javafx.scene.image.Image;

public class CardCreator {

    public static CardLogic newCard(String name, CardType cardType, int power, int cost, Image image) {
        switch (cardType) {
            case NormalCard:
                return new NormalCard(name, power, cost, image, cardType);
            case DasDingo:
                return new DasDingo(name, power, cost, image, cardType);
            default:
                return null;
        }
    }
}