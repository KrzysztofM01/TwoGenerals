package graphics;

import logic.battleFields.LineType;
import javafx.scene.layout.Pane;
import variables.VariablesGraphics;

import java.util.ArrayList;

public class BattleFieldGUI extends Pane {
    public static final String STYLESHEET = "battleFrontStyles.css";
    private BattleFrontGUI playerCards = new BattleFrontGUI(true);
    private BattleFrontGUI opponentCards = new BattleFrontGUI(false);
    private ArrayList<BattleFrontGUI> battleFrontGUIArrayList = new ArrayList<>();
    private LineType lineType;
    //mozna dodac jeszcze health bary itp.

    public BattleFieldGUI(LineType lineType) {
        this.getStylesheets().addAll(STYLESHEET);
        this.lineType = lineType;
        // Set IDs of Panes
        this.opponentCards.getActiveCards().setId(this.lineType.toString() + this.opponentCards.getActiveCards().getId());
        this.playerCards.getActiveCards().setId(this.lineType.toString() + this.playerCards.getActiveCards().getId());
        this.setId(this.lineType.toString() + "BattleField");
        //
        // Add battle fronts to array list
        this.battleFrontGUIArrayList.add(this.playerCards);
        this.battleFrontGUIArrayList.add(this.opponentCards);
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
        this.playerCards.getActiveCards().setLayoutY(VariablesGraphics.battleFieldHeight/2);
        this.getChildren().addAll(this.opponentCards.getActiveCards(), this.playerCards.getActiveCards());
    }

    public BattleFrontGUI getPlayerCards() {
        return playerCards;
    }

    public BattleFrontGUI getOpponentCards() {
        return opponentCards;
    }

    public ArrayList<BattleFrontGUI> getBattleFrontGUIArrayList() {
        return battleFrontGUIArrayList;
    }

    public LineType getLineType() {
        return lineType;
    }

}