package cards;

import javax.swing.ImageIcon;

public abstract class Card {
    private String name;
    private int power;
    private int cost;
    private ImageIcon imageIcon;
    private CardType cardType;

    public Card(String name, int power, int cost, ImageIcon imageIcon, CardType cardType) {
        this.name = name;
        this.power = power;
        this.cost = cost;
        this.imageIcon = imageIcon;
        this.cardType = cardType;
    }

    public void action(){

    }

    public String getName() {
        return name;
    }

    public int getPower() {
        return power;
    }

    public int getCost() {
        return cost;
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Card{" +
                "name='" + name + '\'' +
                ", power=" + power +
                ", cost=" + cost +
                ", cardType=" + cardType +
                '}';
    }
}
