package logic.cards;

import graphic.PlayerType;
import logic.LogicManager;
import logic.battleFields.LineType;

public class InfernoMage extends CardLogic {

    InfernoMage(String name, int power, int cost, String imageURL, CardType cardType) {
        super(name, power, cost, imageURL, cardType);
        this.setUpdateGraphics(true);
    }

    @Override
    public void action(LogicManager logicManager, LineType frontLineType, PlayerType playerType){
        logicManager.getPlayer(playerType).setHitPoints(logicManager.getPlayer(playerType).getHitPoints()-8);
    }

    @Override
    public String getDescription() {
        return "When used this card deals 8 damage to player's hit points.";
    }
}
