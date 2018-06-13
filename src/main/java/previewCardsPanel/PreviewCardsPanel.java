package previewCardsPanel;

import database.DataBaseConnector;
import database.User;
import game.graphic.buttons.EndTurnButton;
import game.graphic.buttons.ExitButton;
import game.graphic.panes.CardPreviewPane;
import game.variables.VGraphics;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
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

        InfrastructurePane infrastructurePane = new InfrastructurePane();
        ScrollPane cardScrollPane = infrastructurePane.infrastructurePane(cardPreviewPane, selectedPlayerCards, user.getCardDeck());
        cardScrollPane.setId("scrollPane");
        scene.getStylesheets().addAll("cssFiles/previewCardsPanel.css");

        EndTurnButton endTurnButton = new EndTurnButton(false);
        endTurnButton.setOnMouseClicked(e -> {
            user.setCardDeck(infrastructurePane.getPlayerCardsCopy());
            DataBaseConnector.saveCardList(user);
        });
        ExitButton exitButton = new ExitButton(false);
        exitButton.setOnMouseClicked(e -> new MainMenuPanel(primaryStage, user));

        previewCardsPane.getChildren().addAll(cardsBackgroundPane, cardScrollPane, cardPreviewPane, selectedPlayerCards, exitButton, endTurnButton);


        primaryStage.close();
        primaryStage.setScene(scene);
        primaryStage.setFullScreenExitHint("");
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }
}
