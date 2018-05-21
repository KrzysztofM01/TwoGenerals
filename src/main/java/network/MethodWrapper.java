package network;

import graphic.GraphicManager;
import graphic.cards.Card;
import logic.LogicManager;
import logic.battleFields.LineType;

import java.io.Serializable;

public class MethodWrapper implements Serializable{
    private Card card;
    private LineType lineType;
    private int playerID;
    private MethodType methodType;

    private MethodWrapper() {
    }

    public static MethodWrapper addCardToFront(Card card, LineType lineType, int playerID){
        MethodWrapper methodWrapper = new MethodWrapper();
        methodWrapper.card = card;
        methodWrapper.lineType = lineType;
        methodWrapper.playerID = playerID;
        methodWrapper.methodType = MethodType.addCardToFront;
        return methodWrapper;
    }

    public static MethodWrapper attackOnFront(LineType lineType){
        MethodWrapper methodWrapper = new MethodWrapper();
        methodWrapper.lineType = lineType;
        methodWrapper.methodType = MethodType.attackOnFront;
        return methodWrapper;
    }

    public static MethodWrapper removeCardFromPlayer(Card card, int playerID){
        MethodWrapper methodWrapper = new MethodWrapper();
        methodWrapper.card = card;
        methodWrapper.playerID = playerID;
        methodWrapper.methodType = MethodType.removeCardFromPlayer;
        return methodWrapper;
    }

    public void unwrapMethod (){
        switch (this.methodType) {
            case addCardToFront:
                System.out.println("Card was added to front.");

                break;
            case removeCardFromPlayer:
                System.out.println("Card was removed from player's cards.");
                break;
            case attackOnFront:
                System.out.println("Player attacked your front.");
                break;
        }
    }

    public Card getCard() {
        return card;
    }

    public LineType getLineType() {
        return lineType;
    }

    public int getPlayerID() {
        return playerID;
    }

    public MethodType getMethodType() {
        return methodType;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public void setLineType(LineType lineType) {
        this.lineType = lineType;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public void setMethodType(MethodType methodType) {
        this.methodType = methodType;
    }
}
