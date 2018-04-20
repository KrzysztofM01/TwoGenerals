import cards.Card;
import cards.CardCreator;
import cards.CardType;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        /*
        DB.connect();
        DB.insertUser("insane", "wololo3", true);
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\User\\IdeaProjects\\TwoGenerals\\src\\main\\resources\\cardImages\\02a.png", "idk");
        System.out.println(DB.checkPassword("insane", "wololo3"));
        DB.disconnect();
        */
        String select1 = "NORMALCARD";
        String select2 = "DASDINGO";
        String select3 = "NormalCard";
        String select4 = "DasDingo";

        List<Card> list = new ArrayList<Card>();
        list.add(CardCreator.newCard(select1, CardType.valueOf(select3),3,3, new ImageIcon("C:\\Users\\User\\IdeaProjects\\TwoGenerals\\src\\main\\resources\\cardImages\\02a.png")));
        list.add(CardCreator.newCard(select2, CardType.valueOf(select4),3,9, new ImageIcon("C:\\Users\\User\\IdeaProjects\\TwoGenerals\\src\\main\\resources\\cardImages\\02b.png")));
        list.add(CardCreator.newCard(select1, CardType.valueOf(select3),2,5, new ImageIcon("C:\\Users\\User\\IdeaProjects\\TwoGenerals\\src\\main\\resources\\cardImages\\02c.png")));
        list.add(CardCreator.newCard(select2, CardType.valueOf(select4),2,5, new ImageIcon("C:\\Users\\User\\IdeaProjects\\TwoGenerals\\src\\main\\resources\\cardImages\\02d.png")));
        for (Card card: list){
            System.out.println(card.toString());
            card.action();
        }
    }
}