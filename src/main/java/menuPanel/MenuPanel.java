package menuPanel;

import database.Entities.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuPanel {

    public MenuPanel(Stage primaryStage, User user) {

        try {
            // Loads FXML file and sets the parameters
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("FXML/menuPanel.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 450, 400);
            primaryStage.setFullScreen(false);
            primaryStage.close();
            primaryStage.setScene(scene);
            primaryStage.show();

            // Sends primary stage and user to controller
            MenuController controller = fxmlLoader.getController();
            controller.setPrimaryStage(primaryStage);
            controller.setUser(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
