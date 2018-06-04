package logic.battleFields;

import variables.VariablesLogic;
import logic.cards.CardLogic;

import java.util.ArrayList;

public class FrontLine {
    private ArrayList<CardLogic> cardList = new ArrayList<>();
    private int HP = VariablesLogic.getInstance().getBattleFieldHitPoints();

    public FrontLine() {
    }

    public ArrayList<CardLogic> getCardList() {
        return cardList;
    }

    public int getHP() {
        return HP;
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


