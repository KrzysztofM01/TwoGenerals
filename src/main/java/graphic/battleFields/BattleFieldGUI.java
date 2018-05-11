package graphic.battleFields;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import logic.battleFields.LineType;
import javafx.scene.layout.Pane;
import graphic.PlayerType;
import variables.VariablesGraphics;

public class BattleFieldGUI extends Pane {
    public static final String STYLESHEET = "battleFrontStyles.css";
    private BattleFrontGUI playerCards = new BattleFrontGUI(true);
    private BattleFrontGUI opponentCards = new BattleFrontGUI(false);

    private LineType lineType;

    private BattleFrontTextBoxGUI playerTextBox;
    private BattleFrontTextBoxGUI opponentTextBox;


    public BattleFieldGUI(LineType lineType) {
        this.getStylesheets().addAll(STYLESHEET);
        this.lineType = lineType;

        this.playerTextBox = new BattleFrontTextBoxGUI(PlayerType.player, this.lineType);
        this.opponentTextBox = new BattleFrontTextBoxGUI(PlayerType.opponent, this.lineType);

        // Set IDs of Panes
        this.opponentCards.getCards().setId(this.lineType.toString() + this.opponentCards.getCards().getId());
        this.playerCards.getCards().setId(this.lineType.toString() + this.playerCards.getCards().getId());
        this.setId(this.lineType.toString() + "BattleField");
        //
        //Set BattleFieldPane Layout
        this.setPrefSize(VariablesGraphics.battleFieldWidth, VariablesGraphics.battleFieldHeight);
        this.setLayoutY(VariablesGraphics.battleFieldPositionY);
        switch (this.lineType) {
            case left:
                this.setLayoutX(VariablesGraphics.battleFieldBreakWidth);
                break;
            case center:
                this.setLayoutX(VariablesGraphics.battleFieldWidth + VariablesGraphics.battleFieldBreakWidth * 2);
                break;
            case right:
                this.setLayoutX(VariablesGraphics.battleFieldWidth * 2 + VariablesGraphics.battleFieldBreakWidth * 3);
                break;
        }
        this.playerCards.getCards().setLayoutY(VariablesGraphics.battleFieldHeight/2);
        this.getChildren().addAll(this.opponentCards.getCards(), this.playerCards.getCards(), this.playerTextBox, this.opponentTextBox);
    }

    public ObservableList<Node> getCardsNodeList (PlayerType playerType) {
        if (playerType == PlayerType.player){
            return playerCards.getCards().getChildren();
        } else {
            return opponentCards.getCards().getChildren();
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
}