package suggestCardsPanel;

import database.Entities.User;
import game.graphic.buttons.RectangleButton;
import game.graphic.panes.CardPreviewPane;
import game.variables.VGraphics;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import menuPanel.MenuPanel;

import java.io.IOException;

public class SuggestCardPanel {

    public SuggestCardPanel(Stage primaryStage, User user) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("FXML/suggestCardPanel.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 800, 650);

            CardPreviewPane cardPreviewPane = new CardPreviewPane();
            cardPreviewPane.setLayoutX(400);
            cardPreviewPane.setLayoutY(0);

            RectangleButton rectangleButton = new RectangleButton("Back to Menu");
            rectangleButton.setLayoutX(470);
            rectangleButton.setLayoutY(515);
            rectangleButton.setOnMouseClicked(e -> new MenuPanel(primaryStage, user));

            ImageView borderImage = new ImageView("cardImages/BorderBattleCard.png");
            borderImage.setViewOrder(-3);
            borderImage.setFitHeight(VGraphics.getInstance().getCardPreviewHeigth());
            borderImage.setFitWidth(VGraphics.getInstance().getCardPrevieWidth());
            borderImage.setTranslateY(-VGraphics.getInstance().getScreenHeight() * 0.09);

            cardPreviewPane.getChildren().add(borderImage);
            ((Pane) root).getChildren().addAll(cardPreviewPane, rectangleButton);

            SuggestCardController controller = fxmlLoader.getController();
            controller.setBorderImage(borderImage);
            controller.setCardPreviewPane(cardPreviewPane);

            primaryStage.close();
            primaryStage.setFullScreen(false);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
