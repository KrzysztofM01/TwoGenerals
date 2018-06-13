package game.graphic.panes;

import game.graphic.cards.cardPreview.CardPreview;
import javafx.scene.layout.StackPane;
import game.variables.VGraphics;

public class CardPreviewPane extends StackPane {

    private CardPreview tempCardPreview;

    public CardPreviewPane() {

        this.setId("cardPreview");
        this.setPrefSize(VGraphics.getInstance().getBattleFieldWidth(), VGraphics.getInstance().getBattleFieldHeight());
        this.setLayoutX(VGraphics.getInstance().getBattleFieldWidth() * 3 + VGraphics.getInstance().getBattleFieldBreakWidth() * 4);
        this.setLayoutY(VGraphics.getInstance().getBattleFieldPositionY());
    }

    public void setTempCardPreview(CardPreview tempCardPreview) {
        this.tempCardPreview = tempCardPreview;
    }

    public CardPreview getTempCardPreview() {
        return tempCardPreview;
    }
}
