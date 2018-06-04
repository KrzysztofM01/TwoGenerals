package logic.cards;

import graphic.GraphicManager;
import graphic.PlayerType;
import logic.LogicManager;
import logic.battleFields.LineType;

public class UncontrolledPower extends CardLogic {

    UncontrolledPower(String name, int power, int cost, String imageURL, CardType cardType, int specialPower) {
        super(name, power, cost, imageURL, cardType, specialPower);
        this.setUpdateGraphics(true);
    }

    @Override
    public void action(LogicManager logicManager, GraphicManager graphicManager, LineType frontLineType, PlayerType playerType){
        logicManager.getPlayer(playerType).setHitPoints(logicManager.getPlayer(playerType).getHitPoints()-getSpecialPower());
    }

    @Override
    public String getDescription() {
        return "When used this card deals " + getSpecialPower() + " damage to player's hit points.";
    }
}
