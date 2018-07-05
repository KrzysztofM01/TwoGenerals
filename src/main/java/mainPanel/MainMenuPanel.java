package mainPanel;

import database.User;
import game.logic.cards.CardLogic;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuPanel {

    public MainMenuPanel(Stage primaryStage, User user) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("FXML/mainMenu.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MainMenuController controller = fxmlLoader.getController();
        controller.setPrimaryStage(primaryStage);
        controller.setUser(user);
        Scene scene = new Scene(root, 450, 400);

        primaryStage.setFullScreen(false);
        primaryStage.close();
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
