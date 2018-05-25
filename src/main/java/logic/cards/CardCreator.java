package logic.cards;

public class CardCreator {

    public static CardLogic newCard(String name, CardType cardType, int power, int cost, String imageURL) {
        switch (cardType) {
            case BattleCard:
                return new BattleCard(name, power, cost, imageURL, cardType);
            case BoostArmyMorale:
                return new BattleCleric(name, power, cost, imageURL, cardType);
            case Thaumaturge:
                return new Thaumaturge(name, power, cost, imageURL, cardType);
            default:
                return null;
        }
    }
}