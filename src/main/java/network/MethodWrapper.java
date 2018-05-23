package network;

import graphic.GraphicManager;
import graphic.PlayerType;
import graphic.cards.Card;
import javafx.scene.input.MouseEvent;
import logic.LogicManager;
import logic.battleFields.LineType;
import main.GameManager;

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

    public void unwrapMethod(GameManager gameManager) {
        switch (this.methodType) {
            case addCardToFront:
                gameManager.addCardToFront(card, lineType.toOpponentBattleFrontNodeID(), PlayerType.opponent);
                break;
            case removeCardFromPlayer:
                gameManager.removeCardFromPlayerDeck(card, PlayerType.opponent);
                break;
            case attackOnFront:
                PlayerType loserPlayerType = gameManager.getLogicManager().attackFrontLine(lineType);
                gameManager.getGraphicManager().getBattleFrontTextBoxGUI(lineType, loserPlayerType).setHitPointsAmount(gameManager.getLogicManager().getFrontLineHitPoints(lineType, loserPlayerType));
                gameManager.getGraphicManager().getPlayerHealthBox(loserPlayerType).setHPAmount(gameManager.getLogicManager().getPlayer(loserPlayerType).getHitPoints());

                break;
            case addCardToPlayer:
                gameManager.getLogicManager().getPlayer(PlayerType.opponent).getCardList().add(card.getCardLogic());
                card.renderCardGraphics();
                card.setOnMouseEntered((MouseEvent e) -> {
                    gameManager.getGraphicManager().createCardPreview(card.getCardLogic());
                });
                card.setOnMouseExited((MouseEvent e) -> {
                    gameManager.getGraphicManager().removeCardPreview();
                });
                gameManager.getGraphicManager().getOpponentCardList().add(card);
                gameManager.getGraphicManager().addCardToPlayerDeck(card, PlayerType.opponent);
                break;
        }
    }

    public Card getCard() {
        return card;
    }

    public LineType getLineType() {
        return lineType;
    }

    public MethodType getMethodType() {
        return methodType;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public void setLineType(LineType lineType) {
        this.lineType = lineType;
    }

    public void setMethodType(MethodType methodType) {
        this.methodType = methodType;
    }
}
