package suggestCardsPanel;

import database.User;
import game.graphic.buttons.RectangleButton;
import game.graphic.panes.CardPreviewPane;
import game.variables.VGraphics;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import mainPanel.MainMenuPanel;

import java.io.IOException;

public class SuggestCardPanel{

    public SuggestCardPanel (Stage primaryStage, User user) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("FXML/suggestCard.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        CardPreviewPane cardPreviewPane = new CardPreviewPane();
        cardPreviewPane.setLayoutX(400);
        cardPreviewPane.setLayoutY(0);

        RectangleButton rectangleButton = new RectangleButton("Back to Menu");
        rectangleButton.setLayoutX(470);
        rectangleButton.setLayoutY(515);
        rectangleButton.setOnMouseClicked(e -> new MainMenuPanel(primaryStage, user));

        ImageView borderImage = new ImageView("cardImages/BorderBattleCard.png");
        borderImage.setViewOrder(-3);
        borderImage.setFitHeight(VGraphics.getInstance().getCardPreviewHeigth());
        borderImage.setFitWidth(VGraphics.getInstance().getCardPrevieWidth());
        borderImage.setTranslateY(-VGraphics.getInstance().getScreenHeight() * 0.09);
        cardPreviewPane.getChildren().add(borderImage);
        assert root != null;
        ((Pane) root).getChildren().addAll(cardPreviewPane, rectangleButton);
        SuggestCardController controller = fxmlLoader.getController();
        controller.setBorderImage(borderImage);
        controller.setCardPreviewPane(cardPreviewPane);

        primaryStage.close();
        primaryStage.setFullScreen(false);
        Scene scene = new Scene(root, 800, 650);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
