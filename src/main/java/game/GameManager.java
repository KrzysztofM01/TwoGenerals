package game;

import game.eventHandlers.*;
import game.graphic.PlayerType;
import game.graphic.cards.Card;
import game.graphic.GraphicManager;
import game.graphic.NodeIDConverter;
import game.logic.cards.CardLogic;
import game.logic.battleFields.LineType;
import game.logic.LogicManager;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import game.network.NetworkManager;

public class GameManager {

    private Card tempCard;

    private LogicManager logicManager;
    private GraphicManager graphicManager;
    private NetworkManager networkManager;

    public GameManager(Stage primaryStage) {

        // Launch all managers
        graphicManager = new GraphicManager(primaryStage);
        networkManager = new NetworkManager(primaryStage, this);
        logicManager = new LogicManager();

        // Set up handlers for events
        graphicManager.getExitGameButton().setOnMouseClicked(new ExitGameHandler(networkManager, primaryStage));
        graphicManager.getEndTurnButton().setOnMouseClicked(new EndTurnHandler(networkManager, graphicManager, logicManager));

        SendCardToFrontHandler sendCardToFrontHandler = new SendCardToFrontHandler(this);
        for (LineType lineType : LineType.values()) {
            graphicManager.getBattleFieldGUI(lineType).setOnMouseClicked(sendCardToFrontHandler);
        }
        AttackOnFrontHandler attackOnFrontHandler = new AttackOnFrontHandler(this);
        for (LineType lineType : LineType.values()) {
            graphicManager.getAttackButton(lineType).setOnAction(attackOnFrontHandler);
        }
    }


    public void addCardToPlayerDeck(CardLogic cardLogic, PlayerType playerType) {
        // Create a new card with graphics and add it to game.graphic and game.logic managers
        Card card = new Card(cardLogic, playerType);
        graphicManager.addCardToPlayerDeck(card, playerType);
        logicManager.getPlayer(playerType).getCardList().add(cardLogic);
        // Set handlers for the card
        card.setOnMouseEntered((MouseEvent e) -> graphicManager.createCardPreview(cardLogic));
        card.setOnMouseExited((MouseEvent e) -> graphicManager.removeCardPreview());
        // This handler has to be here, as it needs to have constant access to tempCard
        card.setOnMouseClicked((MouseEvent e) -> {
            // There's already card selected, the same as picked one
            if (tempCard != null && tempCard == card) {
                card.setHighlighted(false);
                card.setViewOrder(0);
                tempCard = null;
                // There's already card selected, but a different one from picked one
            } else if (tempCard != null) {
                tempCard.setHighlighted(false);
                tempCard.setViewOrder(0);
                tempCard = card;
                card.setHighlighted(true);
                card.setViewOrder(-1);
                // No card selected
            } else {
                card.setHighlighted(true);
                card.setViewOrder(-1);
                tempCard = card;
            }
        });

    }

    public void removeCardFromPlayerDeck(Card card, PlayerType playerType) {
        // Remove card from game.graphic and game.logic managers
        graphicManager.removeCardFromPlayerDeck(card, playerType);
        logicManager.getPlayer(playerType).removeCard(card.getCardLogic());
        // Replace the on mouse handler with one that sets nodeID of card to the parent battlefield nodeID
        card.setOnMouseClicked((MouseEvent e) -> {
            if (e.getPickResult().getIntersectedNode().getId() == null) {
                e.getPickResult().getIntersectedNode().setId(e.getPickResult().getIntersectedNode().getParent().getId());
            }
        });
    }

    public void checkForVictoryCondition(PlayerType loserPlayerType) {
        if (logicManager.getPlayer(loserPlayerType).getHitPoints() == 0 && loserPlayerType == PlayerType.opponent) {
            networkManager.setYourTurn(false);
            graphicManager.showMessagePane("You have won!", true);
        } else if (logicManager.getPlayer(loserPlayerType).getHitPoints() == 0 && loserPlayerType == PlayerType.player) {
            networkManager.setYourTurn(false);
            graphicManager.showMessagePane("Your opponent has won!", true);
        }
    }

    public void addCardToFront(Card card, String nodeID, PlayerType playerType) {
        // Convert nodeID to line type
        LineType lineType = NodeIDConverter.toLineType(nodeID);
        logicManager.addCardToFront(card.getCardLogic(), lineType, playerType);
        card.getCardLogic().action(logicManager, graphicManager, lineType, playerType);
        graphicManager.addCardToFront(card, lineType, playerType);
        // In case of card acting on different battlefront, check and update all of them
        graphicManager.updateBattleFieldTextBoxes(logicManager, playerType);
        // If card is special and has to upgrade some graphics:
        if (card.getCardLogic().isUpdateGraphics()) {
            graphicManager.getPlayerHealthBox(PlayerType.player).setHPAmount(logicManager.getPlayer(PlayerType.player).getHitPoints());
            graphicManager.getPlayerHealthBox(PlayerType.opponent).setHPAmount(logicManager.getPlayer(PlayerType.opponent).getHitPoints());
            for (Card cardGraphic : graphicManager.getCardList()) {
                graphicManager.updateCardGraphics(cardGraphic);
            }
        }
        // TODO: Apparently setting ID here doesn't work, the nodeID get's somehow nulled later on, worth investigating
        card.setId(nodeID);
        card.setHighlighted(false);
    }

    public void removeCardFromFront(Card card, String nodeID, PlayerType playerType) {
        LineType lineType = NodeIDConverter.toLineType(nodeID);
        logicManager.removeCardFromFront(card.getCardLogic(), lineType, playerType);
        graphicManager.removeCardFromFront(card, lineType, playerType);
        graphicManager.updateBattleFieldTextBoxes(logicManager, playerType);
    }

    public LogicManager getLogicManager() {
        return logicManager;
    }

    public GraphicManager getGraphicManager() {
        return graphicManager;
    }

    public NetworkManager getNetworkManager() {
        return networkManager;
    }

    public Card getTempCard() {
        return tempCard;
    }

    public void setTempCard(Card tempCard) {
        this.tempCard = tempCard;
    }
}
