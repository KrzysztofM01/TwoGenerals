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
        CardLogic card = CardCreator.newCard("Normal Card", CardType.NormalCard, 3, 2, image);
        CardLogic card2 = CardCreator.newCard("Normal Card", CardType.NormalCard, 3, 2, image2);
        CardLogic card3 = CardCreator.newCard("Normal Card", CardType.DasDingo, 1, 2, image3);
        CardLogic card4 = CardCreator.newCard("Normal Card", CardType.DasDingo, 1, 2, image3);
        CardLogic card16 = CardCreator.newCard("Normal Card", CardType.DasDingo, 1, 2, image3);
        CardLogic card17 = CardCreator.newCard("Normal Card", CardType.DasDingo, 1, 2, image3);
        CardLogic card5 = CardCreator.newCard("Normal Card", CardType.NormalCard, 3, 2, image4);
        CardLogic card6 = CardCreator.newCard("Normal Card", CardType.NormalCard, 3, 2, image);
        CardLogic card7 = CardCreator.newCard("Normal Card", CardType.NormalCard, 3, 2, image2);
        CardLogic card8 = CardCreator.newCard("Normal Card", CardType.NormalCard, 3, 2, image);
        CardLogic card9 = CardCreator.newCard("Normal Card", CardType.NormalCard, 3, 2, image);
        CardLogic card10 = CardCreator.newCard("Normal Card", CardType.NormalCard, 3, 2, image4);
        CardLogic card11 = CardCreator.newCard("Normal Card", CardType.NormalCard, 3, 2, image2);
        CardLogic card12 = CardCreator.newCard("Normal Card", CardType.NormalCard, 3, 2, image2);
        CardLogic card13 = CardCreator.newCard("Normal Card", CardType.NormalCard, 3, 2, image4);
        CardLogic card14 = CardCreator.newCard("Normal Card", CardType.NormalCard, 3, 2, image2);
        CardLogic card15 = CardCreator.newCard("Normal Card", CardType.NormalCard, 3, 2, image4);

        guiManager.addCardToPlayerDeck(card);
        guiManager.addCardToPlayerDeck(card2);
        guiManager.addCardToPlayerDeck(card3);
        guiManager.addCardToPlayerDeck(card4);
        guiManager.addCardToPlayerDeck(card5);
        guiManager.addCardToPlayerDeck(card6);
        guiManager.addCardToPlayerDeck(card7);
        guiManager.addCardToPlayerDeck(card8);
        guiManager.addCardToPlayerDeck(card9);
        guiManager.addCardToPlayerDeck(card10);
        guiManager.addCardToPlayerDeck(card11);
        guiManager.addCardToPlayerDeck(card12);
        guiManager.addCardToPlayerDeck(card13);
        guiManager.addCardToPlayerDeck(card14);
        guiManager.addCardToPlayerDeck(card15);
        guiManager.addCardToPlayerDeck(card16);
        guiManager.addCardToPlayerDeck(card17);

    }
}
