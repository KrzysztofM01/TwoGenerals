package graphic.cards;

import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import logic.cards.CardLogic;
import variables.VariablesGraphics;

public class CardPreview extends StackPane {

    public CardPreview(CardLogic cardLogic) {
        ImageView imageView = new ImageView(cardLogic.getImage());
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(VariablesGraphics.cardHeight *2.5);
        this.setLayoutX(VariablesGraphics.battleFieldWidth/2-imageView.getFitHeight()/3);
        this.setLayoutY(VariablesGraphics.battleFieldHeight/8);
        this.setPadding(new Insets(VariablesGraphics.cardPadding, VariablesGraphics.cardPadding, VariablesGraphics.cardPadding, VariablesGraphics.cardPadding));
        this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        CardText cardTextPower = new CardText(cardLogic.getCurrentPower(), CardTextType.power);
        CardText cardTextCost = new CardText(cardLogic.getCost(), CardTextType.cost);

        cardTextPower.getText().setStyle("-fx-font: 30 regular;");
        cardTextCost.getText().setStyle("-fx-font: 30 regular;");

        cardTextPower.setMaxSize(VariablesGraphics.cardWidth/2, VariablesGraphics.cardHeight/8*2.5);
        cardTextCost.setMaxSize(VariablesGraphics.cardWidth/2, VariablesGraphics.cardHeight/8*2.5);

        cardTextPower.setTranslateY(-imageView.getFitHeight()/2.5);
        cardTextCost.setTranslateY(-imageView.getFitHeight()/2.5);

        cardTextPower.setTranslateX(-imageView.getFitHeight()/3.8);
        cardTextCost.setTranslateX(-imageView.getFitHeight()/7);

        Text text = new Text("Card name: " + cardLogic.getName() + "\nPower : " + cardLogic.getCurrentPower() + "\nCost: " + cardLogic.getCost() + "\nCard description:\n" + cardLogic.getDescription());
        text.setTextAlignment(TextAlignment.CENTER);
        text.setWrappingWidth(VariablesGraphics.cardWidth * 2.5);
        text.setTranslateY(VariablesGraphics.screenHeight/5);



        this.getChildren().addAll(imageView, cardTextPower, cardTextCost, text);
    }
}
