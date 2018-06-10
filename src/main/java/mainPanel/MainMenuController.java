package mainPanel;

import game.GameManager;
import game.graphic.PlayerType;
import game.logic.cards.CardLogic;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import previewCardsPanel.PreviewCardsPanel;

import java.util.ArrayList;

public class MainMenuController {

    @FXML
    private Pane exitGamePane;

    @FXML
    private Pane startGamePane;

    @FXML
    private Pane previewCardsPane;

    @FXML
    private Pane suggestCardsPane;

    private Stage primaryStage;

    private ArrayList<CardLogic> cardDeck;

    @FXML
    protected void exitGame(){
        Stage stage = (Stage) exitGamePane.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void startGame(){
        GameManager gameManager = new GameManager(primaryStage);
        for (CardLogic cardLogic: cardDeck) {
            gameManager.addCardToPlayerDeck(cardLogic, PlayerType.player);
        }
    }

    @FXML
    protected void previewCards(){
        PreviewCardsPanel previewCardsPanel = new PreviewCardsPanel(primaryStage);
    }

    @FXML
    protected void suggestCards(){
        System.out.println("Suggest Cards!");
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setCardDeck(ArrayList<CardLogic> cardDeck) {
        this.cardDeck = cardDeck;
    }
}
