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
    private CardPreview tempCardPreview;

    private LogicManager logicManager;
    private GraphicManager graphicManager;


    public GameManager(Stage primaryStage, Player player0, Player player1) {

        // Launch the graphic and logic managers
        this.graphicManager = new GraphicManager(primaryStage);
        this.logicManager = new LogicManager(player0, player1);
        //

        // Handler must be in main manager
        EventHandler<MouseEvent> sendCardToFrontHandler = e -> {
            if (e.getPickResult().getIntersectedNode().getId() != null && !NodeIDConverter.isItBattleFieldID(e.getPickResult().getIntersectedNode().getId())) {
                if (GameManager.this.tempCard != null) {
                    if (GameManager.this.tempCard.isHighlighted()) {
                        GameManager.this.removeCardFromPlayerDeck(GameManager.this.tempCard, GameManager.this.tempCard.getTempCardPlayerID());
                        GameManager.this.addCardToFront(GameManager.this.tempCard, e.getPickResult().getIntersectedNode().getId(), GameManager.this.tempCard.getTempCardPlayerID());
                        //GameManager.this.tempCard = null;
                    }
                }
            }
        };

        EventHandler<ActionEvent> attackOnFrontHandler = e -> {
            LineType lineType = NodeIDConverter.toLineType(e.getSource().toString());
            int loserPlayerID = this.logicManager.attackFrontLine(lineType);
            PlayerType playerType = PlayerTypeConverter.toPlayerType(loserPlayerID);
            this.graphicManager.getBattleFrontTextBoxGUI(lineType, playerType).setHitPointsAmount(this.logicManager.getFrontLineHitPoints(lineType, loserPlayerID));
        };

        graphicManager.getLeftBattleFieldGUI().setOnMouseClicked(sendCardToFrontHandler);
        graphicManager.getCenterBattleFieldGUI().setOnMouseClicked(sendCardToFrontHandler);
        graphicManager.getRightBattleFieldGUI().setOnMouseClicked(sendCardToFrontHandler);

        graphicManager.getLeftAttackButton().setOnAction(attackOnFrontHandler);
        graphicManager.getCenterAttackButton().setOnAction(attackOnFrontHandler);
        graphicManager.getRightAttackButton().setOnAction(attackOnFrontHandler);

    }

    public void addCardToPlayerDeck(CardLogic cardLogic, int playerID){
        Card card = new Card(cardLogic);
        graphicManager.addCardToPlayerDeck(card, PlayerTypeConverter.toPlayerType(playerID));
        logicManager.getPlayer(playerID).getCardList().add(cardLogic);
        card.setOnMouseEntered((MouseEvent e) ->{
            CardPreview cardPreview = new CardPreview(cardLogic);
            this.tempCardPreview = cardPreview;
            graphicManager.getCardPreviewPane().getChildren().add(cardPreview);
        });
        card.setOnMouseExited((MouseEvent e) -> {
            graphicManager.getCardPreviewPane().getChildren().remove(tempCardPreview);
            this.tempCardPreview = null;
        });

        card.setOnMouseClicked((MouseEvent e) -> {
            if (this.tempCard != null && card.isHighlighted()){
                card.setHighlighted(false);
                card.setViewOrder(0);
                } else if (this.tempCard != null && !card.isHighlighted()){
                this.tempCard.setHighlighted(false);
                this.tempCard.setViewOrder(0);
                this.tempCard = card;
                this.tempCard.setTempCardPlayerID(playerID);
                card.setHighlighted(true);
                card.setViewOrder(-1);
                } else {
                card.setHighlighted(true);
                card.setViewOrder(-1);
                this.tempCard = card;
                this.tempCard.setTempCardPlayerID(playerID);
                }
        });
    }

    public void removeCardFromPlayerDeck(Card card, int playerID){
        graphicManager.removeCardFromPlayerDeck(card, PlayerTypeConverter.toPlayerType(playerID));
        logicManager.getPlayer(playerID).removeCard(card.getCardLogic());
        this.tempCard.setOnMouseClicked((MouseEvent e) -> {
            e.getPickResult().getIntersectedNode().setId(e.getPickResult().getIntersectedNode().getParent().getId());
        });


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
