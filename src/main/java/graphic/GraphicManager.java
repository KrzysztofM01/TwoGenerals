package graphic;

import graphic.battleFields.BattleFieldGUI;
import graphic.battleFields.BattleFrontTextBoxGUI;
import graphic.buttons.AttackButton;
import graphic.buttons.ExitButton;
import graphic.cards.Card;
import graphic.cards.cardPreview.CardPreview;
import graphic.panes.ActionPointsBox;
import graphic.panes.CardPreviewPane;
import graphic.panes.PlayerHealthBox;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import logic.battleFields.LineType;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import logic.cards.CardLogic;
import variables.VariablesGraphics;

import java.util.ArrayList;

public class GraphicManager {

    public static final String STYLESHEET = "mainPane.css";

    private Scene scene;
    private Pane mainPane = new Pane();
    private FlowPane playerCards = new FlowPane();
    private FlowPane opponentCards = new FlowPane();
    private CardPreviewPane cardPreviewPane = new CardPreviewPane();
    private BattleFieldGUI leftBattleFieldGUI = new BattleFieldGUI(LineType.left);
    private BattleFieldGUI centerBattleFieldGUI = new BattleFieldGUI(LineType.center);
    private BattleFieldGUI rightBattleFieldGUI = new BattleFieldGUI(LineType.right);

    private AttackButton leftAttackButton = new AttackButton(LineType.left);
    private AttackButton centerAttackButton = new AttackButton(LineType.center);
    private AttackButton rightAttackButton = new AttackButton(LineType.right);

    private PlayerHealthBox playerHealthBox = new PlayerHealthBox(PlayerType.player);
    private PlayerHealthBox opponentHealthBox = new PlayerHealthBox(PlayerType.opponent);

    private ActionPointsBox movePointsBox = new ActionPointsBox();
    private ExitButton exitButton = new ExitButton();

    private ArrayList<Card> cardList = new ArrayList<Card>();

    private Stage primaryStage;


    public GraphicManager(Stage primaryStage) {
        // Set IDs of Panes
        this.playerCards.setId("playerCards");
        this.opponentCards.setId("opponentCards");
        this.primaryStage = primaryStage;
        //
        // Set scene and it's style
        this.scene = new Scene(this.mainPane, VariablesGraphics.screenWidth, VariablesGraphics.screenHeight);
        this.scene.getStylesheets().addAll(STYLESHEET);
        //
        // Set mainPane layouts
        this.playerCards.setHgap(-VariablesGraphics.cardWidth/2);
        this.playerCards.setAlignment(Pos.BOTTOM_CENTER);
        this.playerCards.setPrefSize(VariablesGraphics.battleFieldWidth*3+ VariablesGraphics.battleFieldBreakWidth*3, VariablesGraphics.cardHeight+ VariablesGraphics.cardPadding*2);
        this.playerCards.setLayoutY(VariablesGraphics.playerCardPositionY);

        this.opponentCards.setLayoutY(VariablesGraphics.screenHeight*0.01);
        this.opponentCards.setHgap(-VariablesGraphics.cardWidth/1.5);
        this.opponentCards.setAlignment(Pos.TOP_CENTER);
        this.opponentCards.setPrefSize(VariablesGraphics.battleFieldWidth*3+ VariablesGraphics.battleFieldBreakWidth*3, VariablesGraphics.cardHeight+ VariablesGraphics.cardPadding*2);

        this.mainPane.getChildren().addAll(this.playerCards, this.opponentCards, this.leftBattleFieldGUI,
                this.centerBattleFieldGUI, this.rightBattleFieldGUI, this.leftAttackButton, this.centerAttackButton,
                this.rightAttackButton, this.cardPreviewPane, this.playerHealthBox, this.opponentHealthBox,
                this.movePointsBox, this.exitButton);
    }


    public void loadGameScene(){
        //
        // Add scene to the primary stage
        //primaryStage.setTitle("Two Generals");
        this.primaryStage.setFullScreen(true);
        this.primaryStage.setFullScreenExitHint("");
        //primaryStage.getIcons().add(new Image("images/2GIcon.png"));
        this.primaryStage.close();
        this.primaryStage.setScene(this.scene);
        this.primaryStage.show();
        //
    }

    public CardPreviewPane getCardPreviewPane() {
        return cardPreviewPane;
    }

    public void addCardToPlayerDeck(Card card, PlayerType playerType){
        this.cardList.add(card);
        if (playerType == PlayerType.player){
            this.playerCards.getChildren().add(card);
        } else {
            this.opponentCards.getChildren().add(card);
        }
    }

    public void removeCardFromPlayerDeck(Card card, PlayerType playerType){
        this.cardList.remove(card);
        if (playerType == PlayerType.player){
            this.playerCards.getChildren().remove(card);
        } else {
            this.opponentCards.getChildren().remove(card);
        }
    }

    public void addCardToFront(Card card, LineType lineType, int power, PlayerType playerType){
        this.cardList.add(card);
        this.updateGraphics(card);
        card.setHighlighted(false);
        this.getBattleFrontTextBoxGUI(lineType, playerType).setPowerAmount(power);
        this.getBattleFrontNodeList(lineType, playerType).add(card);
    }

    public void updateGraphics(Card card){
        card.getCardTextPower().setText(card.getCardLogic().getCurrentPower());
        card.getCardTextCost().setText(card.getCardLogic().getCost());
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

    public ExitButton getExitButton() {
        return exitButton;
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

    public PlayerHealthBox getPlayerHealthBox(PlayerType playerType) {
        if (playerType == PlayerType.player){
            return this.playerHealthBox;
        } else {
            return this.opponentHealthBox;
        }
    }

    public CardPreview createCardPreview (CardLogic cardLogic){
        CardPreview cardPreview = new CardPreview(cardLogic);
        this.cardPreviewPane.getChildren().add(cardPreview);
        return cardPreview;
    }

    public void removeCardPreview (CardPreview cardPreview){
        this.cardPreviewPane.getChildren().remove(cardPreview);
    }
}