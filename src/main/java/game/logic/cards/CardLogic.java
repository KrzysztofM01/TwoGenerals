package game.logic.cards;

import game.graphic.GraphicManager;
import game.graphic.PlayerType;
import game.logic.battleFields.LineType;
import game.logic.LogicManager;

import java.io.Serializable;

public abstract class CardLogic implements Serializable {
    private String name;
    private int power;
    private int cost;
    private String imageURL;
    private CardType cardType;
    private int currentPower;
    private LineType lineType;
    private boolean updateGraphics = false;
    private int specialPower;

    CardLogic(String name, int power, int cost, String imageURL, CardType cardType, int specialPower) {
        this.name = name;
        this.power = power;
        this.cost = cost;
        this.imageURL = imageURL;
        this.cardType = cardType;
        this.currentPower = power;
        this.specialPower = specialPower;
    }

    public void action(LogicManager logicManager, GraphicManager graphicManager, LineType frontLineType, PlayerType playerType) {

    }

    public String getDescription() {
        return "";
    }

    public String getName() {
        return name;
    }

    public int getPower() {
        return power;
    }

    public int getCost() {
        return cost;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setPower(int power) {
        this.power = power;
        if (this.power < 0) {
            this.power = 0;
        }
    }

    public void setCost(int cost) {
        this.cost = cost;
        if (this.cost < 0) {
            this.cost = 0;
        }
    }

    public CardType getCardType() {
        return cardType;
    }

    public int getCurrentPower() {
        return currentPower;
    }

    public void setCurrentPower(int currentPower) {
        this.currentPower = currentPower;
    }

    void setUpdateGraphics(boolean updateGraphics) {
        this.updateGraphics = updateGraphics;
    }

    public boolean isUpdateGraphics() {
        return updateGraphics;
    }

    public LineType getLineType() {
        return lineType;
    }

    public void setLineType(LineType lineType) {
        this.lineType = lineType;
    }

    int getSpecialPower() {
        return specialPower;
    }
}
