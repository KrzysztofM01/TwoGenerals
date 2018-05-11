package graphic;

import graphic.battleFields.BattleFieldGUI;
import graphic.battleFields.BattleFrontTextBoxGUI;
import graphic.buttons.AttackButton;
import graphic.cards.Card;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import logic.battleFields.LineType;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import variables.VariablesGraphics;

import java.util.ArrayList;

public class GraphicManager {

    public static final String STYLESHEET = "mainPane.css";

    private Scene scene;
    private Pane mainPane = new Pane();
    private FlowPane playerCards = new FlowPane();
    private FlowPane opponentCards = new FlowPane();
    private Pane cardPreviewPane = new Pane();
    private BattleFieldGUI leftBattleFieldGUI = new BattleFieldGUI(LineType.left);
    private BattleFieldGUI centerBattleFieldGUI = new BattleFieldGUI(LineType.center);
    private BattleFieldGUI rightBattleFieldGUI = new BattleFieldGUI(LineType.right);

    private AttackButton leftAttackButton = new AttackButton(LineType.left);
    private AttackButton centerAttackButton = new AttackButton(LineType.center);
    private AttackButton rightAttackButton = new AttackButton(LineType.right);

    private ArrayList<Card> cardList = new ArrayList<Card>();

    // To remove
    private Pane lowerPane = new Pane();
    private FlowPane lowerLeftFlowPane = new FlowPane();
    private FlowPane lowerCenterFlowPane = new FlowPane();
    private FlowPane lowerRightFlowPane = new FlowPane();
    //


    public GraphicManager(Stage primaryStage) {
        // Set IDs of Panes
        this.playerCards.setId("playerCards");
        this.opponentCards.setId("opponentCards");
        this.cardPreviewPane.setId("cardPreview");
        //
        // Set scene and it's style
        this.scene = new Scene(this.mainPane, VariablesGraphics.screenWidth, VariablesGraphics.screenHeight);
        this.scene.getStylesheets().addAll(STYLESHEET);
        //
        // Set mainPane layouts
        this.playerCards.setHgap(-VariablesGraphics.cardWidth/4);
        this.playerCards.setAlignment(Pos.BOTTOM_CENTER);
        this.playerCards.setPrefSize(VariablesGraphics.battleFieldWidth*3+ VariablesGraphics.battleFieldBreakWidth*3, VariablesGraphics.cardHeight+ VariablesGraphics.cardPadding*2);
        this.playerCards.setLayoutY(VariablesGraphics.screenHeight*0.957 - VariablesGraphics.cardHeight);

        this.opponentCards.setHgap(-VariablesGraphics.cardWidth/4);
        this.opponentCards.setAlignment(Pos.TOP_CENTER);
        this.opponentCards.setPrefSize(VariablesGraphics.battleFieldWidth*3+ VariablesGraphics.battleFieldBreakWidth*3, VariablesGraphics.cardHeight+ VariablesGraphics.cardPadding*2);

        this.cardPreviewPane.setLayoutY(VariablesGraphics.battleFieldPositionY);
        this.cardPreviewPane.setLayoutX(VariablesGraphics.battleFieldWidth*3+ VariablesGraphics.battleFieldBreakWidth*4);
        this.cardPreviewPane.setPrefSize(VariablesGraphics.battleFieldWidth, VariablesGraphics.battleFieldHeight);

        this.mainPane.getChildren().addAll(this.playerCards, this.opponentCards, this.leftBattleFieldGUI,
                this.centerBattleFieldGUI, this.rightBattleFieldGUI, this.leftAttackButton, this.centerAttackButton,
                this.rightAttackButton, this.cardPreviewPane);
        //
        // Add scene to the primary stage
        primaryStage.setTitle("Two Generals");
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("images/2GIcon.png"));
        primaryStage.setWidth(VariablesGraphics.screenWidth);
        primaryStage.setHeight(VariablesGraphics.screenHeight);
        primaryStage.setScene(scene);
        primaryStage.show();
        //
    }

    public Pane getCardPreviewPane() {
        return cardPreviewPane;
    }

    public void addCardToPlayerDeck(Card card){
        this.playerCards.getChildren().add(card);
        this.cardList.add(card);
    }

    public void removeCardFromPlayerDeck(Card card){
        this.cardList.remove(card);
        this.playerCards.getChildren().remove(card);
    }

    public void addCardToFront(Card card, LineType lineType, int power, PlayerType playerType){
        this.cardList.add(card);
        this.updateGraphics(card);
        card.setHighlighted(false);
        this.getBattleFrontTextBoxGUI(lineType, playerType).setPowerAmount(power);
        this.getBattleFrontNodeList(lineType, playerType).add(card);
    }

    public void updateGraphics(Card card){
        card.getPowerText().setText(card.getCardLogic().getCurrentPower());
    }

    public void removeCardFromFront(Card card, LineType lineType, int power, PlayerType playerType){
        this.cardList.remove(card);
        this.updateGraphics(card);
        this.getBattleFrontTextBoxGUI(lineType, playerType).setPowerAmount(power);
        this.getBattleFrontNodeList(lineType, playerType).remove(card);
    }

    public BattleFieldGUI getLeftBattleFieldGUI() {
        return leftBattleFieldGUI;
    }

    public BattleFieldGUI getCenterBattleFieldGUI() {
        return centerBattleFieldGUI;
    }

    public BattleFieldGUI getRightBattleFieldGUI() {
        return rightBattleFieldGUI;
    }

    public AttackButton getLeftAttackButton() {
        return leftAttackButton;
    }

    public AttackButton getCenterAttackButton() {
        return centerAttackButton;
    }

    public AttackButton getRightAttackButton() {
        return rightAttackButton;
    }

    public ArrayList<Card> getCardList() {
        return cardList;
    }

    public BattleFrontTextBoxGUI getBattleFrontTextBoxGUI(LineType lineType, PlayerType playerType){
        if (playerType == PlayerType.player){
            switch (lineType){
                case left:
                    return this.leftBattleFieldGUI.getPlayerTextBox();
                case center:
                    return this.centerBattleFieldGUI.getPlayerTextBox();
                case right:
                    return this.rightBattleFieldGUI.getPlayerTextBox();
            }
        } else {
            switch (lineType){
                case left:
                    return this.leftBattleFieldGUI.getOpponentTextBox();
                case center:
                    return this.centerBattleFieldGUI.getOpponentTextBox();
                case right:
                    return this.rightBattleFieldGUI.getOpponentTextBox();
            }
        }
        return null;
    }

    public ObservableList<Node> getBattleFrontNodeList (LineType lineType, PlayerType playerType){
        switch (lineType) {
            case left:
                return this.leftBattleFieldGUI.getCardsNodeList(playerType);
            case center:
                return this.centerBattleFieldGUI.getCardsNodeList(playerType);
            case right:
                return this.rightBattleFieldGUI.getCardsNodeList(playerType);
        }
        return null;
    }
}
