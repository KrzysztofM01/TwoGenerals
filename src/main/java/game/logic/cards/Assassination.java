package game.logic.cards;

import game.graphic.GraphicManager;
import game.graphic.PlayerType;
import game.graphic.cards.Card;
import game.logic.LogicManager;
import game.logic.battleFields.LineType;

import java.util.Comparator;

public class Assassination extends CardLogic {

    Assassination(String name, int power, int cost, String imageURL, CardType cardType, int specialPower) {
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

        // Searches for strongest card on battle front and removes it
        graphicManager.getCardsFromBattleFront(frontLineType, newPlayerType).stream()
                .max(Comparator.comparingInt(s -> s.getCardLogic().getCurrentPower()))
                .ifPresent(s -> {
                    logicManager.removeCardFromFront(s.getCardLogic(), frontLineType, newPlayerType);
                    graphicManager.removeCardFromFront(s, frontLineType, newPlayerType);
                    graphicManager.updateBattleFieldTextBoxes(logicManager, newPlayerType);
                });
    }

    @Override
    public String getDescription() {
        return "When used removes the strongest enemy on the same battle front.";
    }
}
