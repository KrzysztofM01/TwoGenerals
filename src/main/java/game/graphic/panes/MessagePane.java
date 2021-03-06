package game.graphic.panes;

import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import game.variables.VGraphics;

public class MessagePane extends StackPane {

    private Text messageText = new Text("");

    public MessagePane() {

        this.setId("messagePane");
        this.setPrefSize(VGraphics.getInstance().getScreenWidth() * 0.42, VGraphics.getInstance().getScreenHeight() * 0.3);
        this.setLayoutX(VGraphics.getInstance().getScreenWidth() * 0.5 - this.getPrefWidth() / 2);
        this.setLayoutY(VGraphics.getInstance().getScreenHeight() * 0.5 - this.getPrefHeight() / 2);

        messageText.setId("messageText");
        this.getChildren().add(messageText);
        // Ensures that it's always on top of all graphics
        this.setViewOrder(-50);
    }

    public void setMessageText(String messageText) {
        this.messageText.setText(messageText);
    }

}
