import logic.players.Player;
import logic.cards.CardCreator;
import logic.cards.CardLogic;
import logic.cards.CardType;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){



        /* To fix:
        1) when you add card to front it doesnt have correct padding background
        ^ it may be connected to multiply color background in leftbattleground
        */




        Player player0 = new Player(0,"Krystian");
        Player player1 = new Player(1,"Karczynski");

        GameManager guiManager = new GameManager(primaryStage, player0, player1);

        Image image = new Image("cardImages/02a.png");
        Image image2 = new Image("cardImages/02b.png");
        Image image3 = new Image("cardImages/02c.png");
        Image image4 = new Image("cardImages/02d.png");

        //Player Cards
        CardLogic card = CardCreator.newCard("Normal Card", CardType.NormalCard, 3, 2, image);
        CardLogic card2 = CardCreator.newCard("Normal Card", CardType.NormalCard, 3, 4, image2);
        CardLogic card3 = CardCreator.newCard("Normal Card", CardType.DasDingo, 1, 2, image3);
        CardLogic card4 = CardCreator.newCard("Normal Card", CardType.DasDingo, 1, 2, image3);
        CardLogic card16 = CardCreator.newCard("Normal Card", CardType.DasDingo, 1, 2, image3);
        CardLogic card17 = CardCreator.newCard("Normal Card", CardType.DasDingo, 1, 2, image3);
        CardLogic card5 = CardCreator.newCard("Normal Card", CardType.NormalCard, 6, 8, image4);
        CardLogic card6 = CardCreator.newCard("Normal Card", CardType.NormalCard, 2, 5, image);
        CardLogic card7 = CardCreator.newCard("Normal Card", CardType.NormalCard, 4, 3, image2);
        CardLogic card8 = CardCreator.newCard("Normal Card", CardType.NormalCard, 6, 7, image);
        CardLogic card9 = CardCreator.newCard("Normal Card", CardType.NormalCard, 1, 1, image);
        CardLogic card10 = CardCreator.newCard("Normal Card", CardType.NormalCard, 5, 3, image4);
        CardLogic card11 = CardCreator.newCard("Normal Card", CardType.NormalCard, 4, 6, image2);
        CardLogic card12 = CardCreator.newCard("Normal Card", CardType.NormalCard, 5, 3, image2);
        CardLogic card13 = CardCreator.newCard("Normal Card", CardType.NormalCard, 12, 8, image4);
        CardLogic card14 = CardCreator.newCard("Normal Card", CardType.NormalCard, 10, 9, image2);
        CardLogic card15 = CardCreator.newCard("Normal Card", CardType.NormalCard, 3, 2, image4);
        //Opponent Cards
        CardLogic card18 = CardCreator.newCard("Normal Card", CardType.NormalCard, 3, 2, image4);
        CardLogic card19 = CardCreator.newCard("Normal Card", CardType.NormalCard, 5, 4, image);
        CardLogic card20 = CardCreator.newCard("Normal Card", CardType.NormalCard, 1, 2, image);
        CardLogic card21 = CardCreator.newCard("Normal Card", CardType.NormalCard, 5, 5, image2);
        CardLogic card22 = CardCreator.newCard("Normal Card", CardType.NormalCard, 10, 8, image4);
        CardLogic card23 = CardCreator.newCard("Normal Card", CardType.NormalCard, 2, 1, image2);
        CardLogic card24 = CardCreator.newCard("Normal Card", CardType.NormalCard, 4, 2, image);
        CardLogic card25 = CardCreator.newCard("Normal Card", CardType.NormalCard, 6, 6, image2);
        CardLogic card26 = CardCreator.newCard("Normal Card", CardType.NormalCard, 6, 5, image2);
        CardLogic card27 = CardCreator.newCard("Normal Card", CardType.NormalCard, 3, 2, image4);
        CardLogic card28 = CardCreator.newCard("Normal Card", CardType.DasDingo, 1, 2, image3);
        CardLogic card29 = CardCreator.newCard("Normal Card", CardType.DasDingo, 1, 2, image3);
        CardLogic card30 = CardCreator.newCard("Normal Card", CardType.DasDingo, 1, 2, image3);
        CardLogic card31 = CardCreator.newCard("Normal Card", CardType.DasDingo, 1, 2, image3);

        guiManager.addCardToPlayerDeck(card,0);
        guiManager.addCardToPlayerDeck(card2,0);
        guiManager.addCardToPlayerDeck(card3,0);
        guiManager.addCardToPlayerDeck(card4,0);
        guiManager.addCardToPlayerDeck(card5,0);
        guiManager.addCardToPlayerDeck(card6,0);
        guiManager.addCardToPlayerDeck(card7,0);
        guiManager.addCardToPlayerDeck(card8,0);
        guiManager.addCardToPlayerDeck(card9,0);
        guiManager.addCardToPlayerDeck(card10,0);
        guiManager.addCardToPlayerDeck(card11,0);
        guiManager.addCardToPlayerDeck(card12,0);
        guiManager.addCardToPlayerDeck(card13,0);
        guiManager.addCardToPlayerDeck(card14,0);
        guiManager.addCardToPlayerDeck(card15,0);
        guiManager.addCardToPlayerDeck(card16,0);
        guiManager.addCardToPlayerDeck(card17,0);
        guiManager.addCardToPlayerDeck(card18,1);
        guiManager.addCardToPlayerDeck(card19,1);
        guiManager.addCardToPlayerDeck(card20,1);
        guiManager.addCardToPlayerDeck(card21,1);
        guiManager.addCardToPlayerDeck(card22,1);
        guiManager.addCardToPlayerDeck(card23,1);
        guiManager.addCardToPlayerDeck(card24,1);
        guiManager.addCardToPlayerDeck(card25,1);
        guiManager.addCardToPlayerDeck(card26,1);
        guiManager.addCardToPlayerDeck(card27,1);
        guiManager.addCardToPlayerDeck(card28,1);
        guiManager.addCardToPlayerDeck(card29,1);
        guiManager.addCardToPlayerDeck(card30,1);
        guiManager.addCardToPlayerDeck(card31,1);

    }
}
