package logic.cards;

public class CardCreator {

    public static CardLogic newCard(String name, CardType cardType, int power, int cost, String imageURL) {
        switch (cardType) {
            case BattleCard:
                return new BattleCard(name, power, cost, imageURL, cardType);
            case BoostArmyMorale:
                return new BattleCleric(name, power, cost, imageURL, cardType);
            case MagicCatalyst:
                return new Thaumaturge(name, power, cost, imageURL, cardType);
            case MagicNullification:
                return new BlindMage(name, power, cost, imageURL, cardType);
            case UncontrolledPower:
                return new InfernoMage(name, power, cost, imageURL, cardType);
            case Assassination:
                return new MorgusAssassin(name, power, cost, imageURL, cardType);
            case Trickster:
                return new PlanesWalker(name, power, cost, imageURL, cardType);
            default:
                return null;
        }
    }
}