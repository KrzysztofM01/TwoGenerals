package logic.cards;

import graphic.GraphicManager;
import graphic.PlayerType;
import logic.battleFields.LineType;
import logic.LogicManager;

public class BoostArmyMorale extends CardLogic {

    BoostArmyMorale(String name, int power, int cost, String imageURL, CardType cardType, int specialPower) {
        super(name, power, cost, imageURL, cardType, specialPower);
        this.setUpdateGraphics(true);
    }

    @Override
    public void action(LogicManager logicManager, GraphicManager graphicManager, LineType frontLineType, PlayerType playerType){
        for (CardLogic cardLogic : logicManager.getFrontLine(frontLineType, playerType).getCardList()){
            cardLogic.setCurrentPower(cardLogic.getCurrentPower()+getSpecialPower());
        }
        this.setCurrentPower(this.getPower());
    }

    @Override
    public String getDescription() {
        return "When used this card increases power by " + getSpecialPower() +" of all friendly cards in battle front.";
    }
}
