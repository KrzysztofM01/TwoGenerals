package logic.cards;

import graphic.PlayerType;
import logic.LogicManager;
import logic.battleFields.LineType;

public class Thaumaturge extends CardLogic {

    Thaumaturge(String name, int power, int cost, String imageURL, CardType cardType) {
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
        for (CardLogic cardLogic : logicManager.getFrontLine(frontLineType, newPlayerType).getCardList()){
            if (cardLogic.getCardType() != CardType.BattleCard){
                this.setCurrentPower(this.getCurrentPower()+3);
            }
        }
        this.setCurrentPower(this.getPower());
    }

    @Override
    public String getDescription() {
        return "When used this card gets +3 increased power for every enemy special card on the same battle front.";
    }
}
