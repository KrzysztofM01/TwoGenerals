package logic.cards;

import graphic.GraphicManager;
import graphic.PlayerType;
import logic.LogicManager;
import logic.battleFields.LineType;

public class MorgusAssassin extends CardLogic {

    MorgusAssassin(String name, int power, int cost, String imageURL, CardType cardType) {
        super(name, power, cost, imageURL, cardType);
        this.setUpdateGraphics(true);
    }

    @Override
    public void action(LogicManager logicManager, LineType frontLineType, PlayerType playerType){
        PlayerType newPlayerType;
        if (playerType == PlayerType.player) {
            newPlayerType = PlayerType.opponent;
        } else {
            newPlayerType = PlayerType.player;
        }
        int maxPower = 0;
        CardLogic cardWithMaxPower = CardCreator.newCard("", CardType.BattleCard, 0, 0, "");
        for (CardLogic cardLogic : logicManager.getFrontLine(frontLineType, newPlayerType).getCardList()){
            if (cardLogic.getCurrentPower() > maxPower) {
                maxPower = cardLogic.getCurrentPower();
                cardWithMaxPower = cardLogic;
            }
        }
        if (cardWithMaxPower != null) {
            cardWithMaxPower.setCurrentPower(0);
        }
    }

    @Override
    public String getDescription() {
        return "When used reduce the power of strongest enemy on the same battle front to 0.";
    }
}
