package logic.cards;

import logic.battleFields.LineType;
import logic.LogicManager;

import java.io.Serializable;

public abstract class CardLogic implements Serializable{
    private String name;
    private int power;
    private int cost;
    private String imageURL;
    private CardType cardType;
    private int currentPower;
    private boolean isHidden = false;
    private int onWhichFront = 0;
    private boolean updateGraphics = false;

    CardLogic(String name, int power, int cost, String imageURL, CardType cardType) {
        this.name = name;
        this.power = power;
        this.cost = cost;
        this.imageURL = imageURL;
        this.cardType = cardType;
        this.currentPower = power;
    }

    public void action(LogicManager logicManager, LineType frontLineType, int playerID){

    }

    public String getDescription(){
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
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public CardType getCardType() { return cardType; }

    public int getCurrentPower() { return currentPower;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public int getOnWhichFront() {
        return onWhichFront;
    }

    public void setCurrentPower(int currentPower) {
        this.currentPower = currentPower;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }

    public void setOnWhichFront(int onWhichFront) {
        this.onWhichFront = onWhichFront;
    }

    public void setUpdateGraphics(boolean updateGraphics) {
        this.updateGraphics = updateGraphics;
    }

    public boolean isUpdateGraphics() {
        return updateGraphics;
    }

}
