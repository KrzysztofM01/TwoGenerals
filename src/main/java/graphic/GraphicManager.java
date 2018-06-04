package graphic;

import graphic.battleFields.BattleFieldGUI;
import graphic.battleFields.BattleFrontTextBoxGUI;
import graphic.buttons.AttackButton;
import graphic.buttons.EndTurnButton;
import graphic.buttons.ExitButton;
import graphic.cards.Card;
import graphic.cards.cardPreview.CardPreview;
import graphic.panes.*;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.Node;
import logic.LogicManager;
import logic.battleFields.LineType;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import logic.cards.CardLogic;
import variables.VariablesGraphics;
import variables.VariablesLogic;

import java.util.ArrayList;

public class GraphicManager {

    private static final String STYLESHEET = "twoGeneralsGame.css";

    private CardPreview tempCardPreview;
    private Scene scene;
    private Pane gamePane = new Pane();

    private FlowPane playerCards = new FlowPane();
    private FlowPane opponentCards = new FlowPane();

    private CardPreviewPane cardPreviewPane = new CardPreviewPane();

    private BattleFieldGUI leftBattleFieldGUI = new BattleFieldGUI(LineType.left);
    private BattleFieldGUI centerBattleFieldGUI = new BattleFieldGUI(LineType.center);
    private BattleFieldGUI rightBattleFieldGUI = new BattleFieldGUI(LineType.right);

    private AttackButton leftAttackButton = new AttackButton(LineType.left);
    private AttackButton centerAttackButton = new AttackButton(LineType.center);
    private AttackButton rightAttackButton = new AttackButton(LineType.right);

    private PlayerHealthBox playerHealthBox = new PlayerHealthBox(PlayerType.player);
    private PlayerHealthBox opponentHealthBox = new PlayerHealthBox(PlayerType.opponent);

    private ActionPointsBox actionPointsBox = new ActionPointsBox();

    private ExitButton exitButton = new ExitButton();
    private EndTurnButton endTurnButton = new EndTurnButton();

    private MessagePane messagePane = new MessagePane();
    private AttackActionPane attackActionPane = new AttackActionPane();

    private ArrayList<Card> cardList = new ArrayList<>();

    private Stage primaryStage;


    public GraphicManager(Stage primaryStage) {

        this.primaryStage = primaryStage;

        // Create scene and set it's style
        scene = new Scene(gamePane, VariablesGraphics.getInstance().getScreenWidth(), VariablesGraphics.getInstance().getScreenHeight());
        scene.getStylesheets().addAll(STYLESHEET);

        // Set layout for player and opponent card panes
        playerCards.setHgap(-VariablesGraphics.getInstance().getCardWidth() * 0.6);
        playerCards.setAlignment(Pos.BOTTOM_CENTER);
        playerCards.setPrefSize(VariablesGraphics.getInstance().getBattleFieldWidth() * 3 + VariablesGraphics.getInstance().getBattleFieldBreakWidth() * 3, VariablesGraphics.getInstance().getCardHeight() + VariablesGraphics.getInstance().getCardPadding() * 2);
        playerCards.setLayoutY(VariablesGraphics.getInstance().getPlayerCardPositionY());

        opponentCards.setLayoutY(VariablesGraphics.getInstance().getScreenHeight() * 0.01);
        opponentCards.setHgap(-VariablesGraphics.getInstance().getCardWidth() * 0.83);
        opponentCards.setAlignment(Pos.TOP_CENTER);
        opponentCards.setPrefSize(VariablesGraphics.getInstance().getBattleFieldWidth() * 3 + VariablesGraphics.getInstance().getBattleFieldBreakWidth() * 3, VariablesGraphics.getInstance().getCardHeight() + VariablesGraphics.getInstance().getCardPadding() * 2);

        gamePane.getChildren().addAll(playerCards, opponentCards, leftBattleFieldGUI,
                centerBattleFieldGUI, rightBattleFieldGUI, leftAttackButton, centerAttackButton,
                rightAttackButton, cardPreviewPane, playerHealthBox, opponentHealthBox,
                actionPointsBox, exitButton, endTurnButton);
    }


