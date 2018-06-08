package game.eventHandlers;


import game.graphic.NodeIDConverter;
import game.graphic.PlayerType;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import game.GameManager;
import game.network.MethodWrapper;

import java.io.IOException;

public class SendCardToFrontHandler implements EventHandler<MouseEvent> {

    private GameManager gameManager;

    public SendCardToFrontHandler(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public void handle(MouseEvent e) {
        if (gameManager.getNetworkManager().isYourTurn()) {
            // Puts the pick result into String and later checks if it was battlefield
            String nodeID = e.getPickResult().getIntersectedNode().getId();
            if (nodeID != null && !NodeIDConverter.isItBattleFieldID(nodeID)) {
                if (gameManager.getTempCard() != null) {
                    if (gameManager.getLogicManager().getPlayer(PlayerType.player).getActionPoints() >= gameManager.getTempCard().getCardLogic().getCost()) {
                        try {
                            // Set proper action points amount
                            int actionPointsAmount = gameManager.getLogicManager().getPlayer(PlayerType.player).getActionPoints() - gameManager.getTempCard().getCardLogic().getCost();
                            gameManager.getLogicManager().getPlayer(PlayerType.player).setActionPoints(actionPointsAmount);
                            gameManager.getGraphicManager().setActionPointsText(actionPointsAmount);
                            // Remove card from player and send the proper data through game.network
                            gameManager.removeCardFromPlayerDeck(gameManager.getTempCard(), gameManager.getTempCard().getOwnerOfCard());
                            MethodWrapper removeCardToSend = MethodWrapper.removeCardFromPlayer(gameManager.getTempCard());
                            gameManager.getNetworkManager().getOos().writeObject(removeCardToSend);
                            // Add card to front and send the proper data through game.network
                            gameManager.addCardToFront(gameManager.getTempCard(), nodeID, gameManager.getTempCard().getOwnerOfCard());
                            MethodWrapper addCardToFrontToSend = MethodWrapper.addCardToFront(gameManager.getTempCard(), NodeIDConverter.toLineType(nodeID));
                            gameManager.getNetworkManager().getOos().writeObject(addCardToFrontToSend);
                            // Null the selected card in game manager
                            gameManager.setTempCard(null);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }
        }
    }

}
