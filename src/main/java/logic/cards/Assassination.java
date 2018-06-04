package logic.cards;

import graphic.GraphicManager;
import graphic.PlayerType;
import graphic.cards.Card;
import logic.LogicManager;
import logic.battleFields.LineType;

public class Assassination extends CardLogic {

    Assassination(String name, int power, int cost, String imageURL, CardType cardType, int specialPower) {
        super(name, power, cost, imageURL, cardType, specialPower);
        this.setUpdateGraphics(true);
    }

    @Override
    public void action(LogicManager logicManager, GraphicManager graphicManager, LineType frontLineType, PlayerType playerType){
        PlayerType newPlayerType;
        if (playerType == PlayerType.player) {
            newPlayerType = PlayerType.opponent;
        } else {
            newPlayerType = PlayerType.player;
        }
        int maxPower = 0;
        Card cardWithMaxPower = new Card();
        for (Card card : graphicManager.getCardsFromBattleFront(frontLineType, newPlayerType)){
            if (card.getCardLogic().getCurrentPower() > maxPower) {
                maxPower = card.getCardLogic().getCurrentPower();
                cardWithMaxPower = card;
            }
        }
        logicManager.removeCardFromFront(cardWithMaxPower.getCardLogic(), frontLineType, newPlayerType);
        graphicManager.removeCardFromFront(cardWithMaxPower, frontLineType, newPlayerType);
        graphicManager.updateBattleFieldTextBoxes(logicManager, newPlayerType);

    }

    @Override
    public String getDescription() {
        return "When used removes the strongest enemy on the same battle front.";
    }
}