    public void loadGameScene(boolean isYourTurn) {
        // Loads the game scene to the primary stage, ensures that the game is full screen
        if (!isYourTurn) {
            endTurnButton.setYourTurnText(false);
        }
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");
        primaryStage.close();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void addCardToPlayerDeck(Card card, PlayerType playerType) {
        // Adds card to list of all cards in game, needed when updating graphics and iterating through every card
        // also adds cards to panes
        cardList.add(card);
        getCardFlowPane(playerType).getChildren().add(card);
    }

    public void removeCardFromPlayerDeck(Card card, PlayerType playerType) {
        // Removes card from list of all cards in game, removes it from panes and removes padding
        cardList.remove(card);
        card.setPadding(Insets.EMPTY);
        getCardFlowPane(playerType).getChildren().remove(card);
    }

    public void addCardToFront(Card card, LineType lineType, PlayerType playerType) {
        // Adds card to list of all cards in game, adds it to the list of cards in certain battlefield
        // updates played cards graphics, sets the highlight off and adds it to the battlefront nodeList
        cardList.add(card);
        getBattleFieldGUI(lineType).getCardsList(playerType).add(card);
        updateCardGraphics(card);
        card.setHighlighted(false);
        getBattleFrontNodeList(lineType, playerType).add(card);
    }

    private void updateBattleFrontBoxPower(LineType lineType, PlayerType playerType, int power) {
        getBattleFrontTextBoxGUI(lineType, playerType).setPowerAmount(power);
    }

    public void updateCardGraphics(Card card) {
        // Updates the power and cost text of card
        card.getCardTextPower().setText(card.getCardLogic().getCurrentPower());
        card.getCardTextCost().setText(card.getCardLogic().getCost());
    }

    public void removeCardFromFront(Card card, LineType lineType, PlayerType playerType) {
        cardList.remove(card);
        getBattleFrontNodeList(lineType, playerType).remove(card);
    }

    @SuppressWarnings("Duplicates")
    // Why the hell does IntelliJ points it as duplicate???
    public AttackButton getAttackButton(LineType lineType) {
        switch (lineType) {
            case left:
                return leftAttackButton;
            case center:
                return centerAttackButton;
            case right:
                return rightAttackButton;
        }
        return null;
    }

    @SuppressWarnings("Duplicates")
    // Why the hell does IntelliJ points it as duplicate???
    public BattleFieldGUI getBattleFieldGUI(LineType lineType) {
        switch (lineType) {
            case left:
                return leftBattleFieldGUI;
            case center:
                return centerBattleFieldGUI;
            case right:
                return rightBattleFieldGUI;
        }
        return null;
    }

    private FlowPane getCardFlowPane(PlayerType playerType) {
        if (playerType == PlayerType.player) {
            return playerCards;
        } else {
            return opponentCards;
        }
    }

    public ExitButton getExitButton() {
        return exitButton;
    }

    public EndTurnButton getEndTurnButton() {
        return endTurnButton;
    }

    public ArrayList<Card> getCardList() {
        return cardList;
    }

    public void setActionPointsText(int amount) {
        actionPointsBox.setActionPointsAmount(amount);
    }

    public BattleFrontTextBoxGUI getBattleFrontTextBoxGUI(LineType lineType, PlayerType playerType) {
        if (playerType == PlayerType.player) {
            switch (lineType) {
                case left:
                    return leftBattleFieldGUI.getPlayerTextBox();
                case center:
                    return centerBattleFieldGUI.getPlayerTextBox();
                case right:
                    return rightBattleFieldGUI.getPlayerTextBox();
            }
        } else {
            switch (lineType) {
                case left:
                    return leftBattleFieldGUI.getOpponentTextBox();
                case center:
                    return centerBattleFieldGUI.getOpponentTextBox();
                case right:
                    return rightBattleFieldGUI.getOpponentTextBox();
            }
        }
        return null;
    }


    private ObservableList<Node> getBattleFrontNodeList(LineType lineType, PlayerType playerType) {
        return getBattleFieldGUI(lineType).getCardsNodeList(playerType);
    }

    public ArrayList<Card> getCardsFromBattleFront(LineType lineType, PlayerType playerType) {
        return getBattleFieldGUI(lineType).getCardsList(playerType);
    }

    public PlayerHealthBox getPlayerHealthBox(PlayerType playerType) {
        if (playerType == PlayerType.player) {
            return playerHealthBox;
        } else {
            return opponentHealthBox;
        }
    }

    public void createCardPreview(CardLogic cardLogic) {
        CardPreview cardPreview = new CardPreview(cardLogic);
        cardPreviewPane.getChildren().add(cardPreview);
        tempCardPreview = cardPreview;
    }

    public void removeCardPreview() {
        cardPreviewPane.getChildren().remove(tempCardPreview);
    }

    private Task<Void> sleeperTask() {
        // A method that returns a task that waits X seconds, specified in logic variables,
        // needed when something needs to be shown for a moment and then removed
        return new Task<>() {
            @Override
            protected Void call() {
                try {
                    Thread.sleep(VariablesLogic.getInstance().getMessageShowUpTimeInMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
    }

    public void showMessagePane(String messageText, boolean isPermanent) {
        // Sets the text of message pane
        messagePane.setMessageText(messageText);
        // Ensures that there is but one messagePane in the game pane
        if (!gamePane.getChildren().contains(messagePane)) {
            gamePane.getChildren().add(messagePane);
        }
        // Should the pop-up message be non-permanent, launch the sleeper task that will remove the message
        // after it's finished
        if (!isPermanent) {
            Task<Void> sleeper = sleeperTask();
            sleeper.setOnSucceeded(e -> gamePane.getChildren().remove(messagePane));
            new Thread(sleeper).start();
        }
    }

    public void showAttackOnFrontGraphics(LineType lineType) {
        // Ensures that there is but one attackActionPane in the game pane
        // TODO: Make an attackActionPane for each of the battle field and get rid of this
        if (!gamePane.getChildren().contains(attackActionPane)) {
            gamePane.getChildren().add(attackActionPane);
        }
        // Moves the pane to the center of battle field it should be displayed on
        attackActionPane.setPosition(lineType);
        // Launches the sleeper task, upon success it will remove the attackActionPane from game pane
        Task<Void> sleeper = sleeperTask();
        sleeper.setOnSucceeded(e -> gamePane.getChildren().remove(attackActionPane));
        new Thread(sleeper).start();

    }

    public void endTurnGraphics() {
        // Changes end turn button text, shows up pop-up message for player
        endTurnButton.setYourTurnText(false);
        showMessagePane("Opponent Turn", false);
        // Resets the action points and attack buttons of player
        setActionPointsText(VariablesLogic.getInstance().getPlayerActionPoints());
        getAttackButton(LineType.left).setUsedInThisTurn(false);
        getAttackButton(LineType.center).setUsedInThisTurn(false);
        getAttackButton(LineType.right).setUsedInThisTurn(false);
    }

    public void updateBattleFieldTextBoxes(LogicManager logicManager, PlayerType playerType) {
        // Goes through all of battle front boxes and updates their numbers
        for (LineType lineTypeForAll : LineType.values()) {
            updateBattleFrontBoxPower(lineTypeForAll, playerType, logicManager.getFrontLinePower(lineTypeForAll, playerType));
        }
    }
}