package graphic.panes;

import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import variables.VariablesGraphics;

public class MessagePane extends StackPane {

    private Text messageText = new Text("");

    public MessagePane() {
        this.setPrefSize(VariablesGraphics.screenWidth*0.42, VariablesGraphics.screenHeight*0.3);
        this.setLayoutX(VariablesGraphics.screenWidth*0.5-this.getPrefWidth()/2);
        this.setLayoutY(VariablesGraphics.screenHeight*0.5-this.getPrefHeight()/2);
        this.setId("messagePane");
        this.messageText.setId("messageText");
        this.getChildren().add(messageText);
        this.setViewOrder(-50);
    }

    public void setMessageText (String messageText) {
        this.messageText.setText(messageText);
    }

}
