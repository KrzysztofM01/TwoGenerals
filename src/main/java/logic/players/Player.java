package logic.players;

import logic.cards.CardLogic;
import variables.VariablesLogic;

import java.util.ArrayList;

public class Player {
    private int ID;
    private String name;
    private ArrayList<CardLogic> cardList = new ArrayList<>();
    private int hitPoints = VariablesLogic.playerHitPoints;
    private int actionPoints = VariablesLogic.playerActionPoints;

    public Player() {
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
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
            System.out.println("GAME OVER!!!");
        }
    }

    public void setActionPoints(int actionPoints) {
        this.actionPoints = actionPoints;
    }

    public void removeCard(CardLogic card) {
        this.cardList.remove(card);
    }

    public void addCard(CardLogic card){
        this.cardList.add(card);
    }
}
