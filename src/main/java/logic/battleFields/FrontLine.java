package logic.battleFields;

import variables.VariablesLogic;
import logic.cards.CardLogic;

import java.util.ArrayList;

public class FrontLine {
    private LineType frontLineType;
    private int playerID;
    private ArrayList<CardLogic> cardList = new ArrayList<CardLogic>();
    private int HP = VariablesLogic.battleFieldHitPoints;

    public FrontLine(LineType frontLineType, int playerID) {
        this.frontLineType = frontLineType;
        this.playerID = playerID;
    }

    public LineType getFrontLineType() {
        return frontLineType;
    }

    public int getPlayerID() {
        return playerID;
    }

    public ArrayList<CardLogic> getCardList() {
        return cardList;
    }

    public int getHP() {
        return HP;
    }

    public int getSummedPower() {
        int summedPower = 0;
        for (CardLogic card: this.cardList){
            summedPower += card.getCurrentPower();
        }
        return summedPower;
    }

    public void setHP(int HP) {
        this.HP = HP;
        if (this.HP < 0) {
            this.HP = 0;
        }
    }

    public void addCard (CardLogic card){
        this.cardList.add(card);
    }

    public void removeCard (CardLogic card) {this.cardList.remove(card);}

}


