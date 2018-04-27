import cards.Card;

import java.util.ArrayList;

public class Player {
    private int ID;
    private String name;
    private ArrayList<Card> deck;
    private int HP = 10;
    private int MP = 20;

    public Player(int ID, String name, ArrayList<Card> deck) {
        this.ID = ID;
        this.name = name;
        this.deck = deck;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public int getHP() {
        return HP;
    }

    public int getMP() {
        return MP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void setMP(int MP) {
        this.MP = MP;
    }
}
