package logic;

import logic.cards.CardLogic;
import variables.VariablesLogic;

import java.util.ArrayList;

public class Player {

    private ArrayList<CardLogic> cardList = new ArrayList<>();
    private int hitPoints = VariablesLogic.getInstance().getPlayerHitPoints();
    private int actionPoints = VariablesLogic.getInstance().getPlayerActionPoints();

    public Player() {
    }
    public ArrayList<CardLogic> getCardList() {
        return cardList;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public int getActionPoints() {
        return actionPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
        if (this.hitPoints < 0){
            this.hitPoints = 0;
        }
    }

    public void setActionPoints(int actionPoints) {
        this.actionPoints = actionPoints;
    }

    public void removeCard(CardLogic card) {
        this.cardList.remove(card);
    }

}