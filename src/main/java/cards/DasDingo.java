package cards;

import javax.swing.ImageIcon;

public class DasDingo extends Card {

    public DasDingo(String name, int power, int cost, ImageIcon imageIcon, CardType cardType) {
        super(name, power, cost, imageIcon, cardType);
    }

    @Override
    public void action(){
        System.out.println("DASSSSSSS DINGO");
    }
}
