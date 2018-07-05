package previewSuggestedCardsPanel;

import database.DataBaseConnector;
import database.User;
import game.graphic.PlayerType;
import game.graphic.buttons.RectangleButton;
import game.graphic.buttons.SquareButton;
import game.graphic.cards.Card;
import game.graphic.panes.CardPreviewPane;
import game.graphic.panes.CardScrollingPane;
import game.logic.cards.CardLogic;
import game.variables.VGraphics;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import mainPanel.MainMenuPanel;

import java.util.ArrayList;


public class PreviewSuggestedCardsPanel {

    public PreviewSuggestedCardsPanel(Stage primaryStage, User user) {

        Pane previewSuggestedCardsPane = new Pane();
        Pane cardsBackgroundPane = new Pane();
        cardsBackgroundPane.setPrefSize(VGraphics.getInstance().getScreenWidth() * 0.6, VGraphics.getInstance().getScreenHeight() * 0.6);
        cardsBackgroundPane.setTranslateX(VGraphics.getInstance().getScreenWidth() * 0.05);
        cardsBackgroundPane.setTranslateY(VGraphics.getInstance().getScreenHeight() * 0.2);
        cardsBackgroundPane.setId("cardsBackgroundPane");

        CardPreviewPane cardPreviewPane = new CardPreviewPane();
        cardPreviewPane.setTranslateY(cardPreviewPane.getTranslateY() - VGraphics.getInstance().getScreenHeight() * 0.08);
        cardPreviewPane.setTranslateX(cardPreviewPane.getTranslateX() - VGraphics.getInstance().getScreenWidth() * 0.03);

        Scene scene = new Scene(previewSuggestedCardsPane, VGraphics.getInstance().getScreenWidth(), VGraphics.getInstance().getScreenHeight());
        CardScrollingPane cardScrollingPane = new CardScrollingPane();
        cardScrollingPane.setId("scrollPane");
        cardScrollingPane.setTranslateY(VGraphics.getInstance().getScreenHeight() * 0.25);
        scene.getStylesheets().addAll("cssFiles/previewSuggestedCardsPanel.css");

        SquareButton acceptCardButton = new SquareButton("Accept\nCard");
        acceptCardButton.setLayoutX(VGraphics.getInstance().getScreenWidth() * 0.85);
        acceptCardButton.setLayoutY(VGraphics.getInstance().getScreenHeight() * 0.65);
        SquareButton declineCardButton = new SquareButton("Decline\nCard");
        declineCardButton.setLayoutX(VGraphics.getInstance().getScreenWidth() * 0.72);
        declineCardButton.setLayoutY(VGraphics.getInstance().getScreenHeight() * 0.65);
        RectangleButton exitButton = new RectangleButton("Back to Menu");
        exitButton.setLayoutX(VGraphics.getInstance().getScreenWidth() * 0.77);
        exitButton.setLayoutY(VGraphics.getInstance().getScreenHeight() * 0.79);
        exitButton.setOnMouseClicked(e -> new MainMenuPanel(primaryStage, user));

        previewSuggestedCardsPane.getChildren().addAll(cardsBackgroundPane, cardScrollingPane, cardPreviewPane, exitButton, acceptCardButton, declineCardButton);

        ArrayList<CardLogic> cardSuggestionList = DataBaseConnector.getAllCardSuggestions();
        new PreviewSuggestedCardsController(cardPreviewPane, cardSuggestionList, cardScrollingPane, acceptCardButton, declineCardButton);

        primaryStage.close();
        primaryStage.setScene(scene);
        primaryStage.setFullScreenExitHint("");
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }
}
