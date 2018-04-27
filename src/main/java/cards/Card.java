package cards;

import javax.swing.ImageIcon;

public abstract class Card {
    private String name;
    private int power;
    private int cost;
    private ImageIcon imageIcon;
    private CardType cardType;
    private int currentPower;
    private boolean isHidden;
    private int onWhichFront;

    Card(String name, int power, int cost, ImageIcon imageIcon, CardType cardType) {
        this.name = name;
        this.power = power;
        this.cost = cost;
        this.imageIcon = imageIcon;
        this.cardType = cardType;
        this.currentPower = power;
        this.isHidden = false;
        this.onWhichFront = 0;
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

    public CardType getCardType() { return cardType; }

    public int getCurrentPower() { return currentPower; }

    public boolean isHidden() { return isHidden; }

    public int getOnWhichFront() { return onWhichFront; }

    public void setCurrentPower(int currentPower) { this.currentPower = currentPower; }

    public void setHidden(boolean hidden) { isHidden = hidden; }

    public void setOnWhichFront(int onWhichFront) { this.onWhichFront = onWhichFront; }

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
