package logic.cards;

import graphic.GraphicManager;
import graphic.PlayerType;
import logic.LogicManager;
import logic.battleFields.LineType;

public class MagicCatalyst extends CardLogic {

    MagicCatalyst(String name, int power, int cost, String imageURL, CardType cardType, int specialPower) {
        super(name, power, cost, imageURL, cardType, specialPower);
        this.setUpdateGraphics(true);
    }

    @Override
    public void action(LogicManager logicManager, GraphicManager graphicManager, LineType frontLineType, PlayerType playerType) {

        PlayerType newPlayerType;
        // Changes the player type to opposite
        if (playerType == PlayerType.player) {
            newPlayerType = PlayerType.opponent;
        } else {
            newPlayerType = PlayerType.player;
        }
        // Increases power of this card by +X for each special card that opponent has in same battle front
        for (CardLogic cardLogic : logicManager.getFrontLine(frontLineType, newPlayerType).getCardList()) {
            if (cardLogic.getCardType() != CardType.BattleCard) {
                this.setCurrentPower(this.getCurrentPower() + getSpecialPower());
            }
        }
    }

    @Override
    public String getDescription() {
        return "When used this card gets +" + getSpecialPower() + " increased power for every enemy special card on the same battle front.";
    }
}
