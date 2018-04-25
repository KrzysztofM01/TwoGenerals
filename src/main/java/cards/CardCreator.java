package cards;

import javax.swing.ImageIcon;

public class CardCreator {
    public static Card newCard(String name, CardType cardType, int power, int cost, ImageIcon imageIcon) {
        switch (cardType) {
            case NormalCard:
                return new NormalCard(name, power, cost, imageIcon, cardType);
            case DasDingo:
                return new DasDingo(name, power, cost, imageIcon, cardType);
            default:
                return null;
        }
    }
}