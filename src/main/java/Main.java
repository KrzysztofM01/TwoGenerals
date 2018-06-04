import graphic.PlayerType;
import logic.cards.CardCreator;
import logic.cards.CardLogic;
import logic.cards.CardType;
import javafx.application.Application;
import javafx.stage.Stage;
import main.GameManager;

import java.util.ArrayList;

public class Main extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){

        /* To fix:
        1) Add ability for card to remove others
        2) Scale font with screenSize
        3) Send all things to css and scale it into one CSS
        4) Remove things from graphicManager and create new class for each of them
        5) Fix NetworkManager, especially get Data dependency
        6) Change Logic Manager
        7) Remove redundant methods
        8) Add special cards
        9) Investigate some strange errors that sometimes appear
        */

        // Start game
        GameManager gameManager = new GameManager(primaryStage);

        // To be removed later, images of cards
        String image = "cardImages/BloodKnight2.jpg";
        String image2 = "cardImages/Thaumaturg.jpg";
        String image3 = "cardImages/HawkMaster.jpg";
        String image4 = "cardImages/TheBloodKnight.jpg";
        String image5 = "cardImages/HoodedWarrior.png";
        String image6 = "cardImages/BlindMage.png";
        String archer = "cardImages/ArcherMan.png";
        String archerWoman = "cardImages/ArcherWoman.png";
        String assassin = "cardImages/AssassinWoman.png";
        String battleCleric = "cardImages/BattleCleric.png";
        String battleMage1 = "cardImages/BattleMage.png";
        String battleMage2 = "cardImages/BattleMage3.png";
        String burningMage = "cardImages/BurningMage.png";
        String desertBowMan = "cardImages/DesertBowMan.png";
        String hunterWithOwl = "cardImages/HunterWithOwl.png";
        String inquisitor = "cardImages/Inquisitor.png";
        String mercenary = "cardImages/Mercenary.png";
        String planesWalker = "cardImages/PlanesWalker.png";
        String swordsMan = "cardImages/RagingSwordsman.png";
        String RavenKnight = "cardImages/RavenKnight.png";
        String soldierInRain = "cardImages/SoldierInRain.png";
        String thief = "cardImages/Thief.png";
        String thiefAssassinWoman = "cardImages/ThiefAssassinWoman.png";
        String thiefClimbingWall = "cardImages/ThiefClimbingWall.png";
        String warMercenary = "cardImages/WarMercenary.png";
        String rogue = "cardImages/WomanRogue.png";


