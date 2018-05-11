package graphic.battleFields;

import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;
import variables.VariablesGraphics;

public class BattleFrontGUI {
    private FlowPane cards = new FlowPane();

    public BattleFrontGUI(boolean isPlayer) {
        Pos pos = Pos.TOP_CENTER;
        if (isPlayer){
            this.cards.setId("PlayerCards");

        } else {
            this.cards.setId("OpponentCards");
            pos = Pos.BOTTOM_CENTER;

        }

        this.cards.setHgap(-VariablesGraphics.cardWidth/3);
        this.cards.setVgap(-VariablesGraphics.cardHeight/5);
        this.cards.setAlignment(pos);
        this.cards.setPrefWidth(VariablesGraphics.battleFieldWidth);
        this.cards.setPrefHeight(VariablesGraphics.battleFieldHeight/2);
    }

    public FlowPane getCards() {
        return cards;
    }

}
