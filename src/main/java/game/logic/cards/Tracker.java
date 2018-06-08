package game.logic.cards;

import game.graphic.GraphicManager;
import game.graphic.PlayerType;
import game.logic.LogicManager;
import game.logic.battleFields.LineType;

import java.util.Random;

public class Tracker extends CardLogic {

    Tracker(String name, int power, int cost, String imageURL, CardType cardType, int specialPower) {
        super(name, power, cost, imageURL, cardType, specialPower);
    }

    @Override
    public void action(LogicManager logicManager, GraphicManager graphicManager, LineType frontLineType, PlayerType playerType) {


        // Randomizes an int index to find a card that is owned by proper player
        int randomIndex = new Random().nextInt(graphicManager.getCardList().size());
        while (true) {
            if (graphicManager.getCardList().get(randomIndex).getOwnerOfCard() == playerType) {
                break;
            } else {
                randomIndex = new Random().nextInt(graphicManager.getCardList().size());
            }
        }

        // Reduces that card's action points cost
        graphicManager.getCardList().get(randomIndex).getCardLogic().setCost(graphicManager.getCardList().get(randomIndex).getCardLogic().getCost() - getSpecialPower());
        graphicManager.updateCardGraphics(graphicManager.getCardList().get(randomIndex));

    }


    @Override
    public String getDescription() {
        return "When used this card will reduce the action points cost of random card in player's hand by " + getSpecialPower();
    }
}
