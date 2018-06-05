package logic.cards;

import graphic.GraphicManager;
import graphic.PlayerType;
import logic.LogicManager;
import logic.battleFields.LineType;

import java.util.Arrays;

public class MagicNullification extends CardLogic {

    MagicNullification(String name, int power, int cost, String imageURL, CardType cardType, int specialPower) {
        super(name, power, cost, imageURL, cardType, specialPower);
        this.setUpdateGraphics(true);
    }

    @Override
    public void action(LogicManager logicManager, GraphicManager graphicManager, LineType frontLineType, PlayerType playerType){

        PlayerType newPlayerType;
        // Changes the player type to opposite
        if (playerType == PlayerType.player) {
            newPlayerType = PlayerType.opponent;
        } else {
            newPlayerType = PlayerType.player;
        }

        // Goes through all opponent cards and changes their power to normal
        Arrays.stream(LineType.values())
                .map(s -> logicManager.getFrontLine(s, newPlayerType).getCardList())
                .forEach(s -> s
                        .forEach(s2 -> s2.setCurrentPower(s2.getPower())));

        // Need to update the boxes
        graphicManager.updateBattleFieldTextBoxes(logicManager, newPlayerType);
    }

    @Override
    public String getDescription() {
        return "When used this card nullifies enemy powers, changing their powers to default.";
    }
}
