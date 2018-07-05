package database;

import game.logic.cards.CardLogic;

public class CardSuggest {

    private int id;
    private String name;
    private int power;
    private int cost;
    private String imageURL;
    private String cardType;
    private String cardDescription;

    public CardSuggest() {
    }

    public CardSuggest(CardLogic cardLogic) {
        this.id = cardLogic.getCardID();
        this.name = cardLogic.getName();
        this.power = cardLogic.getPower();
        this.cost = cardLogic.getCost();
        this.imageURL = cardLogic.getImageURL();
        this.cardType = cardLogic.getCardType().toString();
        this.cardDescription = cardLogic.getDescription();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardDescription() {
        return cardDescription;
    }

    public void setCardDescription(String cardDescription) {
        this.cardDescription = cardDescription;
    }
}
