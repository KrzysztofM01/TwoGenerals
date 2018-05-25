package logic;


import graphic.PlayerType;
import logic.battleFields.FrontLine;
import logic.battleFields.LineType;
import logic.cards.CardLogic;
import logic.players.Player;
import variables.VariablesLogic;

import java.util.ArrayList;

public class LogicManager {

    private FrontLine leftPlayerFrontLine = new FrontLine(LineType.left, 0);
    private FrontLine centerPlayerFrontLine = new FrontLine(LineType.center, 0);
    private FrontLine rightPlayerFrontLine = new FrontLine(LineType.right, 0);

    private FrontLine leftOpponentFrontLine = new FrontLine(LineType.left, 1);
    private FrontLine centerOpponentFrontLine = new FrontLine(LineType.center, 1);
    private FrontLine rightOpponentFrontLine = new FrontLine(LineType.right, 1);

    private Player player = new Player();
    private Player opponent = new Player();

    public LogicManager(){
    }

    public void addCardToFront(CardLogic cardLogic, LineType lineType, PlayerType playerType){
        this.getPlayer(playerType).removeCard(cardLogic);
        cardLogic.setLineType(lineType);
        if (playerType == PlayerType.player){
            switch (lineType) {
                case left:
                    this.leftPlayerFrontLine.addCard(cardLogic);
                    break;
                case center:
                    this.centerPlayerFrontLine.addCard(cardLogic);
                    break;
                case right:
                    this.rightPlayerFrontLine.addCard(cardLogic);
                    break;
        }} else {
            switch (lineType) {
                case left:
                    this.leftOpponentFrontLine.addCard(cardLogic);
                    break;
                case center:
                    this.centerOpponentFrontLine.addCard(cardLogic);
                    break;
                case right:
                    this.rightOpponentFrontLine.addCard(cardLogic);
                    break;
            }
        }
    }

    public void removeCardFromFront(CardLogic cardLogic, LineType frontLineType, PlayerType playerType){
        if (playerType == PlayerType.player){
            switch (frontLineType) {
                case left:
                    this.leftPlayerFrontLine.removeCard(cardLogic);
                    break;
                case center:
                    this.centerPlayerFrontLine.removeCard(cardLogic);
                    break;
                case right:
                    this.rightPlayerFrontLine.removeCard(cardLogic);
                    break;
            }
        } else {
            switch (frontLineType) {
                case left:
                    this.leftOpponentFrontLine.removeCard(cardLogic);
                    break;
                case center:
                    this.centerOpponentFrontLine.removeCard(cardLogic);
                    break;
                case right:
                    this.rightOpponentFrontLine.removeCard(cardLogic);
                    break;
            }
        }
    }

    public FrontLine getFrontLine(LineType lineType, PlayerType playerType) {
        if (playerType == PlayerType.player) {
            switch (lineType) {
                case left:
                    return this.leftPlayerFrontLine;
                case center:
                    return this.centerPlayerFrontLine;
                case right:
                    return this.rightPlayerFrontLine;
            }
        } else {
            switch (lineType) {
                case left:
                    return this.leftOpponentFrontLine;
                case center:
                    return this.centerOpponentFrontLine;
                case right:
                    return this.rightOpponentFrontLine;
            }
        }
        return null;
    }

    public int getFrontLinePower(LineType lineType, PlayerType playerType){
        int summedPower = 0;
        for (LineType fromAllLineTypes: LineType.values()){
            for (CardLogic cardLogic: getFrontLine(fromAllLineTypes, playerType).getCardList()) {
                if (cardLogic.getLineType() == lineType) {
                    summedPower += cardLogic.getCurrentPower();
                }
            }
        }
        return summedPower;
    }

    public int getFrontLineHitPoints(LineType lineType, PlayerType playerType){
        if (playerType == PlayerType.player) {
            switch (lineType) {
                case left:
                    return this.leftPlayerFrontLine.getHP();
                case center:
                    return this.centerPlayerFrontLine.getHP();
                case right:
                    return this.rightPlayerFrontLine.getHP();
            }
        } else {
            switch (lineType) {
                case left:
                    return this.leftOpponentFrontLine.getHP();
                case center:
                    return this.centerOpponentFrontLine.getHP();
                case right:
                    return this.rightOpponentFrontLine.getHP();
            }
        }
        return 0;
    }

