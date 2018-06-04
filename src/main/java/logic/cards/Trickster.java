package logic.cards;

import graphic.GraphicManager;
import graphic.PlayerType;
import logic.LogicManager;
import logic.battleFields.LineType;

public class Trickster extends CardLogic {

    Trickster(String name, int power, int cost, String imageURL, CardType cardType, int specialPower) {
        super(name, power, cost, imageURL, cardType, specialPower);
        this.setUpdateGraphics(true);
    }

    @Override
    public void action(LogicManager logicManager, GraphicManager graphicManager, LineType frontLineType, PlayerType playerType){
        switch (frontLineType){
            case left:
                this.setLineType(LineType.center);
                break;
            case center:
                this.setLineType(LineType.right);
                break;
            case right:
                this.setLineType(LineType.left);
                break;
        }
    }

    @Override
    public String getDescription() {
        return "This card does not increase power of battle front the card is played on, but increases power of battle front to the right.";
    }
}
