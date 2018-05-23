import graphic.PlayerType;
import logic.cards.CardCreator;
import logic.cards.CardLogic;
import logic.cards.CardType;
import logic.players.Player;
import javafx.application.Application;
import javafx.stage.Stage;
import main.GameManager;

public class Main extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){

        /* To fix:
        -1)Clicking on textbox changes the ID, removing the CSS and also when clicking on opponent card changes it to yours
        0) When clicking exit game, the OOS or OIS thread is still running
        1) when you add card to front it doesnt have correct padding background
        ^ it may be connected to multiply color background in leftbattleground
        2) Scale font with screenSize
        3) Send all things to css and scale it into one CSS
        4) Remove things from graphicManager and create new class for each of them

        */

        //NetworkConnectPanel networkConnectPanel = new NetworkConnectPanel(primaryStage);


        //Player player0 = new Player(0,"Krystian");
        //Player player1 = new Player(1,"Karczynski");

        GameManager gameManager = new GameManager(primaryStage);


        String image = "cardImages/BloodKnight2.jpg";
        String image2 = "cardImages/Thaumaturg.jpg";
        String image3 = "cardImages/HawkMaster.jpg";
        String image4 = "cardImages/TheBloodKnight.jpg";
        String image5 = "cardImages/HoodedWarrior.png";
        String image6 = "cardImages/BlindMage.png";

        //Player Cards
        CardLogic card = CardCreator.newCard("Normal Card", CardType.NormalCard, 3, 2, image);
        CardLogic card2 = CardCreator.newCard("White Knight Riding", CardType.NormalCard, 3, 4, image2);
        CardLogic card3 = CardCreator.newCard("Normal Card", CardType.DasDingo, 1, 2, image3);
        CardLogic card4 = CardCreator.newCard("Normal Card", CardType.DasDingo, 1, 2, image3);
        CardLogic card16 = CardCreator.newCard("Normal Card", CardType.DasDingo, 1, 2, image3);
        CardLogic card17 = CardCreator.newCard("Normal Card", CardType.DasDingo, 1, 2, image3);
        CardLogic card5 = CardCreator.newCard("Normal Card", CardType.NormalCard, 6, 8, image6);
        CardLogic card6 = CardCreator.newCard("Normal Card", CardType.NormalCard, 2, 5, image6);
        CardLogic card7 = CardCreator.newCard("Normal Card", CardType.NormalCard, 4, 3, image5);
        CardLogic card8 = CardCreator.newCard("Normal Card", CardType.NormalCard, 6, 7, image5);
        CardLogic card9 = CardCreator.newCard("Normal Card", CardType.NormalCard, 1, 1, image5);
        CardLogic card10 = CardCreator.newCard("Normal Card", CardType.NormalCard, 5, 3, image6);
        CardLogic card11 = CardCreator.newCard("Normal Card", CardType.NormalCard, 4, 6, image2);
        CardLogic card12 = CardCreator.newCard("Normal Card", CardType.NormalCard, 5, 3, image2);
        CardLogic card13 = CardCreator.newCard("Normal Card", CardType.NormalCard, 12, 8, image4);
        CardLogic card14 = CardCreator.newCard("Normal Card", CardType.NormalCard, 10, 9, image2);
        CardLogic card15 = CardCreator.newCard("Normal Card", CardType.NormalCard, 3, 2, image4);
        CardLogic card18 = CardCreator.newCard("Normal Card", CardType.NormalCard, 3, 2, image4);
        CardLogic card19 = CardCreator.newCard("Normal Card", CardType.NormalCard, 5, 4, image);
        CardLogic card20 = CardCreator.newCard("Normal Card", CardType.NormalCard, 1, 2, image);
        CardLogic card21 = CardCreator.newCard("Normal Card", CardType.NormalCard, 5, 5, image2);
        CardLogic card22 = CardCreator.newCard("Normal Card", CardType.NormalCard, 10, 8, image4);
        CardLogic card23 = CardCreator.newCard("Normal Card", CardType.NormalCard, 2, 1, image2);
        CardLogic card24 = CardCreator.newCard("Normal Card", CardType.NormalCard, 4, 2, image);
        CardLogic card25 = CardCreator.newCard("Normal Card", CardType.NormalCard, 6, 6, image2);

        //Opponent Cards
        CardLogic card26 = CardCreator.newCard("Normal Card", CardType.NormalCard, 6, 5, image2);
        CardLogic card27 = CardCreator.newCard("Normal Card", CardType.NormalCard, 3, 2, image4);
        CardLogic card28 = CardCreator.newCard("Normal Card", CardType.DasDingo, 1, 2, image3);
        CardLogic card29 = CardCreator.newCard("Normal Card", CardType.DasDingo, 1, 2, image3);
        CardLogic card30 = CardCreator.newCard("Normal Card", CardType.DasDingo, 1, 2, image3);
        CardLogic card31 = CardCreator.newCard("Normal Card", CardType.DasDingo, 1, 2, image3);

        gameManager.addCardToPlayerDeck(card, PlayerType.player);
        gameManager.addCardToPlayerDeck(card2,PlayerType.player);
        gameManager.addCardToPlayerDeck(card3,PlayerType.player);
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
        /*
        gameManager.addCardToPlayerDeck(card26,PlayerType.opponent);
        gameManager.addCardToPlayerDeck(card27,PlayerType.opponent);
        gameManager.addCardToPlayerDeck(card28,PlayerType.opponent);
        gameManager.addCardToPlayerDeck(card29,PlayerType.opponent);
        gameManager.addCardToPlayerDeck(card30,PlayerType.opponent);
        gameManager.addCardToPlayerDeck(card31,PlayerType.opponent);
        */


    }
}
