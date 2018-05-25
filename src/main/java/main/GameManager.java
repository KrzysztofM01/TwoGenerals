package main;

import graphic.PlayerType;
import graphic.cards.Card;
import graphic.cards.cardPreview.CardPreview;
import graphic.GraphicManager;
import graphic.NodeIDConverter;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import logic.cards.CardLogic;
import logic.battleFields.LineType;
import logic.LogicManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import network.MethodWrapper;
import network.NetworkManager;
import variables.VariablesLogic;

import java.io.IOException;

public class GameManager {

    private Card tempCard;

    private LogicManager logicManager;
    private GraphicManager graphicManager;
    private NetworkManager networkManager;

    public GameManager(Stage primaryStage) {

        // Launch the graphic and logic managers
        this.graphicManager = new GraphicManager(primaryStage);
        this.networkManager = new NetworkManager(primaryStage, this);
        this.logicManager = new LogicManager();
        //


        // Handler must be in main manager
        EventHandler<MouseEvent> exitGameButton = e -> {
            try {
                networkManager.getOos().writeObject(MethodWrapper.stopGettingData());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            primaryStage.close();
            networkManager.closeReceiveDataThread();
        };
        this.graphicManager.getExitButton().setOnMouseClicked(exitGameButton);

        EventHandler<MouseEvent> endTurnButton = e -> {
            if (networkManager.isYourTurn()){
                try {
                    networkManager.setYourTurn(false);
                    networkManager.getOos().writeObject(MethodWrapper.endTurn());
                    graphicManager.getEndTurnButton().isYourTurnText(false);
                    graphicManager.showMessagePane("Opponent Turn", false);
                    logicManager.getPlayer(PlayerType.player).setActionPoints(VariablesLogic.playerActionPoints);
                    graphicManager.setActionPointsText(VariablesLogic.playerActionPoints);
                    graphicManager.getAttackButton(LineType.left).setUsedInThisTurn(false);
                    graphicManager.getAttackButton(LineType.center).setUsedInThisTurn(false);
                    graphicManager.getAttackButton(LineType.right).setUsedInThisTurn(false);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        };
        this.graphicManager.getEndTurnButton().setOnMouseClicked(endTurnButton);

        EventHandler<MouseEvent> sendCardToFrontHandler = e -> {
            if (networkManager.isYourTurn()){
                String nodeID = e.getPickResult().getIntersectedNode().getId();
                if (nodeID != null && !NodeIDConverter.isItBattleFieldID(nodeID)) {
                    if (main.GameManager.this.tempCard != null && main.GameManager.this.tempCard.isHighlighted()) {
                        if (logicManager.getPlayer(PlayerType.player).getActionPoints() >= this.tempCard.getCardLogic().getCost()){
                            try {
                                int actionPointsAmount = logicManager.getPlayer(PlayerType.player).getActionPoints() - this.tempCard.getCardLogic().getCost();
                                logicManager.getPlayer(PlayerType.player).setActionPoints(actionPointsAmount);
                                graphicManager.setActionPointsText(actionPointsAmount);
                                main.GameManager.this.removeCardFromPlayerDeck(main.GameManager.this.tempCard, main.GameManager.this.tempCard.getTempPlayerType());
                                // To ma sie wyslac
                                MethodWrapper removeCardToSend = MethodWrapper.removeCardFromPlayer(main.GameManager.this.tempCard);
                                networkManager.getOos().writeObject(removeCardToSend);
                                //
                                main.GameManager.this.addCardToFront(main.GameManager.this.tempCard, nodeID, main.GameManager.this.tempCard.getTempPlayerType());
                                // To ma sie wyslac
                                MethodWrapper addCardToFrontToSend = MethodWrapper.addCardToFront(main.GameManager.this.tempCard, NodeIDConverter.toLineType(nodeID));
                                networkManager.getOos().writeObject(addCardToFrontToSend);
                                //
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                }
            }
        };

        EventHandler<ActionEvent> attackOnFrontHandler = e -> {
            if (networkManager.isYourTurn()){
                LineType lineType = NodeIDConverter.toLineType(e.getSource().toString());
                if (!graphicManager.getAttackButton(lineType).isUsedInThisTurn()){
                    graphicManager.getAttackButton(lineType).setUsedInThisTurn(true);
                    graphicManager.showAttackOnFrontGraphics(lineType);
                    try {
                        PlayerType loserPlayerType = this.logicManager.attackFrontLine(lineType);
                        this.graphicManager.getBattleFrontTextBoxGUI(lineType, loserPlayerType).setHitPointsAmount(this.logicManager.getFrontLineHitPoints(lineType, loserPlayerType));
                        this.graphicManager.getPlayerHealthBox(loserPlayerType).setHPAmount(this.logicManager.getPlayer(loserPlayerType).getHitPoints());
                        // To ma sie wyslac
                        MethodWrapper attackOnFrontToSend = MethodWrapper.attackOnFront(lineType);
                        networkManager.getOos().writeObject(attackOnFrontToSend);
                        checkForVictoryCondition(loserPlayerType);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        };

        graphicManager.getLeftBattleFieldGUI().setOnMouseClicked(sendCardToFrontHandler);
        graphicManager.getCenterBattleFieldGUI().setOnMouseClicked(sendCardToFrontHandler);
        graphicManager.getRightBattleFieldGUI().setOnMouseClicked(sendCardToFrontHandler);

        graphicManager.getAttackButton(LineType.left).setOnAction(attackOnFrontHandler);
        graphicManager.getAttackButton(LineType.center).setOnAction(attackOnFrontHandler);
        graphicManager.getAttackButton(LineType.right).setOnAction(attackOnFrontHandler);
    }


    public void addCardToPlayerDeck(CardLogic cardLogic, PlayerType playerType) {
        Card card = new Card(cardLogic);
        graphicManager.addCardToPlayerDeck(card, playerType);
        logicManager.getPlayer(playerType).getCardList().add(cardLogic);

        card.setOnMouseEntered((MouseEvent e) -> {
            this.graphicManager.createCardPreview(cardLogic);
        });
        card.setOnMouseExited((MouseEvent e) -> {
            this.graphicManager.removeCardPreview();
        });

        card.setOnMouseClicked((MouseEvent e) -> {
            if (this.tempCard != null && card.isHighlighted()) {
                card.setHighlighted(false);
                card.setViewOrder(0);
            } else if (this.tempCard != null && !card.isHighlighted()) {
                this.tempCard.setHighlighted(false);
                this.tempCard.setViewOrder(0);
                this.tempCard = card;
                this.tempCard.setTempPlayerType(playerType);
                card.setHighlighted(true);
                card.setViewOrder(-1);
            } else {
                card.setHighlighted(true);
                card.setViewOrder(-1);
                this.tempCard = card;
                this.tempCard.setTempPlayerType(playerType);
            }
        });
    }

    public void removeCardFromPlayerDeck(Card card, PlayerType playerType) {
        graphicManager.removeCardFromPlayerDeck(card, playerType);
        logicManager.getPlayer(playerType).removeCard(card.getCardLogic());
        card.setOnMouseClicked((MouseEvent e) -> {
            if (e.getPickResult().getIntersectedNode().getId() == null) {
                e.getPickResult().getIntersectedNode().setId(e.getPickResult().getIntersectedNode().getParent().getId());
            }
        });


    }

    public void checkForVictoryCondition (PlayerType loserPlayerType){
        if (this.logicManager.getPlayer(loserPlayerType).getHitPoints() == 0 && loserPlayerType == PlayerType.opponent) {
            networkManager.setYourTurn(false);
            graphicManager.showMessagePane("You have won!", true);
        } else if (this.logicManager.getPlayer(loserPlayerType).getHitPoints() == 0 && loserPlayerType == PlayerType.player) {
            networkManager.setYourTurn(false);
            graphicManager.showMessagePane("Your opponent has won!", true);
        }
    }

    public void addCardToFront(Card card, String nodeID, PlayerType playerType) {
        LineType lineType = NodeIDConverter.toLineType(nodeID);
        logicManager.addCardToFront(card.getCardLogic(), lineType, playerType);
        card.getCardLogic().action(this.logicManager, lineType, playerType);
        graphicManager.addCardToFront(card, lineType, logicManager.getFrontLinePower(lineType, playerType), playerType);
        for (LineType lineTypeForAll: LineType.values()){
            graphicManager.updateBattleFrontBoxPower(lineTypeForAll, playerType, logicManager.getFrontLinePower(lineTypeForAll, playerType) );
        }

        if (card.getCardLogic().isUpdateGraphics()) {
            graphicManager.getPlayerHealthBox(PlayerType.player).setHPAmount(logicManager.getPlayer(PlayerType.player).getHitPoints());
            graphicManager.getPlayerHealthBox(PlayerType.opponent).setHPAmount(logicManager.getPlayer(PlayerType.opponent).getHitPoints());
            for (Card cardGraphic : this.graphicManager.getCardList()) {
                this.graphicManager.updateGraphics(cardGraphic);
            }
        }
        card.setId(nodeID);
        card.setHighlighted(false);
    }

    public void removeCardFromFront(Card card, String nodeID, PlayerType playerType) {
        LineType lineType = NodeIDConverter.toLineType(nodeID);
        logicManager.removeCardFromFront(card.getCardLogic(), lineType, playerType);
        graphicManager.removeCardFromFront(card, lineType, logicManager.getFrontLinePower(lineType, playerType), playerType);
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
}