        // Creating Player Cards, special cards disabled for now...
        CardLogic card = CardCreator.newCard("Blood Knight", CardType.BattleCard, 8, 6, image, 0);
        CardLogic card2 = CardCreator.newCard("MagicCatalyst", CardType.MagicCatalyst, 3, 5, image2, 3);
        CardLogic card42 = CardCreator.newCard("MagicCatalyst", CardType.MagicCatalyst, 3, 5, image2, 3);
        // CardLogic card3 = CardCreator.newCard("Hawk Master", CardType.HawkMaster, 3, 5, image3);
        // reveal enemy cards
        CardLogic card4 = CardCreator.newCard("Witch Slayer", CardType.BattleCard, 7, 5, image4, 0);
        CardLogic card16 = CardCreator.newCard("Hooded Warrior", CardType.BattleCard, 4, 2, image5, 0);
        CardLogic card17 = CardCreator.newCard("Blind Mage", CardType.MagicNullification, 2, 5, image6, 0);
        CardLogic card43 = CardCreator.newCard("Blind Mage", CardType.MagicNullification, 2, 5, image6, 0);
        CardLogic card5 = CardCreator.newCard("Field Archer", CardType.BattleCard, 3, 1, archer, 0);
        CardLogic card6 = CardCreator.newCard("Field Archer", CardType.BattleCard, 3, 1, archer, 0);
        CardLogic card7 = CardCreator.newCard("Field Archer", CardType.BattleCard, 3, 1, archer, 0);
        CardLogic card8 = CardCreator.newCard("Marksman", CardType.BattleCard, 5, 2, archerWoman, 0);
        CardLogic card9 = CardCreator.newCard("Marksman", CardType.BattleCard, 5, 2, archerWoman, 0);
        CardLogic card10 = CardCreator.newCard("Sister of Blade", CardType.BattleCard, 6, 4, assassin, 0);
        CardLogic card11 = CardCreator.newCard("Sister of Blade", CardType.BattleCard, 6, 4, assassin, 0);
        CardLogic card12 = CardCreator.newCard("Battle Cleric", CardType.BoostArmyMorale, 2, 5, battleCleric, 2);
        CardLogic card44 = CardCreator.newCard("Battle Cleric", CardType.BoostArmyMorale, 2, 5, battleCleric,2 );
        CardLogic card13 = CardCreator.newCard("Battle Mage", CardType.BattleCard, 12, 8, battleMage1, 0);
        CardLogic card14 = CardCreator.newCard("Battle Mage", CardType.BattleCard, 12, 8, battleMage1, 0);
        CardLogic card15 = CardCreator.newCard("Conjurer", CardType.BattleCard, 7, 5, battleMage2, 0);
        CardLogic card18 = CardCreator.newCard("Conjurer", CardType.BattleCard, 7, 5, battleMage2, 0);
        CardLogic card19 = CardCreator.newCard("Inferno Mage", CardType.UncontrolledPower, 10, 5, burningMage, 8);
        CardLogic card45 = CardCreator.newCard("Inferno Mage", CardType.UncontrolledPower, 10, 5, burningMage, 8);
        CardLogic card20 = CardCreator.newCard("Ak'vir Mercenary", CardType.BattleCard, 6, 4, desertBowMan, 0);
        CardLogic card21 = CardCreator.newCard("Ak'vir Mercenary", CardType.BattleCard, 6, 4, desertBowMan, 0);
        CardLogic card22 = CardCreator.newCard("Nylotha Shaman", CardType.BattleCard, 10, 7, hunterWithOwl, 0);
        CardLogic card23 = CardCreator.newCard("Inquisitor", CardType.BattleCard, 6, 4, inquisitor, 0);
        CardLogic card24 = CardCreator.newCard("Inquisitor", CardType.BattleCard, 6, 4, inquisitor, 0);
        CardLogic card25 = CardCreator.newCard("Ak'vir Sellsword", CardType.BattleCard, 5, 3, mercenary, 0);
        CardLogic card26 = CardCreator.newCard("Ak'vir Sellsword", CardType.BattleCard, 5, 3, mercenary, 0);
        CardLogic card27 = CardCreator.newCard("Ak'vir Sellsword", CardType.BattleCard, 5, 3, mercenary, 0);
        CardLogic card40 = CardCreator.newCard("Thergoth Soldier", CardType.BattleCard, 2, 1, soldierInRain, 0);
        CardLogic card41 = CardCreator.newCard("Thergoth Soldier", CardType.BattleCard, 2, 1, soldierInRain, 0);
        CardLogic card28 = CardCreator.newCard("Swordsman", CardType.BattleCard, 2, 1, swordsMan, 0);
        CardLogic card29 = CardCreator.newCard("Swordsman", CardType.BattleCard, 2, 1, swordsMan, 0);
        CardLogic card30 = CardCreator.newCard("Swordsman", CardType.BattleCard, 2, 1, swordsMan, 0);
        CardLogic card31 = CardCreator.newCard("Rogue", CardType.BattleCard, 4, 2, rogue, 0);
        CardLogic card32 = CardCreator.newCard("Rogue", CardType.BattleCard, 4, 2, rogue, 0);
        CardLogic card33 = CardCreator.newCard("Raven Knight", CardType.BattleCard, 8, 5, RavenKnight, 0);
        //CardLogic card34 = CardCreator.newCard("Shadow Dancer", CardType.ShadowDancer, 3, 6, thiefClimbingWall);
        CardLogic card35 = CardCreator.newCard("Morgus Assassin", CardType.Assassination, 2, 5, thiefAssassinWoman, 0);
        CardLogic card46 = CardCreator.newCard("Morgus Assassin", CardType.Assassination, 2, 5, thiefAssassinWoman, 0);
        CardLogic card36 = CardCreator.newCard("Skirmisher", CardType.BattleCard, 3, 2, thief, 0);
        CardLogic card37 = CardCreator.newCard("Skirmisher", CardType.BattleCard, 3, 2, thief, 0);
        CardLogic card38 = CardCreator.newCard("Planes Walker", CardType.Trickster, 7, 5, planesWalker, 0);
        CardLogic card47 = CardCreator.newCard("Planes Walker", CardType.Trickster, 7, 5, planesWalker, 0);
        CardLogic card39 = CardCreator.newCard("Berserk", CardType.BattleCard, 8, 5, warMercenary, 0);

