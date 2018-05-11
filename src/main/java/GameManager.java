import graphic.PlayerType;
import graphic.PlayerTypeConverter;
import graphic.cards.Card;
import graphic.cards.CardPreview;
import graphic.GraphicManager;
import graphic.NodeIDConverter;
import logic.players.Player;
import logic.cards.CardLogic;
import logic.battleFields.LineType;
import logic.LogicManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class GameManager {

    private Card tempCard;
    private LogicManager logicManager;
    private GraphicManager graphicManager;
    private CardPreview tempCardPreview;

    public GameManager(Stage primaryStage, Player player0, Player player1) {

        // Launch the graphic and logic managers
        this.graphicManager = new GraphicManager(primaryStage);
        this.logicManager = new LogicManager(player0, player1);
        //

        // Handler must be in main manager
        EventHandler<MouseEvent> eventHandler = e -> {
            System.out.println(e.getPickResult().getIntersectedNode().getId());
            if (e.getPickResult().getIntersectedNode().getId() != null && !NodeIDConverter.isItBattleFieldID(e.getPickResult().getIntersectedNode().getId())) {
                if (GameManager.this.tempCard != null) {
                    if (GameManager.this.tempCard.isHighlighted()) {
                        GameManager.this.removeCardFromPlayerDeck(GameManager.this.tempCard);
                        GameManager.this.addCardToFront(GameManager.this.tempCard, e.getPickResult().getIntersectedNode().getId(), 0);
                        //GameManager.this.tempCard = null;
                    }
                }
            }
        };

        EventHandler<ActionEvent> buttonHandler = e -> {
            LineType lineType = NodeIDConverter.toLineType(e.getSource().toString());
            int loserPlayerID = this.logicManager.attackFrontLine(lineType);
            PlayerType playerType = PlayerTypeConverter.toPlayerType(loserPlayerID);
            this.graphicManager.getBattleFrontTextBoxGUI(lineType, playerType).setHitPointsAmount(this.logicManager.getFrontLineHitPoints(lineType, loserPlayerID));
        };

        graphicManager.getLeftBattleFieldGUI().setOnMouseClicked(eventHandler);
        graphicManager.getCenterBattleFieldGUI().setOnMouseClicked(eventHandler);
        graphicManager.getRightBattleFieldGUI().setOnMouseClicked(eventHandler);

        graphicManager.getLeftAttackButton().setOnAction(buttonHandler);
        graphicManager.getCenterAttackButton().setOnAction(buttonHandler);
        graphicManager.getRightAttackButton().setOnAction(buttonHandler);

    }

    public void addCardToPlayerDeck(CardLogic cardLogic){
        Card card = new Card(cardLogic);
        graphicManager.addCardToPlayerDeck(card);
        //logic manager here
        card.setOnMouseEntered((MouseEvent e) ->{
            CardPreview cardPreview = new CardPreview(cardLogic);
            this.tempCardPreview = cardPreview;
            graphicManager.getCardPreviewPane().getChildren().add(cardPreview);
            //cardPreview.setLayoutX(card.getParent().getLayoutX()+card.getLayoutX()- VariablesGraphics.cardHeight /3);
            //cardPreview.setLayoutX(VariablesGraphics.screenWidth/2-VariablesGraphics.cardHeight);
            //cardPreview.setLayoutY(card.getParent().getLayoutY()- VariablesGraphics.cardHeight*2 -VariablesGraphics.cardPadding*2);
        });
        card.setOnMouseExited((MouseEvent e) -> {
            graphicManager.getCardPreviewPane().getChildren().remove(tempCardPreview);
            this.tempCardPreview = null;
        });

        card.setOnMouseClicked((MouseEvent e) -> {
            if (this.tempCard != null && card.isHighlighted()){
                card.setHighlighted(false);
                card.setViewOrder(0);
                //this.tempCard = null;
                } else if (this.tempCard != null && !card.isHighlighted()){
                this.tempCard.setHighlighted(false);
                this.tempCard.setViewOrder(0);
                this.tempCard = card;
                card.setHighlighted(true);
                card.setViewOrder(-1);
                } else {
                card.setHighlighted(true);
                card.setViewOrder(-1);
                this.tempCard = card;
                }
        });
    }

    public void removeCardFromPlayerDeck(Card card){
        graphicManager.removeCardFromPlayerDeck(card);
        this.tempCard.setOnMouseClicked((MouseEvent e) -> {
            e.getPickResult().getIntersectedNode().setId(e.getPickResult().getIntersectedNode().getParent().getId());
        });

        //logic manager here
    }


    public void addCardToFront(Card card, String nodeID, int playerID){
        LineType lineType = NodeIDConverter.toLineType(nodeID);
        PlayerType playerType = PlayerTypeConverter.toPlayerType(playerID);
        logicManager.addCardToFront(card.getCardLogic(), lineType, playerID);
        card.getCardLogic().action(this.logicManager, lineType, playerID);
        graphicManager.addCardToFront(card, lineType, logicManager.getFrontLinePower(lineType, playerID), playerType);
        if (card.getCardLogic().isUpdateGraphics()){
            for (Card cardGraphic: this.graphicManager.getCardList()){
                this.graphicManager.updateGraphics(cardGraphic);
            }
        }
        card.setId(nodeID);
        card.setHighlighted(false);
    }

    public void removeCardFromFront(Card card, String nodeID, int playerID){
        LineType lineType = NodeIDConverter.toLineType(nodeID);
        PlayerType playerType = PlayerTypeConverter.toPlayerType(playerID);
        logicManager.removeCardFromFront(card.getCardLogic(), lineType, playerID);
        graphicManager.removeCardFromFront(card, lineType, logicManager.getFrontLinePower(lineType, playerID), playerType);
    }
}
