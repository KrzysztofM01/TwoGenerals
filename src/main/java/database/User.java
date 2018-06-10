package database;


import game.logic.cards.CardLogic;

import java.util.ArrayList;

public class User {
    private int id;
    private String login;
    private String password;
    private boolean hasAdmin = false;
    private String cardListString = "1, 1, 4, 5, 5, 7, 7, 7, 8, 8, 9, 9, 11, 12, 12, 14, 14, 15, 15, 16, 16, 17, 17, 19, 19, 19, 20, 20, 20, 21, 21, 22, 25, 25, 25, 2, 2, 3, 3, 6, 6, 10, 10, 13, 13, 18, 18, 23, 24, 24";
    private ArrayList<CardLogic> cardDeck;


    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isHasAdmin() {
        return hasAdmin;
    }

    public void setHasAdmin(boolean hasAdmin) {
        this.hasAdmin = hasAdmin;
    }

    public String getCardListString() {
        return cardListString;
    }

    public void setCardListString(String cardListString) {
        this.cardListString = cardListString;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", hasAdmin=" + hasAdmin +
                ", cardListString='" + cardListString + '\'' +
                '}';
    }

    public ArrayList<CardLogic> getCardDeck() {
        return cardDeck;
    }

    public void setCardDeck(ArrayList<CardLogic> cardDeck) {
        this.cardDeck = cardDeck;
    }
}