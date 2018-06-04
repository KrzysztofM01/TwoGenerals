package graphic.battleFields;

import graphic.cards.Card;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import logic.battleFields.LineType;
import javafx.scene.layout.Pane;
import graphic.PlayerType;
import variables.VariablesGraphics;

import java.util.ArrayList;

public class BattleFieldGUI extends Pane {

    private BattleFrontGUI playerCards = new BattleFrontGUI();
    private BattleFrontGUI opponentCards = new BattleFrontGUI();

    private LineType lineType;

    private BattleFrontTextBoxGUI playerTextBox;
    private BattleFrontTextBoxGUI opponentTextBox;

    private ArrayList<Card> playerCardsList = new ArrayList<>();
    private ArrayList<Card> opponentCardsList = new ArrayList<>();


    public BattleFieldGUI(LineType lineType) {

        this.lineType = lineType;

        // Create the boxes according to line type
        playerTextBox = new BattleFrontTextBoxGUI(PlayerType.player, lineType);
        opponentTextBox = new BattleFrontTextBoxGUI(PlayerType.opponent, lineType);

        // Set IDs of Panes
        opponentCards.setId(lineType.toString() + "Opponent" + opponentCards.getId());
        playerCards.setId(lineType.toString() + "Player" + playerCards.getId());
        this.setId(lineType.toString() + "BattleField");

        //Set BattleFieldPane Layout
        this.setPrefSize(VariablesGraphics.getInstance().getBattleFieldWidth(), VariablesGraphics.getInstance().getBattleFieldHeight());
        this.setLayoutY(VariablesGraphics.getInstance().getBattleFieldPositionY());
        playerCards.setLayoutY(VariablesGraphics.getInstance().getBattleFieldHeight() / 2);
        switch (lineType) {
            case left:
                this.setLayoutX(VariablesGraphics.getInstance().getBattleFieldBreakWidth());
                break;
            case center:
                this.setLayoutX(VariablesGraphics.getInstance().getBattleFieldWidth() + VariablesGraphics.getInstance().getBattleFieldBreakWidth() * 2);
                break;
            case right:
                this.setLayoutX(VariablesGraphics.getInstance().getBattleFieldWidth() * 2 + VariablesGraphics.getInstance().getBattleFieldBreakWidth() * 3);
                break;
        }

        this.getChildren().addAll(opponentCards, playerCards, playerTextBox, opponentTextBox);
    }

    public ObservableList<Node> getCardsNodeList(PlayerType playerType) {
        if (playerType == PlayerType.player) {
            return playerCards.getChildren();
        } else {
            return opponentCards.getChildren();
        }
    }

    public LineType getLineType() {
        return lineType;
    }

    public BattleFrontTextBoxGUI getPlayerTextBox() {
        return playerTextBox;
    }

    public BattleFrontTextBoxGUI getOpponentTextBox() {
        return opponentTextBox;
    }

    public ArrayList<Card> getCardsList(PlayerType playerType) {
        if (playerType == PlayerType.player) {
            return playerCardsList;
        } else {
            return opponentCardsList;
        }

    }

}