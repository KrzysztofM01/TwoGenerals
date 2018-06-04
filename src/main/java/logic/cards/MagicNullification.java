package logic.cards;

import graphic.GraphicManager;
import graphic.PlayerType;
import logic.LogicManager;
import logic.battleFields.LineType;

public class MagicNullification extends CardLogic {

    MagicNullification(String name, int power, int cost, String imageURL, CardType cardType, int specialPower) {
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
        for (LineType lineType : LineType.values()) {
            for (CardLogic cardLogic : logicManager.getFrontLine(lineType, newPlayerType).getCardList()){
                cardLogic.setCurrentPower(cardLogic.getPower());
            }
        }
    }

    @Override
    public String getDescription() {
        return "When used this card nullifies enemy powers, changing their powers to default.";
    }
}
