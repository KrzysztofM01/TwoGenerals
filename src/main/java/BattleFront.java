import cards.Card;

import java.util.ArrayList;

public class BattleFront {
    private int ID;
    private int playerID;
    private ArrayList<Card> cardList = new ArrayList<Card>();
    private int HP = 30;


    public BattleFront(int ID, int playerID) {
        this.ID = ID;
        this.playerID = playerID;
    }

    public int getID() {
        return ID;
    }

    public int getPlayerID() {
        return playerID;
    }

    public ArrayList<Card> getCardList() {
        return cardList;
    }

    public int getHP() {
        return HP;
    }

    public int getSummedPower() {
        int summedPower = 0;
        for (Card card: this.cardList){
            summedPower += card.getCurrentPower();
        }
        return summedPower;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void addCard (Card card){
        this.cardList.add(card);
    }

    public String displayBattleFront(){
        StringBuilder stringBuilder = new StringBuilder("BattleFrontID[");
        stringBuilder.append(this.ID);
        stringBuilder.append("] PlayerID[");
        stringBuilder.append(this.playerID);
        stringBuilder.append("] Power = ");
        stringBuilder.append(this.getSummedPower());
        stringBuilder.append(", HP = ");
        stringBuilder.append(this.HP);
        stringBuilder.append("\nCards = { ");
        for (Card card: this.cardList){
            stringBuilder.append(card.getName());
            stringBuilder.append(" ");
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}


