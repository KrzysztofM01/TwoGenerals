import cards.Card;
import cards.CardCreator;
import cards.CardType;

import javax.swing.*;
import java.util.ArrayList;

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

        Card card1 = CardCreator.newCard(select2, CardType.valueOf(select4),9,5, new ImageIcon("C:\\Users\\User\\IdeaProjects\\TwoGenerals\\src\\main\\resources\\cardImages\\02d.png"));


        ArrayList<Card> list = new ArrayList<Card>();
        list.add(CardCreator.newCard(select1, CardType.valueOf(select3),3,3, new ImageIcon("C:\\Users\\User\\IdeaProjects\\TwoGenerals\\src\\main\\resources\\cardImages\\02a.png")));
        list.add(CardCreator.newCard(select2, CardType.valueOf(select4),8,9, new ImageIcon("C:\\Users\\User\\IdeaProjects\\TwoGenerals\\src\\main\\resources\\cardImages\\02b.png")));
        list.add(CardCreator.newCard(select1, CardType.valueOf(select3),2,5, new ImageIcon("C:\\Users\\User\\IdeaProjects\\TwoGenerals\\src\\main\\resources\\cardImages\\02c.png")));
        list.add(CardCreator.newCard(select2, CardType.valueOf(select4),9,5, new ImageIcon("C:\\Users\\User\\IdeaProjects\\TwoGenerals\\src\\main\\resources\\cardImages\\02d.png")));
        Player player1 = new Player(0,"Srutek",list);

        /*
        Display player's cards
        for (Card card: player1.getDeck()){
            System.out.println(card.toString());
        }
        */
        BattleFront battleFront1 = new BattleFront(0,0);
        BattleFront battleFront2 = new BattleFront(0,1);

        System.out.println(battleFront1.displayBattleFront());
        System.out.println(battleFront2.displayBattleFront());

        BattleField.addCardToBattleFront(player1.getDeck().get(0),player1,battleFront1);
        BattleField.addCardToBattleFront(player1.getDeck().get(0),player1,battleFront2);

        System.out.println(battleFront1.displayBattleFront());
        System.out.println(battleFront2.displayBattleFront());

        BattleField.attackOnBattleFront(battleFront1, battleFront2);

        System.out.println(battleFront1.displayBattleFront());
        System.out.println(battleFront2.displayBattleFront());

    }
}