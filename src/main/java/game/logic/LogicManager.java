package game.logic;


import game.graphic.PlayerType;
import game.logic.battleFields.FrontLine;
import game.logic.battleFields.LineType;
import game.logic.cards.CardLogic;

public class LogicManager {

    private FrontLine leftPlayerFrontLine = new FrontLine();
    private FrontLine centerPlayerFrontLine = new FrontLine();
    private FrontLine rightPlayerFrontLine = new FrontLine();

    private FrontLine leftOpponentFrontLine = new FrontLine();
    private FrontLine centerOpponentFrontLine = new FrontLine();
    private FrontLine rightOpponentFrontLine = new FrontLine();

    private Player player = new Player();
    private Player opponent = new Player();

    public LogicManager() {
    }

    public void addCardToFront(CardLogic cardLogic, LineType lineType, PlayerType playerType) {
        getPlayer(playerType).removeCard(cardLogic);
        cardLogic.setLineType(lineType);
        getFrontLine(lineType, playerType).addCard(cardLogic);
    }

    public void removeCardFromFront(CardLogic cardLogic, LineType frontLineType, PlayerType playerType) {
        getFrontLine(frontLineType, playerType).removeCard(cardLogic);
    }


    public int getFrontLinePower(LineType lineType, PlayerType playerType) {
        int summedPower = 0;
        // Checks for all front lines, as there are cards that are on X front line, but give power to Y front line
        for (LineType fromAllLineTypes : LineType.values()) {
            for (CardLogic cardLogic : getFrontLine(fromAllLineTypes, playerType).getCardList()) {
                if (cardLogic.getLineType() == lineType) {
                    summedPower += cardLogic.getCurrentPower();
                }
            }
        }
        return summedPower;
    }

    public int getFrontLineHitPoints(LineType lineType, PlayerType playerType) {
        return getFrontLine(lineType, playerType).getHP();
    }

    public PlayerType attackFrontLine(LineType lineType) {
        int powerDifference = getFrontLinePower(lineType, PlayerType.player) - getFrontLinePower(lineType, PlayerType.opponent);

        if (powerDifference > 0){
            if (getFrontLineHitPoints(lineType, PlayerType.opponent)> 0) {
                getFrontLine(lineType, PlayerType.opponent).setHP(getFrontLineHitPoints(lineType, PlayerType.opponent) - powerDifference);
            } else {
                opponent.setHitPoints(opponent.getHitPoints() - powerDifference);
            }
            return PlayerType.opponent;
        } else {
            if (getFrontLineHitPoints(lineType, PlayerType.player)> 0) {
                getFrontLine(lineType, PlayerType.player).setHP(getFrontLineHitPoints(lineType, PlayerType.player) + powerDifference);
            } else {
                player.setHitPoints(player.getHitPoints() + powerDifference);
            }
            return PlayerType.player;
        }

    }

    public Player getPlayer(PlayerType playerType) {
        if (playerType == PlayerType.player) {
            return player;
        } else {
            return opponent;
        }
    }

    @SuppressWarnings("Duplicates")
    // Why the hell does IntelliJ points it as duplicate???
    public FrontLine getFrontLine(LineType lineType, PlayerType playerType) {
        if (playerType == PlayerType.player) {
            switch (lineType) {
                case left:
                    return leftPlayerFrontLine;
                case center:
                    return centerPlayerFrontLine;
                case right:
                    return rightPlayerFrontLine;
            }
        } else {
            switch (lineType) {
                case left:
                    return leftOpponentFrontLine;
                case center:
                    return centerOpponentFrontLine;
                case right:
                    return rightOpponentFrontLine;
            }
        }
        return null;
    }
}
