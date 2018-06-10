package game.logic.cards;

import database.CardDB;

public class CardCreator {

    public static CardLogic newCard(String name, CardType cardType, int power, int cost, String imageURL, int specialPower) {
        switch (cardType) {
            case BattleCard:
                return new BattleCard(name, power, cost, imageURL, cardType, specialPower);
            case BoostArmyMorale:
                return new BoostArmyMorale(name, power, cost, imageURL, cardType, specialPower);
            case MagicCatalyst:
                return new MagicCatalyst(name, power, cost, imageURL, cardType, specialPower);
            case MagicNullification:
                return new MagicNullification(name, power, cost, imageURL, cardType, specialPower);
            case UncontrolledPower:
                return new UncontrolledPower(name, power, cost, imageURL, cardType, specialPower);
            case Assassination:
                return new Assassination(name, power, cost, imageURL, cardType, specialPower);
            case Trickster:
                return new Trickster(name, power, cost, imageURL, cardType, specialPower);
            case Infiltrator:
                return new Infiltrator(name, power, cost, imageURL, cardType, specialPower);
            case Tracker:
                return new Tracker(name, power, cost, imageURL, cardType, specialPower);
            default:
                return null;
        }
    }

    public static CardLogic newCardFromDB (CardDB cardDB) {
        return newCard(cardDB.getName(), CardType.valueOf(cardDB.getCardType()), cardDB.getPower(), cardDB.getCost(), cardDB.getImageURL(), cardDB.getSpecialPower());
    }
}