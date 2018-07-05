package loginPanel;

import database.DataBaseConnector;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class LoginPanel extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("FXML/loginMenu.fxml"));
        Parent root = fxmlLoader.load();
        LoginController controller = fxmlLoader.getController();
        controller.setPrimaryStage(primaryStage);
        Scene scene = new Scene(root, 450, 400);
        primaryStage.setTitle("Two Generals");
        primaryStage.getIcons().add(new Image("images/2GIcon.png"));
        primaryStage.setScene(scene);
        primaryStage.show();

        Task task = new Task<Void>() {
            @Override
            public Void call() {
                DataBaseConnector.startSessionFactory();
                return null;
            }
        };
        task.setOnSucceeded(e2 -> controller.setSystemResponse("Successfully connected to database."));
        new Thread(task).start();
        
    }

    @Override
    public void stop() {
        DataBaseConnector.getSessionFactory().close();
    }
}
