package game.network;

import game.graphic.PlayerType;
import game.graphic.cards.Card;
import javafx.scene.input.MouseEvent;
import game.logic.battleFields.LineType;
import game.GameManager;

import java.io.Serializable;

public class MethodWrapper implements Serializable {

    private Card card;
    private LineType lineType;
    private MethodType methodType;

    private MethodWrapper() {
    }

    public static MethodWrapper addCardToFront(Card card, LineType lineType) {
        MethodWrapper methodWrapper = new MethodWrapper();
        methodWrapper.card = card;
        methodWrapper.lineType = lineType;
        methodWrapper.methodType = MethodType.addCardToFront;
        return methodWrapper;
    }

    public static MethodWrapper attackOnFront(LineType lineType) {
        MethodWrapper methodWrapper = new MethodWrapper();
        methodWrapper.lineType = lineType;
        methodWrapper.methodType = MethodType.attackOnFront;
        return methodWrapper;
    }

    public static MethodWrapper removeCardFromPlayer(Card card) {
        MethodWrapper methodWrapper = new MethodWrapper();
        methodWrapper.card = card;
        methodWrapper.methodType = MethodType.removeCardFromPlayer;
        return methodWrapper;
    }

    public static MethodWrapper addCardToPlayer(Card card) {
        MethodWrapper methodWrapper = new MethodWrapper();
        methodWrapper.card = card;
        methodWrapper.methodType = MethodType.addCardToPlayer;
        return methodWrapper;
    }

    public static MethodWrapper stopGettingData() {
        MethodWrapper methodWrapper = new MethodWrapper();
        methodWrapper.methodType = MethodType.stopGettingData;
        return methodWrapper;
    }

    public static MethodWrapper endTurn() {
        MethodWrapper methodWrapper = new MethodWrapper();
        methodWrapper.methodType = MethodType.endTurn;
        return methodWrapper;
    }

    public void unwrapMethod(GameManager gameManager) {
        switch (this.methodType) {
            case addCardToFront:
                // Reveals card, and adds events for creating card preview
                card.turnCard(false);
                card.setOnMouseEntered((MouseEvent e) -> gameManager.getGraphicManager().createCardPreview(card.getCardLogic()));
                card.setOnMouseExited((MouseEvent e) -> gameManager.getGraphicManager().removeCardPreview());
                // Rest of the job is already done in game manager
                gameManager.addCardToFront(card, lineType.toOpponentBattleFrontNodeID(), PlayerType.opponent);
                break;
            case removeCardFromPlayer:
                gameManager.removeCardFromPlayerDeck(card, PlayerType.opponent);
                break;
            case attackOnFront:
                PlayerType loserPlayerType = gameManager.getLogicManager().attackFrontLine(lineType);
                gameManager.getGraphicManager().getBattleFrontTextBoxGUI(lineType, loserPlayerType).setHitPointsAmount(gameManager.getLogicManager().getFrontLineHitPoints(lineType, loserPlayerType));
                gameManager.getGraphicManager().getPlayerHealthBox(loserPlayerType).setHPAmount(gameManager.getLogicManager().getPlayer(loserPlayerType).getHitPoints());
                gameManager.checkForVictoryCondition(loserPlayerType);
                gameManager.getGraphicManager().showAttackOnFrontGraphics(lineType);
                break;
            case addCardToPlayer:
                gameManager.getLogicManager().getPlayer(PlayerType.opponent).getCardList().add(card.getCardLogic());
                card.setOwnerOfCard(PlayerType.opponent);
                card.renderCardFront();
                card.turnCard(true);
                gameManager.getGraphicManager().addCardToPlayerDeck(card, PlayerType.opponent);
                break;
            case stopGettingData:
                gameManager.getNetworkManager().closeReceiveDataThread();
                gameManager.getGraphicManager().showMessagePane("Opponent has left", true);
                break;
            case endTurn:
                gameManager.getNetworkManager().setYourTurn(true);
                gameManager.getGraphicManager().getEndTurnButton().setYourTurnText(true);
                gameManager.getGraphicManager().showMessagePane("Your Turn", false);
                break;
        }
    }

    public Card getCard() {
        return card;
    }

    public LineType getLineType() {
        return lineType;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public void setLineType(LineType lineType) {
        this.lineType = lineType;
    }
}
