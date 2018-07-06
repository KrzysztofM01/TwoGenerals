package menuPanel;

import database.Entities.User;
import game.GameManager;
import game.graphic.PlayerType;
import game.logic.cards.CardLogic;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import previewCardsPanel.PreviewCardsPanel;
import previewSuggestedCardsPanel.PreviewSuggestedCardsPanel;
import suggestCardsPanel.SuggestCardPanel;

public class MenuController {

    @FXML
    private Pane exitGamePane;

    @FXML
    private Text suggestCardsPaneText;

    private Stage primaryStage;
    private User user;

    @FXML
    protected void exitGame() {
        Stage stage = (Stage) exitGamePane.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void startGame() {
        GameManager gameManager = new GameManager(primaryStage);
        for (CardLogic cardLogic : user.getCardDeck()) {
            gameManager.addCardToPlayerDeck(cardLogic, PlayerType.player);
        }
    }

    @FXML
    protected void previewCards() {
        new PreviewCardsPanel(primaryStage, user);
    }

    @FXML
    protected void suggestCards() {
        if (user.isHasAdmin()) {
            new PreviewSuggestedCardsPanel(primaryStage, user);
        } else {
            new SuggestCardPanel(primaryStage, user);
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setUser(User user) {
        this.user = user;
        if (user.isHasAdmin()) {
            suggestCardsPaneText.setText("Preview\nSuggestions");
        }
    }
}