        ArrayList<CardLogic> cardList = new ArrayList<>();
        cardList.add(card);
        cardList.add(card2);
        //cardList.add(card3);
        cardList.add(card4);
        cardList.add(card5);
        cardList.add(card6);
        cardList.add(card7);
        cardList.add(card8);
        cardList.add(card9);
        cardList.add(card10);
        cardList.add(card11);
        cardList.add(card12);
        cardList.add(card13);
        cardList.add(card14);
        cardList.add(card15);
        cardList.add(card16);
        cardList.add(card17);
        cardList.add(card18);
        cardList.add(card19);
        cardList.add(card20);
        cardList.add(card21);
        cardList.add(card22);
        cardList.add(card23);
        cardList.add(card24);
        cardList.add(card25);
        cardList.add(card26);
        cardList.add(card27);
        cardList.add(card28);
        cardList.add(card29);
        cardList.add(card30);
        cardList.add(card31);
        cardList.add(card32);
        cardList.add(card33);
        //cardList.add(card34);
        cardList.add(card35);
        cardList.add(card36);
        cardList.add(card37);
        cardList.add(card38);
        cardList.add(card39);
        cardList.add(card40);
        cardList.add(card41);
        cardList.add(card42);
        cardList.add(card43);
        cardList.add(card44);
        cardList.add(card45);
        cardList.add(card46);
        cardList.add(card47);

        for (CardLogic cardLogig: cardList) {
            gameManager.addCardToPlayerDeck(cardLogig, PlayerType.player);
        }

        /*
        //Adding Cards to player deck
        gameManager.addCardToPlayerDeck(card, PlayerType.player);
        gameManager.addCardToPlayerDeck(card2,PlayerType.player);
        //gameManager.addCardToPlayerDeck(card3,PlayerType.player);
        gameManager.addCardToPlayerDeck(card4,PlayerType.player);
        gameManager.addCardToPlayerDeck(card5,PlayerType.player);
        gameManager.addCardToPlayerDeck(card6,PlayerType.player);
        gameManager.addCardToPlayerDeck(card7,PlayerType.player);
        gameManager.addCardToPlayerDeck(card8,PlayerType.player);
        gameManager.addCardToPlayerDeck(card9,PlayerType.player);
        gameManager.addCardToPlayerDeck(card10,PlayerType.player);
        gameManager.addCardToPlayerDeck(card11,PlayerType.player);
        gameManager.addCardToPlayerDeck(card12,PlayerType.player);
        gameManager.addCardToPlayerDeck(card13,PlayerType.player);
        gameManager.addCardToPlayerDeck(card14,PlayerType.player);
        gameManager.addCardToPlayerDeck(card15,PlayerType.player);
        gameManager.addCardToPlayerDeck(card16,PlayerType.player);
        gameManager.addCardToPlayerDeck(card17,PlayerType.player);
        gameManager.addCardToPlayerDeck(card18,PlayerType.player);
        gameManager.addCardToPlayerDeck(card19,PlayerType.player);
        gameManager.addCardToPlayerDeck(card20,PlayerType.player);
        gameManager.addCardToPlayerDeck(card21,PlayerType.player);
        gameManager.addCardToPlayerDeck(card22,PlayerType.player);
        gameManager.addCardToPlayerDeck(card23,PlayerType.player);
        gameManager.addCardToPlayerDeck(card24,PlayerType.player);
        gameManager.addCardToPlayerDeck(card25,PlayerType.player);
        gameManager.addCardToPlayerDeck(card26,PlayerType.player);
        gameManager.addCardToPlayerDeck(card27,PlayerType.player);
        gameManager.addCardToPlayerDeck(card28,PlayerType.player);
        gameManager.addCardToPlayerDeck(card29,PlayerType.player);
        gameManager.addCardToPlayerDeck(card30,PlayerType.player);
        gameManager.addCardToPlayerDeck(card31,PlayerType.player);
        gameManager.addCardToPlayerDeck(card32,PlayerType.player);
        gameManager.addCardToPlayerDeck(card33,PlayerType.player);
       // gameManager.addCardToPlayerDeck(card34,PlayerType.player);
        gameManager.addCardToPlayerDeck(card35,PlayerType.player);
        gameManager.addCardToPlayerDeck(card36,PlayerType.player);
        gameManager.addCardToPlayerDeck(card37,PlayerType.player);
        gameManager.addCardToPlayerDeck(card38,PlayerType.player);
        gameManager.addCardToPlayerDeck(card39,PlayerType.player);
        gameManager.addCardToPlayerDeck(card40,PlayerType.player);
        gameManager.addCardToPlayerDeck(card41,PlayerType.player);
        gameManager.addCardToPlayerDeck(card42,PlayerType.player);
        gameManager.addCardToPlayerDeck(card43,PlayerType.player);
        gameManager.addCardToPlayerDeck(card44,PlayerType.player);
        gameManager.addCardToPlayerDeck(card45,PlayerType.player);
        gameManager.addCardToPlayerDeck(card46,PlayerType.player);
        gameManager.addCardToPlayerDeck(card47,PlayerType.player);
        */


    }
}
