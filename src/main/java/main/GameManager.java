package main;

import graphic.PlayerType;
import graphic.cards.Card;
import graphic.cards.cardPreview.CardPreview;
import graphic.GraphicManager;
import graphic.NodeIDConverter;
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
            primaryStage.close();
            networkManager.closeReceiveDataThread();
        };
        this.graphicManager.getExitButton().setOnMouseClicked(exitGameButton);


        EventHandler<MouseEvent> sendCardToFrontHandler = e -> {
            if (e.getPickResult().getIntersectedNode().getId() != null && !NodeIDConverter.isItBattleFieldID(e.getPickResult().getIntersectedNode().getId())) {
                if (main.GameManager.this.tempCard != null && main.GameManager.this.tempCard.isHighlighted()) {
                    try {
                        this.tempCard.setPadding(Insets.EMPTY);
                        main.GameManager.this.removeCardFromPlayerDeck(main.GameManager.this.tempCard, main.GameManager.this.tempCard.getTempPlayerType());
                        // To ma sie wyslac
                        MethodWrapper removeCardToSend = MethodWrapper.removeCardFromPlayer(main.GameManager.this.tempCard);
                        networkManager.getOos().writeObject(removeCardToSend);
                        //
                        main.GameManager.this.addCardToFront(main.GameManager.this.tempCard, e.getPickResult().getIntersectedNode().getId(), main.GameManager.this.tempCard.getTempPlayerType());
                        // To ma sie wyslac
                        MethodWrapper addCardToFrontToSend = MethodWrapper.addCardToFront(main.GameManager.this.tempCard, NodeIDConverter.toLineType(e.getPickResult().getIntersectedNode().getId()));
                        networkManager.getOos().writeObject(addCardToFrontToSend);
                        //
                        //main.GameManager.this.tempCard = null;
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                }
            }
        };

        EventHandler<ActionEvent> attackOnFrontHandler = e -> {
            try {
                LineType lineType = NodeIDConverter.toLineType(e.getSource().toString());
                PlayerType loserPlayerType = this.logicManager.attackFrontLine(lineType);
                this.graphicManager.getBattleFrontTextBoxGUI(lineType, loserPlayerType).setHitPointsAmount(this.logicManager.getFrontLineHitPoints(lineType, loserPlayerType));
                this.graphicManager.getPlayerHealthBox(loserPlayerType).setHPAmount(this.logicManager.getPlayer(loserPlayerType).getHitPoints());
                // To ma sie wyslac
                MethodWrapper attackOnFrontToSend = MethodWrapper.attackOnFront(lineType);
                networkManager.getOos().writeObject(attackOnFrontToSend);

            } catch (IOException e1) {
                e1.printStackTrace();
            }
        };

        graphicManager.getLeftBattleFieldGUI().setOnMouseClicked(sendCardToFrontHandler);
        graphicManager.getCenterBattleFieldGUI().setOnMouseClicked(sendCardToFrontHandler);
        graphicManager.getRightBattleFieldGUI().setOnMouseClicked(sendCardToFrontHandler);

        graphicManager.getLeftAttackButton().setOnAction(attackOnFrontHandler);
        graphicManager.getCenterAttackButton().setOnAction(attackOnFrontHandler);
        graphicManager.getRightAttackButton().setOnAction(attackOnFrontHandler);
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
        this.tempCard.setOnMouseClicked((MouseEvent e) -> {
            e.getPickResult().getIntersectedNode().setId(e.getPickResult().getIntersectedNode().getParent().getId());
        });


    }

    public void addCardToFront(Card card, String nodeID, PlayerType playerType) {
        LineType lineType = NodeIDConverter.toLineType(nodeID);
        logicManager.addCardToFront(card.getCardLogic(), lineType, playerType);
        card.getCardLogic().action(this.logicManager, lineType, playerType);
        graphicManager.addCardToFront(card, lineType, logicManager.getFrontLinePower(lineType, playerType), playerType);
        if (card.getCardLogic().isUpdateGraphics()) {
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
}
