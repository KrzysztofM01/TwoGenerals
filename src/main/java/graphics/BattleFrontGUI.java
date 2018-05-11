package graphics;

import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;
import variables.VariablesGraphics;

public class BattleFrontGUI {
    private FlowPane activeCards = new FlowPane();
    private boolean isPlayer;

    public BattleFrontGUI(boolean isPlayer) {
        this.isPlayer = isPlayer;
        //if it's player's or opponent's card
        Pos pos = Pos.TOP_CENTER;
        if (isPlayer){
            this.activeCards.setId("PlayerActiveCards");

        } else {
            this.activeCards.setId("OpponentActiveCards");
            pos = Pos.BOTTOM_CENTER;

        }

        this.activeCards.setHgap(-VariablesGraphics.cardWidth/3);
        this.activeCards.setAlignment(pos);
        this.activeCards.setPrefWidth(VariablesGraphics.battleFieldWidth);
        this.activeCards.setPrefHeight(VariablesGraphics.battleFieldHeight/2);
    }

    public FlowPane getActiveCards() {
        return activeCards;
    }

}
