package previewCardsPanel;

import database.DataBaseConnector;
import database.User;
import game.graphic.buttons.RectangleButton;
import game.graphic.buttons.SquareButton;
import game.graphic.panes.CardPreviewPane;
import game.variables.VGraphics;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import mainPanel.MainMenuPanel;


public class PreviewCardsPanel {



    public PreviewCardsPanel(Stage primaryStage, User user) {

        // To remove
        // DataBaseConnector.startSessionFactory();
        //

        FlowPane selectedPlayerCards = new FlowPane();
        selectedPlayerCards.setHgap(-VGraphics.getInstance().getCardWidth() * 0.4);
        selectedPlayerCards.setAlignment(Pos.BOTTOM_CENTER);
        selectedPlayerCards.setPrefSize(VGraphics.getInstance().getBattleFieldWidth() * 3 + VGraphics.getInstance().getBattleFieldBreakWidth() * 3, VGraphics.getInstance().getCardHeight() + VGraphics.getInstance().getCardPadding() * 2);
        selectedPlayerCards.setLayoutY(VGraphics.getInstance().getPlayerCardPositionY() - VGraphics.getInstance().getScreenHeight() *0.1);

        Pane previewCardsPane = new Pane();
        Pane cardsBackgroundPane = new Pane();
        cardsBackgroundPane.setPrefSize(VGraphics.getInstance().getScreenWidth() * 0.6, VGraphics.getInstance().getScreenHeight() * 0.6);
        cardsBackgroundPane.setTranslateX(VGraphics.getInstance().getScreenWidth() * 0.05);
        cardsBackgroundPane.setTranslateY(VGraphics.getInstance().getScreenHeight() * 0.05);
        cardsBackgroundPane.setId("cardsBackgroundPane");

        CardPreviewPane cardPreviewPane = new CardPreviewPane();
        cardPreviewPane.setTranslateY(cardPreviewPane.getTranslateY()-VGraphics.getInstance().getScreenHeight()*0.1);

        Scene scene = new Scene(previewCardsPane, VGraphics.getInstance().getScreenWidth(), VGraphics.getInstance().getScreenHeight());

        PreviewCardsController previewCardsController = new PreviewCardsController(cardPreviewPane, selectedPlayerCards, user.getCardDeck());
        previewCardsController.getCardScrollingPane().setId("scrollPane");
        scene.getStylesheets().addAll("cssFiles/previewCardsPanel.css");

        SquareButton saveDeckButton = new SquareButton("Save\nDeck");
        saveDeckButton.setLayoutX(VGraphics.getInstance().getScreenWidth()*0.815);
        saveDeckButton.setLayoutY(VGraphics.getInstance().getScreenHeight()*0.65);
        saveDeckButton.setOnMouseClicked(e -> {
            user.setCardDeck(previewCardsController.getPlayerCardsCopy());
            DataBaseConnector.saveCardList(user);
        });
        RectangleButton exitButton = new RectangleButton("Back to Menu");
        exitButton.setLayoutX(VGraphics.getInstance().getScreenWidth() * 0.8);
        exitButton.setLayoutY(VGraphics.getInstance().getScreenHeight() * 0.79);
        exitButton.setOnMouseClicked(e -> new MainMenuPanel(primaryStage, user));

        previewCardsPane.getChildren().addAll(cardsBackgroundPane, previewCardsController.getCardScrollingPane(), cardPreviewPane, selectedPlayerCards, exitButton, saveDeckButton);


        primaryStage.close();
        primaryStage.setScene(scene);
        primaryStage.setFullScreenExitHint("");
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }
}
