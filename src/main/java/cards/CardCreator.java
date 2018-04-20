package cards;

import javax.swing.ImageIcon;

public class CardCreator {
    public static Card newCard(String name, CardType cardType, int power, int cost, ImageIcon imageIcon){
        if (cardType.equals(CardType.NormalCard)){
            return new NormalCard(name, power, cost, imageIcon, cardType);
        } else if (cardType.equals(CardType.DasDingo)){
            return new DasDingo(name, power, cost, imageIcon, cardType);
        }
        else {
            return null;
        }
    }
}
