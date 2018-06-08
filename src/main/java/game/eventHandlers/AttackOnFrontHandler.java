package game.eventHandlers;

import game.graphic.NodeIDConverter;
import game.graphic.PlayerType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import game.logic.battleFields.LineType;
import game.GameManager;

import game.network.MethodWrapper;


import java.io.IOException;

public class AttackOnFrontHandler implements EventHandler<ActionEvent> {

    private GameManager gameManager;

    public AttackOnFrontHandler(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public void handle(ActionEvent e) {
        // Check if it's your turn
        if (gameManager.getNetworkManager().isYourTurn()) {
            // Convert picked nodeID to front line type
            LineType lineType = NodeIDConverter.toLineType(e.getSource().toString());
            // Check if attack button was already used or not
            if (!gameManager.getGraphicManager().getAttackButton(lineType).isUsedInThisTurn()) {
                try {
                    // Set the button on 'used', show the attack graphics and update game.logic & game.graphic managers
                    gameManager.getGraphicManager().getAttackButton(lineType).setUsedInThisTurn(true);
                    gameManager.getGraphicManager().showAttackOnFrontGraphics(lineType);
                    PlayerType loserPlayerType = this.gameManager.getLogicManager().attackFrontLine(lineType);
                    this.gameManager.getGraphicManager().getBattleFrontTextBoxGUI(lineType, loserPlayerType).setHitPointsAmount(this.gameManager.getLogicManager().getFrontLineHitPoints(lineType, loserPlayerType));
                    this.gameManager.getGraphicManager().getPlayerHealthBox(loserPlayerType).setHPAmount(this.gameManager.getLogicManager().getPlayer(loserPlayerType).getHitPoints());
                    // Send the data to opponent
                    MethodWrapper attackOnFrontToSend = MethodWrapper.attackOnFront(lineType);
                    gameManager.getNetworkManager().getOos().writeObject(attackOnFrontToSend);
                    gameManager.checkForVictoryCondition(loserPlayerType);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
