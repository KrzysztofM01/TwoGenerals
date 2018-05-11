package logic;


import logic.battleFields.FrontLine;
import logic.battleFields.LineType;
import logic.cards.CardLogic;
import logic.players.Player;
import variables.VariablesLogic;

public class LogicManager {

    private FrontLine leftPlayerFrontLine = new FrontLine(LineType.left, 0);
    private FrontLine centerPlayerFrontLine = new FrontLine(LineType.center, 0);
    private FrontLine rightPlayerFrontLine = new FrontLine(LineType.right, 0);

    private FrontLine leftOpponentFrontLine = new FrontLine(LineType.left, 1);
    private FrontLine centerOpponentFrontLine = new FrontLine(LineType.center, 1);
    private FrontLine rightOpponentFrontLine = new FrontLine(LineType.right, 1);

    private Player player0;
    private Player player1;

    public LogicManager(Player player0, Player player1){
        this.player0 = player0;
        this.player1 = player1;
    }

    public void addCardToFront(CardLogic cardLogic, LineType frontLineType, int playerID){
        this.getPlayer(playerID).removeCard(cardLogic);
        if (playerID == 0){
            switch (frontLineType) {
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
            switch (frontLineType) {
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

    public void removeCardFromFront(CardLogic cardLogic, LineType frontLineType, int playerID){
        if (playerID == 0){
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

    public FrontLine getFrontLine(LineType lineType, int playerID) {
        if (playerID == 0) {
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

    public int getFrontLinePower(LineType lineType, int playerID){
        if (playerID == 0) {
            switch (lineType) {
                case left:
                    return this.leftPlayerFrontLine.getSummedPower();
                case center:
                    return this.centerPlayerFrontLine.getSummedPower();
                case right:
                    return this.rightPlayerFrontLine.getSummedPower();
            }
        } else {
            switch (lineType) {
                case left:
                    return this.leftOpponentFrontLine.getSummedPower();
                case center:
                    return this.centerOpponentFrontLine.getSummedPower();
                case right:
                    return this.rightOpponentFrontLine.getSummedPower();
            }
        }
        return 0;
    }

    public int getFrontLineHitPoints(LineType lineType, int playerID){
        if (playerID == 0) {
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

    public int attackFrontLine(LineType lineType){
        int powerDifference;
        switch (lineType){

            case left:
                powerDifference = leftPlayerFrontLine.getSummedPower() - leftOpponentFrontLine.getSummedPower();
                if (powerDifference>0){
                    if (leftOpponentFrontLine.getHP()>0){
                        this.leftOpponentFrontLine.setHP(this.leftOpponentFrontLine.getHP() - new Double(powerDifference * VariablesLogic.frontLineAttackFactor).intValue());
                    } else {
                        this.player1.setHitPoints(this.player1.getHitPoints() - new Double(powerDifference*VariablesLogic.playerAttackFactor).intValue());
                    }
                    return 1;
                } else if (powerDifference < 0) {
                    if (leftPlayerFrontLine.getHP() > 0) {
                        this.leftPlayerFrontLine.setHP(this.leftPlayerFrontLine.getHP() + new Double(powerDifference*VariablesLogic.frontLineAttackFactor).intValue());
                    } else {
                        this.player0.setHitPoints(this.player1.getHitPoints() + new Double(powerDifference * VariablesLogic.playerAttackFactor).intValue());
                    }
                    return 0;
                }
                return 0;

            case center:
                powerDifference = centerPlayerFrontLine.getSummedPower() - centerOpponentFrontLine.getSummedPower();
                if (powerDifference>0){
                    if (centerOpponentFrontLine.getHP()>0){
                        this.centerOpponentFrontLine.setHP(this.centerOpponentFrontLine.getHP() - new Double(powerDifference * VariablesLogic.frontLineAttackFactor).intValue());
                    } else {
                        this.player1.setHitPoints(this.player1.getHitPoints() - new Double(powerDifference*VariablesLogic.playerAttackFactor).intValue());
                    }
                    return 1;
                } else if (powerDifference < 0) {
                    if (centerPlayerFrontLine.getHP() > 0) {
                        this.centerPlayerFrontLine.setHP(this.centerPlayerFrontLine.getHP() + new Double(powerDifference*VariablesLogic.frontLineAttackFactor).intValue());
                    } else {
                        this.player0.setHitPoints(this.player1.getHitPoints() + new Double(powerDifference * VariablesLogic.playerAttackFactor).intValue());
                    }
                    return 0;
                }
                return 0;

            case right:
                powerDifference = rightPlayerFrontLine.getSummedPower() - rightOpponentFrontLine.getSummedPower();
                if (powerDifference>0){
                    if (rightOpponentFrontLine.getHP()>0){
                        this.rightOpponentFrontLine.setHP(this.rightOpponentFrontLine.getHP() - new Double(powerDifference * VariablesLogic.frontLineAttackFactor).intValue());
                    } else {
                        this.player1.setHitPoints(this.player1.getHitPoints() - new Double(powerDifference*VariablesLogic.playerAttackFactor).intValue());
                    }
                    return 1;
                } else if (powerDifference < 0) {
                    if (rightPlayerFrontLine.getHP() > 0) {
                        this.rightPlayerFrontLine.setHP(this.rightPlayerFrontLine.getHP() + new Double(powerDifference*VariablesLogic.frontLineAttackFactor).intValue());
                    } else {
                        this.player0.setHitPoints(this.player1.getHitPoints() + new Double(powerDifference * VariablesLogic.playerAttackFactor).intValue());
                    }
                    return 0;
                }
                return 0;
        }
        return 0;
    }

    public Player getPlayer(int playerID) {
        if (playerID == 0){
            return this.player0;
        } else {
            return this.player1;
        }
    }
}
