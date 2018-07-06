package loginPanel;

import database.DataBaseConnector;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginPanel {

    public LoginPanel(Stage primaryStage) {

        try {
            // Loads FXML file and sets the parameters
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("FXML/loginPanel.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 450, 400);
            primaryStage.setTitle("Two Generals");
            primaryStage.getIcons().add(new Image("images/2GIcon.png"));
            primaryStage.setScene(scene);
            primaryStage.show();

            // Sends primary stage to controller, needed to change scenes in controller
            LoginController controller = fxmlLoader.getController();
            controller.setPrimaryStage(primaryStage);

            // A void task so the application can run while database is loading
            Task task = new Task<Void>() {
                @Override
                public Void call() {
                    DataBaseConnector.startSessionFactory();
                    return null;
                }
            };
            task.setOnSucceeded(e2 -> {
                controller.turnOnButtons(true);
                controller.setSystemResponse("Successfully connected to database.");
            });
            new Thread(task).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
