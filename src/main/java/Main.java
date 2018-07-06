import database.DataBaseConnector;
import javafx.application.Application;
import javafx.stage.Stage;
import loginPanel.LoginPanel;

public class Main extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        new LoginPanel(primaryStage);
    }

    @Override
    public void stop() {
        DataBaseConnector.closeSessionFactory();
    }
}
