package graphics;

import logic.cards.CardLogic;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import variables.VariablesGraphics;

public class CardPreview extends StackPane {

    public CardPreview(CardLogic card) {
        ImageView imageView = new ImageView(card.getImage());
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(VariablesGraphics.cardHeight *2.5);
        this.setLayoutX(VariablesGraphics.battleFieldWidth/2-imageView.getFitHeight()/3);
        this.setLayoutY(VariablesGraphics.battleFieldHeight/8);
        this.setPadding(new Insets(VariablesGraphics.cardPadding, VariablesGraphics.cardPadding, VariablesGraphics.cardPadding, VariablesGraphics.cardPadding));
        this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        PowerText powerText = new PowerText(card.getCurrentPower());
        powerText.getText().setStyle("-fx-font: 30 regular;");
        powerText.setMaxSize(VariablesGraphics.cardWidth/2, VariablesGraphics.cardHeight/8*2.5);
        powerText.setPrefSize(VariablesGraphics.cardWidth/2, VariablesGraphics.cardHeight/8*2.5);
        powerText.setTranslateY(-imageView.getFitHeight()/2.52);
        powerText.setTranslateX(-imageView.getFitHeight()/3.8);
        this.getChildren().addAll(imageView, powerText);
    }
}