    public PlayerType attackFrontLine(LineType lineType){
        int powerDifference;
        switch (lineType){
            case left:
                powerDifference = getFrontLinePower(lineType, PlayerType.player) - getFrontLinePower(lineType, PlayerType.opponent);
                if (powerDifference>0){
                    if (leftOpponentFrontLine.getHP()>0){
                        this.leftOpponentFrontLine.setHP(this.leftOpponentFrontLine.getHP() - new Double(powerDifference * VariablesLogic.frontLineAttackFactor).intValue());
                    } else {
                        this.opponent.setHitPoints(this.opponent.getHitPoints() - new Double(powerDifference*VariablesLogic.playerAttackFactor).intValue());
                    }
                    return PlayerType.opponent;
                } else if (powerDifference < 0) {
                    if (leftPlayerFrontLine.getHP() > 0) {
                        this.leftPlayerFrontLine.setHP(this.leftPlayerFrontLine.getHP() + new Double(powerDifference*VariablesLogic.frontLineAttackFactor).intValue());
                    } else {
                        this.player.setHitPoints(this.player.getHitPoints() + new Double(powerDifference * VariablesLogic.playerAttackFactor).intValue());
                    }
                    return PlayerType.player;
                }
                return PlayerType.player;

            case center:
                powerDifference = getFrontLinePower(lineType, PlayerType.player) - getFrontLinePower(lineType, PlayerType.opponent);
                if (powerDifference>0){
                    if (centerOpponentFrontLine.getHP()>0){
                        this.centerOpponentFrontLine.setHP(this.centerOpponentFrontLine.getHP() - new Double(powerDifference * VariablesLogic.frontLineAttackFactor).intValue());
                    } else {
                        this.opponent.setHitPoints(this.opponent.getHitPoints() - new Double(powerDifference*VariablesLogic.playerAttackFactor).intValue());
                    }
                    return PlayerType.opponent;
                } else if (powerDifference < 0) {
                    if (centerPlayerFrontLine.getHP() > 0) {
                        this.centerPlayerFrontLine.setHP(this.centerPlayerFrontLine.getHP() + new Double(powerDifference*VariablesLogic.frontLineAttackFactor).intValue());
                    } else {
                        this.player.setHitPoints(this.player.getHitPoints() + new Double(powerDifference * VariablesLogic.playerAttackFactor).intValue());
                    }
                    return PlayerType.player;
                }
                return PlayerType.player;

            case right:
                powerDifference = getFrontLinePower(lineType, PlayerType.player) - getFrontLinePower(lineType, PlayerType.opponent);
                if (powerDifference>0){
                    if (rightOpponentFrontLine.getHP()>0){
                        this.rightOpponentFrontLine.setHP(this.rightOpponentFrontLine.getHP() - new Double(powerDifference * VariablesLogic.frontLineAttackFactor).intValue());
                    } else {
                        this.opponent.setHitPoints(this.opponent.getHitPoints() - new Double(powerDifference*VariablesLogic.playerAttackFactor).intValue());
                    }
                    return PlayerType.opponent;
                } else if (powerDifference < 0) {
                    if (rightPlayerFrontLine.getHP() > 0) {
                        this.rightPlayerFrontLine.setHP(this.rightPlayerFrontLine.getHP() + new Double(powerDifference*VariablesLogic.frontLineAttackFactor).intValue());
                    } else {
                        this.player.setHitPoints(this.player.getHitPoints() + new Double(powerDifference * VariablesLogic.playerAttackFactor).intValue());
                    }
                    return PlayerType.player;
                }
                return PlayerType.player;
        }
        return PlayerType.player;
    }

    public Player getPlayer(PlayerType playerType) {
        if (playerType == PlayerType.player){
            return this.player;
        } else {
            return this.opponent;
        }
    }
}
