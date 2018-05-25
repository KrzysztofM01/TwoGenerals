package logic.cards;

import graphic.PlayerType;
import logic.battleFields.LineType;
import logic.LogicManager;

public class BattleCleric extends CardLogic {

    BattleCleric(String name, int power, int cost, String imageURL, CardType cardType) {
        super(name, power, cost, imageURL, cardType);
        this.setUpdateGraphics(true);
    }

    @Override
    public void action(LogicManager logicManager, LineType frontLineType, PlayerType playerType){
        for (CardLogic cardLogic : logicManager.getFrontLine(frontLineType, playerType).getCardList()){
            cardLogic.setCurrentPower(cardLogic.getCurrentPower()+2);
        }
        this.setCurrentPower(this.getPower());
    }

    @Override
    public String getDescription() {
        return "When used this card increases power by 2 of all friendly cards in battle front.";
    }
}
